package androidx.compose.foundation.pager;

import androidx.collection.SieveCacheKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.ScrollIndicatorState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope;
import androidx.compose.foundation.lazy.layout.NestedPrefetchScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.foundation.lazy.layout.PrefetchScheduler;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0002wz\b'\u0018\u00002\u00020\u0001B)\b\u0000\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB\u001d\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\nJ\u0010\u00100\u001a\u00020\u00052\u0006\u00101\u001a\u00020\u0005H\u0002J\b\u0010|\u001a\u00020\u0003H\u0002J%\u0010§\u0001\u001a\u00030¨\u00012\u0007\u0010©\u0001\u001a\u00020\u00032\t\b\u0003\u0010ª\u0001\u001a\u00020\u0005H\u0086@¢\u0006\u0003\u0010«\u0001J!\u0010¬\u0001\u001a\u00030¨\u0001*\u00030\u00ad\u00012\u0007\u0010©\u0001\u001a\u00020\u00032\t\b\u0003\u0010ª\u0001\u001a\u00020\u0005J\u0015\u0010®\u0001\u001a\u00030¨\u0001*\u00030\u00ad\u00012\u0006\u0010n\u001a\u00020\u0003J+\u0010¯\u0001\u001a\u00030¨\u00012\u0007\u0010©\u0001\u001a\u00020\u00032\u0007\u0010°\u0001\u001a\u00020\u00052\u0007\u0010±\u0001\u001a\u00020\fH\u0000¢\u0006\u0003\b²\u0001J\u001e\u0010µ\u0001\u001a\u00030¨\u00012\t\b\u0001\u0010©\u0001\u001a\u00020\u00032\t\b\u0003\u0010ª\u0001\u001a\u00020\u0005J7\u0010¶\u0001\u001a\u00030¨\u00012\u0007\u0010©\u0001\u001a\u00020\u00032\t\b\u0003\u0010ª\u0001\u001a\u00020\u00052\u0010\b\u0002\u0010·\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050¸\u0001H\u0086@¢\u0006\u0003\u0010¹\u0001J\u0011\u0010º\u0001\u001a\u00030¨\u0001H\u0082@¢\u0006\u0003\u0010»\u0001JK\u0010¼\u0001\u001a\u00030¨\u00012\b\u0010½\u0001\u001a\u00030¾\u00012.\u0010¿\u0001\u001a)\b\u0001\u0012\u0005\u0012\u00030\u00ad\u0001\u0012\f\u0012\n\u0012\u0005\u0012\u00030¨\u00010Á\u0001\u0012\u0007\u0012\u0005\u0018\u00010Â\u00010À\u0001¢\u0006\u0003\bÃ\u0001H\u0096@¢\u0006\u0003\u0010Ä\u0001J\u0011\u0010Å\u0001\u001a\u00020\u00052\u0006\u00101\u001a\u00020\u0005H\u0016J-\u0010Ù\u0001\u001a\u00030¨\u00012\u0007\u0010Ú\u0001\u001a\u00020\u00102\u0007\u0010Û\u0001\u001a\u00020\f2\t\b\u0002\u0010Ü\u0001\u001a\u00020\fH\u0000¢\u0006\u0003\bÝ\u0001J\u0013\u0010Þ\u0001\u001a\u00030¨\u00012\u0007\u0010Ú\u0001\u001a\u00020\u0010H\u0002J\r\u0010ß\u0001\u001a\u00020\u0003*\u00020\u0003H\u0002J\u0012\u0010à\u0001\u001a\u00020\f2\u0007\u0010á\u0001\u001a\u00020\u0005H\u0002J\u000f\u0010â\u0001\u001a\u00020\fH\u0000¢\u0006\u0003\bã\u0001J\u001b\u0010ä\u0001\u001a\u00030¨\u00012\u0006\u00101\u001a\u00020\u00052\u0007\u0010å\u0001\u001a\u00020BH\u0002J\u0013\u0010æ\u0001\u001a\u00030¨\u00012\u0007\u0010å\u0001\u001a\u00020BH\u0002J\u001b\u0010ç\u0001\u001a\u00020\u00032\u0007\u0010è\u0001\u001a\u00020\f2\u0007\u0010å\u0001\u001a\u00020BH\u0002J\u0010\u0010é\u0001\u001a\u00020\u00052\u0007\u0010©\u0001\u001a\u00020\u0003J#\u0010ê\u0001\u001a\u00020\u00032\b\u0010ë\u0001\u001a\u00030ì\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0003\bí\u0001R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\u0010@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R+\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00188@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016R\u001e\u0010$\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016R\u001a\u0010&\u001a\u00020'X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001b\"\u0004\b)\u0010\u001dR\u001a\u0010*\u001a\u00020'X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001b\"\u0004\b,\u0010\u001dR\u000e\u0010-\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b3\u0010\u0016R\u001e\u00104\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0016R\u000e\u00106\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00107\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u000f\"\u0004\b9\u0010:R\u000e\u0010;\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00100@X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010A\u001a\u00020B8F¢\u0006\u0006\u001a\u0004\bC\u0010DR\u0014\u0010E\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bF\u0010\u0016R\u0014\u0010G\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bH\u0010\u0016R\u001a\u0010I\u001a\u00020JX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u0014\u0010O\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bP\u0010\u0016R\u001a\u0010Q\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\u0016\"\u0004\bS\u0010TR\u0014\u0010U\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0014\u0010X\u001a\u00020YX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010[R\u0011\u0010\\\u001a\u00020]8F¢\u0006\u0006\u001a\u0004\b^\u0010_R\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b`\u0010\u0016R+\u0010a\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bd\u0010e\u001a\u0004\bb\u0010\u0016\"\u0004\bc\u0010TR+\u0010f\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bi\u0010e\u001a\u0004\bg\u0010\u0016\"\u0004\bh\u0010TR\u001b\u0010j\u001a\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bl\u0010m\u001a\u0004\bk\u0010\u0016R\u001b\u0010n\u001a\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bp\u0010m\u001a\u0004\bo\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\bq\u0010WR\u0014\u0010r\u001a\u00020sX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bt\u0010uR\u0010\u0010v\u001a\u00020wX\u0082\u0004¢\u0006\u0004\n\u0002\u0010xR\u0010\u0010y\u001a\u00020zX\u0082\u0004¢\u0006\u0004\n\u0002\u0010{R\u0015\u0010}\u001a\u00020~X\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u007f\u0010\u0080\u0001R\u0018\u0010\u0081\u0001\u001a\u00030\u0082\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0018\u0010\u0085\u0001\u001a\u00030\u0086\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R7\u0010\u008a\u0001\u001a\u0005\u0018\u00010\u0089\u00012\t\u0010\u0017\u001a\u0005\u0018\u00010\u0089\u00018@@BX\u0080\u008e\u0002¢\u0006\u0017\n\u0005\b\u008f\u0001\u0010\u001f\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\"\u0006\b\u008d\u0001\u0010\u008e\u0001R\u0018\u0010\u0090\u0001\u001a\u00030\u0091\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001R!\u0010\u0094\u0001\u001a\u00030\u0095\u0001X\u0080\u000e¢\u0006\u0013\n\u0003\u0010\u0098\u0001\u001a\u0005\b\u0096\u0001\u0010\u001b\"\u0005\b\u0097\u0001\u0010\u001dR\u0018\u0010\u0099\u0001\u001a\u00030\u009a\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R!\u0010\u009d\u0001\u001a\u00030\u009e\u00018@X\u0080\u0084\u0002¢\u0006\u0010\u001a\u0006\b¡\u0001\u0010¢\u0001*\u0006\b\u009f\u0001\u0010 \u0001R\u001a\u0010£\u0001\u001a\u00030¤\u0001X\u0080\u0004¢\u0006\f\n\u0002\u0010\u001f\u001a\u0006\b¥\u0001\u0010¦\u0001R\u001a\u0010³\u0001\u001a\u00030¤\u0001X\u0080\u0004¢\u0006\f\n\u0002\u0010\u001f\u001a\u0006\b´\u0001\u0010¦\u0001R\u0016\u0010Æ\u0001\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÆ\u0001\u0010\u000fR/\u0010Ç\u0001\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f8F@BX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÊ\u0001\u0010\u001f\u001a\u0005\bÈ\u0001\u0010\u000f\"\u0005\bÉ\u0001\u0010:R/\u0010Ë\u0001\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f8F@BX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÎ\u0001\u0010\u001f\u001a\u0005\bÌ\u0001\u0010\u000f\"\u0005\bÍ\u0001\u0010:R\u0015\u0010Ï\u0001\u001a\b\u0012\u0004\u0012\u00020\f0@X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010Ð\u0001\u001a\b\u0012\u0004\u0012\u00020\f0@X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010Ñ\u0001\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÒ\u0001\u0010\u000fR\u0016\u0010Ó\u0001\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÔ\u0001\u0010\u000fR\u001a\u0010Õ\u0001\u001a\u0005\u0018\u00010Ö\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b×\u0001\u0010Ø\u0001¨\u0006î\u0001"}, d2 = {"Landroidx/compose/foundation/pager/PagerState;", "Landroidx/compose/foundation/gestures/ScrollableState;", "currentPage", "", "currentPageOffsetFraction", "", "prefetchScheduler", "Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "<init>", "(IFLandroidx/compose/foundation/lazy/layout/PrefetchScheduler;)V", "(IF)V", "value", "", "hasLookaheadOccurred", "getHasLookaheadOccurred$foundation", "()Z", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "approachLayoutInfo", "getApproachLayoutInfo$foundation", "()Landroidx/compose/foundation/pager/PagerMeasureResult;", "pageCount", "getPageCount", "()I", "<set-?>", "Landroidx/compose/ui/geometry/Offset;", "upDownDifference", "getUpDownDifference-F1C5BW0$foundation", "()J", "setUpDownDifference-k-4lQ0M$foundation", "(J)V", "upDownDifference$delegate", "Landroidx/compose/runtime/MutableState;", "scrollPosition", "Landroidx/compose/foundation/pager/PagerScrollPosition;", "firstVisiblePage", "getFirstVisiblePage$foundation", "firstVisiblePageOffset", "getFirstVisiblePageOffset$foundation", "maxScrollOffset", "", "getMaxScrollOffset$foundation", "setMaxScrollOffset$foundation", "minScrollOffset", "getMinScrollOffset$foundation", "setMinScrollOffset$foundation", "accumulator", "previousPassDelta", "scrollableState", "performScroll", "delta", "numMeasurePasses", "getNumMeasurePasses$foundation", "layoutWithMeasurement", "getLayoutWithMeasurement$foundation", "layoutWithoutMeasurement", "prefetchingEnabled", "getPrefetchingEnabled$foundation", "setPrefetchingEnabled$foundation", "(Z)V", "indexToPrefetch", "currentPrefetchHandle", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "wasPrefetchingForward", "pagerLayoutInfoState", "Landroidx/compose/runtime/MutableState;", "layoutInfo", "Landroidx/compose/foundation/pager/PagerLayoutInfo;", "getLayoutInfo", "()Landroidx/compose/foundation/pager/PagerLayoutInfo;", "pageSpacing", "getPageSpacing$foundation", "pageSize", "getPageSize$foundation", "density", "Landroidx/compose/ui/unit/Density;", "getDensity$foundation", "()Landroidx/compose/ui/unit/Density;", "setDensity$foundation", "(Landroidx/compose/ui/unit/Density;)V", "pageSizeWithSpacing", "getPageSizeWithSpacing$foundation", "latestPageSizeWithSpacing", "getLatestPageSizeWithSpacing$foundation", "setLatestPageSizeWithSpacing$foundation", "(I)V", "positionThresholdFraction", "getPositionThresholdFraction$foundation", "()F", "internalInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getInternalInteractionSource$foundation", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/InteractionSource;", "getCurrentPage", "programmaticScrollTargetPage", "getProgrammaticScrollTargetPage", "setProgrammaticScrollTargetPage", "programmaticScrollTargetPage$delegate", "Landroidx/compose/runtime/MutableIntState;", "settledPageState", "getSettledPageState", "setSettledPageState", "settledPageState$delegate", "settledPage", "getSettledPage", "settledPage$delegate", "Landroidx/compose/runtime/State;", "targetPage", "getTargetPage", "targetPage$delegate", "getCurrentPageOffsetFraction", "prefetchState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "getPrefetchState$foundation", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "pagerCacheWindow", "androidx/compose/foundation/pager/PagerState$pagerCacheWindow$1", "Landroidx/compose/foundation/pager/PagerState$pagerCacheWindow$1;", "_scrollIndicatorState", "androidx/compose/foundation/pager/PagerState$_scrollIndicatorState$1", "Landroidx/compose/foundation/pager/PagerState$_scrollIndicatorState$1;", "calculateScrollOffset", "cacheWindowLogic", "Landroidx/compose/foundation/pager/PagerCacheWindowLogic;", "getCacheWindowLogic$foundation", "()Landroidx/compose/foundation/pager/PagerCacheWindowLogic;", "beyondBoundsInfo", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "getBeyondBoundsInfo$foundation", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "awaitLayoutModifier", "Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "getAwaitLayoutModifier$foundation", "()Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "Landroidx/compose/ui/layout/Remeasurement;", "remeasurement", "getRemeasurement$foundation", "()Landroidx/compose/ui/layout/Remeasurement;", "setRemeasurement", "(Landroidx/compose/ui/layout/Remeasurement;)V", "remeasurement$delegate", "remeasurementModifier", "Landroidx/compose/ui/layout/RemeasurementModifier;", "getRemeasurementModifier$foundation", "()Landroidx/compose/ui/layout/RemeasurementModifier;", "premeasureConstraints", "Landroidx/compose/ui/unit/Constraints;", "getPremeasureConstraints-msEJaDk$foundation", "setPremeasureConstraints-BRTryo0$foundation", "J", "pinnedPages", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "getPinnedPages$foundation", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "nearestRange", "Lkotlin/ranges/IntRange;", "getNearestRange$foundation$delegate", "(Landroidx/compose/foundation/pager/PagerState;)Ljava/lang/Object;", "getNearestRange$foundation", "()Lkotlin/ranges/IntRange;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "getPlacementScopeInvalidator-zYiylxw$foundation", "()Landroidx/compose/runtime/MutableState;", "scrollToPage", "", "page", "pageOffsetFraction", "(IFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCurrentPage", "Landroidx/compose/foundation/gestures/ScrollScope;", "updateTargetPage", "snapToItem", "offsetFraction", "forceRemeasure", "snapToItem$foundation", "measurementScopeInvalidator", "getMeasurementScopeInvalidator-zYiylxw$foundation", "requestScrollToPage", "animateScrollToPage", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(IFLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitScrollDependencies", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scroll", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatchRawDelta", "isScrollInProgress", "canScrollForward", "getCanScrollForward", "setCanScrollForward", "canScrollForward$delegate", "canScrollBackward", "getCanScrollBackward", "setCanScrollBackward", "canScrollBackward$delegate", "isLastScrollForwardState", "isLastScrollBackwardState", "lastScrolledForward", "getLastScrolledForward", "lastScrolledBackward", "getLastScrolledBackward", "scrollIndicatorState", "Landroidx/compose/foundation/ScrollIndicatorState;", "getScrollIndicatorState", "()Landroidx/compose/foundation/ScrollIndicatorState;", "applyMeasureResult", "result", "isLookingAhead", "visibleItemsStayedTheSame", "applyMeasureResult$foundation", "tryRunPrefetch", "coerceInPageRange", "isGestureActionMatchesScroll", "scrollDelta", "isNotGestureAction", "isNotGestureAction$foundation", "notifyPrefetch", "info", "cancelPrefetchIfVisibleItemsChanged", "calculatePrefetchIndex", "forward", "getOffsetDistanceInPages", "matchScrollPositionWithKey", "itemProvider", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "matchScrollPositionWithKey$foundation", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class PagerState implements ScrollableState {
    public static final int $stable = 0;
    private final PagerState$_scrollIndicatorState$1 _scrollIndicatorState;
    private float accumulator;
    private PagerMeasureResult approachLayoutInfo;
    private final AwaitFirstLayoutModifier awaitLayoutModifier;
    private final LazyLayoutBeyondBoundsInfo beyondBoundsInfo;
    private final PagerCacheWindowLogic cacheWindowLogic;

    /* JADX INFO: renamed from: canScrollBackward$delegate, reason: from kotlin metadata */
    private final MutableState canScrollBackward;

    /* JADX INFO: renamed from: canScrollForward$delegate, reason: from kotlin metadata */
    private final MutableState canScrollForward;
    private LazyLayoutPrefetchState.PrefetchHandle currentPrefetchHandle;
    private Density density;
    private int firstVisiblePage;
    private int firstVisiblePageOffset;
    private boolean hasLookaheadOccurred;
    private int indexToPrefetch;
    private final MutableInteractionSource internalInteractionSource;
    private final MutableState<Boolean> isLastScrollBackwardState;
    private final MutableState<Boolean> isLastScrollForwardState;
    private int latestPageSizeWithSpacing;
    private int layoutWithMeasurement;
    private int layoutWithoutMeasurement;
    private long maxScrollOffset;
    private final MutableState<Unit> measurementScopeInvalidator;
    private long minScrollOffset;
    private final PagerState$pagerCacheWindow$1 pagerCacheWindow;
    private MutableState<PagerMeasureResult> pagerLayoutInfoState;
    private final LazyLayoutPinnedItemList pinnedPages;
    private final MutableState<Unit> placementScopeInvalidator;
    private final LazyLayoutPrefetchState prefetchState;
    private boolean prefetchingEnabled;
    private long premeasureConstraints;
    private float previousPassDelta;

    /* JADX INFO: renamed from: programmaticScrollTargetPage$delegate, reason: from kotlin metadata */
    private final MutableIntState programmaticScrollTargetPage;

    /* JADX INFO: renamed from: remeasurement$delegate, reason: from kotlin metadata */
    private final MutableState remeasurement;
    private final RemeasurementModifier remeasurementModifier;
    private final PagerScrollPosition scrollPosition;
    private final ScrollableState scrollableState;

    /* JADX INFO: renamed from: settledPage$delegate, reason: from kotlin metadata */
    private final State settledPage;

    /* JADX INFO: renamed from: settledPageState$delegate, reason: from kotlin metadata */
    private final MutableIntState settledPageState;

    /* JADX INFO: renamed from: targetPage$delegate, reason: from kotlin metadata */
    private final State targetPage;

    /* JADX INFO: renamed from: upDownDifference$delegate, reason: from kotlin metadata */
    private final MutableState upDownDifference;
    private boolean wasPrefetchingForward;

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$animateScrollToPage$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.pager.PagerState", f = "PagerState.kt", i = {0, 0, 0}, l = {663, 670}, m = "animateScrollToPage", n = {"animationSpec", "page", "pageOffsetFraction"}, s = {"L$0", "I$0", "F$0"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PagerState.this.animateScrollToPage(0, 0.0f, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$animateScrollToPage$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.pager.PagerState$animateScrollToPage$3", f = "PagerState.kt", i = {}, l = {672}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ int $targetPage;
        final /* synthetic */ float $targetPageOffsetToSnappedPosition;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(int i, float f, AnimationSpec<Float> animationSpec, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$targetPage = i;
            this.$targetPageOffsetToSnappedPosition = f;
            this.$animationSpec = animationSpec;
        }

        public static Unit b(PagerState pagerState, ScrollScope scrollScope, int i) {
            pagerState.updateTargetPage(scrollScope, i);
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = PagerState.this.new AnonymousClass3(this.$targetPage, this.$targetPageOffsetToSnappedPosition, this.$animationSpec, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return create(scrollScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LazyLayoutScrollScope LazyLayoutScrollScope = PagerScrollScopeKt.LazyLayoutScrollScope(PagerState.this, (ScrollScope) this.L$0);
                int i2 = this.$targetPage;
                float f = this.$targetPageOffsetToSnappedPosition;
                AnimationSpec<Float> animationSpec = this.$animationSpec;
                final PagerState pagerState = PagerState.this;
                Function2 function2 = new Function2() { // from class: androidx.compose.foundation.pager.f
                    public final Object invoke(Object obj2, Object obj3) {
                        return PagerState.AnonymousClass3.b(pagerState, (ScrollScope) obj2, ((Integer) obj3).intValue());
                    }
                };
                this.label = 1;
                if (PagerStateKt.animateScrollToPage(LazyLayoutScrollScope, i2, f, animationSpec, function2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$requestScrollToPage$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.pager.PagerState$requestScrollToPage$1", f = "PagerState.kt", i = {}, l = {634}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public C01761(Continuation<? super C01761> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PagerState.this.new C01761(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PagerState pagerState = PagerState.this;
                this.label = 1;
                if (ScrollExtensionsKt.stopScroll$default((ScrollableState) pagerState, (MutatePriority) null, (Continuation) this, 1, (Object) null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$scroll$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.pager.PagerState", f = "PagerState.kt", i = {0, 0, 0, 1}, l = {691, 696}, m = "scroll$suspendImpl", n = {"$this", "scrollPriority", "block", "$this"}, s = {"L$0", "L$1", "L$2", "L$0"}, v = 1)
    public static final class C01771 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C01771(Continuation<? super C01771> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PagerState.scroll$suspendImpl(PagerState.this, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$scrollToPage$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.pager.PagerState$scrollToPage$2", f = "PagerState.kt", i = {}, l = {551}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ float $pageOffsetFraction;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(float f, int i, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$pageOffsetFraction = f;
            this.$page = i;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PagerState.this.new AnonymousClass2(this.$pageOffsetFraction, this.$page, continuation);
        }

        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return create(scrollScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PagerState pagerState = PagerState.this;
                this.label = 1;
                if (pagerState.awaitScrollDependencies(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            float f = this.$pageOffsetFraction;
            double d = f;
            boolean z = false;
            if (-0.5d <= d && d <= 0.5d) {
                z = true;
            }
            if (!z) {
                InlineClassHelperKt.throwIllegalArgumentException("pageOffsetFraction " + f + " is not within the range -0.5 to 0.5");
            }
            PagerState.this.snapToItem$foundation(PagerState.this.coerceInPageRange(this.$page), this.$pageOffsetFraction, true);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Type inference failed for: r13v8, types: [androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow, androidx.compose.foundation.pager.PagerState$pagerCacheWindow$1] */
    /* JADX WARN: Type inference failed for: r14v1, types: [androidx.compose.foundation.pager.PagerState$_scrollIndicatorState$1] */
    public PagerState(int i, float f, PrefetchScheduler prefetchScheduler) {
        double d = f;
        boolean z = false;
        if (-0.5d <= d && d <= 0.5d) {
            z = true;
        }
        if (!z) {
            InlineClassHelperKt.throwIllegalArgumentException("currentPageOffsetFraction " + f + " is not within the range -0.5 to 0.5");
        }
        this.upDownDifference = SnapshotStateKt.mutableStateOf$default(Offset.box-impl(Offset.Companion.getZero-F1C5BW0()), (SnapshotMutationPolicy) null, 2, (Object) null);
        PagerScrollPosition pagerScrollPosition = new PagerScrollPosition(i, f, this);
        this.scrollPosition = pagerScrollPosition;
        this.firstVisiblePage = i;
        this.maxScrollOffset = Long.MAX_VALUE;
        this.scrollableState = ScrollableStateKt.ScrollableState(new Function1() { // from class: sxa
            public final Object invoke(Object obj) {
                return Float.valueOf(PagerState.b(this.b, ((Float) obj).floatValue()));
            }
        });
        this.prefetchingEnabled = true;
        this.indexToPrefetch = -1;
        this.pagerLayoutInfoState = SnapshotStateKt.mutableStateOf(PagerStateKt.getEmptyLayoutInfo(), SnapshotStateKt.neverEqualPolicy());
        this.density = PagerStateKt.UnitDensity;
        this.internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
        this.programmaticScrollTargetPage = SnapshotIntStateKt.mutableIntStateOf(-1);
        this.settledPageState = SnapshotIntStateKt.mutableIntStateOf(i);
        this.settledPage = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: txa
            public final Object invoke() {
                return Integer.valueOf(PagerState.e(this.b));
            }
        });
        this.targetPage = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: uxa
            public final Object invoke() {
                return Integer.valueOf(PagerState.c(this.b));
            }
        });
        LazyLayoutPrefetchState lazyLayoutPrefetchState = new LazyLayoutPrefetchState(prefetchScheduler, new Function1() { // from class: vxa
            public final Object invoke(Object obj) {
                return PagerState.d(this.b, (NestedPrefetchScope) obj);
            }
        });
        this.prefetchState = lazyLayoutPrefetchState;
        ?? r13 = new LazyLayoutCacheWindow() { // from class: androidx.compose.foundation.pager.PagerState$pagerCacheWindow$1
            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow
            public int calculateAheadWindow(Density density, int i2) {
                return this.this$0.getLatestPageSizeWithSpacing();
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow
            public int calculateBehindWindow(Density density, int i2) {
                return 0;
            }
        };
        this.pagerCacheWindow = r13;
        this._scrollIndicatorState = new ScrollIndicatorState() { // from class: androidx.compose.foundation.pager.PagerState$_scrollIndicatorState$1
            @Override // androidx.compose.foundation.ScrollIndicatorState
            public int getContentSize() {
                return PagerLayoutInfoKt.calculateContentSize(this.this$0.getLayoutInfo(), this.this$0.getPageCount());
            }

            @Override // androidx.compose.foundation.ScrollIndicatorState
            public int getScrollOffset() {
                return this.this$0.calculateScrollOffset();
            }

            @Override // androidx.compose.foundation.ScrollIndicatorState
            public int getViewportSize() {
                return PagerLayoutInfoKt.getMainAxisViewportSize(this.this$0.getLayoutInfo());
            }
        };
        this.cacheWindowLogic = new PagerCacheWindowLogic(r13, lazyLayoutPrefetchState, new Function0() { // from class: wxa
            public final Object invoke() {
                return Integer.valueOf(PagerState.a(this.b));
            }
        });
        this.beyondBoundsInfo = new LazyLayoutBeyondBoundsInfo();
        this.awaitLayoutModifier = new AwaitFirstLayoutModifier();
        this.remeasurement = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.remeasurementModifier = new RemeasurementModifier() { // from class: androidx.compose.foundation.pager.PagerState$remeasurementModifier$1
            public void onRemeasurementAvailable(Remeasurement remeasurement) {
                this.this$0.setRemeasurement(remeasurement);
            }
        };
        this.premeasureConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, (Object) null);
        this.pinnedPages = new LazyLayoutPinnedItemList();
        pagerScrollPosition.getNearestRangeState();
        this.placementScopeInvalidator = ObservableScopeInvalidator.m1157constructorimpl$default(null, 1, null);
        this.measurementScopeInvalidator = ObservableScopeInvalidator.m1157constructorimpl$default(null, 1, null);
        Boolean bool = Boolean.FALSE;
        this.canScrollForward = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.canScrollBackward = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.isLastScrollForwardState = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.isLastScrollBackwardState = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
    }

    public static int a(PagerState pagerState) {
        return pagerState.getPageCount();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateScrollToPage$default(PagerState pagerState, int i, float f, AnimationSpec animationSpec, Continuation continuation, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: animateScrollToPage");
            return null;
        }
        if ((i2 & 2) != 0) {
            f = 0.0f;
        }
        if ((i2 & 4) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return pagerState.animateScrollToPage(i, f, animationSpec, continuation);
    }

    public static /* synthetic */ void applyMeasureResult$foundation$default(PagerState pagerState, PagerMeasureResult pagerMeasureResult, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: applyMeasureResult");
            return;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        pagerState.applyMeasureResult$foundation(pagerMeasureResult, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitScrollDependencies(Continuation<? super Unit> continuation) {
        Object objWaitForFirstLayout;
        return (this.pagerLayoutInfoState.getValue() == PagerStateKt.getEmptyLayoutInfo() && (objWaitForFirstLayout = this.awaitLayoutModifier.waitForFirstLayout(continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objWaitForFirstLayout : Unit.INSTANCE;
    }

    public static float b(PagerState pagerState, float f) {
        return pagerState.performScroll(f);
    }

    public static int c(PagerState pagerState) {
        int currentPage;
        if (!pagerState.isScrollInProgress()) {
            currentPage = pagerState.getCurrentPage();
        } else if (pagerState.getProgrammaticScrollTargetPage() != -1) {
            currentPage = pagerState.getProgrammaticScrollTargetPage();
        } else if (Math.abs(pagerState.getCurrentPageOffsetFraction()) >= Math.abs(pagerState.getPositionThresholdFraction$foundation())) {
            boolean lastScrolledForward = pagerState.getLastScrolledForward();
            int i = pagerState.firstVisiblePage;
            currentPage = lastScrolledForward ? i + 1 : i;
        } else {
            currentPage = pagerState.getCurrentPage();
        }
        return pagerState.coerceInPageRange(currentPage);
    }

    private final int calculatePrefetchIndex(boolean forward, PagerLayoutInfo info) {
        if (!forward) {
            return (((PageInfo) CollectionsKt.first(info.getVisiblePagesInfo())).getIndex() - info.getBeyondViewportPageCount()) - 1;
        }
        int beyondViewportPageCount = info.getBeyondViewportPageCount() + 1;
        if (beyondViewportPageCount < 0) {
            return Integer.MAX_VALUE;
        }
        return ((PageInfo) CollectionsKt.last(info.getVisiblePagesInfo())).getIndex() + beyondViewportPageCount;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int calculateScrollOffset() {
        long pageSizeWithSpacing$foundation = (((long) getPageSizeWithSpacing$foundation()) * ((long) this.firstVisiblePage)) + ((long) this.firstVisiblePageOffset);
        if (pageSizeWithSpacing$foundation > SieveCacheKt.NodeLinkMask) {
            pageSizeWithSpacing$foundation = 2147483647L;
        }
        return (int) pageSizeWithSpacing$foundation;
    }

    private final void cancelPrefetchIfVisibleItemsChanged(PagerLayoutInfo info) {
        if (this.indexToPrefetch == -1 || info.getVisiblePagesInfo().isEmpty()) {
            return;
        }
        if (this.indexToPrefetch != calculatePrefetchIndex(this.wasPrefetchingForward, info)) {
            this.indexToPrefetch = -1;
            LazyLayoutPrefetchState.PrefetchHandle prefetchHandle = this.currentPrefetchHandle;
            if (prefetchHandle != null) {
                prefetchHandle.cancel();
            }
            this.currentPrefetchHandle = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int coerceInPageRange(int i) {
        if (getPageCount() > 0) {
            return RangesKt.coerceIn(i, 0, getPageCount() - 1);
        }
        return 0;
    }

    public static Unit d(PagerState pagerState, NestedPrefetchScope nestedPrefetchScope) {
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            nestedPrefetchScope.schedulePrecomposition(pagerState.firstVisiblePage);
            Unit unit = Unit.INSTANCE;
            return Unit.INSTANCE;
        } finally {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
        }
    }

    public static int e(PagerState pagerState) {
        return pagerState.isScrollInProgress() ? pagerState.getSettledPageState() : pagerState.getCurrentPage();
    }

    private final int getProgrammaticScrollTargetPage() {
        return this.programmaticScrollTargetPage.getIntValue();
    }

    private final int getSettledPageState() {
        return this.settledPageState.getIntValue();
    }

    private final boolean isGestureActionMatchesScroll(float scrollDelta) {
        if (getLayoutInfo().getOrientation() == Orientation.Vertical) {
            if (Math.signum(scrollDelta) == Math.signum(-Float.intBitsToFloat((int) (m1238getUpDownDifferenceF1C5BW0$foundation() & 4294967295L)))) {
                return true;
            }
        } else if (Math.signum(scrollDelta) == Math.signum(-Float.intBitsToFloat((int) (m1238getUpDownDifferenceF1C5BW0$foundation() >> 32)))) {
            return true;
        }
        return isNotGestureAction$foundation();
    }

    public static /* synthetic */ int matchScrollPositionWithKey$foundation$default(PagerState pagerState, PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider, int i, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: matchScrollPositionWithKey");
            return 0;
        }
        if ((i2 & 2) != 0) {
            Snapshot.Companion companion = Snapshot.Companion;
            Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                int currentPage = pagerState.scrollPosition.getCurrentPage();
                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                i = currentPage;
            } catch (Throwable th) {
                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                throw th;
            }
        }
        return pagerState.matchScrollPositionWithKey$foundation(pagerLazyLayoutItemProvider, i);
    }

    private final void notifyPrefetch(float delta, PagerLayoutInfo info) {
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle2;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle3;
        if (this.prefetchingEnabled && !info.getVisiblePagesInfo().isEmpty()) {
            boolean z = delta > 0.0f;
            int iCalculatePrefetchIndex = calculatePrefetchIndex(z, info);
            if (iCalculatePrefetchIndex < 0 || iCalculatePrefetchIndex >= getPageCount()) {
                return;
            }
            if (iCalculatePrefetchIndex != this.indexToPrefetch) {
                if (this.wasPrefetchingForward != z && (prefetchHandle3 = this.currentPrefetchHandle) != null) {
                    prefetchHandle3.cancel();
                }
                this.wasPrefetchingForward = z;
                this.indexToPrefetch = iCalculatePrefetchIndex;
                this.currentPrefetchHandle = LazyLayoutPrefetchState.m1146schedulePrecompositionAndPremeasureVKLhPVY$default(this.prefetchState, iCalculatePrefetchIndex, this.premeasureConstraints, null, 4, null);
            }
            if (z) {
                if ((((PageInfo) CollectionsKt.last(info.getVisiblePagesInfo())).getOffset() + (info.getPageSize() + info.getPageSpacing())) - info.getViewportEndOffset() >= delta || (prefetchHandle2 = this.currentPrefetchHandle) == null) {
                    return;
                }
                prefetchHandle2.markAsUrgent();
                return;
            }
            if (info.getViewportStartOffset() - ((PageInfo) CollectionsKt.first(info.getVisiblePagesInfo())).getOffset() >= (-delta) || (prefetchHandle = this.currentPrefetchHandle) == null) {
                return;
            }
            prefetchHandle.markAsUrgent();
        }
    }

    private final float performScroll(float delta) {
        PagerMeasureResult pagerMeasureResult;
        long jCurrentAbsoluteScrollOffset = PagerScrollPositionKt.currentAbsoluteScrollOffset(this);
        float f = this.accumulator + delta;
        long jRoundToLong = MathKt.roundToLong(f);
        this.accumulator = f - jRoundToLong;
        if (Math.abs(delta) < 1.0E-4f) {
            return delta;
        }
        long j = jCurrentAbsoluteScrollOffset + jRoundToLong;
        long jCoerceIn = RangesKt.coerceIn(j, this.minScrollOffset, this.maxScrollOffset);
        boolean z = j != jCoerceIn;
        long j2 = jCoerceIn - jCurrentAbsoluteScrollOffset;
        float f2 = j2;
        this.previousPassDelta = f2;
        if (Math.abs(j2) != 0) {
            this.isLastScrollForwardState.setValue(Boolean.valueOf(f2 > 0.0f));
            this.isLastScrollBackwardState.setValue(Boolean.valueOf(f2 < 0.0f));
        }
        int i = (int) j2;
        int i2 = -i;
        PagerMeasureResult pagerMeasureResultCopyWithScrollDeltaWithoutRemeasure = ((PagerMeasureResult) this.pagerLayoutInfoState.getValue()).copyWithScrollDeltaWithoutRemeasure(i2);
        if (pagerMeasureResultCopyWithScrollDeltaWithoutRemeasure != null && (pagerMeasureResult = this.approachLayoutInfo) != null) {
            PagerMeasureResult pagerMeasureResultCopyWithScrollDeltaWithoutRemeasure2 = pagerMeasureResult != null ? pagerMeasureResult.copyWithScrollDeltaWithoutRemeasure(i2) : null;
            if (pagerMeasureResultCopyWithScrollDeltaWithoutRemeasure2 != null) {
                this.approachLayoutInfo = pagerMeasureResultCopyWithScrollDeltaWithoutRemeasure2;
            } else {
                pagerMeasureResultCopyWithScrollDeltaWithoutRemeasure = null;
            }
        }
        if (pagerMeasureResultCopyWithScrollDeltaWithoutRemeasure != null) {
            applyMeasureResult$foundation(pagerMeasureResultCopyWithScrollDeltaWithoutRemeasure, this.hasLookaheadOccurred, true);
            ObservableScopeInvalidator.m1161invalidateScopeimpl(this.placementScopeInvalidator);
            this.layoutWithoutMeasurement++;
        } else {
            this.scrollPosition.applyScrollDelta(i);
            Remeasurement remeasurement$foundation = getRemeasurement$foundation();
            if (remeasurement$foundation != null) {
                remeasurement$foundation.forceRemeasure();
            }
            this.layoutWithMeasurement++;
        }
        return (z ? Long.valueOf(j2) : Float.valueOf(delta)).floatValue();
    }

    public static /* synthetic */ void requestScrollToPage$default(PagerState pagerState, int i, float f, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: requestScrollToPage");
            return;
        }
        if ((i2 & 2) != 0) {
            f = 0.0f;
        }
        pagerState.requestScrollToPage(i, f);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0076, code lost:
    
        if (r9.scroll(r7, r8, r0) == r1) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static /* synthetic */ Object scroll$suspendImpl(PagerState pagerState, MutatePriority mutatePriority, Function2<? super ScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        C01771 c01771;
        if (continuation instanceof C01771) {
            c01771 = (C01771) continuation;
            int i = c01771.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01771.label = i - Integer.MIN_VALUE;
            } else {
                c01771 = pagerState.new C01771(continuation);
            }
        } else {
            c01771 = pagerState.new C01771(continuation);
        }
        Object obj = c01771.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01771.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            c01771.L$0 = pagerState;
            c01771.L$1 = mutatePriority;
            c01771.L$2 = function2;
            c01771.label = 1;
            if (pagerState.awaitScrollDependencies(c01771) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            function2 = (Function2) c01771.L$2;
            mutatePriority = (MutatePriority) c01771.L$1;
            pagerState = (PagerState) c01771.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            if (i2 != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            pagerState = (PagerState) c01771.L$0;
            ResultKt.throwOnFailure(obj);
        }
        pagerState.setProgrammaticScrollTargetPage(-1);
        return Unit.INSTANCE;
        if (!pagerState.isScrollInProgress()) {
            pagerState.setSettledPageState(pagerState.getCurrentPage());
        }
        ScrollableState scrollableState = pagerState.scrollableState;
        c01771.L$0 = pagerState;
        c01771.L$1 = null;
        c01771.L$2 = null;
        c01771.label = 2;
    }

    public static /* synthetic */ Object scrollToPage$default(PagerState pagerState, int i, float f, Continuation continuation, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: scrollToPage");
            return null;
        }
        if ((i2 & 2) != 0) {
            f = 0.0f;
        }
        return pagerState.scrollToPage(i, f, continuation);
    }

    private final void setCanScrollBackward(boolean z) {
        this.canScrollBackward.setValue(Boolean.valueOf(z));
    }

    private final void setCanScrollForward(boolean z) {
        this.canScrollForward.setValue(Boolean.valueOf(z));
    }

    private final void setProgrammaticScrollTargetPage(int i) {
        this.programmaticScrollTargetPage.setIntValue(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRemeasurement(Remeasurement remeasurement) {
        this.remeasurement.setValue(remeasurement);
    }

    private final void setSettledPageState(int i) {
        this.settledPageState.setIntValue(i);
    }

    private final void tryRunPrefetch(PagerMeasureResult result) {
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            if (this.prefetchingEnabled) {
                if (result.getBeyondViewportPageCount() >= getPageCount()) {
                    return;
                }
                if (Math.abs(this.previousPassDelta) <= 0.5f) {
                    return;
                }
                if (isGestureActionMatchesScroll(this.previousPassDelta)) {
                    if (ComposeFoundationFlags.isCacheWindowForPagerEnabled) {
                        this.cacheWindowLogic.onScroll(this.previousPassDelta, result);
                    } else {
                        notifyPrefetch(this.previousPassDelta, result);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        } finally {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
        }
    }

    public static /* synthetic */ void updateCurrentPage$default(PagerState pagerState, ScrollScope scrollScope, int i, float f, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: updateCurrentPage");
            return;
        }
        if ((i2 & 2) != 0) {
            f = 0.0f;
        }
        pagerState.updateCurrentPage(scrollScope, i, f);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c5, code lost:
    
        if (androidx.compose.foundation.gestures.ScrollableState.scroll$default(r16, null, r2, r6, 1, null) == r7) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object animateScrollToPage(int i, float f, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        AnimationSpec<Float> animationSpec2;
        int i2 = i;
        float f2 = f;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i3 = anonymousClass1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i3 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = anonymousClass2.label;
        if (i4 != 0) {
            if (i4 == 1) {
                float f3 = anonymousClass2.F$0;
                int i5 = anonymousClass2.I$0;
                animationSpec2 = (AnimationSpec) anonymousClass2.L$0;
                ResultKt.throwOnFailure(obj);
                f2 = f3;
                i2 = i5;
            } else {
                if (i4 != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        if ((i2 == getCurrentPage() && getCurrentPageOffsetFraction() == f2) || getPageCount() == 0) {
            return Unit.INSTANCE;
        }
        anonymousClass2.L$0 = animationSpec;
        anonymousClass2.I$0 = i2;
        anonymousClass2.F$0 = f2;
        anonymousClass2.label = 1;
        if (awaitScrollDependencies(anonymousClass2) != coroutine_suspended) {
            animationSpec2 = animationSpec;
        }
        return coroutine_suspended;
        double d = f2;
        if (!(-0.5d <= d && d <= 0.5d)) {
            InlineClassHelperKt.throwIllegalArgumentException("pageOffsetFraction " + f2 + " is not within the range -0.5 to 0.5");
        }
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(coerceInPageRange(i2), getPageSizeWithSpacing$foundation() * f2, animationSpec2, null);
        anonymousClass2.L$0 = null;
        anonymousClass2.label = 2;
    }

    public final void applyMeasureResult$foundation(PagerMeasureResult result, boolean isLookingAhead, boolean visibleItemsStayedTheSame) {
        this.prefetchState.setIdealNestedPrefetchCount$foundation(result.getVisiblePagesInfo().size());
        this.latestPageSizeWithSpacing = result.getPageSize() + result.getPageSpacing();
        if (!isLookingAhead && this.hasLookaheadOccurred) {
            this.approachLayoutInfo = result;
            return;
        }
        if (isLookingAhead) {
            this.hasLookaheadOccurred = true;
        }
        PagerScrollPosition pagerScrollPosition = this.scrollPosition;
        if (visibleItemsStayedTheSame) {
            pagerScrollPosition.updateCurrentPageOffsetFraction(result.getCurrentPageOffsetFraction());
        } else {
            pagerScrollPosition.updateFromMeasureResult(result);
            if (!ComposeFoundationFlags.isCacheWindowForPagerEnabled) {
                cancelPrefetchIfVisibleItemsChanged(result);
            } else if (this.prefetchingEnabled) {
                this.cacheWindowLogic.onVisibleItemsChanged(result);
            }
        }
        this.pagerLayoutInfoState.setValue(result);
        setCanScrollForward(result.getCanScrollForward());
        setCanScrollBackward(result.getCanScrollBackward());
        MeasuredPage firstVisiblePage = result.getFirstVisiblePage();
        if (firstVisiblePage != null) {
            this.firstVisiblePage = firstVisiblePage.getIndex();
        }
        this.firstVisiblePageOffset = result.getFirstVisiblePageScrollOffset();
        tryRunPrefetch(result);
        this.maxScrollOffset = PagerStateKt.calculateNewMaxScrollOffset(result, getPageCount());
        this.minScrollOffset = RangesKt.coerceAtMost(PagerStateKt.calculateNewMinScrollOffset(result, getPageCount()), this.maxScrollOffset);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public float dispatchRawDelta(float delta) {
        return this.scrollableState.dispatchRawDelta(delta);
    }

    /* JADX INFO: renamed from: getApproachLayoutInfo$foundation, reason: from getter */
    public final PagerMeasureResult getApproachLayoutInfo() {
        return this.approachLayoutInfo;
    }

    /* JADX INFO: renamed from: getAwaitLayoutModifier$foundation, reason: from getter */
    public final AwaitFirstLayoutModifier getAwaitLayoutModifier() {
        return this.awaitLayoutModifier;
    }

    /* JADX INFO: renamed from: getBeyondBoundsInfo$foundation, reason: from getter */
    public final LazyLayoutBeyondBoundsInfo getBeyondBoundsInfo() {
        return this.beyondBoundsInfo;
    }

    /* JADX INFO: renamed from: getCacheWindowLogic$foundation, reason: from getter */
    public final PagerCacheWindowLogic getCacheWindowLogic() {
        return this.cacheWindowLogic;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollBackward() {
        return ((Boolean) this.canScrollBackward.getValue()).booleanValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollForward() {
        return ((Boolean) this.canScrollForward.getValue()).booleanValue();
    }

    public final int getCurrentPage() {
        return this.scrollPosition.getCurrentPage();
    }

    public final float getCurrentPageOffsetFraction() {
        return this.scrollPosition.getCurrentPageOffsetFraction();
    }

    /* JADX INFO: renamed from: getDensity$foundation, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    /* JADX INFO: renamed from: getFirstVisiblePage$foundation, reason: from getter */
    public final int getFirstVisiblePage() {
        return this.firstVisiblePage;
    }

    /* JADX INFO: renamed from: getFirstVisiblePageOffset$foundation, reason: from getter */
    public final int getFirstVisiblePageOffset() {
        return this.firstVisiblePageOffset;
    }

    /* JADX INFO: renamed from: getHasLookaheadOccurred$foundation, reason: from getter */
    public final boolean getHasLookaheadOccurred() {
        return this.hasLookaheadOccurred;
    }

    public final InteractionSource getInteractionSource() {
        return this.internalInteractionSource;
    }

    /* JADX INFO: renamed from: getInternalInteractionSource$foundation, reason: from getter */
    public final MutableInteractionSource getInternalInteractionSource() {
        return this.internalInteractionSource;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getLastScrolledBackward() {
        return ((Boolean) this.isLastScrollBackwardState.getValue()).booleanValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getLastScrolledForward() {
        return ((Boolean) this.isLastScrollForwardState.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: getLatestPageSizeWithSpacing$foundation, reason: from getter */
    public final int getLatestPageSizeWithSpacing() {
        return this.latestPageSizeWithSpacing;
    }

    public final PagerLayoutInfo getLayoutInfo() {
        return (PagerLayoutInfo) this.pagerLayoutInfoState.getValue();
    }

    /* JADX INFO: renamed from: getLayoutWithMeasurement$foundation, reason: from getter */
    public final int getLayoutWithMeasurement() {
        return this.layoutWithMeasurement;
    }

    /* JADX INFO: renamed from: getMaxScrollOffset$foundation, reason: from getter */
    public final long getMaxScrollOffset() {
        return this.maxScrollOffset;
    }

    /* JADX INFO: renamed from: getMeasurementScopeInvalidator-zYiylxw$foundation, reason: not valid java name */
    public final MutableState<Unit> m1235getMeasurementScopeInvalidatorzYiylxw$foundation() {
        return this.measurementScopeInvalidator;
    }

    /* JADX INFO: renamed from: getMinScrollOffset$foundation, reason: from getter */
    public final long getMinScrollOffset() {
        return this.minScrollOffset;
    }

    public final IntRange getNearestRange$foundation() {
        return (IntRange) this.scrollPosition.getNearestRangeState().getValue();
    }

    public final int getNumMeasurePasses$foundation() {
        return this.layoutWithMeasurement + this.layoutWithoutMeasurement;
    }

    public final float getOffsetDistanceInPages(int page) {
        boolean z = false;
        if (page >= 0 && page <= getPageCount()) {
            z = true;
        }
        if (!z) {
            InlineClassHelperKt.throwIllegalArgumentException("page " + page + " is not within the range 0 to " + getPageCount());
        }
        return (page - getCurrentPage()) - getCurrentPageOffsetFraction();
    }

    public abstract int getPageCount();

    public final int getPageSize$foundation() {
        return ((PagerMeasureResult) this.pagerLayoutInfoState.getValue()).getPageSize();
    }

    public final int getPageSizeWithSpacing$foundation() {
        return getPageSize$foundation() + getPageSpacing$foundation();
    }

    public final int getPageSpacing$foundation() {
        return ((PagerMeasureResult) this.pagerLayoutInfoState.getValue()).getPageSpacing();
    }

    /* JADX INFO: renamed from: getPinnedPages$foundation, reason: from getter */
    public final LazyLayoutPinnedItemList getPinnedPages() {
        return this.pinnedPages;
    }

    /* JADX INFO: renamed from: getPlacementScopeInvalidator-zYiylxw$foundation, reason: not valid java name */
    public final MutableState<Unit> m1236getPlacementScopeInvalidatorzYiylxw$foundation() {
        return this.placementScopeInvalidator;
    }

    public final float getPositionThresholdFraction$foundation() {
        return Math.min(this.density.toPx-0680j_4(PagerStateKt.getDefaultPositionThreshold()), getPageSize$foundation() / 2.0f) / getPageSize$foundation();
    }

    /* JADX INFO: renamed from: getPrefetchState$foundation, reason: from getter */
    public final LazyLayoutPrefetchState getPrefetchState() {
        return this.prefetchState;
    }

    /* JADX INFO: renamed from: getPrefetchingEnabled$foundation, reason: from getter */
    public final boolean getPrefetchingEnabled() {
        return this.prefetchingEnabled;
    }

    /* JADX INFO: renamed from: getPremeasureConstraints-msEJaDk$foundation, reason: not valid java name and from getter */
    public final long getPremeasureConstraints() {
        return this.premeasureConstraints;
    }

    public final Remeasurement getRemeasurement$foundation() {
        return (Remeasurement) this.remeasurement.getValue();
    }

    /* JADX INFO: renamed from: getRemeasurementModifier$foundation, reason: from getter */
    public final RemeasurementModifier getRemeasurementModifier() {
        return this.remeasurementModifier;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public ScrollIndicatorState getScrollIndicatorState() {
        return this._scrollIndicatorState;
    }

    public final int getSettledPage() {
        return ((Number) this.settledPage.getValue()).intValue();
    }

    public final int getTargetPage() {
        return ((Number) this.targetPage.getValue()).intValue();
    }

    /* JADX INFO: renamed from: getUpDownDifference-F1C5BW0$foundation, reason: not valid java name */
    public final long m1238getUpDownDifferenceF1C5BW0$foundation() {
        return ((Offset) this.upDownDifference.getValue()).unbox-impl();
    }

    public final boolean isNotGestureAction$foundation() {
        return ((int) Float.intBitsToFloat((int) (m1238getUpDownDifferenceF1C5BW0$foundation() >> 32))) == 0 && ((int) Float.intBitsToFloat((int) (m1238getUpDownDifferenceF1C5BW0$foundation() & 4294967295L))) == 0;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    public final int matchScrollPositionWithKey$foundation(PagerLazyLayoutItemProvider itemProvider, int currentPage) {
        return this.scrollPosition.matchPageWithKey(itemProvider, currentPage);
    }

    public final void requestScrollToPage(int page, float pageOffsetFraction) {
        if (isScrollInProgress()) {
            BuildersKt.launch$default(((PagerMeasureResult) this.pagerLayoutInfoState.getValue()).getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new C01761(null), 3, (Object) null);
        }
        snapToItem$foundation(page, pageOffsetFraction, false);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public Object scroll(MutatePriority mutatePriority, Function2<? super ScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        return scroll$suspendImpl(this, mutatePriority, function2, continuation);
    }

    public final Object scrollToPage(int i, float f, Continuation<? super Unit> continuation) {
        Object objScroll$default = ScrollableState.scroll$default(this, null, new AnonymousClass2(f, i, null), continuation, 1, null);
        return objScroll$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll$default : Unit.INSTANCE;
    }

    public final void setDensity$foundation(Density density) {
        this.density = density;
    }

    public final void setLatestPageSizeWithSpacing$foundation(int i) {
        this.latestPageSizeWithSpacing = i;
    }

    public final void setMaxScrollOffset$foundation(long j) {
        this.maxScrollOffset = j;
    }

    public final void setMinScrollOffset$foundation(long j) {
        this.minScrollOffset = j;
    }

    public final void setPrefetchingEnabled$foundation(boolean z) {
        this.prefetchingEnabled = z;
    }

    /* JADX INFO: renamed from: setPremeasureConstraints-BRTryo0$foundation, reason: not valid java name */
    public final void m1239setPremeasureConstraintsBRTryo0$foundation(long j) {
        this.premeasureConstraints = j;
    }

    /* JADX INFO: renamed from: setUpDownDifference-k-4lQ0M$foundation, reason: not valid java name */
    public final void m1240setUpDownDifferencek4lQ0M$foundation(long j) {
        this.upDownDifference.setValue(Offset.box-impl(j));
    }

    public final void snapToItem$foundation(int page, float offsetFraction, boolean forceRemeasure) {
        if (this.scrollPosition.getCurrentPage() != page || this.scrollPosition.getCurrentPageOffsetFraction() != offsetFraction) {
            this.cacheWindowLogic.resetStrategy();
        }
        this.scrollPosition.requestPositionAndForgetLastKnownKey(page, offsetFraction);
        if (!forceRemeasure) {
            ObservableScopeInvalidator.m1161invalidateScopeimpl(this.measurementScopeInvalidator);
            return;
        }
        Remeasurement remeasurement$foundation = getRemeasurement$foundation();
        if (remeasurement$foundation != null) {
            remeasurement$foundation.forceRemeasure();
        }
    }

    public final void updateCurrentPage(ScrollScope scrollScope, int i, float f) {
        snapToItem$foundation(i, f, true);
    }

    public final void updateTargetPage(ScrollScope scrollScope, int i) {
        setProgrammaticScrollTargetPage(coerceInPageRange(i));
    }

    public PagerState() {
        this(0, 0.0f, null, 7, null);
    }

    public /* synthetic */ PagerState(int i, float f, PrefetchScheduler prefetchScheduler, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? 0.0f : f, (i2 & 4) != 0 ? null : prefetchScheduler);
    }

    public /* synthetic */ PagerState(int i, float f, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? 0.0f : f);
    }

    public PagerState(int i, float f) {
        this(i, f, null);
    }
}
