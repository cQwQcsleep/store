package androidx.compose.material3;

import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.foundation.lazy.grid.GridCells;
import androidx.compose.foundation.lazy.grid.LazyGridDslKt;
import androidx.compose.foundation.lazy.grid.LazyGridScope;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.foundation.lazy.grid.LazyGridStateKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.DatePickerKt;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.CalendarModel_androidKt;
import androidx.compose.material3.internal.CalendarMonth;
import androidx.compose.material3.internal.Icons;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
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
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\u001aw\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001aE\u0010\u0013\u001a\u00020\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001aO\u0010\u001f\u001a\u00020\u00032\n\u0010 \u001a\u00060!j\u0002`\"2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c¢\u0006\u0004\b#\u0010$\u001a\u0081\u0001\u0010%\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0013\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0013\u0010&\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0001¢\u0006\u0004\b,\u0010-\u001a;\u0010.\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u001a2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u0001012\u0006\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0004\b2\u00103\u001a£\u0001\u00104\u001a\u00020\u00012\b\u00105\u001a\u0004\u0018\u00010\u00152\u0006\u00106\u001a\u00020\u00152\u0006\u0010/\u001a\u00020\u001a2#\u00107\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\u0001012!\u0010;\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u0001012\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0003¢\u0006\u0004\b?\u0010@\u001a\u008d\u0001\u0010A\u001a\u00020\u00012\b\u00105\u001a\u0004\u0018\u00010\u00152\u0006\u00106\u001a\u00020\u00152!\u00107\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\u0001012!\u0010;\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u0001012\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010B\u001aW\u0010C\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020E2\u0006\u0010G\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0001¢\u0006\u0004\bH\u0010I\u001a\u008d\u0001\u0010J\u001a\u00020\u00012\u0006\u0010K\u001a\u00020L2\b\u00105\u001a\u0004\u0018\u00010\u00152!\u00107\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\u0001012!\u0010;\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u0001012\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010M\u001aI\u0010N\u001a\u00020\u00012\u0006\u0010K\u001a\u00020L2!\u0010;\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u0001012\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0017\u001a\u00020\u0018H\u0080@¢\u0006\u0002\u0010O\u001a\u001d\u0010P\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010=\u001a\u00020>H\u0001¢\u0006\u0002\u0010Q\u001a\u0082\u0001\u0010R\u001a\u00020\u00012\u0006\u0010S\u001a\u00020T2!\u00107\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\u0001012\u0006\u0010U\u001a\u00020\u00152\b\u0010V\u001a\u0004\u0018\u00010\u00152\b\u0010W\u001a\u0004\u0018\u00010\u00152\b\u0010X\u001a\u0004\u0018\u00010Y2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\t2\n\u0010 \u001a\u00060!j\u0002`\"H\u0001¢\u0006\u0002\u0010Z\u001a\u0010\u0010[\u001a\u00020\\2\u0006\u0010\u0017\u001a\u00020\u0018H\u0000\u001a7\u0010]\u001a\u0004\u0018\u00010^2\u0006\u0010_\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020\u000f2\u0006\u0010a\u001a\u00020\u000f2\u0006\u0010b\u001a\u00020\u000f2\u0006\u0010c\u001a\u00020\u000fH\u0003¢\u0006\u0002\u0010d\u001ac\u0010e\u001a\u00020\u00012\u0006\u0010f\u001a\u00020^2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010g\u001a\u00020\u000f2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010i\u001a\u00020\u000f2\u0006\u0010j\u001a\u00020\u000f2\u0006\u0010k\u001a\u00020\u000f2\u0006\u0010l\u001a\u00020\u000f2\u0006\u0010m\u001a\u00020^2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010n\u001a`\u0010o\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00106\u001a\u00020\u00152!\u0010p\u001a\u001d\u0012\u0013\u0012\u00110\\¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(q\u0012\u0004\u0012\u00020\u0001012\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010r\u001aS\u0010s\u001a\u00020\u00012\u0006\u0010f\u001a\u00020^2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010g\u001a\u00020\u000f2\u0006\u0010t\u001a\u00020\u000f2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010j\u001a\u00020\u000f2\u0006\u0010m\u001a\u00020^2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010u\u001ag\u0010v\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010w\u001a\u00020\u000f2\u0006\u0010x\u001a\u00020\u000f2\u0006\u0010y\u001a\u00020\u000f2\u0006\u0010z\u001a\u00020^2\f\u0010{\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010|\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010}\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010~\u001aB\u0010\u007f\u001a\u00020\u00012\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0007\u0010\u0080\u0001\u001a\u00020\u000f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0003¢\u0006\u0003\u0010\u0081\u0001\u001aD\u0010\u0082\u0001\u001a\u00020\u00012\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u0007\u0010\u0085\u0001\u001a\u00020^2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010j\u001a\u00020\u000fH\u0003¢\u0006\u0003\u0010\u0086\u0001\"\u001a\u0010\u0087\u0001\u001a\u00020*X\u0080\u0004¢\u0006\r\n\u0003\u0010\u008a\u0001\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001\"\u001a\u0010\u008b\u0001\u001a\u00020*X\u0080\u0004¢\u0006\r\n\u0003\u0010\u008a\u0001\u001a\u0006\b\u008c\u0001\u0010\u0089\u0001\"\u001a\u0010\u008d\u0001\u001a\u00020*X\u0080\u0004¢\u0006\r\n\u0003\u0010\u008a\u0001\u001a\u0006\b\u008e\u0001\u0010\u0089\u0001\"\u0018\u0010\u008f\u0001\u001a\u00030\u0090\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001\"\u0010\u0010\u0093\u0001\u001a\u00030\u0090\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0094\u0001\u001a\u00030\u0090\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0012\u0010\u0095\u0001\u001a\u00020*X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008a\u0001\"\u000f\u0010\u0096\u0001\u001a\u00020\\X\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0097\u0001\u001a\u00020\\X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0098\u0001²\u0006\n\u0010y\u001a\u00020\u000fX\u008a\u008e\u0002"}, d2 = {"DatePicker", "", "state", "Landroidx/compose/material3/DatePickerState;", "modifier", "Landroidx/compose/ui/Modifier;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "colors", "Landroidx/compose/material3/DatePickerColors;", "title", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "headline", "showModeToggle", "", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "(Landroidx/compose/material3/DatePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/DatePickerColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;II)V", "rememberDatePickerState", "initialSelectedDateMillis", "", "initialDisplayedMonthMillis", "yearRange", "Lkotlin/ranges/IntRange;", "initialDisplayMode", "Landroidx/compose/material3/DisplayMode;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "rememberDatePickerState-EU0dCGE", "(Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DatePickerState;", "DatePickerState", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "DatePickerState-sHin3Bw", "(Ljava/util/Locale;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;)Landroidx/compose/material3/DatePickerState;", "DateEntryContainer", "modeToggleButton", "headlineTextStyle", "Landroidx/compose/ui/text/TextStyle;", "headerMinHeight", "Landroidx/compose/ui/unit/Dp;", "content", "DateEntryContainer-au3_HiA", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/text/TextStyle;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "DisplayModeToggleButton", "displayMode", "onDisplayModeChange", "Lkotlin/Function1;", "DisplayModeToggleButton-iUJLfQg", "(Landroidx/compose/ui/Modifier;ILkotlin/jvm/functions/Function1;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "SwitchableDateEntryContent", "selectedDateMillis", "displayedMonthMillis", "onDateSelectionChange", "Lkotlin/ParameterName;", "name", "dateInMillis", "onDisplayedMonthChange", "monthInMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "SwitchableDateEntryContent-KaiTk9E", "(Ljava/lang/Long;JILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;II)V", "DatePickerContent", "(Ljava/lang/Long;JLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "DatePickerHeader", "titleContentColor", "Landroidx/compose/ui/graphics/Color;", "headlineContentColor", "minHeight", "DatePickerHeader-pc5RIQQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;JJFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "HorizontalMonthsList", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "(Landroidx/compose/foundation/lazy/LazyListState;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "updateDisplayedMonth", "(Landroidx/compose/foundation/lazy/LazyListState;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "WeekDays", "(Landroidx/compose/material3/DatePickerColors;Landroidx/compose/material3/internal/CalendarModel;Landroidx/compose/runtime/Composer;I)V", "Month", "month", "Landroidx/compose/material3/internal/CalendarMonth;", "todayMillis", "startDateMillis", "endDateMillis", "rangeSelectionInfo", "Landroidx/compose/material3/SelectedRangeInfo;", "(Landroidx/compose/material3/internal/CalendarMonth;Lkotlin/jvm/functions/Function1;JLjava/lang/Long;Ljava/lang/Long;Landroidx/compose/material3/SelectedRangeInfo;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Ljava/util/Locale;Landroidx/compose/runtime/Composer;I)V", "numberOfMonthsInRange", "", "dayContentDescription", "", "rangeSelectionEnabled", "isToday", "isStartDate", "isEndDate", "isInRange", "(ZZZZZLandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "Day", "text", "selected", "onClick", "animateChecked", "enabled", "today", "inRange", "description", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;ZZZZLjava/lang/String;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "YearPicker", "onYearSelected", "year", "(Landroidx/compose/ui/Modifier;JLkotlin/jvm/functions/Function1;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "Year", "currentYear", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;ZZLkotlin/jvm/functions/Function0;ZLjava/lang/String;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "MonthsNavigation", "nextAvailable", "previousAvailable", "yearPickerVisible", "yearPickerText", "onNextClicked", "onPreviousClicked", "onYearPickerButtonClicked", "(Landroidx/compose/ui/Modifier;ZZZLjava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "YearPickerMenuButton", "expanded", "(Lkotlin/jvm/functions/Function0;ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "IconButtonWithTooltip", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "contentDescription", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Landroidx/compose/ui/Modifier;ZLandroidx/compose/runtime/Composer;II)V", "RecommendedSizeForAccessibility", "getRecommendedSizeForAccessibility", "()F", "F", "MonthYearHeight", "getMonthYearHeight", "DatePickerHorizontalPadding", "getDatePickerHorizontalPadding", "DatePickerModeTogglePadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getDatePickerModeTogglePadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "DatePickerTitlePadding", "DatePickerHeadlinePadding", "YearsVerticalPadding", "MaxCalendarRows", "YearsInRow", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DatePickerKt {
    private static final int MaxCalendarRows = 6;
    private static final int YearsInRow = 3;
    private static final float RecommendedSizeForAccessibility = Dp.m6022constructorimpl(48.0f);
    private static final float MonthYearHeight = Dp.m6022constructorimpl(56.0f);
    private static final float DatePickerHorizontalPadding = Dp.m6022constructorimpl(12.0f);
    private static final PaddingValues DatePickerModeTogglePadding = PaddingKt.PaddingValues-a9UjIt4$default(0.0f, 0.0f, Dp.m6022constructorimpl(12.0f), Dp.m6022constructorimpl(12.0f), 3, (Object) null);
    private static final PaddingValues DatePickerTitlePadding = PaddingKt.PaddingValues-a9UjIt4$default(Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(16.0f), Dp.m6022constructorimpl(12.0f), 0.0f, 8, (Object) null);
    private static final PaddingValues DatePickerHeadlinePadding = PaddingKt.PaddingValues-a9UjIt4$default(Dp.m6022constructorimpl(24.0f), 0.0f, Dp.m6022constructorimpl(12.0f), Dp.m6022constructorimpl(12.0f), 2, (Object) null);
    private static final float YearsVerticalPadding = Dp.m6022constructorimpl(16.0f);

    public static Unit A(SelectedRangeInfo selectedRangeInfo, DatePickerColors datePickerColors, ContentDrawScope contentDrawScope) {
        DateRangePickerKt.m387drawRangeBackgroundmxwnekA(contentDrawScope, selectedRangeInfo, datePickerColors.getDayInSelectionRangeContainerColor());
        contentDrawScope.drawContent();
        return Unit.INSTANCE;
    }

    public static Unit B(CoroutineScope coroutineScope, LazyListState lazyListState) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new DatePickerKt$DatePickerContent$2$2$1$1(lazyListState, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit C(CalendarMonth calendarMonth, Function1 function1, long j, Long l, Long l2, SelectedRangeInfo selectedRangeInfo, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, Locale locale, int i, Composer composer, int i2) {
        Month(calendarMonth, function1, j, l, l2, selectedRangeInfo, datePickerFormatter, selectableDates, datePickerColors, locale, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit D(DatePickerState datePickerState, Modifier modifier, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors, Function2 function2, Function2 function3, boolean z, FocusRequester focusRequester, int i, int i2, Composer composer, int i3) {
        DatePicker(datePickerState, modifier, datePickerFormatter, datePickerColors, function2, function3, z, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DateEntryContainer-au3_HiA, reason: not valid java name */
    public static final void m363DateEntryContainerau3_HiA(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final DatePickerColors datePickerColors, final TextStyle textStyle, final float f, final Function2<? super Composer, ? super Integer, Unit> function5, Composer composer, final int i) {
        int i2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        DatePickerColors datePickerColors2;
        TextStyle textStyle2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1539132883);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            function6 = function2;
            i2 |= composerStartRestartGroup.changedInstance(function6) ? 32 : 16;
        } else {
            function6 = function2;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            function7 = function3;
            i2 |= composerStartRestartGroup.changedInstance(function7) ? 256 : 128;
        } else {
            function7 = function3;
        }
        if ((i & 3072) == 0) {
            function8 = function4;
            i2 |= composerStartRestartGroup.changedInstance(function8) ? 2048 : 1024;
        } else {
            function8 = function4;
        }
        if ((i & 24576) == 0) {
            datePickerColors2 = datePickerColors;
            i2 |= composerStartRestartGroup.changed(datePickerColors2) ? 16384 : 8192;
        } else {
            datePickerColors2 = datePickerColors;
        }
        if ((196608 & i) == 0) {
            textStyle2 = textStyle;
            i2 |= composerStartRestartGroup.changed(textStyle2) ? 131072 : 65536;
        } else {
            textStyle2 = textStyle;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(f) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function5) ? 8388608 : 4194304;
        }
        if (composerStartRestartGroup.shouldExecute((4793491 & i2) != 4793490, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1539132883, i2, -1, "androidx.compose.material3.DateEntryContainer (DatePicker.kt:1351)");
            }
            int i3 = i2;
            Modifier modifier2 = SizeKt.sizeIn-qDBjuR0$default(modifier, DatePickerModalTokens.INSTANCE.m1697getContainerWidthD9Ej5fM(), 0.0f, 0.0f, 0.0f, 14, (Object) null);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: fa3
                    public final Object invoke(Object obj) {
                        return DatePickerKt.d((SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifier3 = BackgroundKt.background-bw27NRU$default(SemanticsModifierKt.semantics$default(modifier2, false, (Function1) objRememberedValue, 1, null), datePickerColors2.getContainerColor(), (Shape) null, 2, (Object) null);
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            final Function2<? super Composer, ? super Integer, Unit> function9 = function6;
            final Function2<? super Composer, ? super Integer, Unit> function10 = function7;
            final Function2<? super Composer, ? super Integer, Unit> function11 = function8;
            final DatePickerColors datePickerColors3 = datePickerColors2;
            final TextStyle textStyle3 = textStyle2;
            m364DatePickerHeaderpc5RIQQ(Modifier.INSTANCE, function2, datePickerColors2.getTitleContentColor(), datePickerColors2.getHeadlineContentColor(), f, ComposableLambdaKt.rememberComposableLambda(-1658370654, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DateEntryContainer$2$1
                public final void invoke(Composer composer3, int i4) {
                    Arrangement.HorizontalOrVertical start;
                    if (!composer3.shouldExecute((i4 & 3) != 2, i4 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1658370654, i4, -1, "androidx.compose.material3.DateEntryContainer.<anonymous>.<anonymous> (DatePicker.kt:1371)");
                    }
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, (Object) null);
                    final Function2<Composer, Integer, Unit> function12 = function10;
                    Function2<Composer, Integer, Unit> function13 = function11;
                    Function2<Composer, Integer, Unit> function14 = function9;
                    DatePickerColors datePickerColors4 = datePickerColors3;
                    TextStyle textStyle4 = textStyle3;
                    Arrangement arrangement = Arrangement.INSTANCE;
                    Arrangement.Vertical top = arrangement.getTop();
                    Alignment.Companion companion3 = Alignment.INSTANCE;
                    MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(top, companion3.getStart(), composer3, 0);
                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                    ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
                    Function0<ComposeUiNode> constructor2 = companion4.getConstructor();
                    if (composer3.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor2);
                    } else {
                        composer3.useNode();
                    }
                    Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composer3);
                    Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyColumnMeasurePolicy2, companion4.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion4.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion4.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion4.getSetModifier());
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    if (function12 == null || function13 == null) {
                        start = function12 != null ? arrangement.getStart() : arrangement.getEnd();
                    } else {
                        start = arrangement.getSpaceBetween();
                    }
                    Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, (Object) null);
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, companion3.getCenterVertically(), composer3, 48);
                    int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap3 = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default2);
                    Function0<ComposeUiNode> constructor3 = companion4.getConstructor();
                    if (composer3.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor3);
                    } else {
                        composer3.useNode();
                    }
                    Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composer3);
                    Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyRowMeasurePolicy, companion4.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion4.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = companion4.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                        composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion4.getSetModifier());
                    final RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    if (function12 != null) {
                        composer3.startReplaceGroup(-516028300);
                        TextKt.ProvideTextStyle(textStyle4, ComposableLambdaKt.rememberComposableLambda(-738208900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DateEntryContainer$2$1$1$1$1
                            public final void invoke(Composer composer4, int i5) {
                                if (!composer4.shouldExecute((i5 & 3) != 2, i5 & 1)) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-738208900, i5, -1, "androidx.compose.material3.DateEntryContainer.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1385)");
                                }
                                Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, (Object) null);
                                Function2<Composer, Integer, Unit> function15 = function12;
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                CompositionLocalMap currentCompositionLocalMap4 = composer4.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer4, modifierWeight$default);
                                ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor4 = companion5.getConstructor();
                                if (composer4.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer4.startReusableNode();
                                if (composer4.getInserting()) {
                                    composer4.createNode(constructor4);
                                } else {
                                    composer4.useNode();
                                }
                                Composer composerM2388constructorimpl4 = Updater.m2388constructorimpl(composer4);
                                Updater.m2396setimpl(composerM2388constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy, companion5.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl4, currentCompositionLocalMap4, companion5.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = companion5.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                                    composerM2388constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                                    composerM2388constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl4, modifierMaterializeModifier4, companion5.getSetModifier());
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                function15.invoke(composer4, 0);
                                composer4.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer3, 54), composer3, 48);
                        composer3.endReplaceGroup();
                    } else {
                        composer3.startReplaceGroup(-515838022);
                        composer3.endReplaceGroup();
                    }
                    if (function13 == null) {
                        composer3.startReplaceGroup(-515799087);
                    } else {
                        composer3.startReplaceGroup(260455984);
                        function13.invoke(composer3, 0);
                    }
                    composer3.endReplaceGroup();
                    composer3.endNode();
                    if (function14 == null && function12 == null && function13 == null) {
                        composer3.startReplaceGroup(-250277930);
                        composer3.endReplaceGroup();
                    } else {
                        composer3.startReplaceGroup(-250360576);
                        DividerKt.m415HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors4.getDividerColor(), composer3, 0, 3);
                        composer3.endReplaceGroup();
                    }
                    composer3.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 112) | 196614 | (57344 & (i3 >> 6)));
            composer2 = composerStartRestartGroup;
            function5.invoke(composer2, Integer.valueOf((i3 >> 21) & 14));
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ga3
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.H(modifier, function2, function3, function4, datePickerColors, textStyle, f, function5, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:108:0x012d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:109:0x012f  */
    /* JADX WARN: Code duplicated, block: B:112:0x0136  */
    /* JADX WARN: Code duplicated, block: B:114:0x0142  */
    /* JADX WARN: Code duplicated, block: B:116:0x015a  */
    /* JADX WARN: Code duplicated, block: B:119:0x0160  */
    /* JADX WARN: Code duplicated, block: B:120:0x0169  */
    /* JADX WARN: Code duplicated, block: B:122:0x016c  */
    /* JADX WARN: Code duplicated, block: B:123:0x0181  */
    /* JADX WARN: Code duplicated, block: B:125:0x0186  */
    /* JADX WARN: Code duplicated, block: B:126:0x0193  */
    /* JADX WARN: Code duplicated, block: B:128:0x0196  */
    /* JADX WARN: Code duplicated, block: B:130:0x0199  */
    /* JADX WARN: Code duplicated, block: B:132:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:134:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:137:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:140:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:142:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:144:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:146:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:150:0x0205  */
    /* JADX WARN: Code duplicated, block: B:152:0x0221  */
    /* JADX WARN: Code duplicated, block: B:155:0x027e  */
    /* JADX WARN: Code duplicated, block: B:158:0x0289  */
    /* JADX WARN: Code duplicated, block: B:161:0x029d  */
    /* JADX WARN: Code duplicated, block: B:163:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0047  */
    /* JADX WARN: Code duplicated, block: B:28:0x004b  */
    /* JADX WARN: Code duplicated, block: B:30:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0054  */
    /* JADX WARN: Code duplicated, block: B:33:0x005a  */
    /* JADX WARN: Code duplicated, block: B:34:0x005d  */
    /* JADX WARN: Code duplicated, block: B:38:0x0064  */
    /* JADX WARN: Code duplicated, block: B:40:0x0068  */
    /* JADX WARN: Code duplicated, block: B:42:0x0070  */
    /* JADX WARN: Code duplicated, block: B:43:0x0073  */
    /* JADX WARN: Code duplicated, block: B:46:0x0079  */
    /* JADX WARN: Code duplicated, block: B:49:0x007f  */
    /* JADX WARN: Code duplicated, block: B:51:0x0084  */
    /* JADX WARN: Code duplicated, block: B:53:0x0088  */
    /* JADX WARN: Code duplicated, block: B:55:0x0090  */
    /* JADX WARN: Code duplicated, block: B:56:0x0093  */
    /* JADX WARN: Code duplicated, block: B:60:0x009c  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:75:0x00be  */
    /* JADX WARN: Code duplicated, block: B:77:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:78:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:82:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:83:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:85:0x00de  */
    /* JADX WARN: Code duplicated, block: B:87:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:88:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:93:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:96:0x0104  */
    /* JADX WARN: Code duplicated, block: B:98:0x010c  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void DatePicker(final DatePickerState datePickerState, Modifier modifier, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, boolean z, FocusRequester focusRequester, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        DatePickerColors datePickerColors2;
        int i4;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        int i5;
        int i6;
        Function2<? super Composer, ? super Integer, Unit> function4;
        int i7;
        int i8;
        boolean z2;
        int i9;
        int i10;
        int i11;
        boolean z3;
        Composer composer2;
        final DatePickerFormatter datePickerFormatter2;
        final FocusRequester focusRequester2;
        final Modifier modifier3;
        final DatePickerColors datePickerColors3;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final boolean z4;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final DatePickerFormatter datePickerFormatter3;
        final DatePickerColors datePickerColorsColors;
        boolean z5;
        int i12;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        boolean z6;
        DatePickerColors datePickerColors4;
        Modifier modifier4;
        int i13;
        FocusRequester focusRequester3;
        Object objRememberedValue;
        Object objRememberedValue2;
        boolean zChanged;
        Object objRememberedValue3;
        CalendarModel calendarModelCreateCalendarModel;
        ComposableLambda composableLambdaRememberComposableLambda;
        int i14;
        boolean zChangedInstance;
        Composer composerStartRestartGroup = composer.startRestartGroup(1105472031);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(datePickerState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i15 = i2 & 2;
        if (i15 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 4) != 0) {
                    i14 = 128;
                } else {
                    if ((i & 512) == 0) {
                        zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                    } else {
                        zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                    }
                    if (zChangedInstance) {
                        i14 = 256;
                    } else {
                        i14 = 128;
                    }
                }
                i3 |= i14;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    datePickerColors2 = datePickerColors;
                    int i16 = composerStartRestartGroup.changed(datePickerColors2) ? 2048 : 1024;
                    i3 |= i16;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                i3 |= i16;
            } else {
                datePickerColors2 = datePickerColors;
            }
            i4 = i2 & 16;
            if (i4 != 0) {
                if ((i & 24576) == 0) {
                    function2RememberComposableLambda = function2;
                    if (composerStartRestartGroup.changedInstance(function2RememberComposableLambda)) {
                        i5 = 16384;
                    } else {
                        i5 = 8192;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        function4 = function3;
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        if ((1572864 & i) == 0) {
                            z2 = z;
                            if (composerStartRestartGroup.changed(z2)) {
                                i9 = 1048576;
                            } else {
                                i9 = 524288;
                            }
                            i3 |= i9;
                        }
                        i10 = i2 & 128;
                        if (i10 != 0) {
                            i3 |= 12582912;
                        } else if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(focusRequester)) {
                                i11 = 8388608;
                            } else {
                                i11 = 4194304;
                            }
                            i3 |= i11;
                        }
                        if ((i3 & 4793491) != 4793490) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i2 & 4) != 0) {
                                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                    }
                                    datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                    i3 &= -897;
                                } else {
                                    datePickerFormatter3 = datePickerFormatter;
                                }
                                if ((i2 & 8) != 0) {
                                    datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -7169;
                                } else {
                                    datePickerColorsColors = datePickerColors2;
                                }
                                if (i4 != 0) {
                                    z5 = true;
                                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                        public final void invoke(Composer composer3, int i17) {
                                            if (!composer3.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                                composer3.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1655706771, i17, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                            }
                                            DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composerStartRestartGroup, 54);
                                    i12 = 54;
                                } else {
                                    z5 = true;
                                    i12 = 54;
                                }
                                if (i6 != 0) {
                                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                        public final void invoke(Composer composer3, int i17) {
                                            if (!composer3.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                                composer3.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1439279037, i17, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                            }
                                            DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composerStartRestartGroup, i12);
                                } else {
                                    function2RememberComposableLambda2 = function4;
                                }
                                if (i8 != 0) {
                                    z2 = true;
                                }
                                if (i10 != 0) {
                                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = new FocusRequester();
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                    }
                                    int i17 = i3;
                                    focusRequester3 = (FocusRequester) objRememberedValue;
                                    z6 = z2;
                                    datePickerColors4 = datePickerColorsColors;
                                    i13 = i17;
                                    function4 = function2RememberComposableLambda2;
                                    function7 = function2RememberComposableLambda;
                                    modifier4 = modifier2;
                                } else {
                                    function4 = function2RememberComposableLambda2;
                                    function7 = function2RememberComposableLambda;
                                    z6 = z2;
                                    datePickerColors4 = datePickerColorsColors;
                                    modifier4 = modifier2;
                                    i13 = i3;
                                    focusRequester3 = focusRequester;
                                }
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 4) != 0) {
                                    i3 &= -897;
                                }
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                }
                                datePickerFormatter3 = datePickerFormatter;
                                i13 = i3;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                focusRequester3 = focusRequester;
                                modifier4 = modifier2;
                                datePickerColors4 = datePickerColors2;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                            }
                            zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                if (datePickerState instanceof BaseDatePickerStateImpl) {
                                    calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                                } else {
                                    calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                                }
                                objRememberedValue3 = calendarModelCreateCalendarModel;
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            CalendarModel calendarModel = (CalendarModel) objRememberedValue3;
                            if (z6) {
                                composerStartRestartGroup.startReplaceGroup(-690551113);
                                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-690163489);
                                composerStartRestartGroup.endReplaceGroup();
                                composableLambdaRememberComposableLambda = null;
                            }
                            ComposableLambda composableLambda = composableLambdaRememberComposableLambda;
                            DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
                            TextStyle value = TypographyKt.getValue(datePickerModalTokens.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                            float fM1703getHeaderContainerHeightD9Ej5fM = datePickerModalTokens.m1703getHeaderContainerHeightD9Ej5fM();
                            FocusRequester focusRequester4 = focusRequester3;
                            AnonymousClass6 anonymousClass6 = new AnonymousClass6(datePickerState, calendarModel, datePickerFormatter3, datePickerColors4, focusRequester4);
                            DatePickerFormatter datePickerFormatter4 = datePickerFormatter3;
                            int i18 = i13 >> 9;
                            composer2 = composerStartRestartGroup;
                            m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda, datePickerColors4, value, fM1703getHeaderContainerHeightD9Ej5fM, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass6, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i18 & 112) | (i18 & 896) | ((i13 << 3) & 57344));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            datePickerFormatter2 = datePickerFormatter4;
                            focusRequester2 = focusRequester4;
                            z4 = z6;
                            modifier3 = modifier4;
                            function5 = function7;
                            datePickerColors3 = datePickerColors4;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            datePickerFormatter2 = datePickerFormatter;
                            focusRequester2 = focusRequester;
                            modifier3 = modifier2;
                            datePickerColors3 = datePickerColors2;
                            function5 = function2RememberComposableLambda;
                            z4 = z2;
                        }
                        function6 = function4;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                                public final Object invoke(Object obj, Object obj2) {
                                    return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    z2 = z;
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(focusRequester)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 4793491) != 4793490) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                    public final void invoke(Composer composer3, int i19) {
                                        if (!composer3.shouldExecute((i19 & 3) != 2, i19 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1655706771, i19, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                        }
                                        DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                    public final void invoke(Composer composer3, int i19) {
                                        if (!composer3.shouldExecute((i19 & 3) != 2, i19 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1439279037, i19, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                        }
                                        DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i19 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i19;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                    public final void invoke(Composer composer3, int i110) {
                                        if (!composer3.shouldExecute((i110 & 3) != 2, i110 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1655706771, i110, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                        }
                                        DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                    public final void invoke(Composer composer3, int i110) {
                                        if (!composer3.shouldExecute((i110 & 3) != 2, i110 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1439279037, i110, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                        }
                                        DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i110 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i110;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                        }
                        zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            if (datePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            if (datePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CalendarModel calendarModel2 = (CalendarModel) objRememberedValue3;
                        if (z6) {
                            composerStartRestartGroup.startReplaceGroup(-690551113);
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-690163489);
                            composerStartRestartGroup.endReplaceGroup();
                            composableLambdaRememberComposableLambda = null;
                        }
                        ComposableLambda composableLambda2 = composableLambdaRememberComposableLambda;
                        DatePickerModalTokens datePickerModalTokens2 = DatePickerModalTokens.INSTANCE;
                        TextStyle value2 = TypographyKt.getValue(datePickerModalTokens2.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                        float fM1703getHeaderContainerHeightD9Ej5fM2 = datePickerModalTokens2.m1703getHeaderContainerHeightD9Ej5fM();
                        FocusRequester focusRequester5 = focusRequester3;
                        AnonymousClass6 anonymousClass7 = new AnonymousClass6(datePickerState, calendarModel2, datePickerFormatter3, datePickerColors4, focusRequester5);
                        DatePickerFormatter datePickerFormatter5 = datePickerFormatter3;
                        int i111 = i13 >> 9;
                        composer2 = composerStartRestartGroup;
                        m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda2, datePickerColors4, value2, fM1703getHeaderContainerHeightD9Ej5fM2, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass7, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i111 & 112) | (i111 & 896) | ((i13 << 3) & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter2 = datePickerFormatter5;
                        focusRequester2 = focusRequester5;
                        z4 = z6;
                        modifier3 = modifier4;
                        function5 = function7;
                        datePickerColors3 = datePickerColors4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        datePickerFormatter2 = datePickerFormatter;
                        focusRequester2 = focusRequester;
                        modifier3 = modifier2;
                        datePickerColors3 = datePickerColors2;
                        function5 = function2RememberComposableLambda;
                        z4 = z2;
                    }
                    function6 = function4;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                            public final Object invoke(Object obj, Object obj2) {
                                return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function4 = function3;
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(focusRequester)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 4793491) != 4793490) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                    public final void invoke(Composer composer3, int i112) {
                                        if (!composer3.shouldExecute((i112 & 3) != 2, i112 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1655706771, i112, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                        }
                                        DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                    public final void invoke(Composer composer3, int i112) {
                                        if (!composer3.shouldExecute((i112 & 3) != 2, i112 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1439279037, i112, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                        }
                                        DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i112 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i112;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                    public final void invoke(Composer composer3, int i113) {
                                        if (!composer3.shouldExecute((i113 & 3) != 2, i113 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1655706771, i113, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                        }
                                        DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                    public final void invoke(Composer composer3, int i113) {
                                        if (!composer3.shouldExecute((i113 & 3) != 2, i113 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1439279037, i113, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                        }
                                        DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i113 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i113;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                        }
                        zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            if (datePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            if (datePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CalendarModel calendarModel3 = (CalendarModel) objRememberedValue3;
                        if (z6) {
                            composerStartRestartGroup.startReplaceGroup(-690551113);
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-690163489);
                            composerStartRestartGroup.endReplaceGroup();
                            composableLambdaRememberComposableLambda = null;
                        }
                        ComposableLambda composableLambda3 = composableLambdaRememberComposableLambda;
                        DatePickerModalTokens datePickerModalTokens3 = DatePickerModalTokens.INSTANCE;
                        TextStyle value3 = TypographyKt.getValue(datePickerModalTokens3.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                        float fM1703getHeaderContainerHeightD9Ej5fM3 = datePickerModalTokens3.m1703getHeaderContainerHeightD9Ej5fM();
                        FocusRequester focusRequester6 = focusRequester3;
                        AnonymousClass6 anonymousClass8 = new AnonymousClass6(datePickerState, calendarModel3, datePickerFormatter3, datePickerColors4, focusRequester6);
                        DatePickerFormatter datePickerFormatter6 = datePickerFormatter3;
                        int i114 = i13 >> 9;
                        composer2 = composerStartRestartGroup;
                        m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda3, datePickerColors4, value3, fM1703getHeaderContainerHeightD9Ej5fM3, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass8, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i114 & 112) | (i114 & 896) | ((i13 << 3) & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter2 = datePickerFormatter6;
                        focusRequester2 = focusRequester6;
                        z4 = z6;
                        modifier3 = modifier4;
                        function5 = function7;
                        datePickerColors3 = datePickerColors4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        datePickerFormatter2 = datePickerFormatter;
                        focusRequester2 = focusRequester;
                        modifier3 = modifier2;
                        datePickerColors3 = datePickerColors2;
                        function5 = function2RememberComposableLambda;
                        z4 = z2;
                    }
                    function6 = function4;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                            public final Object invoke(Object obj, Object obj2) {
                                return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                z2 = z;
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i115) {
                                    if (!composer3.shouldExecute((i115 & 3) != 2, i115 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i115, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i115) {
                                    if (!composer3.shouldExecute((i115 & 3) != 2, i115 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i115, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i115 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i115;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i116) {
                                    if (!composer3.shouldExecute((i116 & 3) != 2, i116 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i116, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i116) {
                                    if (!composer3.shouldExecute((i116 & 3) != 2, i116 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i116, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i116 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i116;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                    }
                    zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel4 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-690551113);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-690163489);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda4 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens4 = DatePickerModalTokens.INSTANCE;
                    TextStyle value4 = TypographyKt.getValue(datePickerModalTokens4.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM1703getHeaderContainerHeightD9Ej5fM4 = datePickerModalTokens4.m1703getHeaderContainerHeightD9Ej5fM();
                    FocusRequester focusRequester7 = focusRequester3;
                    AnonymousClass6 anonymousClass9 = new AnonymousClass6(datePickerState, calendarModel4, datePickerFormatter3, datePickerColors4, focusRequester7);
                    DatePickerFormatter datePickerFormatter7 = datePickerFormatter3;
                    int i117 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda4, datePickerColors4, value4, fM1703getHeaderContainerHeightD9Ej5fM4, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass9, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i117 & 112) | (i117 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter7;
                    focusRequester2 = focusRequester7;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                        public final Object invoke(Object obj, Object obj2) {
                            return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function2RememberComposableLambda = function2;
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    function4 = function3;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(focusRequester)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 4793491) != 4793490) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                    public final void invoke(Composer composer3, int i118) {
                                        if (!composer3.shouldExecute((i118 & 3) != 2, i118 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1655706771, i118, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                        }
                                        DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                    public final void invoke(Composer composer3, int i118) {
                                        if (!composer3.shouldExecute((i118 & 3) != 2, i118 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1439279037, i118, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                        }
                                        DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i118 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i118;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                    public final void invoke(Composer composer3, int i119) {
                                        if (!composer3.shouldExecute((i119 & 3) != 2, i119 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1655706771, i119, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                        }
                                        DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                    public final void invoke(Composer composer3, int i119) {
                                        if (!composer3.shouldExecute((i119 & 3) != 2, i119 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1439279037, i119, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                        }
                                        DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i119 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i119;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                        }
                        zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            if (datePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            if (datePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CalendarModel calendarModel5 = (CalendarModel) objRememberedValue3;
                        if (z6) {
                            composerStartRestartGroup.startReplaceGroup(-690551113);
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-690163489);
                            composerStartRestartGroup.endReplaceGroup();
                            composableLambdaRememberComposableLambda = null;
                        }
                        ComposableLambda composableLambda5 = composableLambdaRememberComposableLambda;
                        DatePickerModalTokens datePickerModalTokens5 = DatePickerModalTokens.INSTANCE;
                        TextStyle value5 = TypographyKt.getValue(datePickerModalTokens5.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                        float fM1703getHeaderContainerHeightD9Ej5fM5 = datePickerModalTokens5.m1703getHeaderContainerHeightD9Ej5fM();
                        FocusRequester focusRequester8 = focusRequester3;
                        AnonymousClass6 anonymousClass10 = new AnonymousClass6(datePickerState, calendarModel5, datePickerFormatter3, datePickerColors4, focusRequester8);
                        DatePickerFormatter datePickerFormatter8 = datePickerFormatter3;
                        int i1110 = i13 >> 9;
                        composer2 = composerStartRestartGroup;
                        m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda5, datePickerColors4, value5, fM1703getHeaderContainerHeightD9Ej5fM5, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass10, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1110 & 112) | (i1110 & 896) | ((i13 << 3) & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter2 = datePickerFormatter8;
                        focusRequester2 = focusRequester8;
                        z4 = z6;
                        modifier3 = modifier4;
                        function5 = function7;
                        datePickerColors3 = datePickerColors4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        datePickerFormatter2 = datePickerFormatter;
                        focusRequester2 = focusRequester;
                        modifier3 = modifier2;
                        datePickerColors3 = datePickerColors2;
                        function5 = function2RememberComposableLambda;
                        z4 = z2;
                    }
                    function6 = function4;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                            public final Object invoke(Object obj, Object obj2) {
                                return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                z2 = z;
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i1111) {
                                    if (!composer3.shouldExecute((i1111 & 3) != 2, i1111 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i1111, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i1111) {
                                    if (!composer3.shouldExecute((i1111 & 3) != 2, i1111 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i1111, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i1111 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i1111;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i1112) {
                                    if (!composer3.shouldExecute((i1112 & 3) != 2, i1112 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i1112, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i1112) {
                                    if (!composer3.shouldExecute((i1112 & 3) != 2, i1112 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i1112, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i1112 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i1112;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                    }
                    zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel6 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-690551113);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-690163489);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda6 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens6 = DatePickerModalTokens.INSTANCE;
                    TextStyle value6 = TypographyKt.getValue(datePickerModalTokens6.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM1703getHeaderContainerHeightD9Ej5fM6 = datePickerModalTokens6.m1703getHeaderContainerHeightD9Ej5fM();
                    FocusRequester focusRequester9 = focusRequester3;
                    AnonymousClass6 anonymousClass11 = new AnonymousClass6(datePickerState, calendarModel6, datePickerFormatter3, datePickerColors4, focusRequester9);
                    DatePickerFormatter datePickerFormatter9 = datePickerFormatter3;
                    int i1113 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda6, datePickerColors4, value6, fM1703getHeaderContainerHeightD9Ej5fM6, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass11, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1113 & 112) | (i1113 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter9;
                    focusRequester2 = focusRequester9;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                        public final Object invoke(Object obj, Object obj2) {
                            return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function4 = function3;
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i1114) {
                                    if (!composer3.shouldExecute((i1114 & 3) != 2, i1114 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i1114, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i1114) {
                                    if (!composer3.shouldExecute((i1114 & 3) != 2, i1114 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i1114, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i1114 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i1114;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i1115) {
                                    if (!composer3.shouldExecute((i1115 & 3) != 2, i1115 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i1115, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i1115) {
                                    if (!composer3.shouldExecute((i1115 & 3) != 2, i1115 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i1115, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i1115 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i1115;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                    }
                    zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel7 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-690551113);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-690163489);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda7 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens7 = DatePickerModalTokens.INSTANCE;
                    TextStyle value7 = TypographyKt.getValue(datePickerModalTokens7.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM1703getHeaderContainerHeightD9Ej5fM7 = datePickerModalTokens7.m1703getHeaderContainerHeightD9Ej5fM();
                    FocusRequester focusRequester10 = focusRequester3;
                    AnonymousClass6 anonymousClass12 = new AnonymousClass6(datePickerState, calendarModel7, datePickerFormatter3, datePickerColors4, focusRequester10);
                    DatePickerFormatter datePickerFormatter10 = datePickerFormatter3;
                    int i1116 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda7, datePickerColors4, value7, fM1703getHeaderContainerHeightD9Ej5fM7, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass12, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1116 & 112) | (i1116 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter10;
                    focusRequester2 = focusRequester10;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                        public final Object invoke(Object obj, Object obj2) {
                            return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            z2 = z;
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(focusRequester)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                            public final void invoke(Composer composer3, int i1117) {
                                if (!composer3.shouldExecute((i1117 & 3) != 2, i1117 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1655706771, i1117, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                }
                                DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                            public final void invoke(Composer composer3, int i1117) {
                                if (!composer3.shouldExecute((i1117 & 3) != 2, i1117 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1439279037, i1117, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                }
                                DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i1117 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i1117;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                            public final void invoke(Composer composer3, int i1118) {
                                if (!composer3.shouldExecute((i1118 & 3) != 2, i1118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1655706771, i1118, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                }
                                DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                            public final void invoke(Composer composer3, int i1118) {
                                if (!composer3.shouldExecute((i1118 & 3) != 2, i1118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1439279037, i1118, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                }
                                DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i1118 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i1118;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                }
                zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    if (datePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    if (datePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                CalendarModel calendarModel8 = (CalendarModel) objRememberedValue3;
                if (z6) {
                    composerStartRestartGroup.startReplaceGroup(-690551113);
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-690163489);
                    composerStartRestartGroup.endReplaceGroup();
                    composableLambdaRememberComposableLambda = null;
                }
                ComposableLambda composableLambda8 = composableLambdaRememberComposableLambda;
                DatePickerModalTokens datePickerModalTokens8 = DatePickerModalTokens.INSTANCE;
                TextStyle value8 = TypographyKt.getValue(datePickerModalTokens8.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                float fM1703getHeaderContainerHeightD9Ej5fM8 = datePickerModalTokens8.m1703getHeaderContainerHeightD9Ej5fM();
                FocusRequester focusRequester11 = focusRequester3;
                AnonymousClass6 anonymousClass13 = new AnonymousClass6(datePickerState, calendarModel8, datePickerFormatter3, datePickerColors4, focusRequester11);
                DatePickerFormatter datePickerFormatter11 = datePickerFormatter3;
                int i1119 = i13 >> 9;
                composer2 = composerStartRestartGroup;
                m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda8, datePickerColors4, value8, fM1703getHeaderContainerHeightD9Ej5fM8, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass13, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1119 & 112) | (i1119 & 896) | ((i13 << 3) & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter2 = datePickerFormatter11;
                focusRequester2 = focusRequester11;
                z4 = z6;
                modifier3 = modifier4;
                function5 = function7;
                datePickerColors3 = datePickerColors4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                datePickerFormatter2 = datePickerFormatter;
                focusRequester2 = focusRequester;
                modifier3 = modifier2;
                datePickerColors3 = datePickerColors2;
                function5 = function2RememberComposableLambda;
                z4 = z2;
            }
            function6 = function4;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                    public final Object invoke(Object obj, Object obj2) {
                        return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) != 0) {
                i14 = 128;
            } else {
                if ((i & 512) == 0) {
                    zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                } else {
                    zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                }
                if (zChangedInstance) {
                    i14 = 256;
                } else {
                    i14 = 128;
                }
            }
            i3 |= i14;
        }
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                datePickerColors2 = datePickerColors;
                if (composerStartRestartGroup.changed(datePickerColors2)) {
                }
                i3 |= i16;
            } else {
                datePickerColors2 = datePickerColors;
            }
            i3 |= i16;
        } else {
            datePickerColors2 = datePickerColors;
        }
        i4 = i2 & 16;
        if (i4 != 0) {
            if ((i & 24576) == 0) {
                function2RememberComposableLambda = function2;
                if (composerStartRestartGroup.changedInstance(function2RememberComposableLambda)) {
                    i5 = 16384;
                } else {
                    i5 = 8192;
                }
                i3 |= i5;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    function4 = function3;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(focusRequester)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 4793491) != 4793490) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                    public final void invoke(Composer composer3, int i11110) {
                                        if (!composer3.shouldExecute((i11110 & 3) != 2, i11110 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1655706771, i11110, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                        }
                                        DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                    public final void invoke(Composer composer3, int i11110) {
                                        if (!composer3.shouldExecute((i11110 & 3) != 2, i11110 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1439279037, i11110, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                        }
                                        DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i11110 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i11110;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                    public final void invoke(Composer composer3, int i11111) {
                                        if (!composer3.shouldExecute((i11111 & 3) != 2, i11111 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1655706771, i11111, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                        }
                                        DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                    public final void invoke(Composer composer3, int i11111) {
                                        if (!composer3.shouldExecute((i11111 & 3) != 2, i11111 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1439279037, i11111, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                        }
                                        DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i11111 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i11111;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                        }
                        zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            if (datePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            if (datePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CalendarModel calendarModel9 = (CalendarModel) objRememberedValue3;
                        if (z6) {
                            composerStartRestartGroup.startReplaceGroup(-690551113);
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-690163489);
                            composerStartRestartGroup.endReplaceGroup();
                            composableLambdaRememberComposableLambda = null;
                        }
                        ComposableLambda composableLambda9 = composableLambdaRememberComposableLambda;
                        DatePickerModalTokens datePickerModalTokens9 = DatePickerModalTokens.INSTANCE;
                        TextStyle value9 = TypographyKt.getValue(datePickerModalTokens9.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                        float fM1703getHeaderContainerHeightD9Ej5fM9 = datePickerModalTokens9.m1703getHeaderContainerHeightD9Ej5fM();
                        FocusRequester focusRequester12 = focusRequester3;
                        AnonymousClass6 anonymousClass14 = new AnonymousClass6(datePickerState, calendarModel9, datePickerFormatter3, datePickerColors4, focusRequester12);
                        DatePickerFormatter datePickerFormatter12 = datePickerFormatter3;
                        int i11112 = i13 >> 9;
                        composer2 = composerStartRestartGroup;
                        m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda9, datePickerColors4, value9, fM1703getHeaderContainerHeightD9Ej5fM9, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass14, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i11112 & 112) | (i11112 & 896) | ((i13 << 3) & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter2 = datePickerFormatter12;
                        focusRequester2 = focusRequester12;
                        z4 = z6;
                        modifier3 = modifier4;
                        function5 = function7;
                        datePickerColors3 = datePickerColors4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        datePickerFormatter2 = datePickerFormatter;
                        focusRequester2 = focusRequester;
                        modifier3 = modifier2;
                        datePickerColors3 = datePickerColors2;
                        function5 = function2RememberComposableLambda;
                        z4 = z2;
                    }
                    function6 = function4;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                            public final Object invoke(Object obj, Object obj2) {
                                return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                z2 = z;
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i11113) {
                                    if (!composer3.shouldExecute((i11113 & 3) != 2, i11113 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i11113, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i11113) {
                                    if (!composer3.shouldExecute((i11113 & 3) != 2, i11113 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i11113, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i11113 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i11113;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i11114) {
                                    if (!composer3.shouldExecute((i11114 & 3) != 2, i11114 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i11114, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i11114) {
                                    if (!composer3.shouldExecute((i11114 & 3) != 2, i11114 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i11114, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i11114 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i11114;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                    }
                    zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel10 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-690551113);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-690163489);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda10 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens10 = DatePickerModalTokens.INSTANCE;
                    TextStyle value10 = TypographyKt.getValue(datePickerModalTokens10.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM1703getHeaderContainerHeightD9Ej5fM10 = datePickerModalTokens10.m1703getHeaderContainerHeightD9Ej5fM();
                    FocusRequester focusRequester13 = focusRequester3;
                    AnonymousClass6 anonymousClass15 = new AnonymousClass6(datePickerState, calendarModel10, datePickerFormatter3, datePickerColors4, focusRequester13);
                    DatePickerFormatter datePickerFormatter13 = datePickerFormatter3;
                    int i11115 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda10, datePickerColors4, value10, fM1703getHeaderContainerHeightD9Ej5fM10, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass15, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i11115 & 112) | (i11115 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter13;
                    focusRequester2 = focusRequester13;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                        public final Object invoke(Object obj, Object obj2) {
                            return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function4 = function3;
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i11116) {
                                    if (!composer3.shouldExecute((i11116 & 3) != 2, i11116 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i11116, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i11116) {
                                    if (!composer3.shouldExecute((i11116 & 3) != 2, i11116 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i11116, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i11116 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i11116;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i11117) {
                                    if (!composer3.shouldExecute((i11117 & 3) != 2, i11117 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i11117, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i11117) {
                                    if (!composer3.shouldExecute((i11117 & 3) != 2, i11117 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i11117, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i11117 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i11117;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                    }
                    zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel11 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-690551113);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-690163489);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda11 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens11 = DatePickerModalTokens.INSTANCE;
                    TextStyle value11 = TypographyKt.getValue(datePickerModalTokens11.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM1703getHeaderContainerHeightD9Ej5fM11 = datePickerModalTokens11.m1703getHeaderContainerHeightD9Ej5fM();
                    FocusRequester focusRequester14 = focusRequester3;
                    AnonymousClass6 anonymousClass16 = new AnonymousClass6(datePickerState, calendarModel11, datePickerFormatter3, datePickerColors4, focusRequester14);
                    DatePickerFormatter datePickerFormatter14 = datePickerFormatter3;
                    int i11118 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda11, datePickerColors4, value11, fM1703getHeaderContainerHeightD9Ej5fM11, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass16, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i11118 & 112) | (i11118 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter14;
                    focusRequester2 = focusRequester14;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                        public final Object invoke(Object obj, Object obj2) {
                            return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            z2 = z;
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(focusRequester)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                            public final void invoke(Composer composer3, int i11119) {
                                if (!composer3.shouldExecute((i11119 & 3) != 2, i11119 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1655706771, i11119, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                }
                                DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                            public final void invoke(Composer composer3, int i11119) {
                                if (!composer3.shouldExecute((i11119 & 3) != 2, i11119 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1439279037, i11119, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                }
                                DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i11119 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i11119;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                            public final void invoke(Composer composer3, int i111110) {
                                if (!composer3.shouldExecute((i111110 & 3) != 2, i111110 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1655706771, i111110, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                }
                                DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                            public final void invoke(Composer composer3, int i111110) {
                                if (!composer3.shouldExecute((i111110 & 3) != 2, i111110 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1439279037, i111110, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                }
                                DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i111110 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i111110;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                }
                zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    if (datePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    if (datePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                CalendarModel calendarModel12 = (CalendarModel) objRememberedValue3;
                if (z6) {
                    composerStartRestartGroup.startReplaceGroup(-690551113);
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-690163489);
                    composerStartRestartGroup.endReplaceGroup();
                    composableLambdaRememberComposableLambda = null;
                }
                ComposableLambda composableLambda12 = composableLambdaRememberComposableLambda;
                DatePickerModalTokens datePickerModalTokens12 = DatePickerModalTokens.INSTANCE;
                TextStyle value12 = TypographyKt.getValue(datePickerModalTokens12.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                float fM1703getHeaderContainerHeightD9Ej5fM12 = datePickerModalTokens12.m1703getHeaderContainerHeightD9Ej5fM();
                FocusRequester focusRequester15 = focusRequester3;
                AnonymousClass6 anonymousClass17 = new AnonymousClass6(datePickerState, calendarModel12, datePickerFormatter3, datePickerColors4, focusRequester15);
                DatePickerFormatter datePickerFormatter15 = datePickerFormatter3;
                int i111111 = i13 >> 9;
                composer2 = composerStartRestartGroup;
                m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda12, datePickerColors4, value12, fM1703getHeaderContainerHeightD9Ej5fM12, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass17, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i111111 & 112) | (i111111 & 896) | ((i13 << 3) & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter2 = datePickerFormatter15;
                focusRequester2 = focusRequester15;
                z4 = z6;
                modifier3 = modifier4;
                function5 = function7;
                datePickerColors3 = datePickerColors4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                datePickerFormatter2 = datePickerFormatter;
                focusRequester2 = focusRequester;
                modifier3 = modifier2;
                datePickerColors3 = datePickerColors2;
                function5 = function2RememberComposableLambda;
                z4 = z2;
            }
            function6 = function4;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                    public final Object invoke(Object obj, Object obj2) {
                        return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        function2RememberComposableLambda = function2;
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                function4 = function3;
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i111112) {
                                    if (!composer3.shouldExecute((i111112 & 3) != 2, i111112 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i111112, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i111112) {
                                    if (!composer3.shouldExecute((i111112 & 3) != 2, i111112 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i111112, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i111112 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i111112;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                                public final void invoke(Composer composer3, int i111113) {
                                    if (!composer3.shouldExecute((i111113 & 3) != 2, i111113 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655706771, i111113, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                    }
                                    DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                                public final void invoke(Composer composer3, int i111113) {
                                    if (!composer3.shouldExecute((i111113 & 3) != 2, i111113 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1439279037, i111113, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                    }
                                    DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i111113 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i111113;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                    }
                    zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (datePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel13 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-690551113);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-690163489);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda13 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens13 = DatePickerModalTokens.INSTANCE;
                    TextStyle value13 = TypographyKt.getValue(datePickerModalTokens13.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM1703getHeaderContainerHeightD9Ej5fM13 = datePickerModalTokens13.m1703getHeaderContainerHeightD9Ej5fM();
                    FocusRequester focusRequester16 = focusRequester3;
                    AnonymousClass6 anonymousClass18 = new AnonymousClass6(datePickerState, calendarModel13, datePickerFormatter3, datePickerColors4, focusRequester16);
                    DatePickerFormatter datePickerFormatter16 = datePickerFormatter3;
                    int i111114 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda13, datePickerColors4, value13, fM1703getHeaderContainerHeightD9Ej5fM13, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass18, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i111114 & 112) | (i111114 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter16;
                    focusRequester2 = focusRequester16;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                        public final Object invoke(Object obj, Object obj2) {
                            return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            z2 = z;
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(focusRequester)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                            public final void invoke(Composer composer3, int i111115) {
                                if (!composer3.shouldExecute((i111115 & 3) != 2, i111115 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1655706771, i111115, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                }
                                DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                            public final void invoke(Composer composer3, int i111115) {
                                if (!composer3.shouldExecute((i111115 & 3) != 2, i111115 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1439279037, i111115, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                }
                                DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i111115 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i111115;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                            public final void invoke(Composer composer3, int i111116) {
                                if (!composer3.shouldExecute((i111116 & 3) != 2, i111116 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1655706771, i111116, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                }
                                DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                            public final void invoke(Composer composer3, int i111116) {
                                if (!composer3.shouldExecute((i111116 & 3) != 2, i111116 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1439279037, i111116, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                }
                                DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i111116 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i111116;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                }
                zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    if (datePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    if (datePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                CalendarModel calendarModel14 = (CalendarModel) objRememberedValue3;
                if (z6) {
                    composerStartRestartGroup.startReplaceGroup(-690551113);
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-690163489);
                    composerStartRestartGroup.endReplaceGroup();
                    composableLambdaRememberComposableLambda = null;
                }
                ComposableLambda composableLambda14 = composableLambdaRememberComposableLambda;
                DatePickerModalTokens datePickerModalTokens14 = DatePickerModalTokens.INSTANCE;
                TextStyle value14 = TypographyKt.getValue(datePickerModalTokens14.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                float fM1703getHeaderContainerHeightD9Ej5fM14 = datePickerModalTokens14.m1703getHeaderContainerHeightD9Ej5fM();
                FocusRequester focusRequester17 = focusRequester3;
                AnonymousClass6 anonymousClass19 = new AnonymousClass6(datePickerState, calendarModel14, datePickerFormatter3, datePickerColors4, focusRequester17);
                DatePickerFormatter datePickerFormatter17 = datePickerFormatter3;
                int i111117 = i13 >> 9;
                composer2 = composerStartRestartGroup;
                m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda14, datePickerColors4, value14, fM1703getHeaderContainerHeightD9Ej5fM14, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass19, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i111117 & 112) | (i111117 & 896) | ((i13 << 3) & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter2 = datePickerFormatter17;
                focusRequester2 = focusRequester17;
                z4 = z6;
                modifier3 = modifier4;
                function5 = function7;
                datePickerColors3 = datePickerColors4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                datePickerFormatter2 = datePickerFormatter;
                focusRequester2 = focusRequester;
                modifier3 = modifier2;
                datePickerColors3 = datePickerColors2;
                function5 = function2RememberComposableLambda;
                z4 = z2;
            }
            function6 = function4;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                    public final Object invoke(Object obj, Object obj2) {
                        return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        function4 = function3;
        i8 = i2 & 64;
        if (i8 != 0) {
            if ((1572864 & i) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(focusRequester)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                            public final void invoke(Composer composer3, int i111118) {
                                if (!composer3.shouldExecute((i111118 & 3) != 2, i111118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1655706771, i111118, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                }
                                DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                            public final void invoke(Composer composer3, int i111118) {
                                if (!composer3.shouldExecute((i111118 & 3) != 2, i111118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1439279037, i111118, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                }
                                DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i111118 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i111118;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                            public final void invoke(Composer composer3, int i111119) {
                                if (!composer3.shouldExecute((i111119 & 3) != 2, i111119 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1655706771, i111119, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                }
                                DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                            public final void invoke(Composer composer3, int i111119) {
                                if (!composer3.shouldExecute((i111119 & 3) != 2, i111119 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1439279037, i111119, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                                }
                                DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i111119 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i111119;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
                }
                zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    if (datePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    if (datePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                CalendarModel calendarModel15 = (CalendarModel) objRememberedValue3;
                if (z6) {
                    composerStartRestartGroup.startReplaceGroup(-690551113);
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-690163489);
                    composerStartRestartGroup.endReplaceGroup();
                    composableLambdaRememberComposableLambda = null;
                }
                ComposableLambda composableLambda15 = composableLambdaRememberComposableLambda;
                DatePickerModalTokens datePickerModalTokens15 = DatePickerModalTokens.INSTANCE;
                TextStyle value15 = TypographyKt.getValue(datePickerModalTokens15.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
                float fM1703getHeaderContainerHeightD9Ej5fM15 = datePickerModalTokens15.m1703getHeaderContainerHeightD9Ej5fM();
                FocusRequester focusRequester18 = focusRequester3;
                AnonymousClass6 anonymousClass110 = new AnonymousClass6(datePickerState, calendarModel15, datePickerFormatter3, datePickerColors4, focusRequester18);
                DatePickerFormatter datePickerFormatter18 = datePickerFormatter3;
                int i1111110 = i13 >> 9;
                composer2 = composerStartRestartGroup;
                m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda15, datePickerColors4, value15, fM1703getHeaderContainerHeightD9Ej5fM15, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass110, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1111110 & 112) | (i1111110 & 896) | ((i13 << 3) & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter2 = datePickerFormatter18;
                focusRequester2 = focusRequester18;
                z4 = z6;
                modifier3 = modifier4;
                function5 = function7;
                datePickerColors3 = datePickerColors4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                datePickerFormatter2 = datePickerFormatter;
                focusRequester2 = focusRequester;
                modifier3 = modifier2;
                datePickerColors3 = datePickerColors2;
                function5 = function2RememberComposableLambda;
                z4 = z2;
            }
            function6 = function4;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                    public final Object invoke(Object obj, Object obj2) {
                        return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 1572864;
        z2 = z;
        i10 = i2 & 128;
        if (i10 != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(focusRequester)) {
                i11 = 8388608;
            } else {
                i11 = 4194304;
            }
            i3 |= i11;
        }
        if ((i3 & 4793491) != 4793490) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i15 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                    i3 &= -897;
                } else {
                    datePickerFormatter3 = datePickerFormatter;
                }
                if ((i2 & 8) != 0) {
                    datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i3 &= -7169;
                } else {
                    datePickerColorsColors = datePickerColors2;
                }
                if (i4 != 0) {
                    z5 = true;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                        public final void invoke(Composer composer3, int i1111111) {
                            if (!composer3.shouldExecute((i1111111 & 3) != 2, i1111111 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1655706771, i1111111, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                            }
                            DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    i12 = 54;
                } else {
                    z5 = true;
                    i12 = 54;
                }
                if (i6 != 0) {
                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                        public final void invoke(Composer composer3, int i1111111) {
                            if (!composer3.shouldExecute((i1111111 & 3) != 2, i1111111 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1439279037, i1111111, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                            }
                            DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, i12);
                } else {
                    function2RememberComposableLambda2 = function4;
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new FocusRequester();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    int i1111111 = i3;
                    focusRequester3 = (FocusRequester) objRememberedValue;
                    z6 = z2;
                    datePickerColors4 = datePickerColorsColors;
                    i13 = i1111111;
                    function4 = function2RememberComposableLambda2;
                    function7 = function2RememberComposableLambda;
                    modifier4 = modifier2;
                } else {
                    function4 = function2RememberComposableLambda2;
                    function7 = function2RememberComposableLambda;
                    z6 = z2;
                    datePickerColors4 = datePickerColorsColors;
                    modifier4 = modifier2;
                    i13 = i3;
                    focusRequester3 = focusRequester;
                }
            } else {
                if (i15 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                    i3 &= -897;
                } else {
                    datePickerFormatter3 = datePickerFormatter;
                }
                if ((i2 & 8) != 0) {
                    datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i3 &= -7169;
                } else {
                    datePickerColorsColors = datePickerColors2;
                }
                if (i4 != 0) {
                    z5 = true;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1655706771, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                        public final void invoke(Composer composer3, int i1111112) {
                            if (!composer3.shouldExecute((i1111112 & 3) != 2, i1111112 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1655706771, i1111112, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                            }
                            DatePickerDefaults.INSTANCE.m359DatePickerTitleFNtVw6o(datePickerState.mo372getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    i12 = 54;
                } else {
                    z5 = true;
                    i12 = 54;
                }
                if (i6 != 0) {
                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1439279037, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
                        public final void invoke(Composer composer3, int i1111112) {
                            if (!composer3.shouldExecute((i1111112 & 3) != 2, i1111112 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1439279037, i1111112, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
                            }
                            DatePickerDefaults.INSTANCE.m358DatePickerHeadlineISIPfiY(datePickerState.getSelectedDateMillis(), datePickerState.mo372getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 199680, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, i12);
                } else {
                    function2RememberComposableLambda2 = function4;
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new FocusRequester();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    int i1111112 = i3;
                    focusRequester3 = (FocusRequester) objRememberedValue;
                    z6 = z2;
                    datePickerColors4 = datePickerColorsColors;
                    i13 = i1111112;
                    function4 = function2RememberComposableLambda2;
                    function7 = function2RememberComposableLambda;
                    modifier4 = modifier2;
                } else {
                    function4 = function2RememberComposableLambda2;
                    function7 = function2RememberComposableLambda;
                    z6 = z2;
                    datePickerColors4 = datePickerColorsColors;
                    modifier4 = modifier2;
                    i13 = i3;
                    focusRequester3 = focusRequester;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1105472031, i13, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:190)");
            }
            zChanged = composerStartRestartGroup.changed(datePickerState.getLocale());
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                if (datePickerState instanceof BaseDatePickerStateImpl) {
                    calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                } else {
                    calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                }
                objRememberedValue3 = calendarModelCreateCalendarModel;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                if (datePickerState instanceof BaseDatePickerStateImpl) {
                    calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) datePickerState).getCalendarModel();
                } else {
                    calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(datePickerState.getLocale());
                }
                objRememberedValue3 = calendarModelCreateCalendarModel;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            CalendarModel calendarModel16 = (CalendarModel) objRememberedValue3;
            if (z6) {
                composerStartRestartGroup.startReplaceGroup(-690551113);
                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1483431603, true, new AnonymousClass5(datePickerState, datePickerColors4), composerStartRestartGroup, 54);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-690163489);
                composerStartRestartGroup.endReplaceGroup();
                composableLambdaRememberComposableLambda = null;
            }
            ComposableLambda composableLambda16 = composableLambdaRememberComposableLambda;
            DatePickerModalTokens datePickerModalTokens16 = DatePickerModalTokens.INSTANCE;
            TextStyle value16 = TypographyKt.getValue(datePickerModalTokens16.getHeaderHeadlineFont(), composerStartRestartGroup, 6);
            float fM1703getHeaderContainerHeightD9Ej5fM16 = datePickerModalTokens16.m1703getHeaderContainerHeightD9Ej5fM();
            FocusRequester focusRequester19 = focusRequester3;
            AnonymousClass6 anonymousClass111 = new AnonymousClass6(datePickerState, calendarModel16, datePickerFormatter3, datePickerColors4, focusRequester19);
            DatePickerFormatter datePickerFormatter19 = datePickerFormatter3;
            int i1111113 = i13 >> 9;
            composer2 = composerStartRestartGroup;
            m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda16, datePickerColors4, value16, fM1703getHeaderContainerHeightD9Ej5fM16, ComposableLambdaKt.rememberComposableLambda(-1346903698, true, anonymousClass111, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1111113 & 112) | (i1111113 & 896) | ((i13 << 3) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            datePickerFormatter2 = datePickerFormatter19;
            focusRequester2 = focusRequester19;
            z4 = z6;
            modifier3 = modifier4;
            function5 = function7;
            datePickerColors3 = datePickerColors4;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            datePickerFormatter2 = datePickerFormatter;
            focusRequester2 = focusRequester;
            modifier3 = modifier2;
            datePickerColors3 = datePickerColors2;
            function5 = function2RememberComposableLambda;
            z4 = z2;
        }
        function6 = function4;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: c93
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.D(datePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DatePickerContent(final Long l, final long j, final Function1<? super Long, Unit> function1, final Function1<? super Long, Unit> function2, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-434467002);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(calendarModel) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(intRange) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= (2097152 & i) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(selectableDates) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (composerStartRestartGroup.shouldExecute((38347923 & i2) != 38347922, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-434467002, i2, -1, "androidx.compose.material3.DatePickerContent (DatePicker.kt:1537)");
            }
            CalendarMonth month = calendarModel.getMonth(j);
            int iCoerceAtLeast = RangesKt.coerceAtLeast(month.indexIn(intRange), 0);
            final LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(iCoerceAtLeast, 0, composerStartRestartGroup, 0, 2);
            Integer numValueOf = Integer.valueOf(iCoerceAtLeast);
            boolean zChanged = composerStartRestartGroup.changed(lazyListStateRememberLazyListState) | composerStartRestartGroup.changed(iCoerceAtLeast);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DatePickerKt$DatePickerContent$1$1(lazyListStateRememberLazyListState, iCoerceAtLeast, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            EffectsKt.LaunchedEffect(numValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue, composerStartRestartGroup, 0);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue2;
            Object[] objArr = new Object[0];
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: w93
                    public final Object invoke() {
                        return DatePickerKt.F();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState = (MutableState) RememberSaveableKt.rememberSaveable(objArr, (Function0) objRememberedValue3, composerStartRestartGroup, 48);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.Vertical top = arrangement.getTop();
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(top, companion3.getStart(), composerStartRestartGroup, 0);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            int i3 = i2;
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion4.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion4.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion4.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion4.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            float f = DatePickerHorizontalPadding;
            Modifier modifier = PaddingKt.padding-VpY3zN4$default(companion2, f, 0.0f, 2, (Object) null);
            boolean canScrollForward = lazyListStateRememberLazyListState.getCanScrollForward();
            boolean canScrollBackward = lazyListStateRememberLazyListState.getCanScrollBackward();
            boolean zDatePickerContent$lambda$26 = DatePickerContent$lambda$26(mutableState);
            String monthYear = datePickerFormatter.formatMonthYear(Long.valueOf(j), calendarModel.getLocale());
            if (monthYear == null) {
                monthYear = "-";
            }
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changed(lazyListStateRememberLazyListState);
            String str = monthYear;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: x93
                    public final Object invoke() {
                        return DatePickerKt.r(coroutineScope, lazyListStateRememberLazyListState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            Function0 function0 = (Function0) objRememberedValue4;
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changed(lazyListStateRememberLazyListState);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: z93
                    public final Object invoke() {
                        return DatePickerKt.B(coroutineScope, lazyListStateRememberLazyListState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            Function0 function3 = (Function0) objRememberedValue5;
            boolean zChanged2 = composerStartRestartGroup.changed(mutableState);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue6 == companion.getEmpty()) {
                objRememberedValue6 = new Function0() { // from class: aa3
                    public final Object invoke() {
                        return DatePickerKt.x(mutableState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            Function0 function4 = (Function0) objRememberedValue6;
            int i4 = i3 & 234881024;
            MonthsNavigation(modifier, canScrollForward, canScrollBackward, zDatePickerContent$lambda$26, str, function0, function3, function4, datePickerColors, composerStartRestartGroup, i4 | 6);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
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
            Modifier modifier2 = PaddingKt.padding-VpY3zN4$default(companion2, f, 0.0f, 2, (Object) null);
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(arrangement.getTop(), companion3.getStart(), composerStartRestartGroup, 0);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
            Function0<ComposeUiNode> constructor3 = companion4.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyColumnMeasurePolicy2, companion4.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion4.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = companion4.getSetCompositeKeyHash();
            if (composerM2388constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion4.getSetModifier());
            WeekDays(datePickerColors, calendarModel, composerStartRestartGroup, ((i3 >> 24) & 14) | ((i3 >> 9) & 112));
            HorizontalMonthsList(lazyListStateRememberLazyListState, l, function1, function2, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composerStartRestartGroup, ((i3 << 3) & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | (i3 & 29360128) | i4);
            composerStartRestartGroup.endNode();
            MotionSchemeKeyTokens motionSchemeKeyTokens = MotionSchemeKeyTokens.DefaultEffects;
            FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6);
            FiniteAnimationSpec finiteAnimationSpecValue2 = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, composerStartRestartGroup, 6);
            FiniteAnimationSpec finiteAnimationSpecValue3 = MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6);
            AnimatedVisibilityKt.AnimatedVisibility(DatePickerContent$lambda$26(mutableState), ClipKt.clipToBounds(companion2), EnterExitTransitionKt.expandVertically$default(finiteAnimationSpecValue3, (Alignment.Vertical) null, false, (Function1) null, 14, (Object) null).plus(EnterExitTransitionKt.fadeIn(finiteAnimationSpecValue, 0.6f)), EnterExitTransitionKt.shrinkVertically$default(finiteAnimationSpecValue3, (Alignment.Vertical) null, false, (Function1) null, 14, (Object) null).plus(EnterExitTransitionKt.fadeOut$default(finiteAnimationSpecValue2, 0.0f, 2, (Object) null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1193716082, true, new DatePickerKt$DatePickerContent$2$4$2(j, mutableState, coroutineScope, lazyListStateRememberLazyListState, intRange, month, selectableDates, calendarModel, datePickerColors), composerStartRestartGroup, 54), composerStartRestartGroup, 196656, 16);
            composerStartRestartGroup = composerStartRestartGroup;
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ba3
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.l(l, j, function1, function2, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DatePickerContent$lambda$26(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DatePickerContent$lambda$27(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: DatePickerHeader-pc5RIQQ, reason: not valid java name */
    public static final void m364DatePickerHeaderpc5RIQQ(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final long j, final long j2, final float f, final Function2<? super Composer, ? super Integer, Unit> function3, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2020490761);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(j2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(f) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 131072 : 65536;
        }
        if (composerStartRestartGroup.shouldExecute((74899 & i2) != 74898, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2020490761, i2, -1, "androidx.compose.material3.DatePickerHeader (DatePicker.kt:1677)");
            }
            Modifier modifierThen = SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, (Object) null).then(function2 != null ? SizeKt.defaultMinSize-VpY3zN4$default(Modifier.INSTANCE, 0.0f, f, 1, (Object) null) : Modifier.INSTANCE);
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getSpaceBetween(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierThen);
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            if (function2 != null) {
                composerStartRestartGroup.startReplaceGroup(396894187);
                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(j, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getHeaderSupportingTextFont(), composerStartRestartGroup, 6), ComposableLambdaKt.rememberComposableLambda(1344395458, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerHeader$1$1
                    public final void invoke(Composer composer2, int i3) {
                        if (!composer2.shouldExecute((i3 & 3) != 2, i3 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1344395458, i3, -1, "androidx.compose.material3.DatePickerHeader.<anonymous>.<anonymous> (DatePicker.kt:1692)");
                        }
                        Alignment bottomStart = Alignment.INSTANCE.getBottomStart();
                        Function2<Composer, Integer, Unit> function4 = function2;
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(bottomStart, false);
                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, companion2);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor2 = companion3.getConstructor();
                        if (composer2.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor2);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composer2);
                        Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        function4.invoke(composer2, 0);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i2 >> 6) & 14) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(397163267);
                composerStartRestartGroup.endReplaceGroup();
            }
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(j2)), function3, composerStartRestartGroup, ProvidedValue.$stable | ((i2 >> 12) & 112));
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: e93
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.w(modifier, function2, j, j2, f, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: DatePickerState-sHin3Bw, reason: not valid java name */
    public static final DatePickerState m365DatePickerStatesHin3Bw(Locale locale, Long l, Long l2, IntRange intRange, int i, SelectableDates selectableDates) {
        return new DatePickerStateImpl(l, l2, intRange, i, selectableDates, locale, null);
    }

    /* JADX INFO: renamed from: DatePickerState-sHin3Bw$default, reason: not valid java name */
    public static /* synthetic */ DatePickerState m366DatePickerStatesHin3Bw$default(Locale locale, Long l, Long l2, IntRange intRange, int i, SelectableDates selectableDates, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            l = null;
        }
        if ((i2 & 4) != 0) {
            l2 = l;
        }
        if ((i2 & 8) != 0) {
            intRange = DatePickerDefaults.INSTANCE.getYearRange();
        }
        if ((i2 & 16) != 0) {
            i = DisplayMode.INSTANCE.m412getPickerjFl4v0();
        }
        if ((i2 & 32) != 0) {
            selectableDates = DatePickerDefaults.INSTANCE.getAllDates();
        }
        SelectableDates selectableDates2 = selectableDates;
        IntRange intRange2 = intRange;
        return m365DatePickerStatesHin3Bw(locale, l, l2, intRange2, i, selectableDates2);
    }

    private static final void Day(final String str, final Modifier modifier, final boolean z, final Function0<Unit> function0, final boolean z2, final boolean z3, final boolean z4, final boolean z5, final String str2, final DatePickerColors datePickerColors, Composer composer, final int i) {
        int i2;
        boolean z6;
        boolean z7;
        boolean z8;
        DatePickerColors datePickerColors2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-945355136);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            z6 = z2;
            i2 |= composerStartRestartGroup.changed(z6) ? 16384 : 8192;
        } else {
            z6 = z2;
        }
        if ((196608 & i) == 0) {
            z7 = z3;
            i2 |= composerStartRestartGroup.changed(z7) ? 131072 : 65536;
        } else {
            z7 = z3;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(z4) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            z8 = z5;
            i2 |= composerStartRestartGroup.changed(z8) ? 8388608 : 4194304;
        } else {
            z8 = z5;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(str2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & i) == 0) {
            datePickerColors2 = datePickerColors;
            i2 |= composerStartRestartGroup.changed(datePickerColors2) ? 536870912 : 268435456;
        } else {
            datePickerColors2 = datePickerColors;
        }
        if (composerStartRestartGroup.shouldExecute((306783379 & i2) != 306783378, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-945355136, i2, -1, "androidx.compose.material3.Day (DatePicker.kt:2003)");
            }
            boolean z9 = (234881024 & i2) == 67108864;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z9 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: g93
                    public final Object invoke(Object obj) {
                        return DatePickerKt.t(str2, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier, true, (Function1) objRememberedValue);
            DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
            int i3 = i2 >> 6;
            SurfaceKt.m955Surfaced85dljk(z, function0, modifierSemantics, z3, ShapesKt.getValue(datePickerModalTokens.getDateContainerShape(), composerStartRestartGroup, 6), datePickerColors2.dayContainerColor$material3(z, z7, z6, composerStartRestartGroup, (i3 & 14) | ((i2 >> 12) & 112) | (i3 & 896) | ((i2 >> 18) & V4Signature.MAX_SIGNING_INFOS_SIZE)).getValue().m3144unboximpl(), 0L, 0.0f, 0.0f, (!z4 || z) ? null : BorderStrokeKt.m12BorderStrokecXLIe8U(datePickerModalTokens.m1702getDateTodayContainerOutlineWidthD9Ej5fM(), datePickerColors.getTodayDateBorderColor()), (MutableInteractionSource) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1126347158, true, new C00402(str, datePickerColors, z4, z, z8, z3), composerStartRestartGroup, 54), composerStartRestartGroup, i3 & 7294, 48, 1472);
            composerStartRestartGroup = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: h93
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.g(str, modifier, z, function0, z2, z3, z4, z5, str2, datePickerColors, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: DisplayModeToggleButton-iUJLfQg, reason: not valid java name */
    public static final void m367DisplayModeToggleButtoniUJLfQg(final Modifier modifier, final int i, final Function1<? super DisplayMode, Unit> function1, final DatePickerColors datePickerColors, Composer composer, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1461252485);
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(i) ? 32 : 16;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= composerStartRestartGroup.changed(datePickerColors) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1461252485, i3, -1, "androidx.compose.material3.DisplayModeToggleButton (DatePicker.kt:1406)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(datePickerColors.getHeadlineContentColor())), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1734512197, true, new DatePickerKt$DisplayModeToggleButton$1(i, function1, modifier), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: v93
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.i(modifier, i, function1, datePickerColors, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit E(LazyListState lazyListState, Long l, Function1 function1, Function1 function2, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        HorizontalMonthsList(lazyListState, l, function1, function2, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static MutableState F() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
    }

    public static Unit G(DatePickerColors datePickerColors, CalendarModel calendarModel, int i, Composer composer, int i2) {
        WeekDays(datePickerColors, calendarModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit H(Modifier modifier, Function2 function2, Function2 function3, Function2 function4, DatePickerColors datePickerColors, TextStyle textStyle, float f, Function2 function5, int i, Composer composer, int i2) {
        m363DateEntryContainerau3_HiA(modifier, function2, function3, function4, datePickerColors, textStyle, f, function5, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final void HorizontalMonthsList(LazyListState lazyListState, final Long l, final Function1<? super Long, Unit> function1, final Function1<? super Long, Unit> function2, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, Composer composer, final int i) {
        int i2;
        Long l2;
        Function1<? super Long, Unit> function3;
        SelectableDates selectableDates2;
        DatePickerColors datePickerColors2;
        Object datePickerKt$HorizontalMonthsList$2$1;
        final LazyListState lazyListState2 = lazyListState;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1994757941);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(lazyListState2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            l2 = l;
            i2 |= composerStartRestartGroup.changed(l2) ? 32 : 16;
        } else {
            l2 = l;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            function3 = function1;
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 256 : 128;
        } else {
            function3 = function1;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(calendarModel) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(intRange) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= (2097152 & i) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            selectableDates2 = selectableDates;
            i2 |= composerStartRestartGroup.changed(selectableDates2) ? 8388608 : 4194304;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((100663296 & i) == 0) {
            datePickerColors2 = datePickerColors;
            i2 |= composerStartRestartGroup.changed(datePickerColors2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            datePickerColors2 = datePickerColors;
        }
        if (composerStartRestartGroup.shouldExecute((38347923 & i2) != 38347922, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1994757941, i2, -1, "androidx.compose.material3.HorizontalMonthsList (DatePicker.kt:1711)");
            }
            CalendarDate today = calendarModel.getToday();
            boolean zChanged = composerStartRestartGroup.changed(intRange);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = calendarModel.getMonth(intRange.getFirst(), 1);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            int i3 = i2;
            TextKt.ProvideTextStyle(TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getDateLabelTextFont(), composerStartRestartGroup, 6), ComposableLambdaKt.rememberComposableLambda(1504086906, true, new AnonymousClass1(lazyListState2, intRange, calendarModel, (CalendarMonth) objRememberedValue, function3, today, l2, datePickerFormatter, selectableDates2, datePickerColors2), composerStartRestartGroup, 54), composerStartRestartGroup, 48);
            int i4 = i3 & 14;
            boolean zChangedInstance = (i4 == 4) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) | composerStartRestartGroup.changedInstance(calendarModel) | composerStartRestartGroup.changedInstance(intRange);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                lazyListState2 = lazyListState;
                datePickerKt$HorizontalMonthsList$2$1 = new DatePickerKt$HorizontalMonthsList$2$1(lazyListState2, function2, calendarModel, intRange, null);
                composerStartRestartGroup.updateRememberedValue(datePickerKt$HorizontalMonthsList$2$1);
            } else {
                datePickerKt$HorizontalMonthsList$2$1 = objRememberedValue2;
                lazyListState2 = lazyListState;
            }
            EffectsKt.LaunchedEffect(lazyListState2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) datePickerKt$HorizontalMonthsList$2$1, composerStartRestartGroup, i4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s93
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.E(lazyListState2, l, function1, function2, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:26:0x004a  */
    /* JADX WARN: Code duplicated, block: B:27:0x004d  */
    /* JADX WARN: Code duplicated, block: B:29:0x0051  */
    /* JADX WARN: Code duplicated, block: B:31:0x0057  */
    /* JADX WARN: Code duplicated, block: B:32:0x005a  */
    /* JADX WARN: Code duplicated, block: B:36:0x0061  */
    /* JADX WARN: Code duplicated, block: B:38:0x0066  */
    /* JADX WARN: Code duplicated, block: B:40:0x006a  */
    /* JADX WARN: Code duplicated, block: B:42:0x0072  */
    /* JADX WARN: Code duplicated, block: B:43:0x0075  */
    /* JADX WARN: Code duplicated, block: B:47:0x007c  */
    /* JADX WARN: Code duplicated, block: B:49:0x0081  */
    /* JADX WARN: Code duplicated, block: B:51:0x0085  */
    /* JADX WARN: Code duplicated, block: B:53:0x008d  */
    /* JADX WARN: Code duplicated, block: B:54:0x0090  */
    /* JADX WARN: Code duplicated, block: B:58:0x009a  */
    /* JADX WARN: Code duplicated, block: B:59:0x009c  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:64:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:67:0x00af  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:73:0x0110  */
    /* JADX WARN: Code duplicated, block: B:75:0x0116  */
    /* JADX WARN: Code duplicated, block: B:78:0x0121  */
    /* JADX WARN: Code duplicated, block: B:80:? A[RETURN, SYNTHETIC] */
    public static final void IconButtonWithTooltip(final Function0<Unit> function0, final ImageVector imageVector, final String str, Modifier modifier, boolean z, Composer composer, final int i, final int i2) {
        final Function0<Unit> function1;
        int i3;
        final ImageVector imageVector2;
        int i4;
        int i5;
        Modifier modifier2;
        int i6;
        int i7;
        boolean z2;
        int i8;
        boolean z3;
        final Modifier modifier3;
        final boolean z4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        boolean z5;
        Composer composerStartRestartGroup = composer.startRestartGroup(-368059805);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            function1 = function0;
        } else if ((i & 6) == 0) {
            function1 = function0;
            i3 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i;
        } else {
            function1 = function0;
            i3 = i;
        }
        if ((i2 & 2) == 0) {
            if ((i & 48) == 0) {
                imageVector2 = imageVector;
                i3 |= composerStartRestartGroup.changed(imageVector2) ? 32 : 16;
            }
            if ((i2 & 4) != 0) {
                i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            } else if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if (composerStartRestartGroup.changed(str)) {
                    i4 = 256;
                } else {
                    i4 = 128;
                }
                i3 |= i4;
            }
            i5 = i2 & 8;
            if (i5 != 0) {
                if ((i & 3072) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i3 |= i6;
                }
                i7 = i2 & 16;
                if (i7 != 0) {
                    if ((i & 24576) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 9363) != 9362) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        if (i5 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i7 != 0) {
                            z5 = true;
                        } else {
                            z5 = z2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-368059805, i3, -1, "androidx.compose.material3.IconButtonWithTooltip (DatePicker.kt:2279)");
                        }
                        final Modifier modifier5 = modifier4;
                        final boolean z6 = z5;
                        TooltipKt.TooltipBox(TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2), ComposableLambdaKt.rememberComposableLambda(-456272562, true, new Function3<TooltipScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1
                            public final void invoke(TooltipScope tooltipScope, Composer composer2, int i9) {
                                int i10;
                                if ((i9 & 6) == 0) {
                                    i10 = i9 | ((i9 & 8) == 0 ? composer2.changed(tooltipScope) : composer2.changedInstance(tooltipScope) ? 4 : 2);
                                } else {
                                    i10 = i9;
                                }
                                if (!composer2.shouldExecute((i10 & 19) != 18, i10 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-456272562, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2283)");
                                }
                                final String str2 = str;
                                TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, null, null, 0.0f, null, 0L, 0L, 0.0f, 0.0f, ComposableLambdaKt.rememberComposableLambda(1905952188, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1.1
                                    public final void invoke(Composer composer3, int i11) {
                                        if (!composer3.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1905952188, i11, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2283)");
                                        }
                                        TextKt.m1097TextNvy7gAk(str2, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer2, 54), composer2, (i10 & 14) | 805306368, 255);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(TooltipScope tooltipScope, Composer composer2, Integer num) {
                                invoke(tooltipScope, composer2, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), TooltipKt.rememberTooltipState(false, false, null, composerStartRestartGroup, 0, 7), null, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-1124908186, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2
                            public final void invoke(Composer composer2, int i9) {
                                if (!composer2.shouldExecute((i9 & 3) != 2, i9 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1124908186, i9, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2286)");
                                }
                                Function0<Unit> function2 = function1;
                                Modifier modifier6 = modifier5;
                                boolean z7 = z6;
                                final ImageVector imageVector3 = imageVector2;
                                final String str2 = str;
                                IconButtonKt.IconButton(function2, modifier6, z7, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1301085432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2.1
                                    public final void invoke(Composer composer3, int i10) {
                                        if (!composer3.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1301085432, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2287)");
                                        }
                                        IconKt.m539Iconww6aTOc(imageVector3, str2, (Modifier) null, 0L, composer3, 0, 12);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composer2, 54), composer2, 1572864, 56);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, 100663344, 248);
                        composerStartRestartGroup = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier5;
                        z4 = z6;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z4 = z2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ea3
                            public final Object invoke(Object obj, Object obj2) {
                                return DatePickerKt.e(function0, imageVector, str, modifier3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                z2 = z;
                if ((i3 & 9363) != 9362) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i7 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-368059805, i3, -1, "androidx.compose.material3.IconButtonWithTooltip (DatePicker.kt:2279)");
                    }
                    final Modifier modifier6 = modifier4;
                    final boolean z7 = z5;
                    TooltipKt.TooltipBox(TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2), ComposableLambdaKt.rememberComposableLambda(-456272562, true, new Function3<TooltipScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1
                        public final void invoke(TooltipScope tooltipScope, Composer composer2, int i9) {
                            int i10;
                            if ((i9 & 6) == 0) {
                                i10 = i9 | ((i9 & 8) == 0 ? composer2.changed(tooltipScope) : composer2.changedInstance(tooltipScope) ? 4 : 2);
                            } else {
                                i10 = i9;
                            }
                            if (!composer2.shouldExecute((i10 & 19) != 18, i10 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-456272562, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2283)");
                            }
                            final String str2 = str;
                            TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, null, null, 0.0f, null, 0L, 0L, 0.0f, 0.0f, ComposableLambdaKt.rememberComposableLambda(1905952188, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1.1
                                public final void invoke(Composer composer3, int i11) {
                                    if (!composer3.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1905952188, i11, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2283)");
                                    }
                                    TextKt.m1097TextNvy7gAk(str2, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer2, 54), composer2, (i10 & 14) | 805306368, 255);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(TooltipScope tooltipScope, Composer composer2, Integer num) {
                            invoke(tooltipScope, composer2, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), TooltipKt.rememberTooltipState(false, false, null, composerStartRestartGroup, 0, 7), null, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-1124908186, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2
                        public final void invoke(Composer composer2, int i9) {
                            if (!composer2.shouldExecute((i9 & 3) != 2, i9 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1124908186, i9, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2286)");
                            }
                            Function0<Unit> function2 = function1;
                            Modifier modifier7 = modifier6;
                            boolean z8 = z7;
                            final ImageVector imageVector3 = imageVector2;
                            final String str2 = str;
                            IconButtonKt.IconButton(function2, modifier7, z8, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1301085432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2.1
                                public final void invoke(Composer composer3, int i10) {
                                    if (!composer3.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1301085432, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2287)");
                                    }
                                    IconKt.m539Iconww6aTOc(imageVector3, str2, (Modifier) null, 0L, composer3, 0, 12);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer2, 54), composer2, 1572864, 56);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, 100663344, 248);
                    composerStartRestartGroup = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier6;
                    z4 = z7;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z4 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ea3
                        public final Object invoke(Object obj, Object obj2) {
                            return DatePickerKt.e(function0, imageVector, str, modifier3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            modifier2 = modifier;
            i7 = i2 & 16;
            if (i7 != 0) {
                if ((i & 24576) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) != 9362) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i7 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-368059805, i3, -1, "androidx.compose.material3.IconButtonWithTooltip (DatePicker.kt:2279)");
                    }
                    final Modifier modifier7 = modifier4;
                    final boolean z8 = z5;
                    TooltipKt.TooltipBox(TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2), ComposableLambdaKt.rememberComposableLambda(-456272562, true, new Function3<TooltipScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1
                        public final void invoke(TooltipScope tooltipScope, Composer composer2, int i9) {
                            int i10;
                            if ((i9 & 6) == 0) {
                                i10 = i9 | ((i9 & 8) == 0 ? composer2.changed(tooltipScope) : composer2.changedInstance(tooltipScope) ? 4 : 2);
                            } else {
                                i10 = i9;
                            }
                            if (!composer2.shouldExecute((i10 & 19) != 18, i10 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-456272562, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2283)");
                            }
                            final String str2 = str;
                            TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, null, null, 0.0f, null, 0L, 0L, 0.0f, 0.0f, ComposableLambdaKt.rememberComposableLambda(1905952188, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1.1
                                public final void invoke(Composer composer3, int i11) {
                                    if (!composer3.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1905952188, i11, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2283)");
                                    }
                                    TextKt.m1097TextNvy7gAk(str2, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer2, 54), composer2, (i10 & 14) | 805306368, 255);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(TooltipScope tooltipScope, Composer composer2, Integer num) {
                            invoke(tooltipScope, composer2, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), TooltipKt.rememberTooltipState(false, false, null, composerStartRestartGroup, 0, 7), null, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-1124908186, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2
                        public final void invoke(Composer composer2, int i9) {
                            if (!composer2.shouldExecute((i9 & 3) != 2, i9 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1124908186, i9, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2286)");
                            }
                            Function0<Unit> function2 = function1;
                            Modifier modifier8 = modifier7;
                            boolean z9 = z8;
                            final ImageVector imageVector3 = imageVector2;
                            final String str2 = str;
                            IconButtonKt.IconButton(function2, modifier8, z9, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1301085432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2.1
                                public final void invoke(Composer composer3, int i10) {
                                    if (!composer3.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1301085432, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2287)");
                                    }
                                    IconKt.m539Iconww6aTOc(imageVector3, str2, (Modifier) null, 0L, composer3, 0, 12);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer2, 54), composer2, 1572864, 56);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, 100663344, 248);
                    composerStartRestartGroup = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier7;
                    z4 = z8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z4 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ea3
                        public final Object invoke(Object obj, Object obj2) {
                            return DatePickerKt.e(function0, imageVector, str, modifier3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z2 = z;
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i7 != 0) {
                    z5 = true;
                } else {
                    z5 = z2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-368059805, i3, -1, "androidx.compose.material3.IconButtonWithTooltip (DatePicker.kt:2279)");
                }
                final Modifier modifier8 = modifier4;
                final boolean z9 = z5;
                TooltipKt.TooltipBox(TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2), ComposableLambdaKt.rememberComposableLambda(-456272562, true, new Function3<TooltipScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1
                    public final void invoke(TooltipScope tooltipScope, Composer composer2, int i9) {
                        int i10;
                        if ((i9 & 6) == 0) {
                            i10 = i9 | ((i9 & 8) == 0 ? composer2.changed(tooltipScope) : composer2.changedInstance(tooltipScope) ? 4 : 2);
                        } else {
                            i10 = i9;
                        }
                        if (!composer2.shouldExecute((i10 & 19) != 18, i10 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-456272562, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2283)");
                        }
                        final String str2 = str;
                        TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, null, null, 0.0f, null, 0L, 0L, 0.0f, 0.0f, ComposableLambdaKt.rememberComposableLambda(1905952188, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1.1
                            public final void invoke(Composer composer3, int i11) {
                                if (!composer3.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1905952188, i11, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2283)");
                                }
                                TextKt.m1097TextNvy7gAk(str2, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer2, 54), composer2, (i10 & 14) | 805306368, 255);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(TooltipScope tooltipScope, Composer composer2, Integer num) {
                        invoke(tooltipScope, composer2, num.intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), TooltipKt.rememberTooltipState(false, false, null, composerStartRestartGroup, 0, 7), null, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-1124908186, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2
                    public final void invoke(Composer composer2, int i9) {
                        if (!composer2.shouldExecute((i9 & 3) != 2, i9 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1124908186, i9, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2286)");
                        }
                        Function0<Unit> function2 = function1;
                        Modifier modifier9 = modifier8;
                        boolean z10 = z9;
                        final ImageVector imageVector3 = imageVector2;
                        final String str2 = str;
                        IconButtonKt.IconButton(function2, modifier9, z10, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1301085432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2.1
                            public final void invoke(Composer composer3, int i10) {
                                if (!composer3.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1301085432, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2287)");
                                }
                                IconKt.m539Iconww6aTOc(imageVector3, str2, (Modifier) null, 0L, composer3, 0, 12);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer2, 54), composer2, 1572864, 56);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, 100663344, 248);
                composerStartRestartGroup = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier8;
                z4 = z9;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ea3
                    public final Object invoke(Object obj, Object obj2) {
                        return DatePickerKt.e(function0, imageVector, str, modifier3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        imageVector2 = imageVector;
        if ((i2 & 4) != 0) {
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        } else if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if (composerStartRestartGroup.changed(str)) {
                i4 = 256;
            } else {
                i4 = 128;
            }
            i3 |= i4;
        }
        i5 = i2 & 8;
        if (i5 != 0) {
            if ((i & 3072) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i3 |= i6;
            }
            i7 = i2 & 16;
            if (i7 != 0) {
                if ((i & 24576) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) != 9362) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i7 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-368059805, i3, -1, "androidx.compose.material3.IconButtonWithTooltip (DatePicker.kt:2279)");
                    }
                    final Modifier modifier9 = modifier4;
                    final boolean z10 = z5;
                    TooltipKt.TooltipBox(TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2), ComposableLambdaKt.rememberComposableLambda(-456272562, true, new Function3<TooltipScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1
                        public final void invoke(TooltipScope tooltipScope, Composer composer2, int i9) {
                            int i10;
                            if ((i9 & 6) == 0) {
                                i10 = i9 | ((i9 & 8) == 0 ? composer2.changed(tooltipScope) : composer2.changedInstance(tooltipScope) ? 4 : 2);
                            } else {
                                i10 = i9;
                            }
                            if (!composer2.shouldExecute((i10 & 19) != 18, i10 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-456272562, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2283)");
                            }
                            final String str2 = str;
                            TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, null, null, 0.0f, null, 0L, 0L, 0.0f, 0.0f, ComposableLambdaKt.rememberComposableLambda(1905952188, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1.1
                                public final void invoke(Composer composer3, int i11) {
                                    if (!composer3.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1905952188, i11, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2283)");
                                    }
                                    TextKt.m1097TextNvy7gAk(str2, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer2, 54), composer2, (i10 & 14) | 805306368, 255);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(TooltipScope tooltipScope, Composer composer2, Integer num) {
                            invoke(tooltipScope, composer2, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), TooltipKt.rememberTooltipState(false, false, null, composerStartRestartGroup, 0, 7), null, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-1124908186, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2
                        public final void invoke(Composer composer2, int i9) {
                            if (!composer2.shouldExecute((i9 & 3) != 2, i9 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1124908186, i9, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2286)");
                            }
                            Function0<Unit> function2 = function1;
                            Modifier modifier10 = modifier9;
                            boolean z11 = z10;
                            final ImageVector imageVector3 = imageVector2;
                            final String str2 = str;
                            IconButtonKt.IconButton(function2, modifier10, z11, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1301085432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2.1
                                public final void invoke(Composer composer3, int i10) {
                                    if (!composer3.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1301085432, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2287)");
                                    }
                                    IconKt.m539Iconww6aTOc(imageVector3, str2, (Modifier) null, 0L, composer3, 0, 12);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composer2, 54), composer2, 1572864, 56);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, 100663344, 248);
                    composerStartRestartGroup = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier9;
                    z4 = z10;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z4 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ea3
                        public final Object invoke(Object obj, Object obj2) {
                            return DatePickerKt.e(function0, imageVector, str, modifier3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z2 = z;
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i7 != 0) {
                    z5 = true;
                } else {
                    z5 = z2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-368059805, i3, -1, "androidx.compose.material3.IconButtonWithTooltip (DatePicker.kt:2279)");
                }
                final Modifier modifier10 = modifier4;
                final boolean z11 = z5;
                TooltipKt.TooltipBox(TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2), ComposableLambdaKt.rememberComposableLambda(-456272562, true, new Function3<TooltipScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1
                    public final void invoke(TooltipScope tooltipScope, Composer composer2, int i9) {
                        int i10;
                        if ((i9 & 6) == 0) {
                            i10 = i9 | ((i9 & 8) == 0 ? composer2.changed(tooltipScope) : composer2.changedInstance(tooltipScope) ? 4 : 2);
                        } else {
                            i10 = i9;
                        }
                        if (!composer2.shouldExecute((i10 & 19) != 18, i10 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-456272562, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2283)");
                        }
                        final String str2 = str;
                        TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, null, null, 0.0f, null, 0L, 0L, 0.0f, 0.0f, ComposableLambdaKt.rememberComposableLambda(1905952188, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1.1
                            public final void invoke(Composer composer3, int i11) {
                                if (!composer3.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1905952188, i11, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2283)");
                                }
                                TextKt.m1097TextNvy7gAk(str2, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer2, 54), composer2, (i10 & 14) | 805306368, 255);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(TooltipScope tooltipScope, Composer composer2, Integer num) {
                        invoke(tooltipScope, composer2, num.intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), TooltipKt.rememberTooltipState(false, false, null, composerStartRestartGroup, 0, 7), null, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-1124908186, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2
                    public final void invoke(Composer composer2, int i9) {
                        if (!composer2.shouldExecute((i9 & 3) != 2, i9 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1124908186, i9, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2286)");
                        }
                        Function0<Unit> function2 = function1;
                        Modifier modifier11 = modifier10;
                        boolean z12 = z11;
                        final ImageVector imageVector3 = imageVector2;
                        final String str2 = str;
                        IconButtonKt.IconButton(function2, modifier11, z12, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1301085432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2.1
                            public final void invoke(Composer composer3, int i10) {
                                if (!composer3.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1301085432, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2287)");
                                }
                                IconKt.m539Iconww6aTOc(imageVector3, str2, (Modifier) null, 0L, composer3, 0, 12);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer2, 54), composer2, 1572864, 56);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, 100663344, 248);
                composerStartRestartGroup = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier10;
                z4 = z11;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ea3
                    public final Object invoke(Object obj, Object obj2) {
                        return DatePickerKt.e(function0, imageVector, str, modifier3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        modifier2 = modifier;
        i7 = i2 & 16;
        if (i7 != 0) {
            if ((i & 24576) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i7 != 0) {
                    z5 = true;
                } else {
                    z5 = z2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-368059805, i3, -1, "androidx.compose.material3.IconButtonWithTooltip (DatePicker.kt:2279)");
                }
                final Modifier modifier11 = modifier4;
                final boolean z12 = z5;
                TooltipKt.TooltipBox(TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2), ComposableLambdaKt.rememberComposableLambda(-456272562, true, new Function3<TooltipScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1
                    public final void invoke(TooltipScope tooltipScope, Composer composer2, int i9) {
                        int i10;
                        if ((i9 & 6) == 0) {
                            i10 = i9 | ((i9 & 8) == 0 ? composer2.changed(tooltipScope) : composer2.changedInstance(tooltipScope) ? 4 : 2);
                        } else {
                            i10 = i9;
                        }
                        if (!composer2.shouldExecute((i10 & 19) != 18, i10 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-456272562, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2283)");
                        }
                        final String str2 = str;
                        TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, null, null, 0.0f, null, 0L, 0L, 0.0f, 0.0f, ComposableLambdaKt.rememberComposableLambda(1905952188, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1.1
                            public final void invoke(Composer composer3, int i11) {
                                if (!composer3.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1905952188, i11, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2283)");
                                }
                                TextKt.m1097TextNvy7gAk(str2, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer2, 54), composer2, (i10 & 14) | 805306368, 255);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(TooltipScope tooltipScope, Composer composer2, Integer num) {
                        invoke(tooltipScope, composer2, num.intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), TooltipKt.rememberTooltipState(false, false, null, composerStartRestartGroup, 0, 7), null, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-1124908186, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2
                    public final void invoke(Composer composer2, int i9) {
                        if (!composer2.shouldExecute((i9 & 3) != 2, i9 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1124908186, i9, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2286)");
                        }
                        Function0<Unit> function2 = function1;
                        Modifier modifier12 = modifier11;
                        boolean z13 = z12;
                        final ImageVector imageVector3 = imageVector2;
                        final String str2 = str;
                        IconButtonKt.IconButton(function2, modifier12, z13, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1301085432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2.1
                            public final void invoke(Composer composer3, int i10) {
                                if (!composer3.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1301085432, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2287)");
                                }
                                IconKt.m539Iconww6aTOc(imageVector3, str2, (Modifier) null, 0L, composer3, 0, 12);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer2, 54), composer2, 1572864, 56);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, 100663344, 248);
                composerStartRestartGroup = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier11;
                z4 = z12;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ea3
                    public final Object invoke(Object obj, Object obj2) {
                        return DatePickerKt.e(function0, imageVector, str, modifier3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        z2 = z;
        if ((i3 & 9363) != 9362) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            if (i5 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i7 != 0) {
                z5 = true;
            } else {
                z5 = z2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-368059805, i3, -1, "androidx.compose.material3.IconButtonWithTooltip (DatePicker.kt:2279)");
            }
            final Modifier modifier12 = modifier4;
            final boolean z13 = z5;
            TooltipKt.TooltipBox(TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2), ComposableLambdaKt.rememberComposableLambda(-456272562, true, new Function3<TooltipScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1
                public final void invoke(TooltipScope tooltipScope, Composer composer2, int i9) {
                    int i10;
                    if ((i9 & 6) == 0) {
                        i10 = i9 | ((i9 & 8) == 0 ? composer2.changed(tooltipScope) : composer2.changedInstance(tooltipScope) ? 4 : 2);
                    } else {
                        i10 = i9;
                    }
                    if (!composer2.shouldExecute((i10 & 19) != 18, i10 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-456272562, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2283)");
                    }
                    final String str2 = str;
                    TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, null, null, 0.0f, null, 0L, 0L, 0.0f, 0.0f, ComposableLambdaKt.rememberComposableLambda(1905952188, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1.1
                        public final void invoke(Composer composer3, int i11) {
                            if (!composer3.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1905952188, i11, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2283)");
                            }
                            TextKt.m1097TextNvy7gAk(str2, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composer2, 54), composer2, (i10 & 14) | 805306368, 255);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(TooltipScope tooltipScope, Composer composer2, Integer num) {
                    invoke(tooltipScope, composer2, num.intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), TooltipKt.rememberTooltipState(false, false, null, composerStartRestartGroup, 0, 7), null, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-1124908186, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2
                public final void invoke(Composer composer2, int i9) {
                    if (!composer2.shouldExecute((i9 & 3) != 2, i9 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1124908186, i9, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2286)");
                    }
                    Function0<Unit> function2 = function1;
                    Modifier modifier13 = modifier12;
                    boolean z14 = z13;
                    final ImageVector imageVector3 = imageVector2;
                    final String str2 = str;
                    IconButtonKt.IconButton(function2, modifier13, z14, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1301085432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2.1
                        public final void invoke(Composer composer3, int i10) {
                            if (!composer3.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1301085432, i10, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2287)");
                            }
                            IconKt.m539Iconww6aTOc(imageVector3, str2, (Modifier) null, 0L, composer3, 0, 12);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composer2, 54), composer2, 1572864, 56);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 100663344, 248);
            composerStartRestartGroup = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier12;
            z4 = z13;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            z4 = z2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ea3
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.e(function0, imageVector, str, modifier3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:175:0x032d  */
    public static final void Month(final CalendarMonth calendarMonth, final Function1<? super Long, Unit> function1, final long j, final Long l, final Long l2, final SelectedRangeInfo selectedRangeInfo, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, final Locale locale, Composer composer, final int i) {
        CalendarMonth calendarMonth2;
        int i2;
        Composer composer2;
        Modifier modifierDrawWithContent;
        int i3;
        int i4;
        Composer composer3;
        char c;
        Object obj;
        boolean z;
        boolean z2;
        Object objMutableStateOf$default;
        Locale locale2 = locale;
        Composer composerStartRestartGroup = composer.startRestartGroup(-333300603);
        if ((i & 6) == 0) {
            calendarMonth2 = calendarMonth;
            i2 = (composerStartRestartGroup.changed(calendarMonth2) ? 4 : 2) | i;
        } else {
            calendarMonth2 = calendarMonth;
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(l) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(l2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(selectedRangeInfo) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= (2097152 & i) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(selectableDates) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(locale2) ? 536870912 : 268435456;
        }
        int i5 = i2;
        if (composerStartRestartGroup.shouldExecute((306783379 & i5) != 306783378, i5 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-333300603, i5, -1, "androidx.compose.material3.Month (DatePicker.kt:1844)");
            }
            if (selectedRangeInfo != null) {
                composerStartRestartGroup.startReplaceGroup(606579709);
                Modifier.Companion companion = Modifier.INSTANCE;
                boolean z3 = ((i5 & 458752) == 131072) | ((234881024 & i5) == 67108864);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: n93
                        public final Object invoke(Object obj2) {
                            return DatePickerKt.A(selectedRangeInfo, datePickerColors, (ContentDrawScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                modifierDrawWithContent = DrawModifierKt.drawWithContent(companion, (Function1) objRememberedValue);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(606771165);
                composerStartRestartGroup.endReplaceGroup();
                modifierDrawWithContent = Modifier.INSTANCE;
            }
            Modifier modifierThen = SizeKt.requiredHeight-3ABfNKs(Modifier.INSTANCE, Dp.m6022constructorimpl(RecommendedSizeForAccessibility * 6.0f)).then(modifierDrawWithContent);
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getSpaceEvenly(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierThen);
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(-680088486);
            int i6 = 0;
            int i7 = 0;
            while (i7 < 6) {
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, (Object) null);
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getSpaceEvenly(), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 54);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default);
                ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                int i8 = i6;
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
                int i9 = i7;
                Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyRowMeasurePolicy, companion3.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
                if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                composerStartRestartGroup.startReplaceGroup(1542622325);
                int i10 = i8;
                int i11 = 0;
                while (i11 < 7) {
                    if (i10 < calendarMonth2.getDaysFromStartOfWeekToFirstOfMonth() || i10 >= calendarMonth2.getDaysFromStartOfWeekToFirstOfMonth() + calendarMonth2.getNumberOfDays()) {
                        i10 = i10;
                        i3 = i11;
                        i4 = i5;
                        composer3 = composerStartRestartGroup;
                        composer3.startReplaceGroup(576825328);
                        Modifier.Companion companion4 = Modifier.INSTANCE;
                        DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
                        SpacerKt.Spacer(SizeKt.size-VpY3zN4(SizeKt.sizeIn-qDBjuR0$default(companion4, datePickerModalTokens.m1699getDateContainerWidthD9Ej5fM(), datePickerModalTokens.m1698getDateContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, (Object) null), ((Dp) composer3.consume(InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl(), ((Dp) composer3.consume(InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl()), composer3, 0);
                        composer3.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(577914947);
                        int daysFromStartOfWeekToFirstOfMonth = i10 - calendarMonth2.getDaysFromStartOfWeekToFirstOfMonth();
                        i3 = i11;
                        final long startUtcTimeMillis = calendarMonth2.getStartUtcTimeMillis() + (((long) daysFromStartOfWeekToFirstOfMonth) * 86400000);
                        boolean z4 = startUtcTimeMillis == j;
                        boolean z5 = l != null && startUtcTimeMillis == l.longValue();
                        boolean z6 = l2 != null && startUtcTimeMillis == l2.longValue();
                        if (selectedRangeInfo != null) {
                            composerStartRestartGroup.startReplaceGroup(578361347);
                            boolean zChanged = ((i5 & 458752) == 131072) | composerStartRestartGroup.changed(startUtcTimeMillis);
                            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                if (startUtcTimeMillis < (l != null ? l.longValue() : Long.MAX_VALUE)) {
                                    z2 = false;
                                } else {
                                    if (startUtcTimeMillis <= (l2 != null ? l2.longValue() : Long.MIN_VALUE)) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                }
                                c = 2;
                                obj = null;
                                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z2), null, 2, null);
                                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
                            } else {
                                objMutableStateOf$default = objRememberedValue2;
                                c = 2;
                                obj = null;
                            }
                            boolean zBooleanValue = ((Boolean) ((MutableState) objMutableStateOf$default).getValue()).booleanValue();
                            composerStartRestartGroup.endReplaceGroup();
                            z = zBooleanValue;
                        } else {
                            i3 = i3;
                            c = 2;
                            obj = null;
                            composerStartRestartGroup.startReplaceGroup(578890300);
                            composerStartRestartGroup.endReplaceGroup();
                            z = false;
                        }
                        Composer composer4 = composerStartRestartGroup;
                        int i12 = i5;
                        boolean z7 = z;
                        String strDayContentDescription = dayContentDescription(selectedRangeInfo != null, z4, z5, z6, z7, composer4, 0);
                        boolean z8 = z4;
                        boolean z9 = z5;
                        boolean z10 = z6;
                        String date = datePickerFormatter.formatDate(Long.valueOf(startUtcTimeMillis), locale2, true);
                        if (date == null) {
                            date = "";
                        }
                        String str = date;
                        String localString$default = CalendarLocale_jvmKt.toLocalString$default(daysFromStartOfWeekToFirstOfMonth + 1, 0, 0, false, locale2, 7, null);
                        Modifier.Companion companion5 = Modifier.INSTANCE;
                        boolean z11 = z9 || z10;
                        boolean zChanged2 = ((i12 & 112) == 32) | composer4.changed(startUtcTimeMillis);
                        Object objRememberedValue3 = composer4.rememberedValue();
                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: y93
                                public final Object invoke() {
                                    return DatePickerKt.o(function1, startUtcTimeMillis);
                                }
                            };
                            composer4.updateRememberedValue(objRememberedValue3);
                        }
                        Function0 function0 = (Function0) objRememberedValue3;
                        i4 = i12;
                        boolean zChanged3 = ((i12 & 29360128) == 8388608) | composer4.changed(startUtcTimeMillis);
                        Object objRememberedValue4 = composer4.rememberedValue();
                        if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = Boolean.valueOf(selectableDates.isSelectableYear(calendarMonth.getYear()) && selectableDates.isSelectableDate(startUtcTimeMillis));
                            composer4.updateRememberedValue(objRememberedValue4);
                        }
                        boolean zBooleanValue2 = ((Boolean) objRememberedValue4).booleanValue();
                        if (strDayContentDescription != null) {
                            str = strDayContentDescription + ", " + str;
                        }
                        Day(localString$default, companion5, z11, function0, z9, zBooleanValue2, z8, z7, str, datePickerColors, composer4, ((i4 << 3) & 1879048192) | 48);
                        composer3 = composer4;
                        composer3.endReplaceGroup();
                    }
                    calendarMonth2 = calendarMonth;
                    locale2 = locale;
                    i10++;
                    i11 = i3 + 1;
                    composerStartRestartGroup = composer3;
                    i5 = i4;
                }
                int i13 = i5;
                Composer composer5 = composerStartRestartGroup;
                composer5.endReplaceGroup();
                composer5.endNode();
                calendarMonth2 = calendarMonth;
                locale2 = locale;
                i7 = i9 + 1;
                i6 = i10;
                i5 = i13;
            }
            composer2 = composerStartRestartGroup;
            composer2.endReplaceGroup();
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: da3
                public final Object invoke(Object obj2, Object obj3) {
                    return DatePickerKt.C(calendarMonth, function1, j, l, l2, selectedRangeInfo, datePickerFormatter, selectableDates, datePickerColors, locale, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final void MonthsNavigation(final Modifier modifier, final boolean z, final boolean z2, final boolean z3, final String str, final Function0<Unit> function0, final Function0<Unit> function1, final Function0<Unit> function2, final DatePickerColors datePickerColors, Composer composer, final int i) {
        int i2;
        Function0<Unit> function3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-773929258);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(z2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(z3) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(str) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            function3 = function2;
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 8388608 : 4194304;
        } else {
            function3 = function2;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (composerStartRestartGroup.shouldExecute((38347923 & i2) != 38347922, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-773929258, i2, -1, "androidx.compose.material3.MonthsNavigation (DatePicker.kt:2191)");
            }
            Modifier modifier2 = SizeKt.requiredHeight-3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, (Object) null), MonthYearHeight);
            int i3 = i2;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(z3 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getSpaceBetween(), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 48);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            YearPickerMenuButton(function3, z3, null, ComposableLambdaKt.rememberComposableLambda(619076006, true, new DatePickerKt$MonthsNavigation$1$1(str, datePickerColors), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 >> 21) & 14) | 3072 | ((i3 >> 6) & 112), 4);
            if (z3) {
                composerStartRestartGroup.startReplaceGroup(282432080);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(281624840);
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(datePickerColors.getNavigationContentColor())), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-128317193, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$MonthsNavigation$1$2
                    public final void invoke(Composer composer2, int i4) {
                        if (!composer2.shouldExecute((i4 & 3) != 2, i4 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-128317193, i4, -1, "androidx.compose.material3.MonthsNavigation.<anonymous>.<anonymous> (DatePicker.kt:2220)");
                        }
                        Function0<Unit> function4 = function1;
                        boolean z4 = z2;
                        Function0<Unit> function5 = function0;
                        boolean z5 = z;
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer2, 0);
                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, companion2);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor2 = companion3.getConstructor();
                        if (composer2.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor2);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composer2);
                        Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyRowMeasurePolicy2, companion3.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                        Icons.AutoMirrored.Filled filled = Icons.AutoMirrored.Filled.INSTANCE;
                        ImageVector keyboardArrowLeft$material3 = filled.getKeyboardArrowLeft$material3();
                        Strings.Companion companion4 = Strings.INSTANCE;
                        DatePickerKt.IconButtonWithTooltip(function4, keyboardArrowLeft$material3, Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_switch_to_previous_month), composer2, 0), null, z4, composer2, 0, 8);
                        DatePickerKt.IconButtonWithTooltip(function5, filled.getKeyboardArrowRight$material3(), Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_switch_to_next_month), composer2, 0), null, z5, composer2, 0, 8);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, 48 | ProvidedValue.$stable);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: i93
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.p(modifier, z, z2, z3, str, function0, function1, function2, datePickerColors, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SwitchableDateEntryContent-KaiTk9E, reason: not valid java name */
    public static final void m368SwitchableDateEntryContentKaiTk9E(final Long l, final long j, final int i, final Function1<? super Long, Unit> function1, final Function1<? super Long, Unit> function2, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, final FocusRequester focusRequester, Composer composer, final int i2, final int i3) {
        int i4;
        CalendarModel calendarModel2;
        IntRange intRange2;
        SelectableDates selectableDates2;
        int i5;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2053685029);
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(j) ? 32 : 16;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function1) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            calendarModel2 = calendarModel;
            i4 |= composerStartRestartGroup.changedInstance(calendarModel2) ? 131072 : 65536;
        } else {
            calendarModel2 = calendarModel;
        }
        if ((1572864 & i2) == 0) {
            intRange2 = intRange;
            i4 |= composerStartRestartGroup.changedInstance(intRange2) ? 1048576 : 524288;
        } else {
            intRange2 = intRange;
        }
        if ((12582912 & i2) == 0) {
            i4 |= (16777216 & i2) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 8388608 : 4194304;
        }
        if ((100663296 & i2) == 0) {
            selectableDates2 = selectableDates;
            i4 |= composerStartRestartGroup.changed(selectableDates2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((i2 & 805306368) == 0) {
            i4 |= composerStartRestartGroup.changed(datePickerColors) ? 536870912 : 268435456;
        }
        if ((i3 & 6) == 0) {
            i5 = i3 | (composerStartRestartGroup.changed(focusRequester) ? 4 : 2);
        } else {
            i5 = i3;
        }
        if (composerStartRestartGroup.shouldExecute(((i4 & 306783379) == 306783378 && (i5 & 3) == 2) ? false : true, i4 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2053685029, i4, i5, "androidx.compose.material3.SwitchableDateEntryContent (DatePicker.kt:1443)");
            }
            final int i6 = -((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4551roundToPx0680j_4(Dp.m6022constructorimpl(48.0f));
            final FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6);
            final FiniteAnimationSpec finiteAnimationSpecValue2 = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, composerStartRestartGroup, 6);
            int i7 = i4;
            MotionSchemeKeyTokens motionSchemeKeyTokens = MotionSchemeKeyTokens.DefaultSpatial;
            final FiniteAnimationSpec finiteAnimationSpecValue3 = MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6);
            final FiniteAnimationSpec finiteAnimationSpecValue4 = MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6);
            DisplayMode displayModeM404boximpl = DisplayMode.m404boximpl(i);
            Modifier.Companion companion = Modifier.INSTANCE;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue == companion2.getEmpty()) {
                objRememberedValue = new Function1() { // from class: p93
                    public final Object invoke(Object obj) {
                        return DatePickerKt.y((SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) objRememberedValue, 1, null);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(finiteAnimationSpecValue3) | composerStartRestartGroup.changedInstance(finiteAnimationSpecValue) | composerStartRestartGroup.changedInstance(finiteAnimationSpecValue2) | composerStartRestartGroup.changed(i6) | composerStartRestartGroup.changedInstance(finiteAnimationSpecValue4);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == companion2.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: q93
                    public final Object invoke(Object obj) {
                        return DatePickerKt.h(finiteAnimationSpecValue3, finiteAnimationSpecValue, finiteAnimationSpecValue2, i6, finiteAnimationSpecValue4, (AnimatedContentTransitionScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Function1 function3 = (Function1) objRememberedValue2;
            final SelectableDates selectableDates3 = selectableDates2;
            final CalendarModel calendarModel3 = calendarModel2;
            final IntRange intRange3 = intRange2;
            composer2 = composerStartRestartGroup;
            AnimatedContentKt.AnimatedContent(displayModeM404boximpl, modifierSemantics$default, function3, (Alignment) null, "DatePickerDisplayModeAnimation", (Function1) null, ComposableLambdaKt.rememberComposableLambda(1838500091, true, new Function4<AnimatedContentScope, DisplayMode, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$3
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    m371invokefYndouo((AnimatedContentScope) obj, ((DisplayMode) obj2).getValue(), (Composer) obj3, ((Number) obj4).intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-fYndouo, reason: not valid java name */
                public final void m371invokefYndouo(AnimatedContentScope animatedContentScope, int i8, Composer composer3, int i9) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1838500091, i9, -1, "androidx.compose.material3.SwitchableDateEntryContent.<anonymous> (DatePicker.kt:1498)");
                    }
                    DisplayMode.Companion companion3 = DisplayMode.INSTANCE;
                    if (DisplayMode.m407equalsimpl0(i8, companion3.m412getPickerjFl4v0())) {
                        composer3.startReplaceGroup(1567031954);
                        DatePickerKt.DatePickerContent(l, j, function1, function2, calendarModel3, intRange3, datePickerFormatter, selectableDates3, datePickerColors, composer3, 0);
                        composer3.endReplaceGroup();
                    } else if (DisplayMode.m407equalsimpl0(i8, companion3.m411getInputjFl4v0())) {
                        composer3.startReplaceGroup(1567050592);
                        DateInputKt.DateInputContent(l, function1, calendarModel3, intRange3, datePickerFormatter, selectableDates3, datePickerColors, focusRequester, composer3, 0);
                        composer3.endReplaceGroup();
                    } else {
                        composer3.startReplaceGroup(1334373351);
                        composer3.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composer2, ((i7 >> 6) & 14) | 1597440, 40);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: r93
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.b(l, j, i, function1, function2, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v8 */
    public static final void WeekDays(final DatePickerColors datePickerColors, final CalendarModel calendarModel, Composer composer, final int i) {
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1849465391);
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changed(datePickerColors) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(calendarModel) ? 32 : 16;
        }
        ?? r8 = 0;
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1849465391, i2, -1, "androidx.compose.material3.WeekDays (DatePicker.kt:1782)");
            }
            int firstDayOfWeek = calendarModel.getFirstDayOfWeek();
            List<Pair<String, String>> weekdayNames = calendarModel.getWeekdayNames();
            ArrayList arrayList = new ArrayList();
            int i3 = firstDayOfWeek - 1;
            int size = weekdayNames.size();
            for (int i4 = i3; i4 < size; i4++) {
                arrayList.add(weekdayNames.get(i4));
            }
            for (int i5 = 0; i5 < i3; i5++) {
                arrayList.add(weekdayNames.get(i5));
            }
            TextStyle value = TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getWeekdaysLabelTextFont(), composerStartRestartGroup, 6);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SizeKt.defaultMinSize-VpY3zN4$default(Modifier.INSTANCE, 0.0f, RecommendedSizeForAccessibility, 1, (Object) null), 0.0f, 1, (Object) null);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getSpaceEvenly(), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 54);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default);
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(24563235);
            int size2 = arrayList.size();
            int i6 = 0;
            while (i6 < size2) {
                final Pair pair = (Pair) arrayList.get(i6);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                boolean zChanged = composerStartRestartGroup.changed(pair);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: ha3
                        public final Object invoke(Object obj) {
                            return DatePickerKt.v(pair, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(companion2, (Function1) objRememberedValue);
                DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
                Modifier modifier = SizeKt.size-VpY3zN4(SizeKt.sizeIn-qDBjuR0$default(modifierClearAndSetSemantics, datePickerModalTokens.m1699getDateContainerWidthD9Ej5fM(), datePickerModalTokens.m1698getDateContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, (Object) null), ((Dp) composerStartRestartGroup.consume(InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl(), ((Dp) composerStartRestartGroup.consume(InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl());
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), (boolean) r8);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, r8);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
                ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
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
                Composer composer3 = composerStartRestartGroup;
                TextKt.m1097TextNvy7gAk((String) pair.getSecond(), SizeKt.wrapContentSize$default(companion2, (Alignment) null, false, 3, (Object) null), datePickerColors.getWeekdayContentColor(), null, 0L, null, null, null, 0L, null, TextAlign.m5893boximpl(TextAlign.INSTANCE.m5900getCentere0LSkKk()), 0L, 0, false, 0, 0, null, value, composer3, 48, 0, 130040);
                composer3.endNode();
                i6++;
                composerStartRestartGroup = composer3;
                arrayList = arrayList;
                r8 = 0;
            }
            composer2 = composerStartRestartGroup;
            composer2.endReplaceGroup();
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ia3
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.G(datePickerColors, calendarModel, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Year(final String str, final Modifier modifier, final boolean z, final boolean z2, final Function0<Unit> function0, final boolean z3, final String str2, final DatePickerColors datePickerColors, Composer composer, final int i) {
        String str3;
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1153850597);
        if ((i & 6) == 0) {
            str3 = str;
            i2 = (composerStartRestartGroup.changed(str3) ? 4 : 2) | i;
        } else {
            str3 = str;
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(z2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(z3) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(str2) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? 8388608 : 4194304;
        }
        if (composerStartRestartGroup.shouldExecute((4793491 & i2) != 4793490, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1153850597, i2, -1, "androidx.compose.material3.Year (DatePicker.kt:2128)");
            }
            boolean z4 = ((i2 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) | ((i2 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z4 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (!z2 || z) ? null : BorderStrokeKt.m12BorderStrokecXLIe8U(DatePickerModalTokens.INSTANCE.m1702getDateTodayContainerOutlineWidthD9Ej5fM(), datePickerColors.getTodayDateBorderColor());
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            BorderStroke borderStroke = (BorderStroke) objRememberedValue;
            boolean z5 = (3670016 & i2) == 1048576;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: t93
                    public final Object invoke(Object obj) {
                        return DatePickerKt.n(str2, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            int i3 = i2 >> 6;
            int i4 = i3 & 14;
            composer2 = composerStartRestartGroup;
            SurfaceKt.m955Surfaced85dljk(z, function0, SemanticsModifierKt.semantics(modifier, true, (Function1) objRememberedValue2), z3, ShapesKt.getValue(DatePickerModalTokens.INSTANCE.getSelectionYearStateLayerShape(), composerStartRestartGroup, 6), datePickerColors.yearContainerColor$material3(z, z3, composerStartRestartGroup, i4 | ((i2 >> 12) & 112) | ((i2 >> 15) & 896)).getValue().m3144unboximpl(), 0L, 0.0f, 0.0f, borderStroke, (MutableInteractionSource) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-564400443, true, new C00432(str3, datePickerColors, z2, z, z3), composerStartRestartGroup, 54), composer2, i4 | ((i2 >> 9) & 112) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE), 48, 1472);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: u93
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.c(str, modifier, z, z2, function0, z3, str2, datePickerColors, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void YearPicker(final Modifier modifier, final long j, final Function1<? super Integer, Unit> function1, final SelectableDates selectableDates, final CalendarModel calendarModel, final IntRange intRange, final DatePickerColors datePickerColors, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1286899812);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(selectableDates) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(calendarModel) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(intRange) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? 1048576 : 524288;
        }
        if (composerStartRestartGroup.shouldExecute((599187 & i2) != 599186, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1286899812, i2, -1, "androidx.compose.material3.YearPicker (DatePicker.kt:2068)");
            }
            TextKt.ProvideTextStyle(TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getSelectionYearLabelTextFont(), composerStartRestartGroup, 6), ComposableLambdaKt.rememberComposableLambda(1301915789, true, new C00441(calendarModel, j, intRange, modifier, datePickerColors, function1, selectableDates), composerStartRestartGroup, 54), composerStartRestartGroup, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: f93
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.z(modifier, j, function1, selectableDates, calendarModel, intRange, datePickerColors, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0063  */
    /* JADX WARN: Code duplicated, block: B:37:0x0066  */
    /* JADX WARN: Code duplicated, block: B:39:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x0070  */
    /* JADX WARN: Code duplicated, block: B:42:0x0073  */
    /* JADX WARN: Code duplicated, block: B:46:0x007d  */
    /* JADX WARN: Code duplicated, block: B:47:0x007f  */
    /* JADX WARN: Code duplicated, block: B:50:0x0088 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:51:0x008a  */
    /* JADX WARN: Code duplicated, block: B:52:0x008f  */
    /* JADX WARN: Code duplicated, block: B:55:0x0097  */
    /* JADX WARN: Code duplicated, block: B:58:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:60:0x0100  */
    /* JADX WARN: Code duplicated, block: B:63:0x010a  */
    /* JADX WARN: Code duplicated, block: B:65:? A[RETURN, SYNTHETIC] */
    private static final void YearPickerMenuButton(final Function0<Unit> function0, final boolean z, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        Function0<Unit> function1;
        int i3;
        Modifier modifier2;
        int i4;
        boolean z2;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-709923073);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            function1 = function0;
        } else if ((i & 6) == 0) {
            function1 = function0;
            i3 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i;
        } else {
            function1 = function0;
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        int i5 = i2 & 4;
        if (i5 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            if ((i2 & 8) != 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i4 = 2048;
                } else {
                    i4 = 1024;
                }
                i3 |= i4;
            }
            if ((i3 & 1171) != 1170) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-709923073, i3, -1, "androidx.compose.material3.YearPickerMenuButton (DatePicker.kt:2247)");
                }
                modifier2 = modifier4;
                ButtonKt.TextButton(function1, modifier2, false, RoundedCornerShapeKt.getCircleShape(), ButtonDefaults.INSTANCE.m147textButtonColorsro_MJ88(0L, ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl(), 0L, 0L, composerStartRestartGroup, 24576, 13), null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1899489890, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.YearPickerMenuButton.1
                    public final void invoke(RowScope rowScope, Composer composer2, int i6) {
                        String strM1471getString2EP1pXo;
                        if (!composer2.shouldExecute((i6 & 17) != 16, i6 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1899489890, i6, -1, "androidx.compose.material3.YearPickerMenuButton.<anonymous> (DatePicker.kt:2256)");
                        }
                        function2.invoke(composer2, 0);
                        Modifier.Companion companion = Modifier.INSTANCE;
                        SpacerKt.Spacer(SizeKt.size-3ABfNKs(companion, ButtonDefaults.INSTANCE.m143getIconSpacingD9Ej5fM()), composer2, 6);
                        ImageVector arrowDropDown$material3 = Icons.Filled.INSTANCE.getArrowDropDown$material3();
                        if (z) {
                            composer2.startReplaceGroup(1509384391);
                            Strings.Companion companion2 = Strings.INSTANCE;
                            strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_switch_to_day_selection), composer2, 0);
                            composer2.endReplaceGroup();
                        } else {
                            composer2.startReplaceGroup(1509478662);
                            Strings.Companion companion3 = Strings.INSTANCE;
                            strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_switch_to_year_selection), composer2, 0);
                            composer2.endReplaceGroup();
                        }
                        IconKt.m539Iconww6aTOc(arrowDropDown$material3, strM1471getString2EP1pXo, RotateKt.rotate(companion, z ? 180.0f : 0.0f), 0L, composer2, 0, 8);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer2, Integer num) {
                        invoke(rowScope, composer2, num.intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 807075840 | ((i3 >> 3) & 112), 388);
                composerStartRestartGroup = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
            }
            modifier3 = modifier2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ja3
                    public final Object invoke(Object obj, Object obj2) {
                        return DatePickerKt.m(function0, z, modifier3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i4 = 2048;
            } else {
                i4 = 1024;
            }
            i3 |= i4;
        }
        if ((i3 & 1171) != 1170) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
            if (i5 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-709923073, i3, -1, "androidx.compose.material3.YearPickerMenuButton (DatePicker.kt:2247)");
            }
            modifier2 = modifier4;
            ButtonKt.TextButton(function1, modifier2, false, RoundedCornerShapeKt.getCircleShape(), ButtonDefaults.INSTANCE.m147textButtonColorsro_MJ88(0L, ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl(), 0L, 0L, composerStartRestartGroup, 24576, 13), null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1899489890, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.YearPickerMenuButton.1
                public final void invoke(RowScope rowScope, Composer composer2, int i6) {
                    String strM1471getString2EP1pXo;
                    if (!composer2.shouldExecute((i6 & 17) != 16, i6 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1899489890, i6, -1, "androidx.compose.material3.YearPickerMenuButton.<anonymous> (DatePicker.kt:2256)");
                    }
                    function2.invoke(composer2, 0);
                    Modifier.Companion companion = Modifier.INSTANCE;
                    SpacerKt.Spacer(SizeKt.size-3ABfNKs(companion, ButtonDefaults.INSTANCE.m143getIconSpacingD9Ej5fM()), composer2, 6);
                    ImageVector arrowDropDown$material3 = Icons.Filled.INSTANCE.getArrowDropDown$material3();
                    if (z) {
                        composer2.startReplaceGroup(1509384391);
                        Strings.Companion companion2 = Strings.INSTANCE;
                        strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_switch_to_day_selection), composer2, 0);
                        composer2.endReplaceGroup();
                    } else {
                        composer2.startReplaceGroup(1509478662);
                        Strings.Companion companion3 = Strings.INSTANCE;
                        strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_switch_to_year_selection), composer2, 0);
                        composer2.endReplaceGroup();
                    }
                    IconKt.m539Iconww6aTOc(arrowDropDown$material3, strM1471getString2EP1pXo, RotateKt.rotate(companion, z ? 180.0f : 0.0f), 0L, composer2, 0, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer2, Integer num) {
                    invoke(rowScope, composer2, num.intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 807075840 | ((i3 >> 3) & 112), 388);
            composerStartRestartGroup = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        modifier3 = modifier2;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ja3
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.m(function0, z, modifier3, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static int a(int i) {
        return i;
    }

    public static Unit b(Long l, long j, int i, Function1 function1, Function1 function2, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, FocusRequester focusRequester, int i2, int i3, Composer composer, int i4) {
        m368SwitchableDateEntryContentKaiTk9E(l, j, i, function1, function2, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    public static Unit c(String str, Modifier modifier, boolean z, boolean z2, Function0 function0, boolean z3, String str2, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        Year(str, modifier, z, z2, function0, z3, str2, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit d(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setContainer(semanticsPropertyReceiver, true);
        return Unit.INSTANCE;
    }

    private static final String dayContentDescription(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(502032503, i, -1, "androidx.compose.material3.dayContentDescription (DatePicker.kt:1972)");
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            composer.startReplaceGroup(974450583);
            if (z3) {
                composer.startReplaceGroup(1416909399);
                Strings.Companion companion = Strings.INSTANCE;
                sb.append(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composer, 0));
                composer.endReplaceGroup();
            } else if (z4) {
                composer.startReplaceGroup(1416913397);
                Strings.Companion companion2 = Strings.INSTANCE;
                sb.append(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composer, 0));
                composer.endReplaceGroup();
            } else if (z5) {
                composer.startReplaceGroup(1416917332);
                Strings.Companion companion3 = Strings.INSTANCE;
                sb.append(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_day_in_range), composer, 0));
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(974832875);
                composer.endReplaceGroup();
            }
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(974838827);
            composer.endReplaceGroup();
        }
        if (z2) {
            composer.startReplaceGroup(1416920485);
            if (sb.length() > 0) {
                sb.append(", ");
            }
            Strings.Companion companion4 = Strings.INSTANCE;
            sb.append(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_today_description), composer, 0));
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(975029291);
            composer.endReplaceGroup();
        }
        String string = sb.length() == 0 ? null : sb.toString();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return string;
    }

    public static Unit e(Function0 function0, ImageVector imageVector, String str, Modifier modifier, boolean z, int i, int i2, Composer composer, int i3) {
        IconButtonWithTooltip(function0, imageVector, str, modifier, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static int f(LazyListState lazyListState) {
        return lazyListState.getFirstVisibleItemIndex();
    }

    public static Unit g(String str, Modifier modifier, boolean z, Function0 function0, boolean z2, boolean z3, boolean z4, boolean z5, String str2, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        Day(str, modifier, z, function0, z2, z3, z4, z5, str2, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final float getDatePickerHorizontalPadding() {
        return DatePickerHorizontalPadding;
    }

    public static final PaddingValues getDatePickerModeTogglePadding() {
        return DatePickerModeTogglePadding;
    }

    public static final float getMonthYearHeight() {
        return MonthYearHeight;
    }

    public static final float getRecommendedSizeForAccessibility() {
        return RecommendedSizeForAccessibility;
    }

    public static ContentTransform h(FiniteAnimationSpec finiteAnimationSpec, FiniteAnimationSpec finiteAnimationSpec2, FiniteAnimationSpec finiteAnimationSpec3, final int i, final FiniteAnimationSpec finiteAnimationSpec4, AnimatedContentTransitionScope animatedContentTransitionScope) {
        return animatedContentTransitionScope.using(DisplayMode.m407equalsimpl0(((DisplayMode) animatedContentTransitionScope.getTargetState()).getValue(), DisplayMode.INSTANCE.m411getInputjFl4v0()) ? AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInVertically(finiteAnimationSpec, new Function1() { // from class: j93
            public final Object invoke(Object obj) {
                return Integer.valueOf(DatePickerKt.a(((Integer) obj).intValue()));
            }
        }).plus(EnterExitTransitionKt.fadeIn$default(finiteAnimationSpec2, 0.0f, 2, (Object) null)), EnterExitTransitionKt.fadeOut$default(finiteAnimationSpec3, 0.0f, 2, (Object) null).plus(EnterExitTransitionKt.slideOutVertically(finiteAnimationSpec, new Function1() { // from class: k93
            public final Object invoke(Object obj) {
                return Integer.valueOf(DatePickerKt.q(i, ((Integer) obj).intValue()));
            }
        }))) : AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInVertically(finiteAnimationSpec, new Function1() { // from class: l93
            public final Object invoke(Object obj) {
                return Integer.valueOf(DatePickerKt.u(i, ((Integer) obj).intValue()));
            }
        }).plus(EnterExitTransitionKt.fadeIn$default(finiteAnimationSpec2, 0.0f, 2, (Object) null)), EnterExitTransitionKt.slideOutVertically(finiteAnimationSpec, new Function1() { // from class: m93
            public final Object invoke(Object obj) {
                return Integer.valueOf(DatePickerKt.k(((Integer) obj).intValue()));
            }
        }).plus(EnterExitTransitionKt.fadeOut$default(finiteAnimationSpec3, 0.0f, 2, (Object) null))), AnimatedContentKt.SizeTransform(true, new Function2() { // from class: o93
            public final Object invoke(Object obj, Object obj2) {
                return DatePickerKt.j(finiteAnimationSpec4, (IntSize) obj, (IntSize) obj2);
            }
        }));
    }

    public static Unit i(Modifier modifier, int i, Function1 function1, DatePickerColors datePickerColors, int i2, Composer composer, int i3) {
        m367DisplayModeToggleButtoniUJLfQg(modifier, i, function1, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    public static FiniteAnimationSpec j(FiniteAnimationSpec finiteAnimationSpec, IntSize intSize, IntSize intSize2) {
        return finiteAnimationSpec;
    }

    public static int k(int i) {
        return i;
    }

    public static Unit l(Long l, long j, Function1 function1, Function1 function2, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        DatePickerContent(l, j, function1, function2, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit m(Function0 function0, boolean z, Modifier modifier, Function2 function2, int i, int i2, Composer composer, int i3) {
        YearPickerMenuButton(function0, z, modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit n(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setText(semanticsPropertyReceiver, new AnnotatedString(str, null, 2, null));
        SemanticsPropertiesKt.m5264setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m5245getButtono7Vup1c());
        return Unit.INSTANCE;
    }

    public static final int numberOfMonthsInRange(IntRange intRange) {
        return ((intRange.getLast() - intRange.getFirst()) + 1) * 12;
    }

    public static Unit o(Function1 function1, long j) {
        function1.invoke(Long.valueOf(j));
        return Unit.INSTANCE;
    }

    public static Unit p(Modifier modifier, boolean z, boolean z2, boolean z3, String str, Function0 function0, Function0 function1, Function0 function2, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        MonthsNavigation(modifier, z, z2, z3, str, function0, function1, function2, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static int q(int i, int i2) {
        return i;
    }

    public static Unit r(CoroutineScope coroutineScope, LazyListState lazyListState) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new DatePickerKt$DatePickerContent$2$1$1$1(lazyListState, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: rememberDatePickerState-EU0dCGE, reason: not valid java name */
    public static final DatePickerState m370rememberDatePickerStateEU0dCGE(Long l, Long l2, IntRange intRange, int i, SelectableDates selectableDates, Composer composer, int i2, int i3) {
        if ((i3 & 1) != 0) {
            l = null;
        }
        final Long l3 = l;
        final Long l4 = (i3 & 2) != 0 ? l3 : l2;
        if ((i3 & 4) != 0) {
            intRange = DatePickerDefaults.INSTANCE.getYearRange();
        }
        final IntRange intRange2 = intRange;
        if ((i3 & 8) != 0) {
            i = DisplayMode.INSTANCE.m412getPickerjFl4v0();
        }
        final int i4 = i;
        if ((i3 & 16) != 0) {
            selectableDates = DatePickerDefaults.INSTANCE.getAllDates();
        }
        final SelectableDates selectableDates2 = selectableDates;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2065763010, i2, -1, "androidx.compose.material3.rememberDatePickerState (DatePicker.kt:373)");
        }
        final Locale localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composer, 0);
        Object[] objArr = new Object[0];
        Saver<DatePickerStateImpl, Object> Saver = DatePickerStateImpl.INSTANCE.Saver(selectableDates2, localeDefaultLocale);
        boolean z = true;
        boolean zChangedInstance = ((((i2 & 14) ^ 6) > 4 && composer.changed(l3)) || (i2 & 6) == 4) | ((((i2 & 112) ^ 48) > 32 && composer.changed(l4)) || (i2 & 48) == 32) | composer.changedInstance(intRange2) | ((((i2 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) > 2048 && composer.changed(i4)) || (i2 & 3072) == 2048);
        if ((((57344 & i2) ^ 24576) <= 16384 || !composer.changed(selectableDates2)) && (i2 & 24576) != 16384) {
            z = false;
        }
        boolean zChangedInstance2 = zChangedInstance | z | composer.changedInstance(localeDefaultLocale);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0() { // from class: d93
                public final Object invoke() {
                    return DatePickerKt.s(l3, l4, intRange2, i4, selectableDates2, localeDefaultLocale);
                }
            };
            composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        DatePickerStateImpl datePickerStateImpl = (DatePickerStateImpl) RememberSaveableKt.m2564rememberSaveable(objArr, (Saver) Saver, (Function0) objRememberedValue, composer, 0);
        datePickerStateImpl.setSelectableDates(selectableDates2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return datePickerStateImpl;
    }

    public static DatePickerStateImpl s(Long l, Long l2, IntRange intRange, int i, SelectableDates selectableDates, Locale locale) {
        return new DatePickerStateImpl(l, l2, intRange, i, selectableDates, locale, null);
    }

    public static Unit t(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setText(semanticsPropertyReceiver, new AnnotatedString(str, null, 2, null));
        SemanticsPropertiesKt.m5264setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m5245getButtono7Vup1c());
        return Unit.INSTANCE;
    }

    public static int u(int i, int i2) {
        return i;
    }

    public static final Object updateDisplayedMonth(final LazyListState lazyListState, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange intRange, Continuation<? super Unit> continuation) {
        Object objCollect = SnapshotStateKt.snapshotFlow(new Function0() { // from class: ca3
            public final Object invoke() {
                return Integer.valueOf(DatePickerKt.f(lazyListState));
            }
        }).collect(new FlowCollector() { // from class: androidx.compose.material3.DatePickerKt.updateDisplayedMonth.3
            public final Object emit(int i, Continuation<? super Unit> continuation2) {
                int firstVisibleItemIndex = lazyListState.getFirstVisibleItemIndex() / 12;
                function1.invoke(Boxing.boxLong(calendarModel.getMonth(intRange.getFirst() + firstVisibleItemIndex, (lazyListState.getFirstVisibleItemIndex() % 12) + 1).getStartUtcTimeMillis()));
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit(((Number) obj).intValue(), (Continuation<? super Unit>) continuation2);
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    public static Unit v(Pair pair, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, (String) pair.getFirst());
        return Unit.INSTANCE;
    }

    public static Unit w(Modifier modifier, Function2 function2, long j, long j2, float f, Function2 function3, int i, Composer composer, int i2) {
        m364DatePickerHeaderpc5RIQQ(modifier, function2, j, j2, f, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit x(MutableState mutableState) {
        DatePickerContent$lambda$27(mutableState, !DatePickerContent$lambda$26(mutableState));
        return Unit.INSTANCE;
    }

    public static Unit y(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setContainer(semanticsPropertyReceiver, true);
        return Unit.INSTANCE;
    }

    public static Unit z(Modifier modifier, long j, Function1 function1, SelectableDates selectableDates, CalendarModel calendarModel, IntRange intRange, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        YearPicker(modifier, j, function1, selectableDates, calendarModel, intRange, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePicker$5, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass5 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerState $state;

        public AnonymousClass5(DatePickerState datePickerState, DatePickerColors datePickerColors) {
            this.$state = datePickerState;
            this.$colors = datePickerColors;
        }

        public static Unit a(DatePickerState datePickerState, DisplayMode displayMode) {
            datePickerState.mo373setDisplayModevCnGnXg(displayMode.getValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1483431603, i, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:206)");
            }
            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
            int iMo372getDisplayModejFl4v0 = this.$state.mo372getDisplayModejFl4v0();
            boolean zChanged = composer.changed(this.$state);
            final DatePickerState datePickerState = this.$state;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.y
                    public final Object invoke(Object obj) {
                        return DatePickerKt.AnonymousClass5.a(datePickerState, (DisplayMode) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            DatePickerKt.m367DisplayModeToggleButtoniUJLfQg(modifierPadding, iMo372getDisplayModejFl4v0, (Function1) objRememberedValue, this.$colors, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePicker$6, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass6 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ FocusRequester $focusRequester;
        final /* synthetic */ DatePickerState $state;

        public AnonymousClass6(DatePickerState datePickerState, CalendarModel calendarModel, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors, FocusRequester focusRequester) {
            this.$state = datePickerState;
            this.$calendarModel = calendarModel;
            this.$dateFormatter = datePickerFormatter;
            this.$colors = datePickerColors;
            this.$focusRequester = focusRequester;
        }

        public static Unit a(DatePickerState datePickerState, long j) {
            datePickerState.setDisplayedMonthMillis(j);
            return Unit.INSTANCE;
        }

        public static Unit b(DatePickerState datePickerState, Long l) {
            datePickerState.setSelectedDateMillis(l);
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1346903698, i, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:220)");
            }
            Long selectedDateMillis = this.$state.getSelectedDateMillis();
            long displayedMonthMillis = this.$state.getDisplayedMonthMillis();
            int iMo372getDisplayModejFl4v0 = this.$state.mo372getDisplayModejFl4v0();
            boolean zChanged = composer.changed(this.$state);
            final DatePickerState datePickerState = this.$state;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.z
                    public final Object invoke(Object obj) {
                        return DatePickerKt.AnonymousClass6.b(datePickerState, (Long) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function1 function1 = (Function1) objRememberedValue;
            boolean zChanged2 = composer.changed(this.$state);
            final DatePickerState datePickerState2 = this.$state;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.a0
                    public final Object invoke(Object obj) {
                        return DatePickerKt.AnonymousClass6.a(datePickerState2, ((Long) obj).longValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            DatePickerKt.m368SwitchableDateEntryContentKaiTk9E(selectedDateMillis, displayedMonthMillis, iMo372getDisplayModejFl4v0, function1, (Function1) objRememberedValue2, this.$calendarModel, this.$state.getYearRange(), this.$dateFormatter, this.$state.getSelectableDates(), this.$colors, this.$focusRequester, composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ CalendarMonth $firstMonth;
        final /* synthetic */ LazyListState $lazyListState;
        final /* synthetic */ Function1<Long, Unit> $onDateSelectionChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedDateMillis;
        final /* synthetic */ CalendarDate $today;
        final /* synthetic */ IntRange $yearRange;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(LazyListState lazyListState, IntRange intRange, CalendarModel calendarModel, CalendarMonth calendarMonth, Function1<? super Long, Unit> function1, CalendarDate calendarDate, Long l, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors) {
            this.$lazyListState = lazyListState;
            this.$yearRange = intRange;
            this.$calendarModel = calendarModel;
            this.$firstMonth = calendarMonth;
            this.$onDateSelectionChange = function1;
            this.$today = calendarDate;
            this.$selectedDateMillis = l;
            this.$dateFormatter = datePickerFormatter;
            this.$selectableDates = selectableDates;
            this.$colors = datePickerColors;
        }

        public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.setHorizontalScrollAxisRange(semanticsPropertyReceiver, new ScrollAxisRange(new Function0() { // from class: androidx.compose.material3.i0
                public final Object invoke() {
                    return Float.valueOf(DatePickerKt.AnonymousClass1.b());
                }
            }, new Function0() { // from class: androidx.compose.material3.j0
                public final Object invoke() {
                    return Float.valueOf(DatePickerKt.AnonymousClass1.c());
                }
            }, false, 4, null));
            return Unit.INSTANCE;
        }

        public static float b() {
            return 0.0f;
        }

        public static float c() {
            return 0.0f;
        }

        public static Unit d(IntRange intRange, final CalendarModel calendarModel, final CalendarMonth calendarMonth, final Function1 function1, final CalendarDate calendarDate, final Long l, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, LazyListScope lazyListScope) {
            LazyListScope.items$default(lazyListScope, DatePickerKt.numberOfMonthsInRange(intRange), (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(72599078, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1$2$1$1
                public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
                    int i3;
                    if ((i2 & 6) == 0) {
                        i3 = i2 | (composer.changed(lazyItemScope) ? 4 : 2);
                    } else {
                        i3 = i2;
                    }
                    if ((i2 & 48) == 0) {
                        i3 |= composer.changed(i) ? 32 : 16;
                    }
                    if (!composer.shouldExecute((i3 & 147) != 146, i3 & 1)) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(72599078, i3, -1, "androidx.compose.material3.HorizontalMonthsList.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1733)");
                    }
                    CalendarMonth calendarMonthPlusMonths = calendarModel.plusMonths(calendarMonth, i);
                    Modifier modifierFillParentMaxWidth$default = LazyItemScope.fillParentMaxWidth$default(lazyItemScope, Modifier.INSTANCE, 0.0f, 1, (Object) null);
                    Function1<Long, Unit> function2 = function1;
                    CalendarDate calendarDate2 = calendarDate;
                    Long l2 = l;
                    DatePickerFormatter datePickerFormatter2 = datePickerFormatter;
                    SelectableDates selectableDates2 = selectableDates;
                    DatePickerColors datePickerColors2 = datePickerColors;
                    CalendarModel calendarModel2 = calendarModel;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierFillParentMaxWidth$default);
                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                    Function0<ComposeUiNode> constructor = companion.getConstructor();
                    if (composer.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer.startReusableNode();
                    if (composer.getInserting()) {
                        composer.createNode(constructor);
                    } else {
                        composer.useNode();
                    }
                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    DatePickerKt.Month(calendarMonthPlusMonths, function2, calendarDate2.getUtcTimeMillis(), l2, null, null, datePickerFormatter2, selectableDates2, datePickerColors2, calendarModel2.getLocale(), composer, 221184);
                    composer.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
                    return Unit.INSTANCE;
                }
            }), 6, (Object) null);
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1504086906, i, -1, "androidx.compose.material3.HorizontalMonthsList.<anonymous> (DatePicker.kt:1721)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue == companion2.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.g0
                    public final Object invoke(Object obj) {
                        return DatePickerKt.AnonymousClass1.a((SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) objRememberedValue, 1, null);
            LazyListState lazyListState = this.$lazyListState;
            FlingBehavior flingBehaviorRememberSnapFlingBehavior$material3 = DatePickerDefaults.INSTANCE.rememberSnapFlingBehavior$material3(lazyListState, null, composer, AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 2);
            boolean zChangedInstance = composer.changedInstance(this.$yearRange) | composer.changedInstance(this.$calendarModel) | composer.changed(this.$firstMonth) | composer.changed(this.$onDateSelectionChange) | composer.changed(this.$today) | composer.changed(this.$selectedDateMillis) | composer.changedInstance(this.$dateFormatter) | composer.changed(this.$selectableDates) | composer.changed(this.$colors);
            final IntRange intRange = this.$yearRange;
            final CalendarModel calendarModel = this.$calendarModel;
            final CalendarMonth calendarMonth = this.$firstMonth;
            final Function1<Long, Unit> function1 = this.$onDateSelectionChange;
            final CalendarDate calendarDate = this.$today;
            final Long l = this.$selectedDateMillis;
            final DatePickerFormatter datePickerFormatter = this.$dateFormatter;
            final SelectableDates selectableDates = this.$selectableDates;
            final DatePickerColors datePickerColors = this.$colors;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == companion2.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.h0
                    public final Object invoke(Object obj) {
                        return DatePickerKt.AnonymousClass1.d(intRange, calendarModel, calendarMonth, function1, calendarDate, l, datePickerFormatter, selectableDates, datePickerColors, (LazyListScope) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            LazyDslKt.LazyRow(modifierSemantics$default, lazyListState, (PaddingValues) null, false, (Arrangement.Horizontal) null, (Alignment.Vertical) null, flingBehaviorRememberSnapFlingBehavior$material3, false, (OverscrollEffect) null, (Function1) objRememberedValue2, composer, 0, 444);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPicker$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class C00441 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ long $displayedMonthMillis;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Function1<Integer, Unit> $onYearSelected;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ IntRange $yearRange;

        /* JADX WARN: Multi-variable type inference failed */
        public C00441(CalendarModel calendarModel, long j, IntRange intRange, Modifier modifier, DatePickerColors datePickerColors, Function1<? super Integer, Unit> function1, SelectableDates selectableDates) {
            this.$calendarModel = calendarModel;
            this.$displayedMonthMillis = j;
            this.$yearRange = intRange;
            this.$modifier = modifier;
            this.$colors = datePickerColors;
            this.$onYearSelected = function1;
            this.$selectableDates = selectableDates;
        }

        public static Unit a(IntRange intRange, CalendarModel calendarModel, int i, int i2, Function1 function1, SelectableDates selectableDates, DatePickerColors datePickerColors, LazyGridScope lazyGridScope) {
            LazyGridScope.items$default(lazyGridScope, CollectionsKt.count(intRange), (Function1) null, (Function2) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(674613074, true, new DatePickerKt$YearPicker$1$1$1$1(intRange, calendarModel, i, i2, function1, selectableDates, datePickerColors)), 14, (Object) null);
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1301915789, i, -1, "androidx.compose.material3.YearPicker.<anonymous> (DatePicker.kt:2070)");
            }
            CalendarModel calendarModel = this.$calendarModel;
            final int year = calendarModel.getMonth(calendarModel.getToday()).getYear();
            final int year2 = this.$calendarModel.getMonth(this.$displayedMonthMillis).getYear();
            LazyGridState lazyGridStateRememberLazyGridState = LazyGridStateKt.rememberLazyGridState(Math.max(0, (year2 - this.$yearRange.getFirst()) - 3), 0, composer, 0, 2);
            GridCells.Fixed fixed = new GridCells.Fixed(3);
            Modifier modifier = BackgroundKt.background-bw27NRU$default(this.$modifier, this.$colors.getContainerColor(), (Shape) null, 2, (Object) null);
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.HorizontalOrVertical spaceEvenly = arrangement.getSpaceEvenly();
            Arrangement.HorizontalOrVertical horizontalOrVertical = arrangement.spacedBy-0680j_4(DatePickerKt.YearsVerticalPadding);
            boolean zChangedInstance = composer.changedInstance(this.$yearRange) | composer.changedInstance(this.$calendarModel) | composer.changed(year2) | composer.changed(year) | composer.changed(this.$onYearSelected) | composer.changed(this.$selectableDates) | composer.changed(this.$colors);
            final IntRange intRange = this.$yearRange;
            final CalendarModel calendarModel2 = this.$calendarModel;
            final Function1<Integer, Unit> function1 = this.$onYearSelected;
            final SelectableDates selectableDates = this.$selectableDates;
            final DatePickerColors datePickerColors = this.$colors;
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Function1 function2 = new Function1() { // from class: androidx.compose.material3.m0
                    public final Object invoke(Object obj) {
                        return DatePickerKt.C00441.a(intRange, calendarModel2, year2, year, function1, selectableDates, datePickerColors, (LazyGridScope) obj);
                    }
                };
                composer.updateRememberedValue(function2);
                objRememberedValue = function2;
            }
            LazyGridDslKt.LazyVerticalGrid(fixed, modifier, lazyGridStateRememberLazyGridState, (PaddingValues) null, false, horizontalOrVertical, spaceEvenly, (FlingBehavior) null, false, (OverscrollEffect) null, (Function1) objRememberedValue, composer, 1769472, 0, 920);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$Year$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class C00432 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ boolean $currentYear;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ boolean $selected;
        final /* synthetic */ String $text;

        public C00432(String str, DatePickerColors datePickerColors, boolean z, boolean z2, boolean z3) {
            this.$text = str;
            this.$colors = datePickerColors;
            this.$currentYear = z;
            this.$selected = z2;
            this.$enabled = z3;
        }

        public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-564400443, i, -1, "androidx.compose.material3.Year.<anonymous> (DatePicker.kt:2157)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null);
            Alignment center = Alignment.INSTANCE.getCenter();
            String str = this.$text;
            DatePickerColors datePickerColors = this.$colors;
            boolean z = this.$currentYear;
            boolean z2 = this.$selected;
            boolean z3 = this.$enabled;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierFillMaxWidth$default);
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion2.getConstructor();
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.l0
                    public final Object invoke(Object obj) {
                        return DatePickerKt.C00432.a((SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            TextKt.m1097TextNvy7gAk(str, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) objRememberedValue), datePickerColors.yearContentColor$material3(z, z2, z3, composer, 0).getValue().m3144unboximpl(), null, 0L, null, null, null, 0L, null, TextAlign.m5893boximpl(TextAlign.INSTANCE.m5900getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, composer, 0, 0, 261112);
            composer.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$Day$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class C00402 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ boolean $inRange;
        final /* synthetic */ boolean $selected;
        final /* synthetic */ String $text;
        final /* synthetic */ boolean $today;

        public C00402(String str, DatePickerColors datePickerColors, boolean z, boolean z2, boolean z3, boolean z4) {
            this.$text = str;
            this.$colors = datePickerColors;
            this.$today = z;
            this.$selected = z2;
            this.$inRange = z3;
            this.$enabled = z4;
        }

        public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1126347158, i, -1, "androidx.compose.material3.Day.<anonymous> (DatePicker.kt:2032)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
            Modifier modifier = SizeKt.requiredSize-VpY3zN4(companion, datePickerModalTokens.m1699getDateContainerWidthD9Ej5fM(), datePickerModalTokens.m1698getDateContainerHeightD9Ej5fM());
            Alignment center = Alignment.INSTANCE.getCenter();
            String str = this.$text;
            DatePickerColors datePickerColors = this.$colors;
            boolean z = this.$today;
            boolean z2 = this.$selected;
            boolean z3 = this.$inRange;
            boolean z4 = this.$enabled;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifier);
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion2.getConstructor();
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.d0
                    public final Object invoke(Object obj) {
                        return DatePickerKt.C00402.a((SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            TextKt.m1097TextNvy7gAk(str, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) objRememberedValue), datePickerColors.dayContentColor$material3(z, z2, z3, z4, composer, 0).getValue().m3144unboximpl(), null, 0L, null, null, null, 0L, null, TextAlign.m5893boximpl(TextAlign.INSTANCE.m5900getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, composer, 0, 0, 261112);
            composer.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }
}
