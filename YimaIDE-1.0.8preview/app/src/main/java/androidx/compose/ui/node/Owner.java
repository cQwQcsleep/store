package androidx.compose.ui.node;

import android.view.View;
import androidx.collection.IntObjectMap;
import androidx.compose.runtime.retain.RetainedValuesStore;
import androidx.compose.ui.autofill.Autofill;
import androidx.compose.ui.autofill.AutofillManager;
import androidx.compose.ui.autofill.AutofillTree;
import androidx.compose.ui.draganddrop.DragAndDropManager;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.pointer.PointerIconService;
import androidx.compose.ui.input.pointer.PositionCalculator;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.platform.AccessibilityManager;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.PlatformTextInputSessionScope;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.spatial.RectManager;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u009c\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\b`\u0018\u0000 ß\u00012\u00020\u0001:\u0004ß\u0001à\u0001J.\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020\u00032\b\b\u0002\u0010u\u001a\u00020m2\b\b\u0002\u0010v\u001a\u00020m2\b\b\u0002\u0010w\u001a\u00020mH&J$\u0010x\u001a\u00020s2\u0006\u0010t\u001a\u00020\u00032\b\b\u0002\u0010u\u001a\u00020m2\b\b\u0002\u0010v\u001a\u00020mH&J\u0010\u0010y\u001a\u00020s2\u0006\u0010t\u001a\u00020\u0003H&J\u0010\u0010z\u001a\u00020s2\u0006\u0010{\u001a\u00020\u0003H&J\u0010\u0010|\u001a\u00020s2\u0006\u0010{\u001a\u00020\u0003H&J\u0010\u0010}\u001a\u00020s2\u0006\u0010{\u001a\u00020\u0003H&J\u001a\u0010~\u001a\u00020\u007f2\u0007\u0010\u0080\u0001\u001a\u00020\u007fH&¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u001b\u0010\u0083\u0001\u001a\u00020\u007f2\u0007\u0010\u0084\u0001\u001a\u00020\u007fH&¢\u0006\u0006\b\u0085\u0001\u0010\u0082\u0001J\u0011\u0010\u0086\u0001\u001a\u00020s2\u0006\u0010{\u001a\u00020\u0003H&J\u0014\u0010\u0087\u0001\u001a\u00020s2\t\b\u0002\u0010\u0088\u0001\u001a\u00020mH&J$\u0010\u0087\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020\u00032\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001H&¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001J\u001b\u0010\u008d\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020\u00032\b\b\u0002\u0010u\u001a\u00020mH&Jl\u0010\u008e\u0001\u001a\u00030\u008f\u00012B\u0010\u0090\u0001\u001a=\u0012\u0017\u0012\u00150\u0092\u0001¢\u0006\u000f\b\u0093\u0001\u0012\n\b\u0094\u0001\u0012\u0005\b\b(\u0095\u0001\u0012\u0019\u0012\u0017\u0018\u00010\u0096\u0001¢\u0006\u000f\b\u0093\u0001\u0012\n\b\u0094\u0001\u0012\u0005\b\b(\u0097\u0001\u0012\u0004\u0012\u00020s0\u0091\u00012\u000e\u0010\u0098\u0001\u001a\t\u0012\u0004\u0012\u00020s0\u0099\u00012\f\b\u0002\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u0096\u0001H&J\t\u0010\u009b\u0001\u001a\u00020sH&J\u0011\u0010\u009c\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020\u0003H&J\u0011\u0010\u009d\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020\u0003H&J\u001b\u0010\u009e\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020\u00032\b\u0010\u009f\u0001\u001a\u00030 \u0001H\u0016J\u001b\u0010¡\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020\u00032\b\u0010\u009f\u0001\u001a\u00030 \u0001H\u0016J\u001e\u0010¢\u0001\u001a\u00020s2\r\u0010£\u0001\u001a\b0¤\u0001j\u0003`¥\u0001H'¢\u0006\u0003\u0010¦\u0001J\u0019\u0010¿\u0001\u001a\u00020s2\u000e\u0010À\u0001\u001a\t\u0012\u0004\u0012\u00020s0\u0099\u0001H&J\t\u0010Á\u0001\u001a\u00020sH&J\u0013\u0010Â\u0001\u001a\u00020s2\b\u0010À\u0001\u001a\u00030Ã\u0001H&JA\u0010È\u0001\u001a\u00030É\u00012.\u0010Ê\u0001\u001a)\b\u0001\u0012\u0005\u0012\u00030Ë\u0001\u0012\f\u0012\n\u0012\u0005\u0012\u00030É\u00010Ì\u0001\u0012\u0007\u0012\u0005\u0018\u00010Í\u00010\u0091\u0001¢\u0006\u0003\bÎ\u0001H¦@¢\u0006\u0003\u0010Ï\u0001J\t\u0010Ð\u0001\u001a\u00020sH\u0016J\t\u0010Ñ\u0001\u001a\u00020sH\u0016J\t\u0010Ò\u0001\u001a\u00020sH\u0016J\t\u0010Ó\u0001\u001a\u00020sH\u0016J\u0013\u0010Ø\u0001\u001a\u00020s2\b\u0010Ù\u0001\u001a\u00030Ú\u0001H\u0016J\u001b\u0010Û\u0001\u001a\u00020s2\u0007\u0010Ü\u0001\u001a\u00020\u007fH\u0016¢\u0006\u0006\bÝ\u0001\u0010Þ\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0012\u0010\"\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0012\u0010&\u001a\u00020'X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0012\u0010*\u001a\u00020+X¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0012\u0010.\u001a\u00020/X¦\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0014\u00102\u001a\u0004\u0018\u000103X¦\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R\u0014\u00106\u001a\u0004\u0018\u000107X¦\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u0012\u0010:\u001a\u00020;X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u0012\u0010>\u001a\u00020?X¦\u0004¢\u0006\u0006\u001a\u0004\b@\u0010AR\u0012\u0010B\u001a\u00020CX¦\u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0012\u0010F\u001a\u00020GX¦\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0012\u0010J\u001a\u00020KX¦\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0012\u0010N\u001a\u00020OX¦\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0012\u0010R\u001a\u00020SX¦\u0004¢\u0006\u0006\u001a\u0004\bT\u0010UR\u0012\u0010V\u001a\u00020WX¦\u0004¢\u0006\u0006\u001a\u0004\bX\u0010YR\u0012\u0010Z\u001a\u00020[X¦\u0004¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u001a\u0010^\u001a\u00020_8&X§\u0004¢\u0006\f\u0012\u0004\b`\u0010a\u001a\u0004\bb\u0010cR\u0012\u0010d\u001a\u00020eX¦\u0004¢\u0006\u0006\u001a\u0004\bf\u0010gR\u0012\u0010h\u001a\u00020iX¦\u0004¢\u0006\u0006\u001a\u0004\bj\u0010kR\u001a\u0010l\u001a\u00020m@'X¦\u000e¢\u0006\f\u001a\u0004\bn\u0010o\"\u0004\bp\u0010qR\u0016\u0010§\u0001\u001a\u00030¨\u0001X¦\u0004¢\u0006\b\u001a\u0006\b©\u0001\u0010ª\u0001R\u0016\u0010«\u0001\u001a\u00030¬\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u00ad\u0001\u0010®\u0001R\u0016\u0010¯\u0001\u001a\u00030°\u0001X¦\u0004¢\u0006\b\u001a\u0006\b±\u0001\u0010²\u0001R\u0016\u0010³\u0001\u001a\u00030´\u0001X¦\u0004¢\u0006\b\u001a\u0006\bµ\u0001\u0010¶\u0001R\u0016\u0010·\u0001\u001a\u00030¸\u0001X¦\u0004¢\u0006\b\u001a\u0006\b¹\u0001\u0010º\u0001R\u0018\u0010»\u0001\u001a\u00030¼\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b½\u0001\u0010¾\u0001R\u0016\u0010Ä\u0001\u001a\u00030Å\u0001X¦\u0004¢\u0006\b\u001a\u0006\bÆ\u0001\u0010Ç\u0001R\u001a\u0010Ô\u0001\u001a\u0005\u0018\u00010Õ\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bÖ\u0001\u0010×\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006á\u0001À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/Owner;", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "root", "Landroidx/compose/ui/node/LayoutNode;", "getRoot", "()Landroidx/compose/ui/node/LayoutNode;", "layoutNodes", "Landroidx/collection/IntObjectMap;", "getLayoutNodes", "()Landroidx/collection/IntObjectMap;", "sharedDrawScope", "Landroidx/compose/ui/node/LayoutNodeDrawScope;", "getSharedDrawScope", "()Landroidx/compose/ui/node/LayoutNodeDrawScope;", "rootForTest", "Landroidx/compose/ui/node/RootForTest;", "getRootForTest", "()Landroidx/compose/ui/node/RootForTest;", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "inputModeManager", "Landroidx/compose/ui/input/InputModeManager;", "getInputModeManager", "()Landroidx/compose/ui/input/InputModeManager;", "clipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "getClipboardManager", "()Landroidx/compose/ui/platform/ClipboardManager;", "clipboard", "Landroidx/compose/ui/platform/Clipboard;", "getClipboard", "()Landroidx/compose/ui/platform/Clipboard;", "accessibilityManager", "Landroidx/compose/ui/platform/AccessibilityManager;", "getAccessibilityManager", "()Landroidx/compose/ui/platform/AccessibilityManager;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "getGraphicsContext", "()Landroidx/compose/ui/graphics/GraphicsContext;", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "autofillTree", "Landroidx/compose/ui/autofill/AutofillTree;", "getAutofillTree", "()Landroidx/compose/ui/autofill/AutofillTree;", "autofill", "Landroidx/compose/ui/autofill/Autofill;", "getAutofill", "()Landroidx/compose/ui/autofill/Autofill;", "autofillManager", "Landroidx/compose/ui/autofill/AutofillManager;", "getAutofillManager", "()Landroidx/compose/ui/autofill/AutofillManager;", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "getTextInputService", "()Landroidx/compose/ui/text/input/TextInputService;", "softwareKeyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "getSoftwareKeyboardController", "()Landroidx/compose/ui/platform/SoftwareKeyboardController;", "pointerIconService", "Landroidx/compose/ui/input/pointer/PointerIconService;", "getPointerIconService", "()Landroidx/compose/ui/input/pointer/PointerIconService;", "semanticsOwner", "Landroidx/compose/ui/semantics/SemanticsOwner;", "getSemanticsOwner", "()Landroidx/compose/ui/semantics/SemanticsOwner;", "focusOwner", "Landroidx/compose/ui/focus/FocusOwner;", "getFocusOwner", "()Landroidx/compose/ui/focus/FocusOwner;", "windowInfo", "Landroidx/compose/ui/platform/WindowInfo;", "getWindowInfo", "()Landroidx/compose/ui/platform/WindowInfo;", "retainedValuesStore", "Landroidx/compose/runtime/retain/RetainedValuesStore;", "getRetainedValuesStore", "()Landroidx/compose/runtime/retain/RetainedValuesStore;", "rectManager", "Landroidx/compose/ui/spatial/RectManager;", "getRectManager", "()Landroidx/compose/ui/spatial/RectManager;", "fontLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "getFontLoader$annotations", "()V", "getFontLoader", "()Landroidx/compose/ui/text/font/Font$ResourceLoader;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "getFontFamilyResolver", "()Landroidx/compose/ui/text/font/FontFamily$Resolver;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "showLayoutBounds", "", "getShowLayoutBounds", "()Z", "setShowLayoutBounds", "(Z)V", "onRequestMeasure", "", "layoutNode", "affectsLookahead", "forceRequest", "scheduleMeasureAndLayout", "onRequestRelayout", "requestOnPositionedCallback", "onPreAttach", "node", "onPostAttach", "onDetach", "calculatePositionInWindow", "Landroidx/compose/ui/geometry/Offset;", "localPosition", "calculatePositionInWindow-MK-Hz9U", "(J)J", "calculateLocalPosition", "positionInWindow", "calculateLocalPosition-MK-Hz9U", "requestAutofill", "measureAndLayout", "sendPointerUpdate", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measureAndLayout-0kLqBqw", "(Landroidx/compose/ui/node/LayoutNode;J)V", "forceMeasureTheSubtree", "createLayer", "Landroidx/compose/ui/node/OwnedLayer;", "drawBlock", "Lkotlin/Function2;", "Landroidx/compose/ui/graphics/Canvas;", "Lkotlin/ParameterName;", "name", "canvas", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "parentLayer", "invalidateParentLayer", "Lkotlin/Function0;", "explicitLayer", "onSemanticsChange", "onLayoutChange", "onLayoutNodeDeactivated", "onPreLayoutNodeReused", "oldSemanticsId", "", "onPostLayoutNodeReused", "onInteropViewLayoutChange", "view", "Landroid/view/View;", "Landroidx/compose/ui/viewinterop/InteropView;", "(Landroid/view/View;)V", "measureIteration", "", "getMeasureIteration", "()J", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "snapshotObserver", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "modifierLocalManager", "Landroidx/compose/ui/modifier/ModifierLocalManager;", "getModifierLocalManager", "()Landroidx/compose/ui/modifier/ModifierLocalManager;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "placementScope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "getPlacementScope", "()Landroidx/compose/ui/layout/Placeable$PlacementScope;", "registerOnEndApplyChangesListener", "listener", "onEndApplyChanges", "registerOnLayoutCompletedListener", "Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "dragAndDropManager", "Landroidx/compose/ui/draganddrop/DragAndDropManager;", "getDragAndDropManager", "()Landroidx/compose/ui/draganddrop/DragAndDropManager;", "textInputSession", "", "session", "Landroidx/compose/ui/platform/PlatformTextInputSessionScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementSensitiveComponentCount", "decrementSensitiveComponentCount", "incrementKeepScreenOnCount", "decrementKeepScreenOnCount", "outOfFrameExecutor", "Landroidx/compose/ui/node/OutOfFrameExecutor;", "getOutOfFrameExecutor", "()Landroidx/compose/ui/node/OutOfFrameExecutor;", "voteFrameRate", "frameRate", "", "dispatchOnScrollChanged", "delta", "dispatchOnScrollChanged-k-4lQ0M", "(J)V", "Companion", "OnLayoutCompletedListener", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface Owner extends PositionCalculator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/compose/ui/node/Owner$Companion;", "", "<init>", "()V", "enableExtraAssertions", "", "getEnableExtraAssertions", "()Z", "setEnableExtraAssertions", "(Z)V", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static boolean enableExtraAssertions;

        private Companion() {
        }

        public final boolean getEnableExtraAssertions() {
            return enableExtraAssertions;
        }

        public final void setEnableExtraAssertions(boolean z) {
            enableExtraAssertions = z;
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "", "onLayoutComplete", "", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public interface OnLayoutCompletedListener {
        void onLayoutComplete();
    }

    static /* synthetic */ OwnedLayer createLayer$default(Owner owner, Function2 function2, Function0 function0, GraphicsLayer graphicsLayer, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: createLayer");
            return null;
        }
        if ((i & 4) != 0) {
            graphicsLayer = null;
        }
        return owner.createLayer(function2, function0, graphicsLayer);
    }

    static /* synthetic */ void forceMeasureTheSubtree$default(Owner owner, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: forceMeasureTheSubtree");
            return;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        owner.forceMeasureTheSubtree(layoutNode, z);
    }

    @Deprecated(message = "fontLoader is deprecated, use fontFamilyResolver", replaceWith = @ReplaceWith(expression = "fontFamilyResolver", imports = {}))
    static /* synthetic */ void getFontLoader$annotations() {
    }

    static /* synthetic */ void measureAndLayout$default(Owner owner, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: measureAndLayout");
            return;
        }
        if ((i & 1) != 0) {
            z = true;
        }
        owner.measureAndLayout(z);
    }

    static /* synthetic */ void onRequestMeasure$default(Owner owner, LayoutNode layoutNode, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: onRequestMeasure");
            return;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        if ((i & 8) != 0) {
            z3 = true;
        }
        owner.onRequestMeasure(layoutNode, z, z2, z3);
    }

    static /* synthetic */ void onRequestRelayout$default(Owner owner, LayoutNode layoutNode, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: onRequestRelayout");
            return;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        owner.onRequestRelayout(layoutNode, z, z2);
    }

    /* JADX INFO: renamed from: calculateLocalPosition-MK-Hz9U, reason: not valid java name */
    long mo5013calculateLocalPositionMKHz9U(long positionInWindow);

    /* JADX INFO: renamed from: calculatePositionInWindow-MK-Hz9U, reason: not valid java name */
    long mo5014calculatePositionInWindowMKHz9U(long localPosition);

    OwnedLayer createLayer(Function2<? super Canvas, ? super GraphicsLayer, Unit> drawBlock, Function0<Unit> invalidateParentLayer, GraphicsLayer explicitLayer);

    default void decrementKeepScreenOnCount() {
    }

    default void decrementSensitiveComponentCount() {
    }

    /* JADX INFO: renamed from: dispatchOnScrollChanged-k-4lQ0M, reason: not valid java name */
    default void mo5015dispatchOnScrollChangedk4lQ0M(long delta) {
    }

    void forceMeasureTheSubtree(LayoutNode layoutNode, boolean affectsLookahead);

    AccessibilityManager getAccessibilityManager();

    Autofill getAutofill();

    AutofillManager getAutofillManager();

    AutofillTree getAutofillTree();

    Clipboard getClipboard();

    ClipboardManager getClipboardManager();

    CoroutineContext getCoroutineContext();

    Density getDensity();

    DragAndDropManager getDragAndDropManager();

    FocusOwner getFocusOwner();

    FontFamily.Resolver getFontFamilyResolver();

    Font.ResourceLoader getFontLoader();

    GraphicsContext getGraphicsContext();

    HapticFeedback getHapticFeedBack();

    InputModeManager getInputModeManager();

    LayoutDirection getLayoutDirection();

    IntObjectMap<LayoutNode> getLayoutNodes();

    long getMeasureIteration();

    ModifierLocalManager getModifierLocalManager();

    default OutOfFrameExecutor getOutOfFrameExecutor() {
        return null;
    }

    default Placeable.PlacementScope getPlacementScope() {
        return PlaceableKt.PlacementScope(this);
    }

    PointerIconService getPointerIconService();

    RectManager getRectManager();

    RetainedValuesStore getRetainedValuesStore();

    LayoutNode getRoot();

    RootForTest getRootForTest();

    SemanticsOwner getSemanticsOwner();

    LayoutNodeDrawScope getSharedDrawScope();

    boolean getShowLayoutBounds();

    OwnerSnapshotObserver getSnapshotObserver();

    SoftwareKeyboardController getSoftwareKeyboardController();

    TextInputService getTextInputService();

    TextToolbar getTextToolbar();

    ViewConfiguration getViewConfiguration();

    WindowInfo getWindowInfo();

    default void incrementKeepScreenOnCount() {
    }

    default void incrementSensitiveComponentCount() {
    }

    void measureAndLayout(boolean sendPointerUpdate);

    /* JADX INFO: renamed from: measureAndLayout-0kLqBqw, reason: not valid java name */
    void mo5016measureAndLayout0kLqBqw(LayoutNode layoutNode, long constraints);

    void onDetach(LayoutNode node);

    void onEndApplyChanges();

    void onInteropViewLayoutChange(View view);

    void onLayoutChange(LayoutNode layoutNode);

    void onLayoutNodeDeactivated(LayoutNode layoutNode);

    void onPostAttach(LayoutNode node);

    default void onPostLayoutNodeReused(LayoutNode layoutNode, int oldSemanticsId) {
    }

    void onPreAttach(LayoutNode node);

    default void onPreLayoutNodeReused(LayoutNode layoutNode, int oldSemanticsId) {
    }

    void onRequestMeasure(LayoutNode layoutNode, boolean affectsLookahead, boolean forceRequest, boolean scheduleMeasureAndLayout);

    void onRequestRelayout(LayoutNode layoutNode, boolean affectsLookahead, boolean forceRequest);

    void onSemanticsChange();

    void registerOnEndApplyChangesListener(Function0<Unit> listener);

    void registerOnLayoutCompletedListener(OnLayoutCompletedListener listener);

    void requestAutofill(LayoutNode node);

    void requestOnPositionedCallback(LayoutNode layoutNode);

    void setShowLayoutBounds(boolean z);

    Object textInputSession(Function2<? super PlatformTextInputSessionScope, ? super Continuation<?>, ? extends Object> function2, Continuation<?> continuation);

    default void voteFrameRate(float frameRate) {
    }
}
