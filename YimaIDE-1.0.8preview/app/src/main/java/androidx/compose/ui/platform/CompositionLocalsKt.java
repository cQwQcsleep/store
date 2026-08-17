package androidx.compose.ui.platform;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.retain.LocalRetainedValuesStoreKt;
import androidx.compose.ui.autofill.Autofill;
import androidx.compose.ui.autofill.AutofillManager;
import androidx.compose.ui.autofill.AutofillTree;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.pointer.PointerIconService;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000ä\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a0\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020<2\u0011\u0010U\u001a\r\u0012\u0004\u0012\u00020Q0V¢\u0006\u0002\bWH\u0001¢\u0006\u0002\u0010X\u001a\u0010\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\\H\u0002\"\u0019\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"$\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\u0004\"\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\b\u001a\u0004\b\r\u0010\u0004\"\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0004\"\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\b\u001a\u0004\b\u0014\u0010\u0004\"\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0004\"\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0004\"\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0004\"\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0001¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0004\" \u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0001X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\b\u001a\u0004\b$\u0010\u0004\"\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u0001¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0004\"\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u0001¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0004\"\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u0001¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0004\"\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u0001¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0004\"$\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\b\u001a\u0004\b4\u0010\u0004\"\u0019\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001060\u0001¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0004\"\u0017\u00108\u001a\b\u0012\u0004\u0012\u0002090\u0001¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0004\"\u0017\u0010;\u001a\b\u0012\u0004\u0012\u00020<0\u0001¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0004\"\u0017\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u0001¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0004\"\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u0001¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\u0004\"\u001c\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E0\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0004\"\u001a\u0010G\u001a\b\u0012\u0004\u0012\u00020H0\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\u0004\"\u0017\u0010J\u001a\b\u0012\u0004\u0012\u00020H0K8F¢\u0006\u0006\u001a\u0004\bL\u0010M\"\u0017\u0010N\u001a\b\u0012\u0004\u0012\u00020H0\u0001¢\u0006\b\n\u0000\u001a\u0004\bO\u0010\u0004¨\u0006]"}, d2 = {"LocalAccessibilityManager", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/ui/platform/AccessibilityManager;", "getLocalAccessibilityManager", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalAutofill", "Landroidx/compose/ui/autofill/Autofill;", "getLocalAutofill$annotations", "()V", "getLocalAutofill", "LocalAutofillTree", "Landroidx/compose/ui/autofill/AutofillTree;", "getLocalAutofillTree$annotations", "getLocalAutofillTree", "LocalAutofillManager", "Landroidx/compose/ui/autofill/AutofillManager;", "getLocalAutofillManager", "LocalClipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "getLocalClipboardManager$annotations", "getLocalClipboardManager", "LocalClipboard", "Landroidx/compose/ui/platform/Clipboard;", "getLocalClipboard", "LocalGraphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "getLocalGraphicsContext", "LocalDensity", "Landroidx/compose/ui/unit/Density;", "getLocalDensity", "LocalFocusManager", "Landroidx/compose/ui/focus/FocusManager;", "getLocalFocusManager", "LocalFontLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "getLocalFontLoader$annotations", "getLocalFontLoader", "LocalFontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "getLocalFontFamilyResolver", "LocalHapticFeedback", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getLocalHapticFeedback", "LocalInputModeManager", "Landroidx/compose/ui/input/InputModeManager;", "getLocalInputModeManager", "LocalLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLocalLayoutDirection", "LocalTextInputService", "Landroidx/compose/ui/text/input/TextInputService;", "getLocalTextInputService$annotations", "getLocalTextInputService", "LocalSoftwareKeyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "getLocalSoftwareKeyboardController", "LocalTextToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getLocalTextToolbar", "LocalUriHandler", "Landroidx/compose/ui/platform/UriHandler;", "getLocalUriHandler", "LocalViewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getLocalViewConfiguration", "LocalWindowInfo", "Landroidx/compose/ui/platform/WindowInfo;", "getLocalWindowInfo", "LocalPointerIconService", "Landroidx/compose/ui/input/pointer/PointerIconService;", "getLocalPointerIconService", "LocalProvidableScrollCaptureInProgress", "", "getLocalProvidableScrollCaptureInProgress", "LocalScrollCaptureInProgress", "Landroidx/compose/runtime/CompositionLocal;", "getLocalScrollCaptureInProgress", "()Landroidx/compose/runtime/CompositionLocal;", "LocalCursorBlinkEnabled", "getLocalCursorBlinkEnabled", "ProvideCommonCompositionLocals", "", "owner", "Landroidx/compose/ui/node/Owner;", "uriHandler", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/ui/node/Owner;Landroidx/compose/ui/platform/UriHandler;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "noLocalProvidedFor", "", "name", "", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CompositionLocalsKt {
    private static final ProvidableCompositionLocal<AccessibilityManager> LocalAccessibilityManager = CompositionLocalKt.staticCompositionLocalOf(new Function0<AccessibilityManager>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalAccessibilityManager$1
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final AccessibilityManager m5115invoke() {
            return null;
        }
    });
    private static final ProvidableCompositionLocal<Autofill> LocalAutofill = CompositionLocalKt.staticCompositionLocalOf(new Function0<Autofill>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalAutofill$1
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Autofill m5116invoke() {
            return null;
        }
    });
    private static final ProvidableCompositionLocal<AutofillTree> LocalAutofillTree = CompositionLocalKt.staticCompositionLocalOf(new Function0<AutofillTree>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalAutofillTree$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final AutofillTree m5118invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalAutofillTree");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<AutofillManager> LocalAutofillManager = CompositionLocalKt.staticCompositionLocalOf(new Function0<AutofillManager>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalAutofillManager$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final AutofillManager m5117invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalAutofillManager");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<ClipboardManager> LocalClipboardManager = CompositionLocalKt.staticCompositionLocalOf(new Function0<ClipboardManager>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalClipboardManager$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final ClipboardManager m5120invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalClipboardManager");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<Clipboard> LocalClipboard = CompositionLocalKt.staticCompositionLocalOf(new Function0<Clipboard>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalClipboard$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Clipboard m5119invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalClipboard");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<GraphicsContext> LocalGraphicsContext = CompositionLocalKt.staticCompositionLocalOf(new Function0<GraphicsContext>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalGraphicsContext$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final GraphicsContext m5126invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalGraphicsContext");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<Density> LocalDensity = CompositionLocalKt.staticCompositionLocalOf(new Function0<Density>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalDensity$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Density m5122invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalDensity");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<FocusManager> LocalFocusManager = CompositionLocalKt.staticCompositionLocalOf(new Function0<FocusManager>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalFocusManager$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final FocusManager m5123invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalFocusManager");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<Font.ResourceLoader> LocalFontLoader = CompositionLocalKt.staticCompositionLocalOf(new Function0<Font.ResourceLoader>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalFontLoader$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Font.ResourceLoader m5125invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalFontLoader");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<FontFamily.Resolver> LocalFontFamilyResolver = CompositionLocalKt.staticCompositionLocalOf(new Function0<FontFamily.Resolver>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalFontFamilyResolver$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final FontFamily.Resolver m5124invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalFontFamilyResolver");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<HapticFeedback> LocalHapticFeedback = CompositionLocalKt.staticCompositionLocalOf(new Function0<HapticFeedback>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalHapticFeedback$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final HapticFeedback m5127invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalHapticFeedback");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<InputModeManager> LocalInputModeManager = CompositionLocalKt.staticCompositionLocalOf(new Function0<InputModeManager>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalInputModeManager$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final InputModeManager m5128invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalInputManager");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<LayoutDirection> LocalLayoutDirection = CompositionLocalKt.staticCompositionLocalOf(new Function0<LayoutDirection>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalLayoutDirection$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final LayoutDirection m5129invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalLayoutDirection");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<TextInputService> LocalTextInputService = CompositionLocalKt.staticCompositionLocalOf(new Function0<TextInputService>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalTextInputService$1
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final TextInputService m5133invoke() {
            return null;
        }
    });
    private static final ProvidableCompositionLocal<SoftwareKeyboardController> LocalSoftwareKeyboardController = CompositionLocalKt.staticCompositionLocalOf(new Function0<SoftwareKeyboardController>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalSoftwareKeyboardController$1
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final SoftwareKeyboardController m5132invoke() {
            return null;
        }
    });
    private static final ProvidableCompositionLocal<TextToolbar> LocalTextToolbar = CompositionLocalKt.staticCompositionLocalOf(new Function0<TextToolbar>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalTextToolbar$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final TextToolbar m5134invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalTextToolbar");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<UriHandler> LocalUriHandler = CompositionLocalKt.staticCompositionLocalOf(new Function0<UriHandler>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalUriHandler$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final UriHandler m5135invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalUriHandler");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<ViewConfiguration> LocalViewConfiguration = CompositionLocalKt.staticCompositionLocalOf(new Function0<ViewConfiguration>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalViewConfiguration$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final ViewConfiguration m5136invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalViewConfiguration");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<WindowInfo> LocalWindowInfo = CompositionLocalKt.staticCompositionLocalOf(new Function0<WindowInfo>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalWindowInfo$1
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final WindowInfo m5137invoke() throws KotlinNothingValueException {
            CompositionLocalsKt.noLocalProvidedFor("LocalWindowInfo");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal<PointerIconService> LocalPointerIconService = CompositionLocalKt.staticCompositionLocalOf(new Function0<PointerIconService>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalPointerIconService$1
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final PointerIconService m5130invoke() {
            return null;
        }
    });
    private static final ProvidableCompositionLocal<Boolean> LocalProvidableScrollCaptureInProgress = CompositionLocalKt.compositionLocalOf$default(null, new Function0<Boolean>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalProvidableScrollCaptureInProgress$1
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Boolean m5131invoke() {
            return Boolean.FALSE;
        }
    }, 1, null);
    private static final ProvidableCompositionLocal<Boolean> LocalCursorBlinkEnabled = CompositionLocalKt.staticCompositionLocalOf(new Function0<Boolean>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalCursorBlinkEnabled$1
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Boolean m5121invoke() {
            return Boolean.TRUE;
        }
    });

    public static final void ProvideCommonCompositionLocals(final Owner owner, final UriHandler uriHandler, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1925803616);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(owner) : composerStartRestartGroup.changedInstance(owner) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composerStartRestartGroup.changed(uriHandler) : composerStartRestartGroup.changedInstance(uriHandler) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1925803616, i2, -1, "androidx.compose.ui.platform.ProvideCommonCompositionLocals (CompositionLocals.kt:215)");
            }
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{LocalAccessibilityManager.provides(owner.getAccessibilityManager()), LocalAutofill.provides(owner.getAutofill()), LocalAutofillManager.provides(owner.getAutofillManager()), LocalAutofillTree.provides(owner.getAutofillTree()), LocalClipboardManager.provides(owner.getClipboardManager()), LocalClipboard.provides(owner.getClipboard()), LocalDensity.provides(owner.getDensity()), LocalFocusManager.provides(owner.getFocusOwner()), LocalFontLoader.providesDefault(owner.getFontLoader()), LocalFontFamilyResolver.providesDefault(owner.getFontFamilyResolver()), LocalHapticFeedback.provides(owner.getHapticFeedBack()), LocalInputModeManager.provides(owner.getInputModeManager()), LocalLayoutDirection.provides(owner.getLayoutDirection()), LocalTextInputService.provides(owner.getTextInputService()), LocalSoftwareKeyboardController.provides(owner.getSoftwareKeyboardController()), LocalTextToolbar.provides(owner.getTextToolbar()), LocalUriHandler.provides(uriHandler), LocalViewConfiguration.provides(owner.getViewConfiguration()), LocalWindowInfo.provides(owner.getWindowInfo()), LocalPointerIconService.provides(owner.getPointerIconService()), LocalGraphicsContext.provides(owner.getGraphicsContext()), LocalRetainedValuesStoreKt.getLocalRetainedValuesStore().provides(owner.getRetainedValuesStore())}, function2, composerStartRestartGroup, ((i2 >> 3) & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.platform.CompositionLocalsKt.ProvideCommonCompositionLocals.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                public final void invoke(Composer composer2, int i3) {
                    CompositionLocalsKt.ProvideCommonCompositionLocals(owner, uriHandler, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            });
        }
    }

    public static final ProvidableCompositionLocal<AccessibilityManager> getLocalAccessibilityManager() {
        return LocalAccessibilityManager;
    }

    public static final ProvidableCompositionLocal<Autofill> getLocalAutofill() {
        return LocalAutofill;
    }

    @Deprecated(message = "\n        Use the new semantics-based Autofill APIs androidx.compose.ui.autofill.ContentType and\n        androidx.compose.ui.autofill.ContentDataType instead.\n        ")
    public static /* synthetic */ void getLocalAutofill$annotations() {
    }

    public static final ProvidableCompositionLocal<AutofillManager> getLocalAutofillManager() {
        return LocalAutofillManager;
    }

    public static final ProvidableCompositionLocal<AutofillTree> getLocalAutofillTree() {
        return LocalAutofillTree;
    }

    @Deprecated(message = "\n        Use the new semantics-based Autofill APIs androidx.compose.ui.autofill.ContentType and\n        androidx.compose.ui.autofill.ContentDataType instead.\n        ")
    public static /* synthetic */ void getLocalAutofillTree$annotations() {
    }

    public static final ProvidableCompositionLocal<Clipboard> getLocalClipboard() {
        return LocalClipboard;
    }

    public static final ProvidableCompositionLocal<ClipboardManager> getLocalClipboardManager() {
        return LocalClipboardManager;
    }

    @Deprecated(message = "Use LocalClipboard instead which supports suspend functions", replaceWith = @ReplaceWith(expression = "LocalClipboard", imports = {"androidx.compose.ui.platform.LocalClipboard"}))
    public static /* synthetic */ void getLocalClipboardManager$annotations() {
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalCursorBlinkEnabled() {
        return LocalCursorBlinkEnabled;
    }

    public static final ProvidableCompositionLocal<Density> getLocalDensity() {
        return LocalDensity;
    }

    public static final ProvidableCompositionLocal<FocusManager> getLocalFocusManager() {
        return LocalFocusManager;
    }

    public static final ProvidableCompositionLocal<FontFamily.Resolver> getLocalFontFamilyResolver() {
        return LocalFontFamilyResolver;
    }

    public static final ProvidableCompositionLocal<Font.ResourceLoader> getLocalFontLoader() {
        return LocalFontLoader;
    }

    @Deprecated(message = "LocalFontLoader is replaced with LocalFontFamilyResolver", replaceWith = @ReplaceWith(expression = "LocalFontFamilyResolver", imports = {}))
    public static /* synthetic */ void getLocalFontLoader$annotations() {
    }

    public static final ProvidableCompositionLocal<GraphicsContext> getLocalGraphicsContext() {
        return LocalGraphicsContext;
    }

    public static final ProvidableCompositionLocal<HapticFeedback> getLocalHapticFeedback() {
        return LocalHapticFeedback;
    }

    public static final ProvidableCompositionLocal<InputModeManager> getLocalInputModeManager() {
        return LocalInputModeManager;
    }

    public static final ProvidableCompositionLocal<LayoutDirection> getLocalLayoutDirection() {
        return LocalLayoutDirection;
    }

    public static final ProvidableCompositionLocal<PointerIconService> getLocalPointerIconService() {
        return LocalPointerIconService;
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalProvidableScrollCaptureInProgress() {
        return LocalProvidableScrollCaptureInProgress;
    }

    public static final CompositionLocal<Boolean> getLocalScrollCaptureInProgress() {
        return LocalProvidableScrollCaptureInProgress;
    }

    public static final ProvidableCompositionLocal<SoftwareKeyboardController> getLocalSoftwareKeyboardController() {
        return LocalSoftwareKeyboardController;
    }

    public static final ProvidableCompositionLocal<TextInputService> getLocalTextInputService() {
        return LocalTextInputService;
    }

    @Deprecated(message = "Use PlatformTextInputModifierNode instead.")
    public static /* synthetic */ void getLocalTextInputService$annotations() {
    }

    public static final ProvidableCompositionLocal<TextToolbar> getLocalTextToolbar() {
        return LocalTextToolbar;
    }

    public static final ProvidableCompositionLocal<UriHandler> getLocalUriHandler() {
        return LocalUriHandler;
    }

    public static final ProvidableCompositionLocal<ViewConfiguration> getLocalViewConfiguration() {
        return LocalViewConfiguration;
    }

    public static final ProvidableCompositionLocal<WindowInfo> getLocalWindowInfo() {
        return LocalWindowInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void noLocalProvidedFor(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }
}
