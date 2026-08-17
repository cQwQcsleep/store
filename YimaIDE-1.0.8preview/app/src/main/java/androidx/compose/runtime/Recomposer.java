package androidx.compose.runtime;

import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectList;
import androidx.collection.ObjectListKt;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.collection.MultiValueMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.collection.ScatterSetWrapperKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.internal.SnapshotThreadLocal;
import androidx.compose.runtime.internal.Trace;
import androidx.compose.runtime.internal.Utils_androidKt;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.ObserverHandle;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.snapshots.TransparentObserverMutableSnapshot;
import androidx.compose.runtime.snapshots.TransparentObserverSnapshot;
import androidx.compose.runtime.tooling.ComposeStackTraceMode;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import androidx.compose.runtime.tooling.CompositionObserverKt;
import androidx.compose.runtime.tooling.CompositionRegistrationObserver;
import androidx.compose.runtime.tooling.ObservableComposition;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.view.PointerIconCompat;
import com.reandroid.apk.ApkUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000ð\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 í\u00012\u00020\u0001:\né\u0001ê\u0001ë\u0001ì\u0001í\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010R\u001a\n\u0012\u0004\u0012\u000202\u0018\u000101H\u0002J\b\u0010S\u001a\u000202H\u0002J\u0006\u0010a\u001a\u00020bJ\b\u0010c\u001a\u000206H\u0002J\u001d\u0010c\u001a\u0002022\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u0002020eH\u0082\bJ\u0010\u0010f\u001a\u0002022\u0006\u0010g\u001a\u00020\u0014H\u0002J\u000e\u0010h\u001a\u000202H\u0086@¢\u0006\u0002\u0010iJ&\u0010j\u001a\u0002022\u0006\u0010k\u001a\u00020\u00162\n\b\u0002\u0010l\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010m\u001a\u000206H\u0002J\u0017\u0010n\u001a\u0002022\f\u0010o\u001a\b\u0012\u0004\u0012\u0002020pH\u0082\bJ\u000e\u0010q\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH\u0002J\u000e\u0010r\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH\u0002J\b\u0010s\u001a\u000202H\u0002J\u0010\u0010t\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J\u0010\u0010v\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J\u0010\u0010w\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J\u0010\u0010x\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J\u0015\u0010y\u001a\u00020z2\u0006\u0010{\u001a\u00020OH\u0000¢\u0006\u0002\b|J\n\u0010}\u001a\u0004\u0018\u000108H\u0002J\b\u0010~\u001a\u000202H\u0002J\u0010\u0010\u007f\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J\u0018\u0010\u0080\u0001\u001a\u0002022\u0006\u0010D\u001a\u00020\u0003H\u0087@¢\u0006\u0003\u0010\u0081\u0001J$\u0010\u0082\u0001\u001a\u0002022\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\b\u0010\u0085\u0001\u001a\u00030\u0086\u0001H\u0082@¢\u0006\u0003\u0010\u0087\u0001J\u000f\u0010\u008a\u0001\u001a\u000202H\u0082@¢\u0006\u0002\u0010iJV\u0010\u008b\u0001\u001a\u0002022D\u0010o\u001a@\b\u0001\u0012\u0005\u0012\u00030\u008d\u0001\u0012\u0017\u0012\u00150\u0084\u0001¢\u0006\u000f\b\u008e\u0001\u0012\n\b\u008f\u0001\u0012\u0005\b\b(\u0083\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u0002020\u0090\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u008c\u0001¢\u0006\u0003\b\u0091\u0001H\u0082@¢\u0006\u0003\u0010\u0092\u0001J\u0007\u0010\u0093\u0001\u001a\u000202J\u0007\u0010\u0094\u0001\u001a\u000202J\u000f\u0010\u0095\u0001\u001a\u000202H\u0086@¢\u0006\u0002\u0010iJ\u0019\u0010\u0096\u0001\u001a\u00030\u0097\u00012\r\u0010\u0098\u0001\u001a\b\u0012\u0004\u0012\u0002020pH\u0016J/\u0010\u0099\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u00192\u0013\u0010\u009a\u0001\u001a\u000e\u0012\u0004\u0012\u0002020p¢\u0006\u0003\b\u009b\u0001H\u0010¢\u0006\u0006\b\u009c\u0001\u0010\u009d\u0001J@\u0010\u009e\u0001\u001a\t\u0012\u0004\u0012\u00020?0\u009f\u00012\u0006\u0010u\u001a\u00020\u00192\b\u0010 \u0001\u001a\u00030¡\u00012\u0013\u0010\u009a\u0001\u001a\u000e\u0012\u0004\u0012\u0002020p¢\u0006\u0003\b\u009b\u0001H\u0010¢\u0006\u0006\b¢\u0001\u0010£\u0001J8\u0010¤\u0001\u001a\t\u0012\u0004\u0012\u00020?0\u009f\u00012\u0006\u0010u\u001a\u00020\u00192\b\u0010 \u0001\u001a\u00030¡\u00012\u000e\u0010¥\u0001\u001a\t\u0012\u0004\u0012\u00020?0\u009f\u0001H\u0010¢\u0006\u0003\b¦\u0001J\u0018\u0010§\u0001\u001a\u0002022\u0007\u0010¨\u0001\u001a\u00020?H\u0010¢\u0006\u0003\b©\u0001J\u0011\u0010ª\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J$\u0010«\u0001\u001a\u0004\u0018\u00010\u00192\u0006\u0010u\u001a\u00020\u00192\u000f\u0010¬\u0001\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001dH\u0002J/\u0010\u00ad\u0001\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b2\r\u0010®\u0001\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u000f\u0010¬\u0001\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001dH\u0002J\t\u0010¯\u0001\u001a\u000202H\u0002J\u001d\u0010°\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002020e2\u0006\u0010u\u001a\u00020\u0019H\u0002J.\u0010±\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002020e2\u0006\u0010u\u001a\u00020\u00192\u000f\u0010¬\u0001\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001dH\u0002J@\u0010²\u0001\u001a\u0003H³\u0001\"\u0005\b\u0000\u0010³\u00012\u0006\u0010u\u001a\u00020\u00192\u000f\u0010¬\u0001\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001d2\r\u0010o\u001a\t\u0012\u0005\u0012\u0003H³\u00010pH\u0082\b¢\u0006\u0003\u0010´\u0001J\u0013\u0010µ\u0001\u001a\u0002022\b\u0010¶\u0001\u001a\u00030·\u0001H\u0002J\u000f\u0010¾\u0001\u001a\u000202H\u0086@¢\u0006\u0002\u0010iJ\u0007\u0010¿\u0001\u001a\u000202J\u0007\u0010À\u0001\u001a\u000202J\u001f\u0010Ì\u0001\u001a\u0002022\u000e\u0010Í\u0001\u001a\t\u0012\u0005\u0012\u00030Î\u00010/H\u0010¢\u0006\u0003\bÏ\u0001J\u0017\u0010Ð\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0010¢\u0006\u0003\bÑ\u0001J\u0017\u0010Ò\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0010¢\u0006\u0003\bÓ\u0001J\u0017\u0010Ô\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0010¢\u0006\u0003\bÕ\u0001J\u0018\u0010Ö\u0001\u001a\u0002022\u0007\u0010¨\u0001\u001a\u00020?H\u0010¢\u0006\u0003\b×\u0001J\u0018\u0010Ø\u0001\u001a\u0002022\u0007\u0010Ù\u0001\u001a\u00020\"H\u0010¢\u0006\u0003\bÚ\u0001J\u0018\u0010Û\u0001\u001a\u0002022\u0007\u0010Ù\u0001\u001a\u00020\"H\u0010¢\u0006\u0003\bÜ\u0001J/\u0010Ý\u0001\u001a\u0002022\u0007\u0010Ù\u0001\u001a\u00020\"2\u0007\u0010Þ\u0001\u001a\u00020+2\f\u0010ß\u0001\u001a\u0007\u0012\u0002\b\u00030à\u0001H\u0010¢\u0006\u0003\bá\u0001J\u0017\u0010â\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0010¢\u0006\u0003\bã\u0001J\u001a\u0010ä\u0001\u001a\u0004\u0018\u00010+2\u0007\u0010Ù\u0001\u001a\u00020\"H\u0010¢\u0006\u0003\bå\u0001R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010#\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100%\u0012\u0004\u0012\u00020\"0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020+0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\"0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u0016\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00100\u001a\n\u0012\u0004\u0012\u000202\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010=\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020?\u0018\u00010\u001d0>X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\u00038PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bE\u0010CR\u0014\u0010F\u001a\u0002068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0014\u0010I\u001a\u0002068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010HR\u0014\u0010K\u001a\u0002068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bL\u0010HR\u001c\u0010M\u001a\n\u0012\u0004\u0012\u00020O\u0018\u00010NX\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\bP\u0010QR\u0014\u0010T\u001a\u0002068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bU\u0010HR \u0010V\u001a\b\u0012\u0004\u0012\u00020<0W8FX\u0087\u0004¢\u0006\f\u0012\u0004\bX\u0010Q\u001a\u0004\bY\u0010ZR\u0017\u0010[\u001a\b\u0012\u0004\u0012\u00020<0\\8F¢\u0006\u0006\u001a\u0004\b]\u0010^R\u0012\u0010_\u001a\u00060`R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0088\u0001\u001a\u0002068BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u0089\u0001\u0010HR\u0013\u0010¸\u0001\u001a\u0002068F¢\u0006\u0007\u001a\u0005\b¹\u0001\u0010HR\u0016\u0010º\u0001\u001a\u0002068BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b»\u0001\u0010HR\u0016\u0010¼\u0001\u001a\u0002068BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b½\u0001\u0010HR\u001b\u0010Á\u0001\u001a\u00070\u0007j\u0003`Â\u00018PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÃ\u0001\u0010\nR\u0016\u0010Ä\u0001\u001a\u0002068PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÅ\u0001\u0010HR\u0016\u0010Æ\u0001\u001a\u0002068PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÇ\u0001\u0010HR\u0016\u0010È\u0001\u001a\u0002068PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÉ\u0001\u0010HR\u0016\u0010Ê\u0001\u001a\u0002068PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bË\u0001\u0010HR\u0019\u0010u\u001a\u0005\u0018\u00010æ\u00018PX\u0090\u0004¢\u0006\b\u001a\u0006\bç\u0001\u0010è\u0001¨\u0006î\u0001"}, d2 = {"Landroidx/compose/runtime/Recomposer;", "Landroidx/compose/runtime/CompositionContext;", "effectCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Lkotlin/coroutines/CoroutineContext;)V", "value", "", "changeCount", "getChangeCount", "()J", "broadcastFrameClock", "Landroidx/compose/runtime/BroadcastFrameClock;", "nextFrameEndCallbackQueue", "Landroidx/compose/runtime/NextFrameEndCallbackQueue;", "stateLock", "", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "runnerJob", "Lkotlinx/coroutines/Job;", "closeCause", "", "_knownCompositions", "", "Landroidx/compose/runtime/ControlledComposition;", "_knownCompositionsCache", "", "snapshotInvalidations", "Landroidx/collection/MutableScatterSet;", "compositionInvalidations", "Landroidx/compose/runtime/collection/MutableVector;", "compositionsAwaitingApply", "movableContentAwaitingInsert", "Landroidx/compose/runtime/MovableContentStateReference;", "movableContentRemoved", "Landroidx/compose/runtime/collection/MultiValueMap;", "Landroidx/compose/runtime/MovableContent;", "Landroidx/collection/MutableScatterMap;", "movableContentNestedStatesAvailable", "Landroidx/compose/runtime/NestedContentMap;", "movableContentStatesAvailable", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/runtime/MovableContentState;", "movableContentNestedExtractionsPending", "failedCompositions", "compositionsRemoved", "", "workContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "concurrentCompositionsOutstanding", "", "isClosed", "", "errorState", "Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "frameClockPaused", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/compose/runtime/Recomposer$State;", "pausedScopes", "Landroidx/compose/runtime/internal/SnapshotThreadLocal;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "effectJob", "Lkotlinx/coroutines/CompletableJob;", "getEffectCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "recomposeCoroutineContext", "getRecomposeCoroutineContext$runtime", "hasBroadcastFrameClockAwaitersLocked", "getHasBroadcastFrameClockAwaitersLocked", "()Z", "hasNextFrameEndAwaitersLocked", "getHasNextFrameEndAwaitersLocked", "hasBroadcastFrameClockAwaiters", "getHasBroadcastFrameClockAwaiters", "registrationObservers", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/runtime/tooling/CompositionRegistrationObserver;", "getRegistrationObservers$annotations", "()V", "deriveStateLocked", "onNewFrameAwaiter", "shouldKeepRecomposing", "getShouldKeepRecomposing", "state", "Lkotlinx/coroutines/flow/Flow;", "getState$annotations", "getState", "()Lkotlinx/coroutines/flow/Flow;", "currentState", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentState", "()Lkotlinx/coroutines/flow/StateFlow;", "recomposerInfo", "Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "asRecomposerInfo", "Landroidx/compose/runtime/RecomposerInfo;", "recordComposerModifications", "onEachInvalidComposition", "Lkotlin/Function1;", "registerRunnerJob", "callingJob", "runRecomposeAndApplyChanges", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processCompositionError", "e", "failedInitialComposition", "recoverable", "withTransparentSnapshot", "block", "Lkotlin/Function0;", "knownCompositions", "knownCompositionsLocked", "clearKnownCompositionsLocked", "removeKnownCompositionLocked", "composition", "addKnownCompositionLocked", "registerCompositionLocked", "unregisterCompositionLocked", "addCompositionRegistrationObserver", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "addCompositionRegistrationObserver$runtime", "resetErrorState", "retryFailedCompositions", "recordFailedCompositionLocked", "runRecomposeConcurrentlyAndApplyChanges", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runFrameLoop", "parentFrameClock", "Landroidx/compose/runtime/MonotonicFrameClock;", "frameSignal", "Landroidx/compose/runtime/ProduceFrameSignal;", "(Landroidx/compose/runtime/MonotonicFrameClock;Landroidx/compose/runtime/ProduceFrameSignal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasSchedulingWork", "getHasSchedulingWork", "awaitWorkAvailable", "recompositionRunner", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/ParameterName;", "name", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancel", "close", "join", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "composeInitial", "content", "Landroidx/compose/runtime/Composable;", "composeInitial$runtime", "(Landroidx/compose/runtime/ControlledComposition;Lkotlin/jvm/functions/Function2;)V", "composeInitialPaused", "Landroidx/collection/ScatterSet;", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "composeInitialPaused$runtime", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ShouldPauseCallback;Lkotlin/jvm/functions/Function2;)Landroidx/collection/ScatterSet;", "recomposePaused", "invalidScopes", "recomposePaused$runtime", "reportPausedScope", "scope", "reportPausedScope$runtime", "performInitialMovableContentInserts", "performRecompose", "modifiedValues", "performInsertValues", "references", "discardUnusedMovableContentState", "readObserverOf", "writeObserverOf", "composing", "T", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/collection/MutableScatterSet;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "applyAndCheck", "snapshot", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "hasPendingWork", "getHasPendingWork", "hasFrameWorkLocked", "getHasFrameWorkLocked", "hasConcurrentFrameWorkLocked", "getHasConcurrentFrameWorkLocked", "awaitIdle", "pauseCompositionFrameClock", "resumeCompositionFrameClock", "compositeKeyHashCode", "Landroidx/compose/runtime/CompositeKeyHashCode;", "getCompositeKeyHashCode$runtime", "collectingCallByInformation", "getCollectingCallByInformation$runtime", "collectingParameterInformation", "getCollectingParameterInformation$runtime", "collectingSourceInformation", "getCollectingSourceInformation$runtime", "stackTraceEnabled", "getStackTraceEnabled$runtime", "recordInspectionTable", "table", "Landroidx/compose/runtime/tooling/CompositionData;", "recordInspectionTable$runtime", "registerComposition", "registerComposition$runtime", "unregisterComposition", "unregisterComposition$runtime", "invalidate", "invalidate$runtime", "invalidateScope", "invalidateScope$runtime", "insertMovableContent", "reference", "insertMovableContent$runtime", "deletedMovableContent", "deletedMovableContent$runtime", "movableContentStateReleased", ApkUtil.NAME_data, "applier", "Landroidx/compose/runtime/Applier;", "movableContentStateReleased$runtime", "reportRemovedComposition", "reportRemovedComposition$runtime", "movableContentStateResolve", "movableContentStateResolve$runtime", "Landroidx/compose/runtime/Composition;", "getComposition$runtime", "()Landroidx/compose/runtime/Composition;", "State", "RecomposerInfoImpl", "HotReloadable", "RecomposerErrorState", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Recomposer extends CompositionContext {
    private final List<ControlledComposition> _knownCompositions;
    private List<? extends ControlledComposition> _knownCompositionsCache;
    private final MutableStateFlow<State> _state;
    private final BroadcastFrameClock broadcastFrameClock;
    private long changeCount;
    private Throwable closeCause;
    private final MutableVector<ControlledComposition> compositionInvalidations;
    private final List<ControlledComposition> compositionsAwaitingApply;
    private Set<ControlledComposition> compositionsRemoved;
    private int concurrentCompositionsOutstanding;
    private final CoroutineContext effectCoroutineContext;
    private final CompletableJob effectJob;
    private RecomposerErrorState errorState;
    private List<ControlledComposition> failedCompositions;
    private boolean frameClockPaused;
    private boolean isClosed;
    private final List<MovableContentStateReference> movableContentAwaitingInsert;
    private final MutableScatterMap<Object, Object> movableContentNestedExtractionsPending;
    private final NestedContentMap movableContentNestedStatesAvailable;
    private final MutableScatterMap<Object, Object> movableContentRemoved;
    private final MutableScatterMap<MovableContentStateReference, MovableContentState> movableContentStatesAvailable;
    private final NextFrameEndCallbackQueue nextFrameEndCallbackQueue;
    private final SnapshotThreadLocal<MutableScatterSet<RecomposeScopeImpl>> pausedScopes;
    private final RecomposerInfoImpl recomposerInfo;
    private MutableObjectList<CompositionRegistrationObserver> registrationObservers;
    private Job runnerJob;
    private MutableScatterSet<Object> snapshotInvalidations;
    private final Object stateLock;
    private CancellableContinuation<? super Unit> workContinuation;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final MutableStateFlow<PersistentSet<RecomposerInfoImpl>> _runningRecomposers = StateFlowKt.MutableStateFlow(ExtensionsKt.persistentSetOf());
    private static final AtomicReference<Boolean> _hotReloadEnabled = new AtomicReference<>(Boolean.FALSE);

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/Recomposer$HotReloadable;", "", "composition", "Landroidx/compose/runtime/CompositionImpl;", "<init>", "(Landroidx/compose/runtime/CompositionImpl;)V", "composable", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/jvm/functions/Function2;", "clearContent", "resetContent", "recompose", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class HotReloadable {
        private Function2<? super Composer, ? super Integer, Unit> composable;
        private final CompositionImpl composition;

        public HotReloadable(CompositionImpl compositionImpl) {
            this.composition = compositionImpl;
            this.composable = compositionImpl.getComposable();
        }

        public final void clearContent() {
            if (this.composition.getIsRoot()) {
                this.composition.setContent(ComposableSingletons$RecomposerKt.INSTANCE.m2323getLambda$1091980426$runtime());
            }
        }

        public final void recompose() {
            if (this.composition.getIsRoot()) {
                this.composition.setContent(this.composable);
            }
        }

        public final void resetContent() {
            this.composition.setComposable(this.composable);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "Landroidx/compose/runtime/RecomposerErrorInfo;", "recoverable", "", "cause", "", "<init>", "(ZLjava/lang/Throwable;)V", "getRecoverable", "()Z", "getCause", "()Ljava/lang/Throwable;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class RecomposerErrorState implements RecomposerErrorInfo {
        private final Throwable cause;
        private final boolean recoverable;

        public RecomposerErrorState(boolean z, Throwable th) {
            this.recoverable = z;
            this.cause = th;
        }

        @Override // androidx.compose.runtime.RecomposerErrorInfo
        public Throwable getCause() {
            return this.cause;
        }

        @Override // androidx.compose.runtime.RecomposerErrorInfo
        public boolean getRecoverable() {
            return this.recoverable;
        }
    }

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eJ\b\u0010 \u001a\u0004\u0018\u00010!J\u0006\u0010\"\u001a\u00020\u001aR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006#"}, d2 = {"Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "Landroidx/compose/runtime/RecomposerInfo;", "<init>", "(Landroidx/compose/runtime/Recomposer;)V", "state", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/compose/runtime/Recomposer$State;", "getState", "()Lkotlinx/coroutines/flow/Flow;", "hasPendingWork", "", "getHasPendingWork", "()Z", "changeCount", "", "getChangeCount", "()J", "currentError", "Landroidx/compose/runtime/RecomposerErrorInfo;", "getCurrentError", "()Landroidx/compose/runtime/RecomposerErrorInfo;", "observe", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "Landroidx/compose/runtime/tooling/CompositionRegistrationObserver;", "invalidateGroupsWithKey", "", "key", "", "saveStateAndDisposeForHotReload", "", "Landroidx/compose/runtime/Recomposer$HotReloadable;", "resetErrorState", "Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "retryFailedCompositions", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public final class RecomposerInfoImpl implements RecomposerInfo {
        public RecomposerInfoImpl() {
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public long getChangeCount() {
            return Recomposer.this.getChangeCount();
        }

        public final RecomposerErrorInfo getCurrentError() {
            RecomposerErrorState recomposerErrorState;
            Object obj = Recomposer.this.stateLock;
            Recomposer recomposer = Recomposer.this;
            synchronized (obj) {
                recomposerErrorState = recomposer.errorState;
            }
            return recomposerErrorState;
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public boolean getHasPendingWork() {
            return Recomposer.this.getHasPendingWork();
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public Flow<State> getState() {
            return Recomposer.this.getCurrentState();
        }

        public final void invalidateGroupsWithKey(int key) {
            List listKnownCompositions = Recomposer.this.knownCompositions();
            ArrayList arrayList = new ArrayList(listKnownCompositions.size());
            int size = listKnownCompositions.size();
            for (int i = 0; i < size; i++) {
                ControlledComposition controlledComposition = (ControlledComposition) listKnownCompositions.get(i);
                CompositionImpl compositionImpl = controlledComposition instanceof CompositionImpl ? (CompositionImpl) controlledComposition : null;
                if (compositionImpl != null) {
                    arrayList.add(compositionImpl);
                }
            }
            int size2 = arrayList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((CompositionImpl) arrayList.get(i2)).invalidateGroupsWithKey(key);
            }
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public CompositionObserverHandle observe(CompositionRegistrationObserver observer) {
            return CompositionObserverKt.observe(Recomposer.this, observer);
        }

        public final RecomposerErrorState resetErrorState() {
            return Recomposer.this.resetErrorState();
        }

        public final void retryFailedCompositions() {
            Recomposer.this.retryFailedCompositions();
        }

        public final List<HotReloadable> saveStateAndDisposeForHotReload() {
            List listKnownCompositions = Recomposer.this.knownCompositions();
            ArrayList arrayList = new ArrayList(listKnownCompositions.size());
            int size = listKnownCompositions.size();
            for (int i = 0; i < size; i++) {
                ControlledComposition controlledComposition = (ControlledComposition) listKnownCompositions.get(i);
                CompositionImpl compositionImpl = controlledComposition instanceof CompositionImpl ? (CompositionImpl) controlledComposition : null;
                if (compositionImpl != null) {
                    arrayList.add(compositionImpl);
                }
            }
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            int size2 = arrayList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                HotReloadable hotReloadable = new HotReloadable((CompositionImpl) arrayList.get(i2));
                hotReloadable.clearContent();
                arrayList2.add(hotReloadable);
            }
            return arrayList2;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/compose/runtime/Recomposer$State;", "", "<init>", "(Ljava/lang/String;I)V", "ShutDown", "ShuttingDown", "Inactive", "InactivePendingWork", "Idle", "PendingWork", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public enum State {
        ShutDown,
        ShuttingDown,
        Inactive,
        InactivePendingWork,
        Idle,
        PendingWork;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$awaitIdle$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/runtime/Recomposer$State;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$awaitIdle$2", f = "Recomposer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<State, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(State state, Continuation<? super Boolean> continuation) {
            return create(state, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxBoolean(((State) this.L$0).compareTo(State.Idle) > 0);
            }
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$join$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/runtime/Recomposer$State;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$join$2", f = "Recomposer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C00762 extends SuspendLambda implements Function2<State, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public C00762(Continuation<? super C00762> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00762 c00762 = new C00762(continuation);
            c00762.L$0 = obj;
            return c00762;
        }

        public final Object invoke(State state, Continuation<? super Boolean> continuation) {
            return create(state, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxBoolean(((State) this.L$0) == State.ShutDown);
            }
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$recompositionRunner$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$recompositionRunner$2", f = "Recomposer.kt", i = {0, 0}, l = {1173}, m = "invokeSuspend", n = {"callingJob", "unregisterApplyObserver"}, s = {"L$0", "L$1"}, v = 1)
    public static final class C00772 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ MonotonicFrameClock $parentFrameClock;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$recompositionRunner$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        @DebugMetadata(c = "androidx.compose.runtime.Recomposer$recompositionRunner$2$2", f = "Recomposer.kt", i = {}, l = {1173}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class C00242 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> $block;
            final /* synthetic */ MonotonicFrameClock $parentFrameClock;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00242(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, MonotonicFrameClock monotonicFrameClock, Continuation<? super C00242> continuation) {
                super(2, continuation);
                this.$block = function3;
                this.$parentFrameClock = monotonicFrameClock;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00242 c00242 = new C00242(this.$block, this.$parentFrameClock, continuation);
                c00242.L$0 = obj;
                return c00242;
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> function3 = this.$block;
                    MonotonicFrameClock monotonicFrameClock = this.$parentFrameClock;
                    this.label = 1;
                    if (function3.invoke(coroutineScope, monotonicFrameClock, this) == coroutine_suspended) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00772(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, MonotonicFrameClock monotonicFrameClock, Continuation<? super C00772> continuation) {
            super(2, continuation);
            this.$block = function3;
            this.$parentFrameClock = monotonicFrameClock;
        }

        /* JADX WARN: Code duplicated, block: B:27:0x007a A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:28:0x007c A[Catch: all -> 0x006f, LOOP:0: B:11:0x0033->B:28:0x007c, LOOP_END, TryCatch #0 {all -> 0x006f, blocks: (B:4:0x0007, B:6:0x0019, B:8:0x0022, B:11:0x0033, B:13:0x0043, B:15:0x004f, B:17:0x0058, B:19:0x0061, B:24:0x0071, B:25:0x0074, B:28:0x007c, B:38:0x00a5, B:29:0x007f, B:30:0x0085, B:32:0x008b, B:34:0x0093, B:37:0x00a1), top: B:48:0x0007 }] */
        /* JADX WARN: Code duplicated, block: B:51:0x00a5 A[EDGE_INSN: B:51:0x00a5->B:38:0x00a5 BREAK  A[LOOP:0: B:11:0x0033->B:28:0x007c], SYNTHETIC] */
        public static Unit b(Recomposer recomposer, Set set, Snapshot snapshot) {
            CancellableContinuation cancellableContinuationDeriveStateLocked;
            synchronized (recomposer.stateLock) {
                try {
                    if (((State) recomposer._state.getValue()).compareTo(State.Idle) >= 0) {
                        MutableScatterSet mutableScatterSet = recomposer.snapshotInvalidations;
                        if (set instanceof ScatterSetWrapper) {
                            ScatterSet set$runtime = ((ScatterSetWrapper) set).getSet$runtime();
                            Object[] objArr = set$runtime.elements;
                            long[] jArr = set$runtime.metadata;
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
                                                Object obj = objArr[(i << 3) + i3];
                                                if (!(obj instanceof StateObjectImpl) || ((StateObjectImpl) obj).m2584isReadInh_f27i8$runtime(ReaderKind.m2571constructorimpl(1))) {
                                                    mutableScatterSet.add(obj);
                                                }
                                            }
                                            j >>= 8;
                                        }
                                        if (i2 != 8) {
                                            break;
                                        }
                                        if (i != length) {
                                            break;
                                        }
                                        i++;
                                    }
                                }
                            }
                        } else {
                            for (Object obj2 : set) {
                                if (!(obj2 instanceof StateObjectImpl) || ((StateObjectImpl) obj2).m2584isReadInh_f27i8$runtime(ReaderKind.m2571constructorimpl(1))) {
                                    mutableScatterSet.add(obj2);
                                }
                            }
                        }
                        cancellableContinuationDeriveStateLocked = recomposer.deriveStateLocked();
                    } else {
                        cancellableContinuationDeriveStateLocked = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (cancellableContinuationDeriveStateLocked != null) {
                Result.Companion companion = Result.Companion;
                cancellableContinuationDeriveStateLocked.resumeWith(Result.constructor-impl(Unit.INSTANCE));
            }
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00772 c00772 = Recomposer.this.new C00772(this.$block, this.$parentFrameClock, continuation);
            c00772.L$0 = obj;
            return c00772;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:26:0x0097 A[Catch: all -> 0x009b, TryCatch #3 {all -> 0x009b, blocks: (B:24:0x0091, B:26:0x0097, B:29:0x009d), top: B:54:0x0091 }] */
        /* JADX WARN: Code duplicated, block: B:39:0x00c3 A[Catch: all -> 0x00c7, TryCatch #0 {all -> 0x00c7, blocks: (B:37:0x00bd, B:39:0x00c3, B:42:0x00c9), top: B:48:0x00bd }] */
        /* JADX WARN: Code duplicated, block: B:48:0x00bd A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:54:0x0091 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        public final Object invokeSuspend(Object obj) throws Throwable {
            Job job;
            ObserverHandle observerHandle;
            Throwable th;
            Object obj2;
            Recomposer recomposer;
            Object obj3;
            Recomposer recomposer2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                observerHandle = (ObserverHandle) this.L$1;
                job = (Job) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    observerHandle.dispose();
                    obj3 = Recomposer.this.stateLock;
                    recomposer2 = Recomposer.this;
                    synchronized (obj3) {
                        try {
                            if (recomposer2.runnerJob == job) {
                                recomposer2.runnerJob = null;
                            }
                            recomposer2.deriveStateLocked();
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                    Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    observerHandle.dispose();
                    obj2 = Recomposer.this.stateLock;
                    recomposer = Recomposer.this;
                    synchronized (obj2) {
                        try {
                            if (recomposer.runnerJob == job) {
                                recomposer.runnerJob = null;
                            }
                            recomposer.deriveStateLocked();
                        } catch (Throwable th4) {
                            throw th4;
                        }
                    }
                    Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                    throw th;
                }
            }
            ResultKt.throwOnFailure(obj);
            job = JobKt.getJob(((CoroutineScope) this.L$0).getCoroutineContext());
            Recomposer.this.registerRunnerJob(job);
            Snapshot.Companion companion = Snapshot.INSTANCE;
            final Recomposer recomposer3 = Recomposer.this;
            ObserverHandle observerHandleRegisterApplyObserver = companion.registerApplyObserver(new Function2() { // from class: androidx.compose.runtime.h
                public final Object invoke(Object obj4, Object obj5) {
                    return Recomposer.C00772.b(recomposer3, (Set) obj4, (Snapshot) obj5);
                }
            });
            Recomposer.INSTANCE.addRunning(Recomposer.this.recomposerInfo);
            try {
                List listKnownCompositions = Recomposer.this.knownCompositions();
                int size = listKnownCompositions.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((ControlledComposition) listKnownCompositions.get(i2)).invalidateAll();
                }
                C00242 c00242 = new C00242(this.$block, this.$parentFrameClock, null);
                this.L$0 = job;
                this.L$1 = observerHandleRegisterApplyObserver;
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(c00242, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                observerHandle = observerHandleRegisterApplyObserver;
                observerHandle.dispose();
                obj3 = Recomposer.this.stateLock;
                recomposer2 = Recomposer.this;
                synchronized (obj3) {
                    if (recomposer2.runnerJob == job) {
                        recomposer2.runnerJob = null;
                    }
                    recomposer2.deriveStateLocked();
                    Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                    return Unit.INSTANCE;
                }
            } catch (Throwable th5) {
                observerHandle = observerHandleRegisterApplyObserver;
                th = th5;
                observerHandle.dispose();
                obj2 = Recomposer.this.stateLock;
                recomposer = Recomposer.this;
                synchronized (obj2) {
                    if (recomposer.runnerJob == job) {
                        recomposer.runnerJob = null;
                    }
                    recomposer.deriveStateLocked();
                    Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runFrameLoop$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer", f = "Recomposer.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {1042, 1049}, m = "runFrameLoop", n = {"parentFrameClock", "frameSignal", "toRecompose", "toApply", "parentFrameClock", "frameSignal", "toRecompose", "toApply"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Recomposer.this.runFrameLoop(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "parentFrameClock", "Landroidx/compose/runtime/MonotonicFrameClock;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2", f = "Recomposer.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {598, 609}, m = "invokeSuspend", n = {"parentFrameClock", "toRecompose", "toInsert", "toApply", "toLateApply", "toComplete", "modifiedValues", "modifiedValuesSet", "alreadyComposed", "parentFrameClock", "toRecompose", "toInsert", "toApply", "toLateApply", "toComplete", "modifiedValues", "modifiedValuesSet", "alreadyComposed"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"}, v = 1)
    public static final class C00782 extends SuspendLambda implements Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;

        public C00782(Continuation<? super C00782> continuation) {
            super(3, continuation);
        }

        /* JADX WARN: Code duplicated, block: B:101:0x01ea A[Catch: all -> 0x01fb, TryCatch #11 {all -> 0x01fb, blocks: (B:94:0x01ca, B:97:0x01d4, B:99:0x01e0, B:101:0x01ea, B:103:0x01f0), top: B:225:0x01ca, outer: #3 }] */
        /* JADX WARN: Code duplicated, block: B:103:0x01f0 A[Catch: all -> 0x01fb, TRY_LEAVE, TryCatch #11 {all -> 0x01fb, blocks: (B:94:0x01ca, B:97:0x01d4, B:99:0x01e0, B:101:0x01ea, B:103:0x01f0), top: B:225:0x01ca, outer: #3 }] */
        /* JADX WARN: Code duplicated, block: B:109:0x0204 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:110:0x0206 A[LOOP:4: B:97:0x01d4->B:110:0x0206, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:205:0x0248 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:223:0x0138 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:225:0x01ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:240:0x0209 A[EDGE_INSN: B:240:0x0209->B:111:0x0209 BREAK  A[LOOP:4: B:97:0x01d4->B:110:0x0206], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:241:0x0209 A[EDGE_INSN: B:241:0x0209->B:111:0x0209 BREAK  A[LOOP:4: B:97:0x01d4->B:110:0x0206], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:243:0x01fe A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:245:0x018e A[EDGE_INSN: B:245:0x018e->B:81:0x018e BREAK  A[LOOP:6: B:66:0x014b->B:79:0x0182], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:246:0x018e A[EDGE_INSN: B:246:0x018e->B:81:0x018e BREAK  A[LOOP:6: B:66:0x014b->B:79:0x0182], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:248:0x017a A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:65:0x0146  */
        /* JADX WARN: Code duplicated, block: B:68:0x015c A[Catch: all -> 0x0177, TryCatch #10 {all -> 0x0177, blocks: (B:63:0x0138, B:66:0x014b, B:68:0x015c, B:70:0x0166, B:72:0x016c), top: B:223:0x0138, outer: #3 }] */
        /* JADX WARN: Code duplicated, block: B:70:0x0166 A[Catch: all -> 0x0177, TryCatch #10 {all -> 0x0177, blocks: (B:63:0x0138, B:66:0x014b, B:68:0x015c, B:70:0x0166, B:72:0x016c), top: B:223:0x0138, outer: #3 }] */
        /* JADX WARN: Code duplicated, block: B:72:0x016c A[Catch: all -> 0x0177, TRY_LEAVE, TryCatch #10 {all -> 0x0177, blocks: (B:63:0x0138, B:66:0x014b, B:68:0x015c, B:70:0x0166, B:72:0x016c), top: B:223:0x0138, outer: #3 }] */
        /* JADX WARN: Code duplicated, block: B:78:0x0180 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:79:0x0182 A[LOOP:6: B:66:0x014b->B:79:0x0182, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:80:0x0185  */
        /* JADX WARN: Code duplicated, block: B:91:0x01b9  */
        /* JADX WARN: Code duplicated, block: B:96:0x01d3  */
        /* JADX WARN: Code duplicated, block: B:99:0x01e0 A[Catch: all -> 0x01fb, TryCatch #11 {all -> 0x01fb, blocks: (B:94:0x01ca, B:97:0x01d4, B:99:0x01e0, B:101:0x01ea, B:103:0x01f0), top: B:225:0x01ca, outer: #3 }] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v19 */
        /* JADX WARN: Type inference failed for: r3v20, types: [int] */
        /* JADX WARN: Type inference failed for: r3v21 */
        /* JADX WARN: Type inference failed for: r3v22, types: [int] */
        /* JADX WARN: Type inference failed for: r3v26 */
        /* JADX WARN: Type inference failed for: r3v27 */
        public static Unit b(Recomposer recomposer, MutableScatterSet mutableScatterSet, MutableScatterSet mutableScatterSet2, List list, List list2, MutableScatterSet mutableScatterSet3, List list3, MutableScatterSet mutableScatterSet4, Set set, long j) {
            boolean z;
            Unit unit;
            Object[] objArr;
            Object[] objArr2;
            char c;
            long[] jArr;
            int length;
            long j2;
            long j3;
            long j4;
            int i;
            long j5;
            int i2;
            int i3;
            Object[] objArr3;
            long[] jArr2;
            int length2;
            int i4;
            long j6;
            int i5;
            int i6;
            Recomposer recomposer2 = recomposer;
            if (recomposer2.getHasBroadcastFrameClockAwaiters()) {
                Trace trace = Trace.INSTANCE;
                Object objBeginSection = trace.beginSection("Recomposer:animation");
                try {
                    recomposer2.broadcastFrameClock.sendFrame(j);
                    Snapshot.INSTANCE.sendApplyNotifications();
                    Unit unit2 = Unit.INSTANCE;
                    trace.endSection(objBeginSection);
                } catch (Throwable th) {
                    Trace.INSTANCE.endSection(objBeginSection);
                    throw th;
                }
            }
            Object objBeginSection2 = Trace.INSTANCE.beginSection("Recomposer:recompose");
            try {
                recomposer2.recordComposerModifications();
                synchronized (recomposer2.stateLock) {
                    try {
                        MutableVector mutableVector = recomposer2.compositionInvalidations;
                        Object[] objArr4 = mutableVector.content;
                        int size = mutableVector.getSize();
                        z = false;
                        for (int i7 = 0; i7 < size; i7++) {
                            list.add((ControlledComposition) objArr4[i7]);
                        }
                        recomposer2.compositionInvalidations.clear();
                        Unit unit3 = Unit.INSTANCE;
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
                mutableScatterSet.clear();
                mutableScatterSet2.clear();
                while (true) {
                    if (list.isEmpty() && list2.isEmpty()) {
                        break;
                    }
                    try {
                        int size2 = list.size();
                        for (int i8 = 0; i8 < size2; i8++) {
                            ControlledComposition controlledComposition = (ControlledComposition) list.get(i8);
                            ControlledComposition controlledCompositionPerformRecompose = recomposer2.performRecompose(controlledComposition, mutableScatterSet);
                            if (controlledCompositionPerformRecompose != null) {
                                list3.add(controlledCompositionPerformRecompose);
                                Unit unit4 = Unit.INSTANCE;
                            }
                            mutableScatterSet2.add(controlledComposition);
                        }
                        list.clear();
                        if (mutableScatterSet.isNotEmpty() || recomposer2.compositionInvalidations.getSize() != 0) {
                            synchronized (recomposer2.stateLock) {
                                try {
                                    List listKnownCompositionsLocked = recomposer2.knownCompositionsLocked();
                                    int size3 = listKnownCompositionsLocked.size();
                                    for (int i9 = 0; i9 < size3; i9++) {
                                        ControlledComposition controlledComposition2 = (ControlledComposition) listKnownCompositionsLocked.get(i9);
                                        if (!mutableScatterSet2.contains(controlledComposition2) && controlledComposition2.observesAnyOf(set)) {
                                            list.add(controlledComposition2);
                                        }
                                    }
                                    MutableVector mutableVector2 = recomposer2.compositionInvalidations;
                                    int size4 = mutableVector2.getSize();
                                    int i10 = 0;
                                    int i11 = 0;
                                    while (true) {
                                        objArr = mutableVector2.content;
                                        if (i10 >= size4) {
                                            break;
                                        }
                                        ControlledComposition controlledComposition3 = (ControlledComposition) objArr[i10];
                                        if (!mutableScatterSet2.contains(controlledComposition3) && !list.contains(controlledComposition3)) {
                                            list.add(controlledComposition3);
                                            i11++;
                                        } else if (i11 > 0) {
                                            Object[] objArr5 = mutableVector2.content;
                                            objArr5[i10 - i11] = objArr5[i10];
                                        }
                                        i10++;
                                    }
                                    int i12 = size4 - i11;
                                    ArraysKt.fill(objArr, (Object) null, i12, size4);
                                    mutableVector2.setSize(i12);
                                    Unit unit5 = Unit.INSTANCE;
                                } catch (Throwable th3) {
                                    throw th3;
                                }
                            }
                        }
                        if (list.isEmpty()) {
                            try {
                                invokeSuspend$fillToInsert(list2, recomposer2);
                                while (!list2.isEmpty()) {
                                    mutableScatterSet3.plusAssign(recomposer2.performInsertValues(list2, mutableScatterSet));
                                    invokeSuspend$fillToInsert(list2, recomposer2);
                                }
                            } catch (Throwable th4) {
                                Recomposer.processCompositionError$default(recomposer2, th4, null, true, 2, null);
                                invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                unit = Unit.INSTANCE;
                            }
                        } else {
                            recomposer2 = recomposer;
                        }
                        z = false;
                    } catch (Throwable th5) {
                        try {
                            Recomposer.processCompositionError$default(recomposer, th5, null, true, 2, null);
                            invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                            unit = Unit.INSTANCE;
                            list.clear();
                        } catch (Throwable th6) {
                            list.clear();
                            throw th6;
                        }
                    }
                    Trace.INSTANCE.endSection(objBeginSection2);
                    return unit;
                }
                Snapshot current = Snapshot.INSTANCE.getCurrent();
                Snapshot transparentObserverMutableSnapshot = current instanceof MutableSnapshot ? new TransparentObserverMutableSnapshot((MutableSnapshot) current, null, null, true, false) : new TransparentObserverSnapshot(current, null, true, z);
                try {
                    Snapshot snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                    try {
                        if (list3.isEmpty()) {
                            if (mutableScatterSet3.isNotEmpty()) {
                                mutableScatterSet4.plusAssign(mutableScatterSet3);
                                objArr2 = ((ScatterSet) mutableScatterSet3).elements;
                                c = 7;
                                jArr = ((ScatterSet) mutableScatterSet3).metadata;
                                length = jArr.length - 2;
                                if (length >= 0) {
                                    i = 0;
                                    j2 = 128;
                                    j3 = 255;
                                    while (true) {
                                        j5 = jArr[i];
                                        j4 = -9187201950435737472L;
                                        if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            if (i != length) {
                                                break;
                                                break;
                                            }
                                            i++;
                                        } else {
                                            i2 = 8 - ((~(i - length)) >>> 31);
                                            for (i3 = 0; i3 < i2; i3++) {
                                                if ((j5 & 255) < 128) {
                                                    ((ControlledComposition) objArr2[(i << 3) + i3]).applyLateChanges();
                                                }
                                                j5 >>= 8;
                                            }
                                            if (i2 == 8) {
                                                break;
                                                break;
                                            }
                                            if (i != length) {
                                                break;
                                                break;
                                            }
                                            i++;
                                        }
                                    }
                                } else {
                                    j2 = 128;
                                    j3 = 255;
                                    j4 = -9187201950435737472L;
                                }
                                mutableScatterSet3.clear();
                            } else {
                                c = 7;
                                j2 = 128;
                                j3 = 255;
                                j4 = -9187201950435737472L;
                            }
                            if (mutableScatterSet4.isNotEmpty()) {
                                objArr3 = ((ScatterSet) mutableScatterSet4).elements;
                                jArr2 = ((ScatterSet) mutableScatterSet4).metadata;
                                length2 = jArr2.length - 2;
                                if (length2 >= 0) {
                                    i4 = 0;
                                    while (true) {
                                        j6 = jArr2[i4];
                                        if ((((~j6) << c) & j6 & j4) != j4) {
                                            if (i4 != length2) {
                                                break;
                                                break;
                                            }
                                            i4++;
                                        } else {
                                            i5 = 8 - ((~(i4 - length2)) >>> 31);
                                            for (i6 = 0; i6 < i5; i6++) {
                                                if ((j6 & j3) < j2) {
                                                    ((ControlledComposition) objArr3[(i4 << 3) + i6]).changesApplied();
                                                }
                                                j6 >>= 8;
                                            }
                                            if (i5 == 8) {
                                                break;
                                                break;
                                            }
                                            if (i4 != length2) {
                                                break;
                                                break;
                                            }
                                            i4++;
                                        }
                                    }
                                }
                                mutableScatterSet4.clear();
                            }
                            Unit unit6 = Unit.INSTANCE;
                            transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                            transparentObserverMutableSnapshot.dispose();
                            synchronized (recomposer.stateLock) {
                                recomposer.deriveStateLocked();
                                Snapshot.INSTANCE.notifyObjectsInitialized();
                                mutableScatterSet2.clear();
                                mutableScatterSet.clear();
                                recomposer.compositionsRemoved = null;
                                Trace.INSTANCE.endSection(objBeginSection2);
                                return Unit.INSTANCE;
                            }
                        }
                        recomposer2.changeCount = recomposer2.getChangeCount() + 1;
                        try {
                            int size5 = list3.size();
                            for (?? r3 = z; r3 < size5; r3++) {
                                mutableScatterSet4.add((ControlledComposition) list3.get(r3));
                            }
                            int size6 = list3.size();
                            for (?? r4 = z; r4 < size6; r4++) {
                                ((ControlledComposition) list3.get(r4)).applyChanges();
                            }
                            list3.clear();
                            if (mutableScatterSet3.isNotEmpty()) {
                                try {
                                    mutableScatterSet4.plusAssign(mutableScatterSet3);
                                    objArr2 = ((ScatterSet) mutableScatterSet3).elements;
                                    c = 7;
                                    jArr = ((ScatterSet) mutableScatterSet3).metadata;
                                    length = jArr.length - 2;
                                    if (length >= 0) {
                                        i = 0;
                                        j2 = 128;
                                        j3 = 255;
                                        while (true) {
                                            j5 = jArr[i];
                                            j4 = -9187201950435737472L;
                                            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                if (i != length) {
                                                    break;
                                                    break;
                                                }
                                                i++;
                                            } else {
                                                i2 = 8 - ((~(i - length)) >>> 31);
                                                while (i3 < i2) {
                                                    if ((j5 & 255) < 128) {
                                                        ((ControlledComposition) objArr2[(i << 3) + i3]).applyLateChanges();
                                                    }
                                                    j5 >>= 8;
                                                }
                                                if (i2 == 8) {
                                                    break;
                                                }
                                                if (i != length) {
                                                    break;
                                                }
                                                i++;
                                            }
                                        }
                                    } else {
                                        j2 = 128;
                                        j3 = 255;
                                        j4 = -9187201950435737472L;
                                    }
                                    mutableScatterSet3.clear();
                                } catch (Throwable th7) {
                                    try {
                                        Recomposer.processCompositionError$default(recomposer, th7, null, false, 6, null);
                                        invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                        unit = Unit.INSTANCE;
                                        mutableScatterSet3.clear();
                                        transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                        transparentObserverMutableSnapshot.dispose();
                                        Trace.INSTANCE.endSection(objBeginSection2);
                                        return unit;
                                    } catch (Throwable th8) {
                                        mutableScatterSet3.clear();
                                        throw th8;
                                    }
                                }
                            } else {
                                c = 7;
                                j2 = 128;
                                j3 = 255;
                                j4 = -9187201950435737472L;
                            }
                            if (mutableScatterSet4.isNotEmpty()) {
                                try {
                                    objArr3 = ((ScatterSet) mutableScatterSet4).elements;
                                    jArr2 = ((ScatterSet) mutableScatterSet4).metadata;
                                    length2 = jArr2.length - 2;
                                    if (length2 >= 0) {
                                        i4 = 0;
                                        while (true) {
                                            j6 = jArr2[i4];
                                            if ((((~j6) << c) & j6 & j4) != j4) {
                                                if (i4 != length2) {
                                                    break;
                                                    break;
                                                }
                                                i4++;
                                            } else {
                                                i5 = 8 - ((~(i4 - length2)) >>> 31);
                                                while (i6 < i5) {
                                                    if ((j6 & j3) < j2) {
                                                        ((ControlledComposition) objArr3[(i4 << 3) + i6]).changesApplied();
                                                    }
                                                    j6 >>= 8;
                                                }
                                                if (i5 == 8) {
                                                    break;
                                                }
                                                if (i4 != length2) {
                                                    break;
                                                }
                                                i4++;
                                            }
                                        }
                                    }
                                    mutableScatterSet4.clear();
                                } catch (Throwable th9) {
                                    try {
                                        Recomposer.processCompositionError$default(recomposer, th9, null, false, 6, null);
                                        invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                        unit = Unit.INSTANCE;
                                        mutableScatterSet4.clear();
                                        transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                        transparentObserverMutableSnapshot.dispose();
                                        Trace.INSTANCE.endSection(objBeginSection2);
                                        return unit;
                                    } catch (Throwable th10) {
                                        mutableScatterSet4.clear();
                                        throw th10;
                                    }
                                }
                            }
                            Unit unit7 = Unit.INSTANCE;
                            transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                            transparentObserverMutableSnapshot.dispose();
                            synchronized (recomposer.stateLock) {
                                recomposer.deriveStateLocked();
                            }
                            Snapshot.INSTANCE.notifyObjectsInitialized();
                            mutableScatterSet2.clear();
                            mutableScatterSet.clear();
                            recomposer.compositionsRemoved = null;
                            Trace.INSTANCE.endSection(objBeginSection2);
                            return Unit.INSTANCE;
                        } catch (Throwable th11) {
                            try {
                                Recomposer.processCompositionError$default(recomposer2, th11, null, false, 6, null);
                                invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                unit = Unit.INSTANCE;
                                list3.clear();
                                transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                transparentObserverMutableSnapshot.dispose();
                                Trace.INSTANCE.endSection(objBeginSection2);
                                return unit;
                            } catch (Throwable th12) {
                                list3.clear();
                                throw th12;
                            }
                        }
                    } catch (Throwable th13) {
                        transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                        throw th13;
                    }
                } catch (Throwable th14) {
                    transparentObserverMutableSnapshot.dispose();
                    throw th14;
                }
                Trace.INSTANCE.endSection(objBeginSection2);
                return unit;
            } catch (Throwable th15) {
                Trace.INSTANCE.endSection(objBeginSection2);
                throw th15;
            }
        }

        /* JADX WARN: Code duplicated, block: B:21:0x0078 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:22:0x007a A[Catch: all -> 0x002e, LOOP:1: B:12:0x0044->B:22:0x007a, LOOP_END, TryCatch #0 {all -> 0x002e, blocks: (B:4:0x000f, B:6:0x001f, B:9:0x0031, B:12:0x0044, B:14:0x0055, B:16:0x005f, B:18:0x0065, B:19:0x0072, B:24:0x0085, B:27:0x0092, B:29:0x009d, B:31:0x00a7, B:33:0x00ad, B:34:0x00b7, B:37:0x00bf, B:38:0x00c2, B:41:0x00d2, B:43:0x00dd, B:45:0x00e7, B:47:0x00ed, B:48:0x00fa, B:51:0x0102, B:52:0x0105, B:22:0x007a), top: B:57:0x000f }] */
        /* JADX WARN: Code duplicated, block: B:36:0x00bd A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:37:0x00bf A[Catch: all -> 0x002e, LOOP:3: B:27:0x0092->B:37:0x00bf, LOOP_END, TryCatch #0 {all -> 0x002e, blocks: (B:4:0x000f, B:6:0x001f, B:9:0x0031, B:12:0x0044, B:14:0x0055, B:16:0x005f, B:18:0x0065, B:19:0x0072, B:24:0x0085, B:27:0x0092, B:29:0x009d, B:31:0x00a7, B:33:0x00ad, B:34:0x00b7, B:37:0x00bf, B:38:0x00c2, B:41:0x00d2, B:43:0x00dd, B:45:0x00e7, B:47:0x00ed, B:48:0x00fa, B:51:0x0102, B:52:0x0105, B:22:0x007a), top: B:57:0x000f }] */
        /* JADX WARN: Code duplicated, block: B:50:0x0100 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:51:0x0102 A[Catch: all -> 0x002e, LOOP:5: B:41:0x00d2->B:51:0x0102, LOOP_END, TryCatch #0 {all -> 0x002e, blocks: (B:4:0x000f, B:6:0x001f, B:9:0x0031, B:12:0x0044, B:14:0x0055, B:16:0x005f, B:18:0x0065, B:19:0x0072, B:24:0x0085, B:27:0x0092, B:29:0x009d, B:31:0x00a7, B:33:0x00ad, B:34:0x00b7, B:37:0x00bf, B:38:0x00c2, B:41:0x00d2, B:43:0x00dd, B:45:0x00e7, B:47:0x00ed, B:48:0x00fa, B:51:0x0102, B:52:0x0105, B:22:0x007a), top: B:57:0x000f }] */
        /* JADX WARN: Code duplicated, block: B:61:0x0085 A[EDGE_INSN: B:61:0x0085->B:24:0x0085 BREAK  A[LOOP:1: B:12:0x0044->B:22:0x007a], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:66:0x00c2 A[EDGE_INSN: B:66:0x00c2->B:38:0x00c2 BREAK  A[LOOP:3: B:27:0x0092->B:37:0x00bf], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:71:0x0105 A[EDGE_INSN: B:71:0x0105->B:52:0x0105 BREAK  A[LOOP:5: B:41:0x00d2->B:51:0x0102], SYNTHETIC] */
        private static final void invokeSuspend$clearRecompositionState(Recomposer recomposer, List<ControlledComposition> list, List<MovableContentStateReference> list2, List<ControlledComposition> list3, MutableScatterSet<ControlledComposition> mutableScatterSet, MutableScatterSet<ControlledComposition> mutableScatterSet2, MutableScatterSet<Object> mutableScatterSet3, MutableScatterSet<ControlledComposition> mutableScatterSet4) {
            char c;
            long j;
            long j2;
            synchronized (recomposer.stateLock) {
                try {
                    list.clear();
                    list2.clear();
                    int size = list3.size();
                    for (int i = 0; i < size; i++) {
                        ControlledComposition controlledComposition = list3.get(i);
                        controlledComposition.abandonChanges();
                        recomposer.recordFailedCompositionLocked(controlledComposition);
                    }
                    list3.clear();
                    Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
                    long[] jArr = ((ScatterSet) mutableScatterSet).metadata;
                    int length = jArr.length - 2;
                    if (length >= 0) {
                        int i2 = 0;
                        j = 255;
                        while (true) {
                            long j3 = jArr[i2];
                            c = 7;
                            j2 = -9187201950435737472L;
                            if ((((~j3) << 7) & j3 & (-9187201950435737472L)) == -9187201950435737472L) {
                                if (i2 != length) {
                                    break;
                                    break;
                                }
                                i2++;
                            } else {
                                int i3 = 8 - ((~(i2 - length)) >>> 31);
                                for (int i4 = 0; i4 < i3; i4++) {
                                    if ((j3 & 255) < 128) {
                                        ControlledComposition controlledComposition2 = (ControlledComposition) objArr[(i2 << 3) + i4];
                                        controlledComposition2.abandonChanges();
                                        recomposer.recordFailedCompositionLocked(controlledComposition2);
                                    }
                                    j3 >>= 8;
                                }
                                if (i3 != 8) {
                                    break;
                                } else if (i2 != length) {
                                    break;
                                } else {
                                    i2++;
                                }
                            }
                        }
                    } else {
                        c = 7;
                        j = 255;
                        j2 = -9187201950435737472L;
                    }
                    mutableScatterSet.clear();
                    Object[] objArr2 = ((ScatterSet) mutableScatterSet2).elements;
                    long[] jArr2 = ((ScatterSet) mutableScatterSet2).metadata;
                    int length2 = jArr2.length - 2;
                    if (length2 >= 0) {
                        int i5 = 0;
                        while (true) {
                            long j4 = jArr2[i5];
                            if ((((~j4) << c) & j4 & j2) == j2) {
                                if (i5 != length2) {
                                    break;
                                    break;
                                }
                                i5++;
                            } else {
                                int i6 = 8 - ((~(i5 - length2)) >>> 31);
                                for (int i7 = 0; i7 < i6; i7++) {
                                    if ((j4 & j) < 128) {
                                        ((ControlledComposition) objArr2[(i5 << 3) + i7]).changesApplied();
                                    }
                                    j4 >>= 8;
                                }
                                if (i6 != 8) {
                                    break;
                                } else if (i5 != length2) {
                                    break;
                                } else {
                                    i5++;
                                }
                            }
                        }
                    }
                    mutableScatterSet2.clear();
                    mutableScatterSet3.clear();
                    Object[] objArr3 = ((ScatterSet) mutableScatterSet4).elements;
                    long[] jArr3 = ((ScatterSet) mutableScatterSet4).metadata;
                    int length3 = jArr3.length - 2;
                    if (length3 >= 0) {
                        int i8 = 0;
                        while (true) {
                            long j5 = jArr3[i8];
                            if ((((~j5) << c) & j5 & j2) == j2) {
                                if (i8 != length3) {
                                    break;
                                    break;
                                }
                                i8++;
                            } else {
                                int i9 = 8 - ((~(i8 - length3)) >>> 31);
                                for (int i10 = 0; i10 < i9; i10++) {
                                    if ((j5 & j) < 128) {
                                        ControlledComposition controlledComposition3 = (ControlledComposition) objArr3[(i8 << 3) + i10];
                                        controlledComposition3.abandonChanges();
                                        recomposer.recordFailedCompositionLocked(controlledComposition3);
                                    }
                                    j5 >>= 8;
                                }
                                if (i9 != 8) {
                                    break;
                                } else if (i8 != length3) {
                                    break;
                                } else {
                                    i8++;
                                }
                            }
                        }
                    }
                    mutableScatterSet4.clear();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        private static final void invokeSuspend$fillToInsert(List<MovableContentStateReference> list, Recomposer recomposer) {
            list.clear();
            synchronized (recomposer.stateLock) {
                try {
                    List list2 = recomposer.movableContentAwaitingInsert;
                    int size = list2.size();
                    for (int i = 0; i < size; i++) {
                        list.add((MovableContentStateReference) list2.get(i));
                    }
                    recomposer.movableContentAwaitingInsert.clear();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MonotonicFrameClock monotonicFrameClock, Continuation<? super Unit> continuation) {
            C00782 c00782 = Recomposer.this.new C00782(continuation);
            c00782.L$0 = monotonicFrameClock;
            return c00782.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x00bb  */
        /* JADX WARN: Code duplicated, block: B:17:0x00d8  */
        /* JADX WARN: Code duplicated, block: B:20:0x00e6  */
        /* JADX WARN: Code duplicated, block: B:23:0x0108  */
        /* JADX WARN: Code duplicated, block: B:25:0x0120  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0108 -> B:24:0x0111). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0120 -> B:12:0x00b3). Please report as a decompilation issue!!! */
        /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
            java.lang.StackOverflowError
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                Method dump skipped, instruction units count: 301
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C00782.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "parentFrameClock", "Landroidx/compose/runtime/MonotonicFrameClock;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2", f = "Recomposer.kt", i = {0, 0, 0, 1}, l = {PointerIconCompat.TYPE_COPY, 1031, 1032}, m = "invokeSuspend", n = {"recomposeCoroutineScope", "frameSignal", "frameLoop", "frameLoop"}, s = {"L$0", "L$1", "L$2", "L$0"}, v = 1)
    public static final class C00792 extends SuspendLambda implements Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> {
        final /* synthetic */ CoroutineContext $recomposeCoroutineContext;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ Recomposer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00792(CoroutineContext coroutineContext, Recomposer recomposer, Continuation<? super C00792> continuation) {
            super(3, continuation);
            this.$recomposeCoroutineContext = coroutineContext;
            this.this$0 = recomposer;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MonotonicFrameClock monotonicFrameClock, Continuation<? super Unit> continuation) {
            C00792 c00792 = new C00792(this.$recomposeCoroutineContext, this.this$0, continuation);
            c00792.L$0 = coroutineScope;
            c00792.L$1 = monotonicFrameClock;
            return c00792.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:22:0x00a8  */
        /* JADX WARN: Code duplicated, block: B:78:0x018b  */
        /* JADX WARN: Code duplicated, block: B:81:0x01a2 A[PHI: r2
          0x01a2: PHI (r2v7 kotlinx.coroutines.Job) = (r2v5 kotlinx.coroutines.Job), (r2v11 kotlinx.coroutines.Job) binds: [B:79:0x019f, B:10:0x0020] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x00b6, code lost:
        
            if (r10.awaitWorkAvailable(r18) == r1) goto L83;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x01aa, code lost:
        
            if (kotlinx.coroutines.JobKt.cancelAndJoin(r2, r18) == r1) goto L83;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00b6 -> B:25:0x00ba). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Job jobLaunch$default;
            CoroutineScope coroutineScope;
            ProduceFrameSignal produceFrameSignal;
            Job job;
            MutableScatterSet mutableScatterSet;
            Continuation<Unit> continuationRequestFrameLocked;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            int i2 = 0;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                MonotonicFrameClock monotonicFrameClock = (MonotonicFrameClock) this.L$1;
                CoroutineContext coroutineContext = this.$recomposeCoroutineContext;
                Job.Key key = Job.Key;
                boolean z = coroutineContext.get(key) == null;
                CoroutineContext coroutineContext2 = this.$recomposeCoroutineContext;
                if (!z) {
                    PreconditionsKt.throwIllegalArgumentException("recomposeCoroutineContext may not contain a Job; found " + coroutineContext2.get(key));
                }
                CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(coroutineScope2.getCoroutineContext().plus(this.$recomposeCoroutineContext).plus(JobKt.Job(JobKt.getJob(coroutineScope2.getCoroutineContext()))));
                ProduceFrameSignal produceFrameSignal2 = new ProduceFrameSignal();
                jobLaunch$default = BuildersKt.launch$default(coroutineScope2, (CoroutineContext) null, (CoroutineStart) null, new Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$frameLoop$1(this.this$0, monotonicFrameClock, produceFrameSignal2, null), 3, (Object) null);
                coroutineScope = CoroutineScope;
                produceFrameSignal = produceFrameSignal2;
                if (this.this$0.getShouldKeepRecomposing()) {
                    Recomposer recomposer = this.this$0;
                    this.L$0 = coroutineScope;
                    this.L$1 = produceFrameSignal;
                    this.L$2 = jobLaunch$default;
                    this.label = 1;
                } else {
                    job = JobKt.getJob(coroutineScope.getCoroutineContext());
                    this.L$0 = jobLaunch$default;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.label = 2;
                    if (JobKt.cancelAndJoin(job, this) != coroutine_suspended) {
                        this.L$0 = null;
                        this.label = 3;
                    }
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                jobLaunch$default = (Job) this.L$2;
                produceFrameSignal = (ProduceFrameSignal) this.L$1;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                Recomposer recomposer2 = this.this$0;
                synchronized (recomposer2.stateLock) {
                    mutableScatterSet = recomposer2.snapshotInvalidations;
                    if (mutableScatterSet.isNotEmpty()) {
                        recomposer2.snapshotInvalidations = new MutableScatterSet(i2, 1, (DefaultConstructorMarker) null);
                    }
                }
                Set<? extends Object> setWrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(mutableScatterSet);
                if (!setWrapIntoSet.isEmpty()) {
                    List listKnownCompositionsLocked = recomposer2.knownCompositionsLocked();
                    int size = listKnownCompositionsLocked.size();
                    for (int i3 = i2; i3 < size; i3++) {
                        ((ControlledComposition) listKnownCompositionsLocked.get(i3)).recordModificationsOf(setWrapIntoSet);
                    }
                }
                MutableVector mutableVector = recomposer2.compositionInvalidations;
                Object[] objArr = mutableVector.content;
                int size2 = mutableVector.getSize();
                int i4 = i2;
                while (i4 < size2) {
                    ControlledComposition controlledComposition = (ControlledComposition) objArr[i4];
                    synchronized (recomposer2.stateLock) {
                        recomposer2.concurrentCompositionsOutstanding++;
                    }
                    BuildersKt.launch$default(coroutineScope, CompositionKt.getRecomposeCoroutineContext(controlledComposition), (CoroutineStart) null, new Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$2(recomposer2, controlledComposition, null), 2, (Object) null);
                    i4++;
                    size2 = size2;
                    objArr = objArr;
                }
                recomposer2.compositionInvalidations.clear();
                synchronized (recomposer2.stateLock) {
                    if (recomposer2.deriveStateLocked() != null) {
                        throw new IllegalStateException("called outside of runRecomposeAndApplyChanges");
                    }
                    Unit unit = Unit.INSTANCE;
                    throw th;
                }
                Object obj2 = this.this$0.stateLock;
                Recomposer recomposer3 = this.this$0;
                synchronized (obj2) {
                    continuationRequestFrameLocked = recomposer3.getHasConcurrentFrameWorkLocked() ? produceFrameSignal.requestFrameLocked() : null;
                }
                if (continuationRequestFrameLocked != null) {
                    Result.Companion companion = Result.Companion;
                    continuationRequestFrameLocked.resumeWith(Result.constructor-impl(Unit.INSTANCE));
                }
                i2 = 0;
                if (this.this$0.getShouldKeepRecomposing()) {
                    Recomposer recomposer4 = this.this$0;
                    this.L$0 = coroutineScope;
                    this.L$1 = produceFrameSignal;
                    this.L$2 = jobLaunch$default;
                    this.label = 1;
                } else {
                    job = JobKt.getJob(coroutineScope.getCoroutineContext());
                    this.L$0 = jobLaunch$default;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.label = 2;
                    if (JobKt.cancelAndJoin(job, this) != coroutine_suspended) {
                        this.L$0 = null;
                        this.label = 3;
                    }
                }
                return coroutine_suspended;
            }
            if (i == 2) {
                jobLaunch$default = (Job) this.L$0;
                ResultKt.throwOnFailure(obj);
                this.L$0 = null;
                this.label = 3;
            } else {
                if (i != 3) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public Recomposer(CoroutineContext coroutineContext) {
        BroadcastFrameClock broadcastFrameClock = new BroadcastFrameClock(new Function0() { // from class: r7c
            public final Object invoke() {
                return Recomposer.f(this.b);
            }
        });
        this.broadcastFrameClock = broadcastFrameClock;
        this.nextFrameEndCallbackQueue = new NextFrameEndCallbackQueue(new Function0() { // from class: s7c
            public final Object invoke() {
                return Recomposer.a(this.b);
            }
        });
        this.stateLock = new Object();
        this._knownCompositions = new ArrayList();
        this.snapshotInvalidations = new MutableScatterSet<>(0, 1, (DefaultConstructorMarker) null);
        this.compositionInvalidations = new MutableVector<>(new ControlledComposition[16], 0);
        this.compositionsAwaitingApply = new ArrayList();
        this.movableContentAwaitingInsert = new ArrayList();
        this.movableContentRemoved = MultiValueMap.m2468constructorimpl$default(null, 1, null);
        this.movableContentNestedStatesAvailable = new NestedContentMap();
        this.movableContentStatesAvailable = ScatterMapKt.mutableScatterMapOf();
        this.movableContentNestedExtractionsPending = MultiValueMap.m2468constructorimpl$default(null, 1, null);
        this._state = StateFlowKt.MutableStateFlow(State.Inactive);
        this.pausedScopes = new SnapshotThreadLocal<>();
        CompletableJob completableJobJob = JobKt.Job(coroutineContext.get(Job.Key));
        completableJobJob.invokeOnCompletion(new Function1() { // from class: t7c
            public final Object invoke(Object obj) {
                return Recomposer.effectJob$lambda$0$0(this.b, (Throwable) obj);
            }
        });
        this.effectJob = completableJobJob;
        this.effectCoroutineContext = coroutineContext.plus(broadcastFrameClock).plus(completableJobJob);
        this.recomposerInfo = new RecomposerInfoImpl();
    }

    public static Unit a(Recomposer recomposer) {
        recomposer.onNewFrameAwaiter();
        return Unit.INSTANCE;
    }

    private final void addKnownCompositionLocked(ControlledComposition composition) {
        this._knownCompositions.add(composition);
        this._knownCompositionsCache = null;
    }

    private final void applyAndCheck(MutableSnapshot snapshot) {
        try {
            if (snapshot.apply() instanceof SnapshotApplyResult.Failure) {
                throw new IllegalStateException("Unsupported concurrent change during composition. A state object was modified by composition as well as being modified outside composition.");
            }
            snapshot.dispose();
        } catch (Throwable th) {
            snapshot.dispose();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitWorkAvailable(Continuation<? super Unit> continuation) {
        Continuation continuation2;
        if (getHasSchedulingWork()) {
            return Unit.INSTANCE;
        }
        Continuation cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        synchronized (this.stateLock) {
            if (getHasSchedulingWork()) {
                continuation2 = cancellableContinuationImpl;
            } else {
                this.workContinuation = cancellableContinuationImpl;
                continuation2 = null;
            }
        }
        if (continuation2 != null) {
            Result.Companion companion = Result.Companion;
            continuation2.resumeWith(Result.constructor-impl(Unit.INSTANCE));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    public static Unit b(ControlledComposition controlledComposition, MutableScatterSet mutableScatterSet, Object obj) {
        controlledComposition.recordWriteOf(obj);
        if (mutableScatterSet != null) {
            mutableScatterSet.add(obj);
        }
        return Unit.INSTANCE;
    }

    public static CancellableContinuation c(Recomposer recomposer, List list, List list2, ProduceFrameSignal produceFrameSignal, long j) {
        int i;
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        if (recomposer.getHasBroadcastFrameClockAwaiters()) {
            Trace trace = Trace.INSTANCE;
            Object objBeginSection = trace.beginSection("Recomposer:animation");
            try {
                recomposer.broadcastFrameClock.sendFrame(j);
                Snapshot.INSTANCE.sendApplyNotifications();
                Unit unit = Unit.INSTANCE;
                trace.endSection(objBeginSection);
            } catch (Throwable th) {
                Trace.INSTANCE.endSection(objBeginSection);
                throw th;
            }
        }
        Object objBeginSection2 = Trace.INSTANCE.beginSection("Recomposer:recompose");
        try {
            recomposer.recordComposerModifications();
            synchronized (recomposer.stateLock) {
                try {
                    List<ControlledComposition> list3 = recomposer.compositionsAwaitingApply;
                    int size = list3.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        list2.add(list3.get(i2));
                    }
                    recomposer.compositionsAwaitingApply.clear();
                    MutableVector<ControlledComposition> mutableVector = recomposer.compositionInvalidations;
                    ControlledComposition[] controlledCompositionArr = mutableVector.content;
                    int size2 = mutableVector.getSize();
                    for (int i3 = 0; i3 < size2; i3++) {
                        list.add(controlledCompositionArr[i3]);
                    }
                    recomposer.compositionInvalidations.clear();
                    produceFrameSignal.takeFrameRequestLocked();
                    Unit unit2 = Unit.INSTANCE;
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            MutableScatterSet<Object> mutableScatterSet = new MutableScatterSet<>(0, 1, (DefaultConstructorMarker) null);
            try {
                int size3 = list.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    ControlledComposition controlledCompositionPerformRecompose = recomposer.performRecompose((ControlledComposition) list.get(i4), mutableScatterSet);
                    if (controlledCompositionPerformRecompose != null) {
                        list2.add(controlledCompositionPerformRecompose);
                    }
                }
                list.clear();
                if (!list2.isEmpty()) {
                    recomposer.changeCount++;
                }
                try {
                    int size4 = list2.size();
                    for (i = 0; i < size4; i++) {
                        ((ControlledComposition) list2.get(i)).applyChanges();
                    }
                    list2.clear();
                    synchronized (recomposer.stateLock) {
                        cancellableContinuationDeriveStateLocked = recomposer.deriveStateLocked();
                    }
                    Trace.INSTANCE.endSection(objBeginSection2);
                    return cancellableContinuationDeriveStateLocked;
                } catch (Throwable th3) {
                    list2.clear();
                    throw th3;
                }
            } catch (Throwable th4) {
                list.clear();
                throw th4;
            }
        } catch (Throwable th5) {
            Trace.INSTANCE.endSection(objBeginSection2);
            throw th5;
        }
    }

    private final void clearKnownCompositionsLocked() {
        List<ControlledComposition> listKnownCompositionsLocked = knownCompositionsLocked();
        int size = listKnownCompositionsLocked.size();
        for (int i = 0; i < size; i++) {
            unregisterCompositionLocked(listKnownCompositionsLocked.get(i));
        }
        this._knownCompositions.clear();
        this._knownCompositionsCache = CollectionsKt.emptyList();
    }

    private final <T> T composing(ControlledComposition composition, MutableScatterSet<Object> modifiedValues, Function0<? extends T> block) {
        MutableSnapshot mutableSnapshotTakeMutableSnapshot = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, modifiedValues));
        try {
            Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot.makeCurrent();
            try {
                T t = (T) block.invoke();
                InlineMarker.finallyStart(1);
                mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                InlineMarker.finallyEnd(1);
                InlineMarker.finallyStart(1);
                applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                return t;
            } finally {
                InlineMarker.finallyStart(1);
                mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                InlineMarker.finallyEnd(1);
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public static Unit d(ControlledComposition controlledComposition, Object obj) {
        controlledComposition.recordReadOf(obj);
        return Unit.INSTANCE;
    }

    private static final void deletedMovableContent$lambda$0$recordNestedStatesOf(Recomposer recomposer, MovableContentStateReference movableContentStateReference, MovableContentStateReference movableContentStateReference2) {
        List<MovableContentStateReference> nestedReferences$runtime = movableContentStateReference2.getNestedReferences$runtime();
        if (nestedReferences$runtime != null) {
            int size = nestedReferences$runtime.size();
            for (int i = 0; i < size; i++) {
                MovableContentStateReference movableContentStateReference3 = nestedReferences$runtime.get(i);
                recomposer.movableContentNestedStatesAvailable.add(movableContentStateReference3.getContent$runtime(), new NestedMovableContent(movableContentStateReference3, movableContentStateReference));
                deletedMovableContent$lambda$0$recordNestedStatesOf(recomposer, movableContentStateReference, movableContentStateReference3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CancellableContinuation<Unit> deriveStateLocked() {
        State state;
        if (((State) this._state.getValue()).compareTo(State.ShuttingDown) <= 0) {
            clearKnownCompositionsLocked();
            this.snapshotInvalidations = new MutableScatterSet<>(0, 1, (DefaultConstructorMarker) null);
            this.compositionInvalidations.clear();
            this.compositionsAwaitingApply.clear();
            this.movableContentAwaitingInsert.clear();
            this.failedCompositions = null;
            CancellableContinuation<? super Unit> cancellableContinuation = this.workContinuation;
            if (cancellableContinuation != null) {
                CancellableContinuation.DefaultImpls.cancel$default(cancellableContinuation, (Throwable) null, 1, (Object) null);
            }
            this.workContinuation = null;
            this.errorState = null;
            return null;
        }
        if (this.errorState != null) {
            state = State.Inactive;
        } else if (this.runnerJob == null) {
            this.snapshotInvalidations = new MutableScatterSet<>(0, 1, (DefaultConstructorMarker) null);
            this.compositionInvalidations.clear();
            state = (getHasBroadcastFrameClockAwaitersLocked() || getHasNextFrameEndAwaitersLocked()) ? State.InactivePendingWork : State.Inactive;
        } else {
            state = (this.compositionInvalidations.getSize() == 0 && !this.snapshotInvalidations.isNotEmpty() && this.compositionsAwaitingApply.isEmpty() && this.movableContentAwaitingInsert.isEmpty() && this.concurrentCompositionsOutstanding <= 0 && !getHasBroadcastFrameClockAwaitersLocked() && !getHasNextFrameEndAwaitersLocked() && !MultiValueMap.m2477isNotEmptyimpl(this.movableContentRemoved)) ? State.Idle : State.PendingWork;
        }
        this._state.setValue(state);
        if (state != State.PendingWork) {
            return null;
        }
        CancellableContinuation<? super Unit> cancellableContinuation2 = this.workContinuation;
        this.workContinuation = null;
        return cancellableContinuation2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void discardUnusedMovableContentState() {
        int i;
        MutableObjectList mutableObjectListEmptyObjectList;
        synchronized (this.stateLock) {
            try {
                if (MultiValueMap.m2477isNotEmptyimpl(this.movableContentRemoved)) {
                    ObjectList objectListM2482valuesimpl = MultiValueMap.m2482valuesimpl(this.movableContentRemoved);
                    MultiValueMap.m2466clearimpl(this.movableContentRemoved);
                    this.movableContentNestedStatesAvailable.clear();
                    MultiValueMap.m2466clearimpl(this.movableContentNestedExtractionsPending);
                    mutableObjectListEmptyObjectList = new MutableObjectList(objectListM2482valuesimpl.getSize());
                    Object[] objArr = objectListM2482valuesimpl.content;
                    int i2 = objectListM2482valuesimpl._size;
                    for (int i3 = 0; i3 < i2; i3++) {
                        MovableContentStateReference movableContentStateReference = (MovableContentStateReference) objArr[i3];
                        mutableObjectListEmptyObjectList.add(TuplesKt.to(movableContentStateReference, this.movableContentStatesAvailable.get(movableContentStateReference)));
                    }
                    this.movableContentStatesAvailable.clear();
                } else {
                    mutableObjectListEmptyObjectList = ObjectListKt.emptyObjectList();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Object[] objArr2 = ((ObjectList) mutableObjectListEmptyObjectList).content;
        int i4 = ((ObjectList) mutableObjectListEmptyObjectList)._size;
        for (i = 0; i < i4; i++) {
            Pair pair = (Pair) objArr2[i];
            MovableContentStateReference movableContentStateReference2 = (MovableContentStateReference) pair.component1();
            MovableContentState movableContentState = (MovableContentState) pair.component2();
            if (movableContentState != null) {
                movableContentStateReference2.getComposition().disposeUnusedMovableContent(movableContentState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit effectJob$lambda$0$0(final Recomposer recomposer, final Throwable th) {
        CancellableContinuation<? super Unit> cancellableContinuation;
        CancellableContinuation<? super Unit> cancellableContinuation2;
        CancellationException CancellationException = ExceptionsKt.CancellationException("Recomposer effect job completed", th);
        synchronized (recomposer.stateLock) {
            try {
                Job job = recomposer.runnerJob;
                cancellableContinuation = null;
                if (job != null) {
                    recomposer._state.setValue(State.ShuttingDown);
                    if (recomposer.isClosed) {
                        cancellableContinuation2 = recomposer.workContinuation;
                        if (cancellableContinuation2 != null) {
                        }
                        recomposer.workContinuation = null;
                        job.invokeOnCompletion(new Function1() { // from class: p7c
                            public final Object invoke(Object obj) {
                                return Recomposer.effectJob$lambda$0$0$0$0(this.b, th, (Throwable) obj);
                            }
                        });
                        cancellableContinuation = cancellableContinuation2;
                    } else {
                        job.cancel(CancellationException);
                    }
                    cancellableContinuation2 = null;
                    recomposer.workContinuation = null;
                    job.invokeOnCompletion(new Function1() { // from class: p7c
                        public final Object invoke(Object obj) {
                            return Recomposer.effectJob$lambda$0$0$0$0(this.b, th, (Throwable) obj);
                        }
                    });
                    cancellableContinuation = cancellableContinuation2;
                } else {
                    recomposer.closeCause = CancellationException;
                    recomposer._state.setValue(State.ShutDown);
                    Unit unit = Unit.INSTANCE;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        if (cancellableContinuation != null) {
            Result.Companion companion = Result.Companion;
            cancellableContinuation.resumeWith(Result.constructor-impl(Unit.INSTANCE));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit effectJob$lambda$0$0$0$0(Recomposer recomposer, Throwable th, Throwable th2) {
        synchronized (recomposer.stateLock) {
            if (th == null) {
                th = null;
            } else if (th2 != null) {
                try {
                    if (th2 instanceof CancellationException) {
                        th2 = null;
                    }
                    if (th2 != null) {
                        kotlin.ExceptionsKt.addSuppressed(th, th2);
                    }
                } catch (Throwable th3) {
                    throw th3;
                }
            }
            recomposer.closeCause = th;
            recomposer._state.setValue(State.ShutDown);
        }
        return Unit.INSTANCE;
    }

    public static Unit f(Recomposer recomposer) {
        recomposer.onNewFrameAwaiter();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasBroadcastFrameClockAwaiters() {
        boolean hasBroadcastFrameClockAwaitersLocked;
        synchronized (this.stateLock) {
            hasBroadcastFrameClockAwaitersLocked = getHasBroadcastFrameClockAwaitersLocked();
        }
        return hasBroadcastFrameClockAwaitersLocked;
    }

    private final boolean getHasBroadcastFrameClockAwaitersLocked() {
        return !this.frameClockPaused && this.broadcastFrameClock.getHasAwaiters();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasConcurrentFrameWorkLocked() {
        return !this.compositionsAwaitingApply.isEmpty() || getHasBroadcastFrameClockAwaitersLocked() || getHasNextFrameEndAwaitersLocked();
    }

    private final boolean getHasFrameWorkLocked() {
        return this.compositionInvalidations.getSize() != 0 || getHasBroadcastFrameClockAwaitersLocked() || getHasNextFrameEndAwaitersLocked() || MultiValueMap.m2477isNotEmptyimpl(this.movableContentRemoved);
    }

    private final boolean getHasNextFrameEndAwaitersLocked() {
        return !this.frameClockPaused && this.nextFrameEndCallbackQueue.getHasAwaiters();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasSchedulingWork() {
        boolean z;
        synchronized (this.stateLock) {
            z = this.snapshotInvalidations.isNotEmpty() || this.compositionInvalidations.getSize() != 0 || getHasBroadcastFrameClockAwaitersLocked() || getHasNextFrameEndAwaitersLocked();
        }
        return z;
    }

    private static /* synthetic */ void getRegistrationObservers$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getShouldKeepRecomposing() {
        boolean z;
        synchronized (this.stateLock) {
            z = this.isClosed;
        }
        if (!z) {
            return true;
        }
        Iterator it = this.effectJob.getChildren().iterator();
        while (it.hasNext()) {
            if (((Job) it.next()).isActive()) {
                return true;
            }
        }
        return false;
    }

    @Deprecated(message = "Replaced by currentState as a StateFlow", replaceWith = @ReplaceWith(expression = "currentState", imports = {}))
    public static /* synthetic */ void getState$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ControlledComposition> knownCompositions() {
        List<ControlledComposition> listKnownCompositionsLocked;
        synchronized (this.stateLock) {
            listKnownCompositionsLocked = knownCompositionsLocked();
        }
        return listKnownCompositionsLocked;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ControlledComposition> knownCompositionsLocked() {
        List list = this._knownCompositionsCache;
        if (list != null) {
            return list;
        }
        List<ControlledComposition> list2 = this._knownCompositions;
        List<ControlledComposition> listEmptyList = list2.isEmpty() ? CollectionsKt.emptyList() : new ArrayList(list2);
        this._knownCompositionsCache = listEmptyList;
        return listEmptyList;
    }

    private final void onNewFrameAwaiter() {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (this.stateLock) {
            cancellableContinuationDeriveStateLocked = deriveStateLocked();
            if (((State) this._state.getValue()).compareTo(State.ShuttingDown) <= 0) {
                throw ExceptionsKt.CancellationException("Recomposer shutdown; frame clock awaiter will never resume", this.closeCause);
            }
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.Companion;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.constructor-impl(Unit.INSTANCE));
        }
    }

    private final void performInitialMovableContentInserts(ControlledComposition composition) {
        synchronized (this.stateLock) {
            List<MovableContentStateReference> list = this.movableContentAwaitingInsert;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (Intrinsics.areEqual(list.get(i).getComposition(), composition)) {
                    Unit unit = Unit.INSTANCE;
                    ArrayList arrayList = new ArrayList();
                    performInitialMovableContentInserts$fillToInsert(arrayList, this, composition);
                    while (!arrayList.isEmpty()) {
                        performInsertValues(arrayList, null);
                        performInitialMovableContentInserts$fillToInsert(arrayList, this, composition);
                    }
                    return;
                }
            }
        }
    }

    private static final void performInitialMovableContentInserts$fillToInsert(List<MovableContentStateReference> list, Recomposer recomposer, ControlledComposition controlledComposition) {
        list.clear();
        synchronized (recomposer.stateLock) {
            try {
                Iterator<MovableContentStateReference> it = recomposer.movableContentAwaitingInsert.iterator();
                while (it.hasNext()) {
                    MovableContentStateReference next = it.next();
                    if (Intrinsics.areEqual(next.getComposition(), controlledComposition)) {
                        list.add(next);
                        it.remove();
                    }
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ControlledComposition> performInsertValues(List<MovableContentStateReference> references, MutableScatterSet<Object> modifiedValues) {
        ArrayList arrayList;
        NestedMovableContent nestedMovableContentRemoveLast;
        HashMap map = new HashMap(references.size());
        int size = references.size();
        for (int i = 0; i < size; i++) {
            MovableContentStateReference movableContentStateReference = references.get(i);
            ControlledComposition composition$runtime = movableContentStateReference.getComposition();
            Object arrayList2 = map.get(composition$runtime);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                map.put(composition$runtime, arrayList2);
            }
            ((ArrayList) arrayList2).add(movableContentStateReference);
        }
        for (Map.Entry entry : map.entrySet()) {
            ControlledComposition controlledComposition = (ControlledComposition) entry.getKey();
            List list = (List) entry.getValue();
            if (controlledComposition.isComposing()) {
                ComposerKt.composeImmediateRuntimeError("Check failed");
            }
            MutableSnapshot mutableSnapshotTakeMutableSnapshot = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(controlledComposition), writeObserverOf(controlledComposition, modifiedValues));
            try {
                Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot.makeCurrent();
                try {
                    synchronized (this.stateLock) {
                        try {
                            arrayList = new ArrayList(list.size());
                            int size2 = list.size();
                            for (int i2 = 0; i2 < size2; i2++) {
                                MovableContentStateReference movableContentStateReference2 = (MovableContentStateReference) list.get(i2);
                                Object objM2479removeLastimpl = MultiValueMap.m2479removeLastimpl(this.movableContentRemoved, movableContentStateReference2.getContent$runtime());
                                MovableContentStateReference movableContentStateReference3 = (MovableContentStateReference) objM2479removeLastimpl;
                                if (movableContentStateReference3 != null) {
                                    this.movableContentNestedStatesAvailable.usedContainer(movableContentStateReference3);
                                }
                                arrayList.add(TuplesKt.to(movableContentStateReference2, objM2479removeLastimpl));
                            }
                            if (ComposeRuntimeFlags.isMovingNestedMovableContentEnabled) {
                                int size3 = arrayList.size();
                                for (int i3 = 0; i3 < size3; i3++) {
                                    Pair<MovableContentStateReference, MovableContentStateReference> pair = arrayList.get(i3);
                                    if (pair.getSecond() == null && this.movableContentNestedStatesAvailable.contains(((MovableContentStateReference) pair.getFirst()).getContent$runtime())) {
                                        ArrayList arrayList3 = new ArrayList(arrayList.size());
                                        int size4 = arrayList.size();
                                        for (int i4 = 0; i4 < size4; i4++) {
                                            Pair<MovableContentStateReference, MovableContentStateReference> pair2 = arrayList.get(i4);
                                            if (pair2.getSecond() == null && (nestedMovableContentRemoveLast = this.movableContentNestedStatesAvailable.removeLast(((MovableContentStateReference) pair2.getFirst()).getContent$runtime())) != null) {
                                                MovableContentStateReference content = nestedMovableContentRemoveLast.getContent();
                                                MultiValueMap.m2464addimpl(this.movableContentNestedExtractionsPending, nestedMovableContentRemoveLast.getContainer(), content);
                                                pair2 = TuplesKt.to(pair2.getFirst(), content);
                                            }
                                            arrayList3.add(pair2);
                                        }
                                        arrayList = arrayList3;
                                        break;
                                    }
                                }
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    int size5 = arrayList.size();
                    for (int i5 = 0; i5 < size5; i5++) {
                        if (arrayList.get(i5).getSecond() != null) {
                            int size6 = arrayList.size();
                            for (int i6 = 0; i6 < size6; i6++) {
                                if (arrayList.get(i6).getSecond() == null) {
                                    ArrayList arrayList4 = new ArrayList(arrayList.size());
                                    int size7 = arrayList.size();
                                    for (int i7 = 0; i7 < size7; i7++) {
                                        Pair<MovableContentStateReference, MovableContentStateReference> pair3 = arrayList.get(i7);
                                        MovableContentStateReference movableContentStateReference4 = pair3.getSecond() == null ? (MovableContentStateReference) pair3.getFirst() : null;
                                        if (movableContentStateReference4 != null) {
                                            arrayList4.add(movableContentStateReference4);
                                        }
                                    }
                                    synchronized (this.stateLock) {
                                        CollectionsKt.addAll(this.movableContentAwaitingInsert, arrayList4);
                                        Unit unit = Unit.INSTANCE;
                                    }
                                    ArrayList arrayList5 = new ArrayList(arrayList.size());
                                    int size8 = arrayList.size();
                                    for (int i8 = 0; i8 < size8; i8++) {
                                        Pair<MovableContentStateReference, MovableContentStateReference> pair4 = arrayList.get(i8);
                                        if (pair4.getSecond() != null) {
                                            arrayList5.add(pair4);
                                        }
                                    }
                                    arrayList = arrayList5;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    controlledComposition.insertMovableContent(arrayList);
                    Unit unit2 = Unit.INSTANCE;
                    mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                } catch (Throwable th2) {
                    mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    throw th2;
                }
            } catch (Throwable th3) {
                applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                throw th3;
            }
        }
        return CollectionsKt.toList(map.keySet());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ControlledComposition performRecompose(final ControlledComposition composition, final MutableScatterSet<Object> modifiedValues) {
        Set<ControlledComposition> set;
        if (composition.isComposing() || composition.isDisposed() || ((set = this.compositionsRemoved) != null && set.contains(composition))) {
            return null;
        }
        MutableSnapshot mutableSnapshotTakeMutableSnapshot = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, modifiedValues));
        try {
            Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot.makeCurrent();
            if (modifiedValues != null) {
                try {
                    if (modifiedValues.isNotEmpty()) {
                        composition.prepareCompose(new Function0() { // from class: v7c
                            public final Object invoke() {
                                return Recomposer.performRecompose$lambda$0$0(modifiedValues, composition);
                            }
                        });
                    }
                } catch (Throwable th) {
                    mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    throw th;
                }
            }
            boolean zRecompose = composition.recompose();
            mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
            if (zRecompose) {
                return composition;
            }
            return null;
        } catch (Throwable th2) {
            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:14:0x003e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:15:0x0040 A[LOOP:0: B:5:0x000b->B:15:0x0040, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:19:0x0043 A[EDGE_INSN: B:19:0x0043->B:16:0x0043 BREAK  A[LOOP:0: B:5:0x000b->B:15:0x0040], SYNTHETIC] */
    public static final Unit performRecompose$lambda$0$0(MutableScatterSet mutableScatterSet, ControlledComposition controlledComposition) {
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
                            controlledComposition.recordWriteOf(objArr[(i << 3) + i3]);
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    }
                    if (i != length) {
                        break;
                    }
                    i++;
                }
            }
        }
        return Unit.INSTANCE;
    }

    private final void processCompositionError(Throwable e, ControlledComposition failedInitialComposition, boolean recoverable) throws Throwable {
        if (!_hotReloadEnabled.get().booleanValue() || (e instanceof ComposeRuntimeError)) {
            synchronized (this.stateLock) {
                Utils_androidKt.logError("Error was captured in composition.", e);
                RecomposerErrorState recomposerErrorState = this.errorState;
                if (recomposerErrorState != null) {
                    throw recomposerErrorState.getCause();
                }
                this.errorState = new RecomposerErrorState(false, e);
                Unit unit = Unit.INSTANCE;
            }
            throw e;
        }
        synchronized (this.stateLock) {
            try {
                Utils_androidKt.logError("Error was captured in composition while live edit was enabled.", e);
                this.compositionsAwaitingApply.clear();
                this.compositionInvalidations.clear();
                this.snapshotInvalidations = new MutableScatterSet<>(0, 1, (DefaultConstructorMarker) null);
                this.movableContentAwaitingInsert.clear();
                MultiValueMap.m2466clearimpl(this.movableContentRemoved);
                this.movableContentStatesAvailable.clear();
                this.errorState = new RecomposerErrorState(recoverable, e);
                if (failedInitialComposition != null) {
                    recordFailedCompositionLocked(failedInitialComposition);
                }
                deriveStateLocked();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static /* synthetic */ void processCompositionError$default(Recomposer recomposer, Throwable th, ControlledComposition controlledComposition, boolean z, int i, Object obj) throws Throwable {
        if ((i & 2) != 0) {
            controlledComposition = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        recomposer.processCompositionError(th, controlledComposition, z);
    }

    private final Function1<Object, Unit> readObserverOf(final ControlledComposition composition) {
        return new Function1() { // from class: q7c
            public final Object invoke(Object obj) {
                return Recomposer.d(composition, obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object recompositionRunner(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(this.broadcastFrameClock, new C00772(function3, MonotonicFrameClockKt.getMonotonicFrameClock(continuation.getContext()), null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    private final void recordComposerModifications(Function1<? super ControlledComposition, Unit> onEachInvalidComposition) {
        MutableScatterSet mutableScatterSet;
        int i;
        synchronized (this.stateLock) {
            try {
                mutableScatterSet = this.snapshotInvalidations;
                if (mutableScatterSet.isNotEmpty()) {
                    this.snapshotInvalidations = new MutableScatterSet(0, 1, (DefaultConstructorMarker) null);
                }
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        Set<? extends Object> setWrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(mutableScatterSet);
        if (!setWrapIntoSet.isEmpty()) {
            List listKnownCompositionsLocked = knownCompositionsLocked();
            int size = listKnownCompositionsLocked.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((ControlledComposition) listKnownCompositionsLocked.get(i2)).recordModificationsOf(setWrapIntoSet);
            }
        }
        MutableVector mutableVector = this.compositionInvalidations;
        Object[] objArr = mutableVector.content;
        int size2 = mutableVector.getSize();
        for (i = 0; i < size2; i++) {
            onEachInvalidComposition.invoke(objArr[i]);
        }
        this.compositionInvalidations.clear();
        synchronized (this.stateLock) {
            try {
                if (deriveStateLocked() != null) {
                    throw new IllegalStateException("called outside of runRecomposeAndApplyChanges");
                }
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
        InlineMarker.finallyEnd(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordFailedCompositionLocked(ControlledComposition composition) {
        List arrayList = this.failedCompositions;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.failedCompositions = arrayList;
        }
        if (!arrayList.contains(composition)) {
            arrayList.add(composition);
        }
        removeKnownCompositionLocked(composition);
    }

    private final void registerCompositionLocked(ControlledComposition composition) {
        MutableObjectList<CompositionRegistrationObserver> mutableObjectList = this.registrationObservers;
        if (mutableObjectList != null) {
            Object[] objArr = ((ObjectList) mutableObjectList).content;
            int i = ((ObjectList) mutableObjectList)._size;
            for (int i2 = 0; i2 < i; i2++) {
                CompositionRegistrationObserver compositionRegistrationObserver = (CompositionRegistrationObserver) objArr[i2];
                if (composition instanceof ObservableComposition) {
                    compositionRegistrationObserver.onCompositionRegistered((ObservableComposition) composition);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerRunnerJob(Job callingJob) {
        synchronized (this.stateLock) {
            Throwable th = this.closeCause;
            if (th != null) {
                throw th;
            }
            if (((State) this._state.getValue()).compareTo(State.ShuttingDown) <= 0) {
                throw new IllegalStateException("Recomposer shut down");
            }
            if (this.runnerJob != null) {
                throw new IllegalStateException("Recomposer already running");
            }
            this.runnerJob = callingJob;
            deriveStateLocked();
        }
    }

    private final void removeKnownCompositionLocked(ControlledComposition composition) {
        if (this._knownCompositions.remove(composition)) {
            this._knownCompositionsCache = null;
            unregisterCompositionLocked(composition);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RecomposerErrorState resetErrorState() {
        RecomposerErrorState recomposerErrorState;
        synchronized (this.stateLock) {
            recomposerErrorState = this.errorState;
            if (recomposerErrorState != null) {
                this.errorState = null;
                deriveStateLocked();
            }
        }
        return recomposerErrorState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void retryFailedCompositions() {
        List<ControlledComposition> list;
        int i;
        synchronized (this.stateLock) {
            list = this.failedCompositions;
            this.failedCompositions = null;
        }
        if (list == null) {
            return;
        }
        while (true) {
            i = 0;
            try {
                if (list.isEmpty()) {
                    break;
                }
                ControlledComposition controlledComposition = (ControlledComposition) CollectionsKt.removeLast(list);
                if (controlledComposition instanceof CompositionImpl) {
                    ((CompositionImpl) controlledComposition).invalidateAll();
                    ((CompositionImpl) controlledComposition).setContent(((CompositionImpl) controlledComposition).getComposable());
                    if (this.errorState != null) {
                        break;
                    }
                }
            } catch (Throwable th) {
                if (!list.isEmpty()) {
                    synchronized (this.stateLock) {
                        try {
                            int size = list.size();
                            while (i < size) {
                                recordFailedCompositionLocked(list.get(i));
                                i++;
                            }
                            Unit unit = Unit.INSTANCE;
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                }
                throw th;
            }
        }
        if (list.isEmpty()) {
            return;
        }
        synchronized (this.stateLock) {
            try {
                int size2 = list.size();
                while (i < size2) {
                    recordFailedCompositionLocked(list.get(i));
                    i++;
                }
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:21:0x007b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0092, code lost:
    
        if (r5.withFrameNanos(r9, r0) == r1) goto L24;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0092 -> B:13:0x003b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object runFrameLoop(MonotonicFrameClock monotonicFrameClock, ProduceFrameSignal produceFrameSignal, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        List arrayList;
        List arrayList2;
        final List list;
        final List list2;
        MonotonicFrameClock monotonicFrameClock2;
        final ProduceFrameSignal produceFrameSignal2;
        Object obj;
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
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            arrayList = new ArrayList();
            arrayList2 = new ArrayList();
            obj = this.stateLock;
            anonymousClass1.L$0 = monotonicFrameClock;
            anonymousClass1.L$1 = produceFrameSignal;
            anonymousClass1.L$2 = arrayList;
            anonymousClass1.L$3 = arrayList2;
            anonymousClass1.label = 1;
            if (produceFrameSignal.awaitFrameRequest(obj, anonymousClass1) != coroutine_suspended) {
                monotonicFrameClock2 = monotonicFrameClock;
                list2 = arrayList2;
                produceFrameSignal2 = produceFrameSignal;
                list = arrayList;
                Function1 function1 = new Function1() { // from class: androidx.compose.runtime.g
                    public final Object invoke(Object obj3) {
                        return Recomposer.c(this.b, list, list2, produceFrameSignal2, ((Long) obj3).longValue());
                    }
                };
                anonymousClass1.L$0 = monotonicFrameClock2;
                anonymousClass1.L$1 = produceFrameSignal2;
                anonymousClass1.L$2 = list;
                anonymousClass1.L$3 = list2;
                anonymousClass1.label = 2;
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            list2 = (List) anonymousClass1.L$3;
            list = (List) anonymousClass1.L$2;
            produceFrameSignal2 = (ProduceFrameSignal) anonymousClass1.L$1;
            monotonicFrameClock2 = (MonotonicFrameClock) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj2);
            Function1 function2 = new Function1() { // from class: androidx.compose.runtime.g
                public final Object invoke(Object obj3) {
                    return Recomposer.c(this.b, list, list2, produceFrameSignal2, ((Long) obj3).longValue());
                }
            };
            anonymousClass1.L$0 = monotonicFrameClock2;
            anonymousClass1.L$1 = produceFrameSignal2;
            anonymousClass1.L$2 = list;
            anonymousClass1.L$3 = list2;
            anonymousClass1.label = 2;
        } else {
            if (i2 != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            list2 = (List) anonymousClass1.L$3;
            list = (List) anonymousClass1.L$2;
            produceFrameSignal2 = (ProduceFrameSignal) anonymousClass1.L$1;
            monotonicFrameClock2 = (MonotonicFrameClock) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj2);
        }
        arrayList = list;
        produceFrameSignal = produceFrameSignal2;
        arrayList2 = list2;
        monotonicFrameClock = monotonicFrameClock2;
        obj = this.stateLock;
        anonymousClass1.L$0 = monotonicFrameClock;
        anonymousClass1.L$1 = produceFrameSignal;
        anonymousClass1.L$2 = arrayList;
        anonymousClass1.L$3 = arrayList2;
        anonymousClass1.label = 1;
        if (produceFrameSignal.awaitFrameRequest(obj, anonymousClass1) != coroutine_suspended) {
            monotonicFrameClock2 = monotonicFrameClock;
            list2 = arrayList2;
            produceFrameSignal2 = produceFrameSignal;
            list = arrayList;
            Function1 function3 = new Function1() { // from class: androidx.compose.runtime.g
                public final Object invoke(Object obj3) {
                    return Recomposer.c(this.b, list, list2, produceFrameSignal2, ((Long) obj3).longValue());
                }
            };
            anonymousClass1.L$0 = monotonicFrameClock2;
            anonymousClass1.L$1 = produceFrameSignal2;
            anonymousClass1.L$2 = list;
            anonymousClass1.L$3 = list2;
            anonymousClass1.label = 2;
        }
        return coroutine_suspended;
    }

    private final void unregisterCompositionLocked(ControlledComposition composition) {
        MutableObjectList<CompositionRegistrationObserver> mutableObjectList = this.registrationObservers;
        if (mutableObjectList != null) {
            Object[] objArr = ((ObjectList) mutableObjectList).content;
            int i = ((ObjectList) mutableObjectList)._size;
            for (int i2 = 0; i2 < i; i2++) {
                CompositionRegistrationObserver compositionRegistrationObserver = (CompositionRegistrationObserver) objArr[i2];
                if (composition instanceof ObservableComposition) {
                    compositionRegistrationObserver.onCompositionUnregistered((ObservableComposition) composition);
                }
            }
        }
    }

    private final void withTransparentSnapshot(Function0<Unit> block) {
        Snapshot current = Snapshot.INSTANCE.getCurrent();
        Snapshot transparentObserverMutableSnapshot = current instanceof MutableSnapshot ? new TransparentObserverMutableSnapshot((MutableSnapshot) current, null, null, true, false) : new TransparentObserverSnapshot(current, null, true, false);
        try {
            Snapshot snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
            try {
                block.invoke();
                InlineMarker.finallyStart(1);
                transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                InlineMarker.finallyEnd(1);
                InlineMarker.finallyStart(1);
                transparentObserverMutableSnapshot.dispose();
            } finally {
                InlineMarker.finallyStart(1);
                transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                InlineMarker.finallyEnd(1);
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            transparentObserverMutableSnapshot.dispose();
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    private final Function1<Object, Unit> writeObserverOf(final ControlledComposition composition, final MutableScatterSet<Object> modifiedValues) {
        return new Function1() { // from class: u7c
            public final Object invoke(Object obj) {
                return Recomposer.b(composition, modifiedValues, obj);
            }
        };
    }

    public final CompositionObserverHandle addCompositionRegistrationObserver$runtime(final CompositionRegistrationObserver observer) {
        synchronized (this.stateLock) {
            try {
                MutableObjectList<CompositionRegistrationObserver> mutableObjectList = this.registrationObservers;
                if (mutableObjectList == null) {
                    mutableObjectList = new MutableObjectList<>(0, 1, (DefaultConstructorMarker) null);
                    this.registrationObservers = mutableObjectList;
                }
                mutableObjectList.add(observer);
                List<ControlledComposition> list = this._knownCompositions;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ControlledComposition controlledComposition = list.get(i);
                    if (controlledComposition instanceof ObservableComposition) {
                        observer.onCompositionRegistered((ObservableComposition) controlledComposition);
                    }
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.Recomposer$addCompositionRegistrationObserver$2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object obj = this.this$0.stateLock;
                Recomposer recomposer = this.this$0;
                CompositionRegistrationObserver compositionRegistrationObserver = observer;
                synchronized (obj) {
                    MutableObjectList mutableObjectList2 = recomposer.registrationObservers;
                    if (mutableObjectList2 != null) {
                        mutableObjectList2.remove(compositionRegistrationObserver);
                    }
                }
            }
        };
    }

    public final RecomposerInfo asRecomposerInfo() {
        return this.recomposerInfo;
    }

    public final Object awaitIdle(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.collect(FlowKt.takeWhile(getCurrentState(), new AnonymousClass2(null)), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    public final void cancel() {
        synchronized (this.stateLock) {
            try {
                if (((State) this._state.getValue()).compareTo(State.Idle) >= 0) {
                    this._state.setValue(State.ShuttingDown);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        Job.DefaultImpls.cancel$default(this.effectJob, (CancellationException) null, 1, (Object) null);
    }

    public final void close() {
        if (this.effectJob.complete()) {
            synchronized (this.stateLock) {
                this.isClosed = true;
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void composeInitial$runtime(ControlledComposition composition, Function2<? super Composer, ? super Integer, Unit> content) throws Throwable {
        State state;
        boolean z;
        boolean zIsComposing = composition.isComposing();
        synchronized (this.stateLock) {
            State state2 = (State) this._state.getValue();
            state = State.ShuttingDown;
            if (state2.compareTo(state) > 0) {
                boolean zContains = knownCompositionsLocked().contains(composition);
                z = !zContains;
                if (!zContains) {
                    registerCompositionLocked(composition);
                }
            } else {
                z = true;
            }
        }
        try {
            Snapshot.Companion companion = Snapshot.INSTANCE;
            MutableSnapshot mutableSnapshotTakeMutableSnapshot = companion.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, null));
            try {
                Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot.makeCurrent();
                try {
                    composition.composeContent(content);
                    Unit unit = Unit.INSTANCE;
                    mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                    synchronized (this.stateLock) {
                        try {
                            if (((State) this._state.getValue()).compareTo(state) <= 0) {
                                unregisterCompositionLocked(composition);
                            } else if (!knownCompositionsLocked().contains(composition)) {
                                addKnownCompositionLocked(composition);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    if (!zIsComposing) {
                        companion.notifyObjectsInitialized();
                    }
                    try {
                        performInitialMovableContentInserts(composition);
                        try {
                            composition.applyChanges();
                            composition.applyLateChanges();
                            if (zIsComposing) {
                                return;
                            }
                            companion.notifyObjectsInitialized();
                        } catch (Throwable th2) {
                            processCompositionError$default(this, th2, null, false, 6, null);
                        }
                    } catch (Throwable th3) {
                        processCompositionError(th3, composition, true);
                    }
                } catch (Throwable th4) {
                    try {
                        mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                        throw th4;
                    } catch (Throwable th5) {
                        th = th5;
                        Throwable th6 = th;
                        try {
                            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                            throw th6;
                        } catch (Throwable th7) {
                            th = th7;
                            Throwable th8 = th;
                            if (z) {
                                synchronized (this.stateLock) {
                                    this.unregisterCompositionLocked(composition);
                                    Unit unit2 = Unit.INSTANCE;
                                }
                            }
                            this.processCompositionError(th8, composition, true);
                        }
                    }
                }
            } catch (Throwable th9) {
                th = th9;
            }
        } catch (Throwable th10) {
            th = th10;
            this = this;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public ScatterSet<RecomposeScopeImpl> composeInitialPaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, Function2<? super Composer, ? super Integer, Unit> content) {
        try {
            ShouldPauseCallback andSetShouldPauseCallback = composition.getAndSetShouldPauseCallback(shouldPause);
            try {
                composeInitial$runtime(composition, content);
                ScatterSet<RecomposeScopeImpl> scatterSetEmptyScatterSet = (MutableScatterSet) this.pausedScopes.get();
                if (scatterSetEmptyScatterSet == null) {
                    scatterSetEmptyScatterSet = ScatterSetKt.emptyScatterSet();
                }
                composition.getAndSetShouldPauseCallback(andSetShouldPauseCallback);
                this.pausedScopes.set(null);
                return scatterSetEmptyScatterSet;
            } catch (Throwable th) {
                composition.getAndSetShouldPauseCallback(andSetShouldPauseCallback);
                throw th;
            }
        } catch (Throwable th2) {
            this.pausedScopes.set(null);
            throw th2;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void deletedMovableContent$runtime(MovableContentStateReference reference) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (this.stateLock) {
            try {
                MultiValueMap.m2464addimpl(this.movableContentRemoved, reference.getContent$runtime(), reference);
                if (reference.getNestedReferences$runtime() != null) {
                    deletedMovableContent$lambda$0$recordNestedStatesOf(this, reference, reference);
                }
                cancellableContinuationDeriveStateLocked = deriveStateLocked();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.Companion;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.constructor-impl(Unit.INSTANCE));
        }
    }

    public final long getChangeCount() {
        return this.changeCount;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public boolean getCollectingCallByInformation$runtime() {
        return _hotReloadEnabled.get().booleanValue();
    }

    @Override // androidx.compose.runtime.CompositionContext
    /* JADX INFO: renamed from: getCollectingParameterInformation$runtime */
    public boolean getCollectingParameterInformation() {
        return false;
    }

    @Override // androidx.compose.runtime.CompositionContext
    /* JADX INFO: renamed from: getCollectingSourceInformation$runtime */
    public boolean getCollectingSourceInformation() {
        return ComposeStackTraceMode.m2589equalsimpl0(ComposerKt.getComposeStackTraceMode(), ComposeStackTraceMode.INSTANCE.m2596getSourceInformationMD5MrJc());
    }

    @Override // androidx.compose.runtime.CompositionContext
    /* JADX INFO: renamed from: getCompositeKeyHashCode$runtime */
    public long getCompositeKeyHashCode() {
        return 1000L;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public Composition getComposition$runtime() {
        return null;
    }

    public final StateFlow<State> getCurrentState() {
        return this._state;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public CoroutineContext getEffectCoroutineContext() {
        return this.effectCoroutineContext;
    }

    public final boolean getHasPendingWork() {
        boolean z;
        synchronized (this.stateLock) {
            z = this.snapshotInvalidations.isNotEmpty() || this.compositionInvalidations.getSize() != 0 || this.concurrentCompositionsOutstanding > 0 || !this.compositionsAwaitingApply.isEmpty() || getHasBroadcastFrameClockAwaitersLocked() || getHasNextFrameEndAwaitersLocked() || MultiValueMap.m2477isNotEmptyimpl(this.movableContentRemoved);
        }
        return z;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public CoroutineContext getRecomposeCoroutineContext$runtime() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public boolean getStackTraceEnabled$runtime() {
        return !ComposeStackTraceMode.m2589equalsimpl0(ComposerKt.getComposeStackTraceMode(), ComposeStackTraceMode.INSTANCE.m2595getNoneMD5MrJc());
    }

    public final Flow<State> getState() {
        return getCurrentState();
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void insertMovableContent$runtime(MovableContentStateReference reference) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (this.stateLock) {
            this.movableContentAwaitingInsert.add(reference);
            cancellableContinuationDeriveStateLocked = deriveStateLocked();
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.Companion;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.constructor-impl(Unit.INSTANCE));
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void invalidate$runtime(ControlledComposition composition) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (this.stateLock) {
            if (this.compositionInvalidations.contains(composition)) {
                cancellableContinuationDeriveStateLocked = null;
            } else {
                this.compositionInvalidations.add(composition);
                cancellableContinuationDeriveStateLocked = deriveStateLocked();
            }
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.Companion;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.constructor-impl(Unit.INSTANCE));
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void invalidateScope$runtime(RecomposeScopeImpl scope) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (this.stateLock) {
            this.snapshotInvalidations.add(scope);
            cancellableContinuationDeriveStateLocked = deriveStateLocked();
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.Companion;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.constructor-impl(Unit.INSTANCE));
        }
    }

    public final Object join(Continuation<? super Unit> continuation) {
        Object objFirst = FlowKt.first(getCurrentState(), new C00762(null), continuation);
        return objFirst == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFirst : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x006b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x006d A[Catch: all -> 0x0063, LOOP:0: B:9:0x002d->B:21:0x006d, LOOP_END, TryCatch #0 {all -> 0x0063, blocks: (B:4:0x0009, B:6:0x001a, B:9:0x002d, B:11:0x003d, B:13:0x0049, B:15:0x0052, B:18:0x0065, B:21:0x006d, B:22:0x0070), top: B:27:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x0070 A[EDGE_INSN: B:30:0x0070->B:22:0x0070 BREAK  A[LOOP:0: B:9:0x002d->B:21:0x006d], SYNTHETIC] */
    @Override // androidx.compose.runtime.CompositionContext
    public void movableContentStateReleased$runtime(MovableContentStateReference reference, MovableContentState data, Applier<?> applier) {
        synchronized (this.stateLock) {
            try {
                this.movableContentStatesAvailable.set(reference, data);
                ObjectList<MovableContentStateReference> objectListM2474getimpl = MultiValueMap.m2474getimpl(this.movableContentNestedExtractionsPending, reference);
                if (objectListM2474getimpl.isNotEmpty()) {
                    ScatterMap<MovableContentStateReference, MovableContentState> scatterMapExtractNestedStates$runtime = data.extractNestedStates$runtime(applier, objectListM2474getimpl);
                    Object[] objArr = scatterMapExtractNestedStates$runtime.keys;
                    Object[] objArr2 = scatterMapExtractNestedStates$runtime.values;
                    long[] jArr = scatterMapExtractNestedStates$runtime.metadata;
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
                                        int i4 = (i << 3) + i3;
                                        Object obj = objArr[i4];
                                        this.movableContentStatesAvailable.set((MovableContentStateReference) obj, (MovableContentState) objArr2[i4]);
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
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public MovableContentState movableContentStateResolve$runtime(MovableContentStateReference reference) {
        MovableContentState movableContentState;
        synchronized (this.stateLock) {
            movableContentState = (MovableContentState) this.movableContentStatesAvailable.remove(reference);
        }
        return movableContentState;
    }

    public final void pauseCompositionFrameClock() {
        synchronized (this.stateLock) {
            this.frameClockPaused = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public ScatterSet<RecomposeScopeImpl> recomposePaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, ScatterSet<RecomposeScopeImpl> invalidScopes) {
        try {
            recordComposerModifications();
            composition.recordModificationsOf(ScatterSetWrapperKt.wrapIntoSet(invalidScopes));
            ShouldPauseCallback andSetShouldPauseCallback = composition.getAndSetShouldPauseCallback(shouldPause);
            try {
                ControlledComposition controlledCompositionPerformRecompose = performRecompose(composition, null);
                if (controlledCompositionPerformRecompose != null) {
                    performInitialMovableContentInserts(composition);
                    controlledCompositionPerformRecompose.applyChanges();
                    controlledCompositionPerformRecompose.applyLateChanges();
                }
                ScatterSet<RecomposeScopeImpl> scatterSetEmptyScatterSet = (MutableScatterSet) this.pausedScopes.get();
                if (scatterSetEmptyScatterSet == null) {
                    scatterSetEmptyScatterSet = ScatterSetKt.emptyScatterSet();
                }
                composition.getAndSetShouldPauseCallback(andSetShouldPauseCallback);
                this.pausedScopes.set(null);
                return scatterSetEmptyScatterSet;
            } catch (Throwable th) {
                composition.getAndSetShouldPauseCallback(andSetShouldPauseCallback);
                throw th;
            }
        } catch (Throwable th2) {
            this.pausedScopes.set(null);
            throw th2;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void recordInspectionTable$runtime(Set<CompositionData> table) {
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void registerComposition$runtime(ControlledComposition composition) {
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void reportPausedScope$runtime(RecomposeScopeImpl scope) {
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSetMutableScatterSetOf = this.pausedScopes.get();
        if (mutableScatterSetMutableScatterSetOf == null) {
            mutableScatterSetMutableScatterSetOf = ScatterSetKt.mutableScatterSetOf();
            this.pausedScopes.set(mutableScatterSetMutableScatterSetOf);
        }
        mutableScatterSetMutableScatterSetOf.add(scope);
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void reportRemovedComposition$runtime(ControlledComposition composition) {
        synchronized (this.stateLock) {
            try {
                Set linkedHashSet = this.compositionsRemoved;
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                    this.compositionsRemoved = linkedHashSet;
                }
                linkedHashSet.add(composition);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void resumeCompositionFrameClock() {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (this.stateLock) {
            if (this.frameClockPaused) {
                this.frameClockPaused = false;
                cancellableContinuationDeriveStateLocked = deriveStateLocked();
            } else {
                cancellableContinuationDeriveStateLocked = null;
            }
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.Companion;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.constructor-impl(Unit.INSTANCE));
        }
    }

    public final Object runRecomposeAndApplyChanges(Continuation<? super Unit> continuation) {
        Object objRecompositionRunner = recompositionRunner(new C00782(null), continuation);
        return objRecompositionRunner == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRecompositionRunner : Unit.INSTANCE;
    }

    public final Object runRecomposeConcurrentlyAndApplyChanges(CoroutineContext coroutineContext, Continuation<? super Unit> continuation) {
        Object objRecompositionRunner = recompositionRunner(new C00792(coroutineContext, this, null), continuation);
        return objRecompositionRunner == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRecompositionRunner : Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public CancellationHandle scheduleFrameEndCallback(Function0<Unit> action) {
        return this.nextFrameEndCallbackQueue.scheduleFrameEndCallback(action);
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void unregisterComposition$runtime(ControlledComposition composition) {
        synchronized (this.stateLock) {
            removeKnownCompositionLocked(composition);
            this.compositionInvalidations.remove(composition);
            this.compositionsAwaitingApply.remove(composition);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0000¢\u0006\u0002\b\u0015J\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\u0019J\u0014\u0010\u001a\u001a\u00020\u00172\n\u0010\u001b\u001a\u00060\u0007R\u00020\bH\u0002J\u0014\u0010\u001c\u001a\u00020\u00172\n\u0010\u001b\u001a\u00060\u0007R\u00020\bH\u0002J\r\u0010\u001d\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u001eJ\u0015\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u0001H\u0000¢\u0006\u0002\b!J\u0015\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$H\u0000¢\u0006\u0002\b%J\u0013\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'H\u0000¢\u0006\u0002\b)J\r\u0010*\u001a\u00020\u0017H\u0000¢\u0006\u0002\b+R\u001e\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007R\u00020\b0\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006,"}, d2 = {"Landroidx/compose/runtime/Recomposer$Companion;", "", "<init>", "()V", "_runningRecomposers", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet;", "Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "Landroidx/compose/runtime/Recomposer;", "_hotReloadEnabled", "Ljava/util/concurrent/atomic/AtomicReference;", "", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "runningRecomposers", "Lkotlinx/coroutines/flow/StateFlow;", "", "Landroidx/compose/runtime/RecomposerInfo;", "getRunningRecomposers", "()Lkotlinx/coroutines/flow/StateFlow;", "currentRunningRecomposers", "currentRunningRecomposers$runtime", "setHotReloadEnabled", "", "value", "setHotReloadEnabled$runtime", "addRunning", "info", "removeRunning", "saveStateAndDisposeForHotReload", "saveStateAndDisposeForHotReload$runtime", "loadStateAndComposeForHotReload", "token", "loadStateAndComposeForHotReload$runtime", "invalidateGroupsWithKey", "key", "", "invalidateGroupsWithKey$runtime", "getCurrentErrors", "", "Landroidx/compose/runtime/RecomposerErrorInfo;", "getCurrentErrors$runtime", "clearErrors", "clearErrors$runtime", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void addRunning(RecomposerInfoImpl info) {
            PersistentSet persistentSet;
            PersistentSet persistentSetAdd;
            do {
                persistentSet = (PersistentSet) Recomposer._runningRecomposers.getValue();
                persistentSetAdd = persistentSet.add(info);
                if (persistentSet == persistentSetAdd) {
                    return;
                }
            } while (!Recomposer._runningRecomposers.compareAndSet(persistentSet, persistentSetAdd));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void removeRunning(RecomposerInfoImpl info) {
            PersistentSet persistentSet;
            PersistentSet persistentSetRemove;
            do {
                persistentSet = (PersistentSet) Recomposer._runningRecomposers.getValue();
                persistentSetRemove = persistentSet.remove(info);
                if (persistentSet == persistentSetRemove) {
                    return;
                }
            } while (!Recomposer._runningRecomposers.compareAndSet(persistentSet, persistentSetRemove));
        }

        public final void clearErrors$runtime() {
            Iterable iterable = (Iterable) Recomposer._runningRecomposers.getValue();
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                RecomposerErrorState recomposerErrorStateResetErrorState = ((RecomposerInfoImpl) it.next()).resetErrorState();
                if (recomposerErrorStateResetErrorState != null) {
                    arrayList.add(recomposerErrorStateResetErrorState);
                }
            }
        }

        public final Set<RecomposerInfo> currentRunningRecomposers$runtime() {
            return (Set) Recomposer._runningRecomposers.getValue();
        }

        public final List<RecomposerErrorInfo> getCurrentErrors$runtime() {
            Iterable iterable = (Iterable) Recomposer._runningRecomposers.getValue();
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                RecomposerErrorInfo currentError = ((RecomposerInfoImpl) it.next()).getCurrentError();
                if (currentError != null) {
                    arrayList.add(currentError);
                }
            }
            return arrayList;
        }

        public final StateFlow<Set<RecomposerInfo>> getRunningRecomposers() {
            return Recomposer._runningRecomposers;
        }

        public final void invalidateGroupsWithKey$runtime(int key) {
            Recomposer._hotReloadEnabled.set(Boolean.TRUE);
            for (RecomposerInfoImpl recomposerInfoImpl : (Iterable) Recomposer._runningRecomposers.getValue()) {
                RecomposerErrorInfo currentError = recomposerInfoImpl.getCurrentError();
                if (currentError == null || currentError.getRecoverable()) {
                    recomposerInfoImpl.resetErrorState();
                    recomposerInfoImpl.invalidateGroupsWithKey(key);
                    recomposerInfoImpl.retryFailedCompositions();
                }
            }
        }

        public final void loadStateAndComposeForHotReload$runtime(Object token) {
            Recomposer._hotReloadEnabled.set(Boolean.TRUE);
            Iterator it = ((Iterable) Recomposer._runningRecomposers.getValue()).iterator();
            while (it.hasNext()) {
                ((RecomposerInfoImpl) it.next()).resetErrorState();
            }
            token.getClass();
            List list = (List) token;
            List list2 = list;
            int size = list2.size();
            for (int i = 0; i < size; i++) {
                ((HotReloadable) list.get(i)).resetContent();
            }
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((HotReloadable) list.get(i2)).recompose();
            }
            Iterator it2 = ((Iterable) Recomposer._runningRecomposers.getValue()).iterator();
            while (it2.hasNext()) {
                ((RecomposerInfoImpl) it2.next()).retryFailedCompositions();
            }
        }

        public final Object saveStateAndDisposeForHotReload$runtime() {
            Recomposer._hotReloadEnabled.set(Boolean.TRUE);
            Iterable iterable = (Iterable) Recomposer._runningRecomposers.getValue();
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, ((RecomposerInfoImpl) it.next()).saveStateAndDisposeForHotReload());
            }
            return arrayList;
        }

        public final void setHotReloadEnabled$runtime(boolean value) {
            Recomposer._hotReloadEnabled.set(Boolean.valueOf(value));
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean recordComposerModifications() {
        boolean hasFrameWorkLocked;
        CollectionsKt.emptyList();
        synchronized (this.stateLock) {
            if (this.snapshotInvalidations.isEmpty()) {
                return getHasFrameWorkLocked();
            }
            List<ControlledComposition> listKnownCompositionsLocked = knownCompositionsLocked();
            Set<? extends Object> setWrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(this.snapshotInvalidations);
            this.snapshotInvalidations = new MutableScatterSet<>(0, 1, (DefaultConstructorMarker) null);
            try {
                int size = listKnownCompositionsLocked.size();
                for (int i = 0; i < size; i++) {
                    listKnownCompositionsLocked.get(i).recordModificationsOf(setWrapIntoSet);
                    if (((State) this._state.getValue()).compareTo(State.ShuttingDown) <= 0) {
                        break;
                    }
                }
                synchronized (this.stateLock) {
                    if (deriveStateLocked() == null) {
                        hasFrameWorkLocked = getHasFrameWorkLocked();
                    } else {
                        throw new IllegalStateException("called outside of runRecomposeAndApplyChanges");
                    }
                }
                return hasFrameWorkLocked;
            } catch (Throwable th) {
                synchronized (this.stateLock) {
                    this.snapshotInvalidations.addAll(setWrapIntoSet);
                    throw th;
                }
            }
        }
    }
}
