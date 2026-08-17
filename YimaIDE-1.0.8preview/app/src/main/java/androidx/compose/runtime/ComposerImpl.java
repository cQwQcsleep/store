package androidx.compose.runtime;

import androidx.collection.MutableIntIntMap;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.changelist.ComposerChangeListWriter;
import androidx.compose.runtime.changelist.FixupList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.internal.Expect_jvmKt;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.internal.PersistentCompositionLocalMapKt;
import androidx.compose.runtime.internal.Trace;
import androidx.compose.runtime.snapshots.ListUtilsKt;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.tooling.ComposeStackTrace;
import androidx.compose.runtime.tooling.ComposeStackTraceBuilderKt;
import androidx.compose.runtime.tooling.ComposeStackTraceFrame;
import androidx.compose.runtime.tooling.ComposeStackTraceKt;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionErrorContext;
import androidx.compose.runtime.tooling.CompositionErrorContextImpl;
import androidx.compose.runtime.tooling.CompositionErrorContextKt;
import androidx.compose.runtime.tooling.CompositionObserver;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import androidx.compose.runtime.tooling.ObjectLocation;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.apk.ApkUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000§\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\f\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u000b*\u0001;\b\u0001\u0018\u00002\u00020\u0001:\u0004\u0081\u0003\u0082\u0003BQ\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020\u001eH\u0017J\b\u0010q\u001a\u00020oH\u0017J\u0010\u0010r\u001a\u00020o2\u0006\u0010p\u001a\u00020\u001eH\u0017J\b\u0010s\u001a\u00020oH\u0017J\b\u0010t\u001a\u00020oH\u0017J\b\u0010u\u001a\u00020oH\u0017J\u001a\u0010z\u001a\u00020o2\u0006\u0010p\u001a\u00020\u001e2\b\u0010{\u001a\u0004\u0018\u00010|H\u0017J\b\u0010}\u001a\u00020oH\u0017J\b\u0010~\u001a\u00020oH\u0002J\b\u0010\u007f\u001a\u00020oH\u0002J\t\u0010\u0080\u0001\u001a\u00020oH\u0002J\u000f\u0010\u0081\u0001\u001a\u00020oH\u0000¢\u0006\u0003\b\u0082\u0001J\t\u0010\u0090\u0001\u001a\u00020oH\u0016J\u001a\u0010\u0091\u0001\u001a\u00030\u0092\u00012\u000e\u0010\u0093\u0001\u001a\t\u0012\u0004\u0012\u00020o0\u0094\u0001H\u0016J\u000f\u0010\u0095\u0001\u001a\u00020oH\u0000¢\u0006\u0003\b\u0096\u0001J\u000f\u0010\u0097\u0001\u001a\u00020oH\u0000¢\u0006\u0003\b\u0098\u0001J\u000e\u0010'\u001a\u00020(H\u0000¢\u0006\u0003\b\u0099\u0001J\u0011\u0010\u009a\u0001\u001a\u00020o2\u0006\u0010p\u001a\u00020\u001eH\u0002J\u001b\u0010\u009a\u0001\u001a\u00020o2\u0006\u0010p\u001a\u00020\u001e2\b\u0010{\u001a\u0004\u0018\u00010|H\u0002J\t\u0010\u009b\u0001\u001a\u00020oH\u0002J\t\u0010\u009c\u0001\u001a\u00020oH\u0002J\t\u0010\u009d\u0001\u001a\u00020oH\u0016J\t\u0010\u009e\u0001\u001a\u00020oH\u0016J!\u0010\u009f\u0001\u001a\u00020o\"\u0005\b\u0000\u0010 \u00012\u000f\u0010¡\u0001\u001a\n\u0012\u0005\u0012\u0003H \u00010\u0094\u0001H\u0016J\t\u0010¢\u0001\u001a\u00020oH\u0016J\t\u0010£\u0001\u001a\u00020oH\u0016J\u001b\u0010¤\u0001\u001a\u00020o2\u0006\u0010p\u001a\u00020\u001e2\b\u0010{\u001a\u0004\u0018\u00010|H\u0016J\t\u0010¥\u0001\u001a\u00020oH\u0016J\t\u0010¦\u0001\u001a\u00020oH\u0016J\t\u0010§\u0001\u001a\u00020oH\u0016J\u0007\u0010¨\u0001\u001a\u00020oJ\u0007\u0010©\u0001\u001a\u00020oJ\u0012\u0010\u00ad\u0001\u001a\u00020o2\u0007\u0010®\u0001\u001a\u00020\u001eH\u0016JJ\u0010¯\u0001\u001a\u00020o\"\u0005\b\u0000\u0010°\u0001\"\u0005\b\u0001\u0010 \u00012\u0007\u0010?\u001a\u0003H°\u00012\"\u0010±\u0001\u001a\u001d\u0012\u0005\u0012\u0003H \u0001\u0012\u0005\u0012\u0003H°\u0001\u0012\u0004\u0012\u00020o0²\u0001¢\u0006\u0003\b³\u0001H\u0016¢\u0006\u0003\u0010´\u0001J\u001f\u0010µ\u0001\u001a\u00020|2\t\u0010¶\u0001\u001a\u0004\u0018\u00010|2\t\u0010·\u0001\u001a\u0004\u0018\u00010|H\u0017J\u000b\u0010¸\u0001\u001a\u0004\u0018\u00010|H\u0001J\u000b\u0010¹\u0001\u001a\u0004\u0018\u00010|H\u0001J\u0013\u0010º\u0001\u001a\u00020(2\b\u0010?\u001a\u0004\u0018\u00010|H\u0017J\u0013\u0010»\u0001\u001a\u00020(2\b\u0010?\u001a\u0004\u0018\u00010|H\u0017J\u0012\u0010º\u0001\u001a\u00020(2\u0007\u0010?\u001a\u00030¼\u0001H\u0017J\u0012\u0010º\u0001\u001a\u00020(2\u0007\u0010?\u001a\u00030½\u0001H\u0017J\u0012\u0010º\u0001\u001a\u00020(2\u0007\u0010?\u001a\u00030¾\u0001H\u0017J\u0011\u0010º\u0001\u001a\u00020(2\u0006\u0010?\u001a\u00020(H\u0017J\u0012\u0010º\u0001\u001a\u00020(2\u0007\u0010?\u001a\u00030¿\u0001H\u0017J\u0012\u0010º\u0001\u001a\u00020(2\u0007\u0010?\u001a\u00030\u0089\u0001H\u0017J\u0012\u0010º\u0001\u001a\u00020(2\u0007\u0010?\u001a\u00030À\u0001H\u0017J\u0011\u0010º\u0001\u001a\u00020(2\u0006\u0010?\u001a\u00020\u001eH\u0017J2\u0010Á\u0001\u001a\u0003H \u0001\"\u0005\b\u0000\u0010 \u00012\u0007\u0010Â\u0001\u001a\u00020(2\u000f\u0010±\u0001\u001a\n\u0012\u0005\u0012\u0003H \u00010\u0094\u0001H\u0087\b¢\u0006\u0003\u0010Ã\u0001J\u0013\u0010Ä\u0001\u001a\u00020o2\b\u0010?\u001a\u0004\u0018\u00010|H\u0002J\u0013\u0010Å\u0001\u001a\u00020o2\b\u0010?\u001a\u0004\u0018\u00010|H\u0001J\u0013\u0010Æ\u0001\u001a\u00020o2\b\u0010?\u001a\u0004\u0018\u00010|H\u0001J\t\u0010Ç\u0001\u001a\u00020\u001eH\u0002J\u0019\u0010Í\u0001\u001a\u00020o2\u000e\u0010Î\u0001\u001a\t\u0012\u0004\u0012\u00020o0\u0094\u0001H\u0016J\t\u0010Ï\u0001\u001a\u000200H\u0002J\u0012\u0010Ï\u0001\u001a\u0002002\u0007\u0010Ô\u0001\u001a\u00020\u001eH\u0002J\u001b\u0010Õ\u0001\u001a\u0002002\u0007\u0010Ö\u0001\u001a\u0002002\u0007\u0010×\u0001\u001a\u000200H\u0002J\u0016\u0010Ø\u0001\u001a\u00020o2\u000b\u0010?\u001a\u0007\u0012\u0002\b\u00030Ù\u0001H\u0017J\u0012\u0010Ú\u0001\u001a\u00020o2\u0007\u0010Û\u0001\u001a\u000200H\u0002J\t\u0010Ü\u0001\u001a\u00020oH\u0017J&\u0010Ý\u0001\u001a\u00020o2\u0015\u0010Þ\u0001\u001a\u0010\u0012\u000b\b\u0001\u0012\u0007\u0012\u0002\b\u00030Ù\u00010ß\u0001H\u0017¢\u0006\u0003\u0010à\u0001J\t\u0010á\u0001\u001a\u00020oH\u0017J'\u0010â\u0001\u001a\u0003H \u0001\"\u0005\b\u0000\u0010 \u00012\u000e\u0010p\u001a\n\u0012\u0005\u0012\u0003H \u00010ã\u0001H\u0017¢\u0006\u0003\u0010ä\u0001J\t\u0010å\u0001\u001a\u00020\u0005H\u0016J\t\u0010ë\u0001\u001a\u00020oH\u0002J\t\u0010ì\u0001\u001a\u00020oH\u0002J\t\u0010í\u0001\u001a\u00020oH\u0002J\u001d\u0010î\u0001\u001a\u00020o2\u0007\u0010ï\u0001\u001a\u00020(2\t\u0010ð\u0001\u001a\u0004\u0018\u00010|H\u0002J:\u0010ñ\u0001\u001a\u00020o2\u0006\u0010p\u001a\u00020\u001e2\t\u0010ò\u0001\u001a\u0004\u0018\u00010|2\b\u0010ó\u0001\u001a\u00030ô\u00012\t\u0010ð\u0001\u001a\u0004\u0018\u00010|H\u0002¢\u0006\u0006\bõ\u0001\u0010ö\u0001J\u001d\u0010÷\u0001\u001a\u00020o2\u0007\u0010ï\u0001\u001a\u00020(2\t\u0010ø\u0001\u001a\u0004\u0018\u00010\u001aH\u0002J\u001b\u0010ù\u0001\u001a\u00020o2\u0007\u0010ú\u0001\u001a\u00020\u001e2\u0007\u0010\u0083\u0001\u001a\u00020(H\u0002J\u0012\u0010û\u0001\u001a\u00020o2\u0007\u0010ï\u0001\u001a\u00020(H\u0002J\t\u0010ü\u0001\u001a\u00020oH\u0002J\u0012\u0010ý\u0001\u001a\u00020\u001e2\u0007\u0010þ\u0001\u001a\u00020\u001eH\u0002J\u001b\u0010ÿ\u0001\u001a\u00020o2\u0007\u0010Ô\u0001\u001a\u00020\u001e2\u0007\u0010\u0080\u0002\u001a\u00020\u001eH\u0002J-\u0010\u0081\u0002\u001a\u00020\u001e2\u0007\u0010\u0082\u0002\u001a\u00020\u001e2\u0007\u0010Ô\u0001\u001a\u00020\u001e2\u0007\u0010\u0083\u0002\u001a\u00020\u001e2\u0007\u0010\u0084\u0002\u001a\u00020\u001eH\u0002J\u0012\u0010\u0085\u0002\u001a\u00020\u001e2\u0007\u0010Ô\u0001\u001a\u00020\u001eH\u0002J\u0012\u0010\u0086\u0002\u001a\u00020\u001e2\u0007\u0010Ô\u0001\u001a\u00020\u001eH\u0002J\u001b\u0010\u0087\u0002\u001a\u00020o2\u0007\u0010Ô\u0001\u001a\u00020\u001e2\u0007\u0010\u0088\u0002\u001a\u00020\u001eH\u0002J\t\u0010\u0089\u0002\u001a\u00020oH\u0002J$\u0010\u008a\u0002\u001a\u00020o2\u0007\u0010\u008b\u0002\u001a\u00020\u001e2\u0007\u0010\u008c\u0002\u001a\u00020\u001e2\u0007\u0010\u008d\u0002\u001a\u00020\u001eH\u0002J\u001b\u0010\u008e\u0002\u001a\u00020o2\u0007\u0010Ô\u0001\u001a\u00020\u001e2\u0007\u0010\u008f\u0002\u001a\u00020\u001eH\u0002J6\u0010\u0090\u0002\u001a\b0\u0089\u0001j\u0003`\u008a\u00012\u0007\u0010Ô\u0001\u001a\u00020\u001e2\u0007\u0010\u0083\u0002\u001a\u00020\u001e2\r\u0010\u0091\u0002\u001a\b0\u0089\u0001j\u0003`\u008a\u0001H\u0002¢\u0006\u0003\u0010\u0092\u0002J\u0016\u0010\u0093\u0002\u001a\u00020\u001e*\u00020J2\u0007\u0010Ô\u0001\u001a\u00020\u001eH\u0002J#\u0010\u0094\u0002\u001a\u00020(2\u0007\u0010\u0095\u0002\u001a\u00020>2\t\u0010\u0096\u0002\u001a\u0004\u0018\u00010|H\u0000¢\u0006\u0003\b\u0097\u0002J\u000f\u0010\u0098\u0002\u001a\u00020\u001eH\u0001¢\u0006\u0003\b\u0099\u0002J\t\u0010\u009a\u0002\u001a\u00020oH\u0017J\t\u0010\u009b\u0002\u001a\u00020oH\u0002J\u001b\u0010\u009c\u0002\u001a\u00020(2\u0007\u0010\u009d\u0002\u001a\u00020(2\u0007\u0010\u009e\u0002\u001a\u00020\u001eH\u0017J\t\u0010\u009f\u0002\u001a\u00020oH\u0017J\u0012\u0010 \u0002\u001a\u00020o2\u0007\u0010º\u0001\u001a\u00020(H\u0017J\u0011\u0010¡\u0002\u001a\u00020\u00012\u0006\u0010p\u001a\u00020\u001eH\u0017J\t\u0010¢\u0002\u001a\u00020oH\u0002J\u0012\u0010£\u0002\u001a\u00020o2\u0007\u0010\u0095\u0002\u001a\u00020>H\u0002J\f\u0010¤\u0002\u001a\u0005\u0018\u00010¥\u0002H\u0017J\"\u0010¦\u0002\u001a\u0012\u0012\u0005\u0012\u00030¨\u0002\u0012\u0004\u0012\u00020o\u0018\u00010§\u00022\u0007\u0010\u0095\u0002\u001a\u00020>H\u0002J!\u0010©\u0002\u001a\u00020o2\u000b\u0010?\u001a\u0007\u0012\u0002\b\u00030ª\u00022\t\u0010«\u0002\u001a\u0004\u0018\u00010|H\u0017J8\u0010¬\u0002\u001a\u00020o2\u0010\u0010\u00ad\u0002\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010|0ª\u00022\u0007\u0010®\u0002\u001a\u0002002\t\u0010«\u0002\u001a\u0004\u0018\u00010|2\u0007\u0010¯\u0002\u001a\u00020(H\u0002J*\u0010°\u0002\u001a\u00020o2\u001f\u0010±\u0002\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030´\u0002\u0012\u0007\u0012\u0005\u0018\u00010´\u00020³\u00020²\u0002H\u0017J*\u0010µ\u0002\u001a\u00020o2\u001f\u0010±\u0002\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030´\u0002\u0012\u0007\u0012\u0005\u0018\u00010´\u00020³\u00020²\u0002H\u0002J1\u0010¶\u0002\u001a\u0003H·\u0002\"\u0005\b\u0000\u0010·\u00022\u0006\u0010I\u001a\u00020J2\u000f\u0010±\u0001\u001a\n\u0012\u0005\u0012\u0003H·\u00020\u0094\u0001H\u0082\b¢\u0006\u0003\u0010¸\u0002Jq\u0010¹\u0002\u001a\u0003H·\u0002\"\u0005\b\u0000\u0010·\u00022\f\b\u0002\u0010º\u0002\u001a\u0005\u0018\u00010»\u00022\f\b\u0002\u0010¼\u0002\u001a\u0005\u0018\u00010»\u00022\u000b\b\u0002\u0010þ\u0001\u001a\u0004\u0018\u00010\u001e2\u001e\b\u0002\u0010+\u001a\u0018\u0012\u0013\u0012\u0011\u0012\u0004\u0012\u00020>\u0012\u0006\u0012\u0004\u0018\u00010|0³\u00020²\u00022\u000f\u0010±\u0001\u001a\n\u0012\u0005\u0012\u0003H·\u00020\u0094\u0001H\u0002¢\u0006\u0003\u0010½\u0002J\u0013\u0010¾\u0002\u001a\u00020o2\b\u0010¾\u0002\u001a\u00030¿\u0002H\u0017J\u001b\u0010À\u0002\u001a\u00020o2\u0006\u0010p\u001a\u00020\u001e2\b\u0010¾\u0002\u001a\u00030¿\u0002H\u0017J\t\u0010Á\u0002\u001a\u00020oH\u0017J\t\u0010Â\u0002\u001a\u00020oH\u0016J\u001a\u0010Ã\u0002\u001a\u00030Ä\u00022\b\u0010?\u001a\u0004\u0018\u00010|H\u0000¢\u0006\u0003\bÅ\u0002J\f\u0010Æ\u0002\u001a\u0005\u0018\u00010Ä\u0002H\u0002J+\u0010Ç\u0002\u001a\n\u0012\u0005\u0012\u00030È\u00020²\u00022\u0007\u0010Ô\u0001\u001a\u00020\u001e2\t\u0010É\u0002\u001a\u0004\u0018\u00010\u001eH\u0002¢\u0006\u0003\u0010Ê\u0002J\u000f\u0010Ë\u0002\u001a\n\u0012\u0005\u0012\u00030È\u00020²\u0002JI\u0010Ì\u0002\u001a\u00020o2\u0014\u0010Í\u0002\u001a\u000f\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020|0Î\u00022\u0014\u0010\u00ad\u0002\u001a\u000f\u0012\u0004\u0012\u00020o0\u0094\u0001¢\u0006\u0003\bÏ\u00022\t\u0010Ð\u0002\u001a\u0004\u0018\u00010eH\u0000¢\u0006\u0006\bÑ\u0002\u0010Ò\u0002J\u001f\u0010Ó\u0002\u001a\u00020o2\u000e\u0010±\u0001\u001a\t\u0012\u0004\u0012\u00020o0\u0094\u0001H\u0000¢\u0006\u0003\bÔ\u0002J3\u0010Õ\u0002\u001a\u00020(2\u0014\u0010Í\u0002\u001a\u000f\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020|0Î\u00022\t\u0010Ð\u0002\u001a\u0004\u0018\u00010eH\u0000¢\u0006\u0006\bÖ\u0002\u0010×\u0002J&\u0010Ø\u0002\u001a\u00020o2\u0014\u0010Í\u0002\u001a\u000f\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020|0Î\u0002¢\u0006\u0006\bÙ\u0002\u0010Ú\u0002J@\u0010Û\u0002\u001a\u00020o2\u0014\u0010Í\u0002\u001a\u000f\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020|0Î\u00022\u0016\u0010\u00ad\u0002\u001a\u0011\u0012\u0004\u0012\u00020o\u0018\u00010\u0094\u0001¢\u0006\u0003\bÏ\u0002H\u0002¢\u0006\u0006\bÜ\u0002\u0010Ý\u0002J\u0018\u0010ã\u0002\u001a\u0004\u0018\u00010|*\u00020J2\u0007\u0010þ\u0001\u001a\u00020\u001eH\u0002J\t\u0010ä\u0002\u001a\u00020oH\u0002J\t\u0010å\u0002\u001a\u00020oH\u0002J\u0012\u0010æ\u0002\u001a\u00020o2\u0007\u0010ç\u0002\u001a\u00020`H\u0002J\t\u0010è\u0002\u001a\u00020oH\u0002J\u0012\u0010é\u0002\u001a\u00020o2\u0007\u0010ê\u0002\u001a\u00020\u001eH\u0002J\t\u0010ë\u0002\u001a\u00020oH\u0002J\t\u0010ì\u0002\u001a\u00020oH\u0002J\t\u0010í\u0002\u001a\u00020oH\u0002J\u000f\u0010î\u0002\u001a\u00020oH\u0000¢\u0006\u0003\bï\u0002J0\u0010ð\u0002\u001a\u00020o2\u0007\u0010ñ\u0002\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001e2\b\u0010{\u001a\u0004\u0018\u00010|2\t\u0010ð\u0001\u001a\u0004\u0018\u00010|H\u0082\bJ\u001b\u0010ò\u0002\u001a\u00020o2\u0007\u0010ñ\u0002\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0082\bJ0\u0010ó\u0002\u001a\u00020o2\u0007\u0010ñ\u0002\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001e2\b\u0010{\u001a\u0004\u0018\u00010|2\t\u0010ð\u0001\u001a\u0004\u0018\u00010|H\u0082\bJ\u001b\u0010ô\u0002\u001a\u00020o2\u0007\u0010ñ\u0002\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0082\bJ\u000f\u0010õ\u0002\u001a\u00020\u001eH\u0000¢\u0006\u0003\bö\u0002J\u000b\u0010þ\u0002\u001a\u0004\u0018\u00010|H\u0016J\u0013\u0010ÿ\u0002\u001a\u00020o2\b\u0010?\u001a\u0004\u0018\u00010|H\u0016J\u0013\u0010\u0080\u0003\u001a\u00020o2\b\u0010\u0095\u0002\u001a\u00030ø\u0002H\u0016R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00101\u001a\n\u0012\u0004\u0012\u000200\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0004\n\u0002\u0010<R\u0016\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u001e\u0010@\u001a\u00020(2\u0006\u0010?\u001a\u00020(@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u001e\u0010C\u001a\u00020(2\u0006\u0010?\u001a\u00020(@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bD\u0010BR\u0014\u0010E\u001a\u00020(8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bF\u0010BR\u0014\u0010G\u001a\u00020(8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bH\u0010BR\u001a\u0010I\u001a\u00020JX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001a\u0010O\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u000e\u0010T\u001a\u00020UX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010W\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010X\u001a\u0004\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u000e\u0010]\u001a\u00020^X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020`X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010d\u001a\u0004\u0018\u00010eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010f\u001a\u0004\u0018\u00010g8@X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010iR\u0014\u0010j\u001a\u00020kX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010mR\u001a\u0010v\u001a\u00020(8VX\u0097\u0004¢\u0006\f\u0012\u0004\bw\u0010x\u001a\u0004\by\u0010BR)\u0010\u0083\u0001\u001a\u00020(2\u0006\u0010?\u001a\u00020(8\u0016@RX\u0097\u000e¢\u0006\u0010\n\u0000\u0012\u0005\b\u0084\u0001\u0010x\u001a\u0005\b\u0085\u0001\u0010BR\u001d\u0010\u0086\u0001\u001a\u00020(8VX\u0097\u0004¢\u0006\u000e\u0012\u0005\b\u0087\u0001\u0010x\u001a\u0005\b\u0088\u0001\u0010BR9\u0010\u008b\u0001\u001a\b0\u0089\u0001j\u0003`\u008a\u00012\f\u0010?\u001a\b0\u0089\u0001j\u0003`\u008a\u00018\u0016@RX\u0097\u000e¢\u0006\u0014\n\u0003\u0010\u008f\u0001\u0012\u0005\b\u008c\u0001\u0010x\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001R\u0017\u0010ª\u0001\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\b\u001a\u0006\b«\u0001\u0010¬\u0001R\u0012\u0010È\u0001\u001a\u0005\u0018\u00010É\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010Ê\u0001\u001a\u00030É\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bË\u0001\u0010Ì\u0001R\u0018\u0010Ð\u0001\u001a\u00030Ñ\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bÒ\u0001\u0010Ó\u0001R\u0017\u0010æ\u0001\u001a\u00020\u001e8@X\u0080\u0004¢\u0006\b\u001a\u0006\bç\u0001\u0010¬\u0001R\u0019\u0010è\u0001\u001a\u0004\u0018\u00010>8@X\u0080\u0004¢\u0006\b\u001a\u0006\bé\u0001\u0010ê\u0001R\u0013\u0010Þ\u0002\u001a\u00020(8F¢\u0006\u0007\u001a\u0005\bß\u0002\u0010BR\u001d\u0010à\u0002\u001a\u0004\u0018\u00010|*\u00020J8BX\u0082\u0004¢\u0006\b\u001a\u0006\bá\u0002\u0010â\u0002R\u001a\u0010÷\u0002\u001a\u0005\u0018\u00010ø\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\bù\u0002\u0010ú\u0002R\u0019\u0010û\u0002\u001a\u0004\u0018\u00010|8VX\u0096\u0004¢\u0006\b\u001a\u0006\bü\u0002\u0010ý\u0002¨\u0006\u0083\u0003"}, d2 = {"Landroidx/compose/runtime/ComposerImpl;", "Landroidx/compose/runtime/Composer;", "applier", "Landroidx/compose/runtime/Applier;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "slotTable", "Landroidx/compose/runtime/SlotTable;", "abandonSet", "", "Landroidx/compose/runtime/RememberObserver;", "changes", "Landroidx/compose/runtime/changelist/ChangeList;", "lateChanges", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "composition", "Landroidx/compose/runtime/CompositionImpl;", "<init>", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/SlotTable;Ljava/util/Set;Landroidx/compose/runtime/changelist/ChangeList;Landroidx/compose/runtime/changelist/ChangeList;Landroidx/compose/runtime/CompositionObserverHolder;Landroidx/compose/runtime/CompositionImpl;)V", "getApplier", "()Landroidx/compose/runtime/Applier;", "getComposition", "()Landroidx/compose/runtime/CompositionImpl;", "pendingStack", "Landroidx/compose/runtime/Stack;", "Landroidx/compose/runtime/Pending;", "Ljava/util/ArrayList;", "pending", "nodeIndex", "", "groupNodeCount", "rGroupIndex", "parentStateStack", "Landroidx/compose/runtime/IntStack;", "nodeCountOverrides", "", "nodeCountVirtualOverrides", "Landroidx/collection/MutableIntIntMap;", "forceRecomposeScopes", "", "forciblyRecompose", "nodeExpected", "invalidations", "", "Landroidx/compose/runtime/Invalidation;", "entersStack", "rootProvider", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "providerUpdates", "Landroidx/collection/MutableIntObjectMap;", "providersInvalid", "providersInvalidStack", "reusing", "reusingGroup", "childrenComposing", "compositionToken", "sourceMarkersEnabled", "derivedStateObserver", "androidx/compose/runtime/ComposerImpl$derivedStateObserver$1", "Landroidx/compose/runtime/ComposerImpl$derivedStateObserver$1;", "invalidateStack", "Landroidx/compose/runtime/RecomposeScopeImpl;", "value", "isComposing", "isComposing$runtime", "()Z", "isDisposed", "isDisposed$runtime", "areChildrenComposing", "getAreChildrenComposing$runtime", "hasPendingChanges", "getHasPendingChanges$runtime", "reader", "Landroidx/compose/runtime/SlotReader;", "getReader$runtime", "()Landroidx/compose/runtime/SlotReader;", "setReader$runtime", "(Landroidx/compose/runtime/SlotReader;)V", "insertTable", "getInsertTable$runtime", "()Landroidx/compose/runtime/SlotTable;", "setInsertTable$runtime", "(Landroidx/compose/runtime/SlotTable;)V", "writer", "Landroidx/compose/runtime/SlotWriter;", "writerHasAProvider", "providerCache", "deferredChanges", "getDeferredChanges$runtime", "()Landroidx/compose/runtime/changelist/ChangeList;", "setDeferredChanges$runtime", "(Landroidx/compose/runtime/changelist/ChangeList;)V", "changeListWriter", "Landroidx/compose/runtime/changelist/ComposerChangeListWriter;", "insertAnchor", "Landroidx/compose/runtime/Anchor;", "insertFixups", "Landroidx/compose/runtime/changelist/FixupList;", "pausable", "shouldPauseCallback", "Landroidx/compose/runtime/ShouldPauseCallback;", "errorContext", "Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "getErrorContext$runtime", "()Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "applyCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getApplyCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "startReplaceableGroup", "", "key", "endReplaceableGroup", "startReplaceGroup", "endReplaceGroup", "startDefaults", "endDefaults", "defaultsInvalid", "getDefaultsInvalid$annotations", "()V", "getDefaultsInvalid", "startMovableGroup", "dataKey", "", "endMovableGroup", "startRoot", "endRoot", "abortRoot", "changesApplied", "changesApplied$runtime", "inserting", "getInserting$annotations", "getInserting", "skipping", "getSkipping$annotations", "getSkipping", "", "Landroidx/compose/runtime/CompositeKeyHashCode;", "compositeKeyHashCode", "getCompositeKeyHashCode$annotations", "getCompositeKeyHashCode", "()J", "J", "collectParameterInformation", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "Lkotlin/Function0;", "dispose", "dispose$runtime", "deactivate", "deactivate$runtime", "forceRecomposeScopes$runtime", "startGroup", "endGroup", "skipGroup", "startNode", "startReusableNode", "createNode", "T", "factory", "useNode", "endNode", "startReusableGroup", "endReusableGroup", "disableReusing", "enableReusing", "startReuseFromRoot", "endReuseFromRoot", "currentMarker", "getCurrentMarker", "()I", "endToMarker", "marker", "apply", "V", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "joinKey", "left", "right", "nextSlot", "nextSlotForCache", "changed", "changedInstance", "", "", "", "", "", "cache", "invalid", "(ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "updateSlot", "updateValue", "updateCachedValue", "rememberObserverGroupIndex", "_compositionData", "Landroidx/compose/runtime/tooling/CompositionData;", "compositionData", "getCompositionData", "()Landroidx/compose/runtime/tooling/CompositionData;", "recordSideEffect", "effect", "currentCompositionLocalScope", "currentCompositionLocalMap", "Landroidx/compose/runtime/CompositionLocalMap;", "getCurrentCompositionLocalMap", "()Landroidx/compose/runtime/CompositionLocalMap;", "group", "updateProviderMapGroup", "parentScope", "currentProviders", "startProvider", "Landroidx/compose/runtime/ProvidedValue;", "recordProviderUpdate", "providers", "endProvider", "startProviders", "values", "", "([Landroidx/compose/runtime/ProvidedValue;)V", "endProviders", "consume", "Landroidx/compose/runtime/CompositionLocal;", "(Landroidx/compose/runtime/CompositionLocal;)Ljava/lang/Object;", "buildContext", "changeCount", "getChangeCount$runtime", "currentRecomposeScope", "getCurrentRecomposeScope$runtime", "()Landroidx/compose/runtime/RecomposeScopeImpl;", "ensureWriter", "createFreshInsertTable", "forceFreshInsertTable", "startReaderGroup", "isNode", ApkUtil.NAME_data, "start", "objectKey", "kind", "Landroidx/compose/runtime/GroupKind;", "start-BaiHCIY", "(ILjava/lang/Object;ILjava/lang/Object;)V", "enterGroup", "newPending", "exitGroup", "expectedNodeCount", "end", "recomposeToGroupEnd", "insertedGroupVirtualIndex", "index", "updateNodeCountOverrides", "newCount", "nodeIndexOf", "groupLocation", "recomposeGroup", "recomposeIndex", "rGroupIndexOf", "updatedNodeCount", "updateNodeCount", "count", "clearUpdatedNodeCounts", "recordUpsAndDowns", "oldGroup", "newGroup", "commonRoot", "doRecordDownsFor", "nearestCommonRoot", "compositeKeyOf", "recomposeKey", "(IIJ)J", "groupCompositeKeyPart", "tryImminentInvalidation", "scope", "instance", "tryImminentInvalidation$runtime", "parentKey", "parentKey$runtime", "skipCurrentGroup", "skipReaderToGroupEnd", "shouldExecute", "parametersChanged", "flags", "skipToGroupEnd", "deactivateToEndGroup", "startRestartGroup", "addRecomposeScope", "enterRecomposeScope", "endRestartGroup", "Landroidx/compose/runtime/ScopeUpdateScope;", "exitRecomposeScope", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "insertMovableContent", "Landroidx/compose/runtime/MovableContent;", "parameter", "invokeMovableContentLambda", "content", "locals", "force", "insertMovableContentReferences", "references", "", "Lkotlin/Pair;", "Landroidx/compose/runtime/MovableContentStateReference;", "insertMovableContentGuarded", "withReader", "R", "(Landroidx/compose/runtime/SlotReader;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "recomposeMovableContent", "from", "Landroidx/compose/runtime/ControlledComposition;", "to", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ControlledComposition;Ljava/lang/Integer;Ljava/util/List;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "sourceInformation", "", "sourceInformationMarkerStart", "sourceInformationMarkerEnd", "disableSourceInformation", "stackTraceForValue", "Landroidx/compose/runtime/tooling/ComposeStackTrace;", "stackTraceForValue$runtime", "currentStackTrace", "stackTraceForGroup", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "dataOffset", "(ILjava/lang/Integer;)Ljava/util/List;", "parentStackTrace", "composeContent", "invalidationsRequested", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/compose/runtime/Composable;", "shouldPause", "composeContent--ZbOJvo$runtime", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/ShouldPauseCallback;)V", "prepareCompose", "prepareCompose$runtime", "recompose", "recompose-aFTiNEg$runtime", "(Landroidx/collection/MutableScatterMap;Landroidx/compose/runtime/ShouldPauseCallback;)Z", "updateComposerInvalidations", "updateComposerInvalidations-RY85e9Y", "(Landroidx/collection/MutableScatterMap;)V", "doCompose", "doCompose-aFTiNEg", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function2;)V", "hasInvalidations", "getHasInvalidations", "node", "getNode", "(Landroidx/compose/runtime/SlotReader;)Ljava/lang/Object;", "nodeAt", "validateNodeExpected", "validateNodeNotExpected", "recordInsert", "anchor", "recordDelete", "reportFreeMovableContent", "groupBeingRemoved", "reportAllMovableContent", "finalizeCompose", "cleanUpCompose", "verifyConsistent", "verifyConsistent$runtime", "updateCompositeKeyWhenWeEnterGroup", "groupKey", "updateCompositeKeyWhenWeEnterGroupKeyHash", "updateCompositeKeyWhenWeExitGroup", "updateCompositeKeyWhenWeExitGroupKeyHash", "stacksSize", "stacksSize$runtime", "recomposeScope", "Landroidx/compose/runtime/RecomposeScope;", "getRecomposeScope", "()Landroidx/compose/runtime/RecomposeScope;", "recomposeScopeIdentity", "getRecomposeScopeIdentity", "()Ljava/lang/Object;", "rememberedValue", "updateRememberedValue", "recordUsed", "CompositionContextHolder", "CompositionContextImpl", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposerImpl implements Composer {
    public static final int $stable = 8;
    private CompositionData _compositionData;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final CoroutineContext applyCoroutineContext;
    private final ComposerChangeListWriter changeListWriter;
    private ChangeList changes;
    private int childrenComposing;
    private long compositeKeyHashCode;
    private final CompositionImpl composition;
    private int compositionToken;
    private ChangeList deferredChanges;
    private final ComposerImpl$derivedStateObserver$1 derivedStateObserver;
    private final CompositionErrorContextImpl errorContext;
    private boolean forceRecomposeScopes;
    private boolean forciblyRecompose;
    private int groupNodeCount;
    private Anchor insertAnchor;
    private FixupList insertFixups;
    private SlotTable insertTable;
    private boolean inserting;
    private final ArrayList<RecomposeScopeImpl> invalidateStack;
    private boolean isComposing;
    private boolean isDisposed;
    private ChangeList lateChanges;
    private int[] nodeCountOverrides;
    private MutableIntIntMap nodeCountVirtualOverrides;
    private boolean nodeExpected;
    private int nodeIndex;
    private final CompositionObserverHolder observerHolder;
    private final CompositionContext parentContext;
    private boolean pausable;
    private Pending pending;
    private PersistentCompositionLocalMap providerCache;
    private MutableIntObjectMap<PersistentCompositionLocalMap> providerUpdates;
    private boolean providersInvalid;
    private int rGroupIndex;
    private SlotReader reader;
    private boolean reusing;
    private ShouldPauseCallback shouldPauseCallback;
    private final SlotTable slotTable;
    private boolean sourceMarkersEnabled;
    private SlotWriter writer;
    private boolean writerHasAProvider;
    private final ArrayList<Pending> pendingStack = Stack.m2373constructorimpl$default(null, 1, null);
    private final IntStack parentStateStack = new IntStack();
    private final List<Invalidation> invalidations = new ArrayList();
    private final IntStack entersStack = new IntStack();
    private PersistentCompositionLocalMap rootProvider = PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf();
    private final IntStack providersInvalidStack = new IntStack();
    private int reusingGroup = -1;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016R\u0015\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/ComposerImpl$CompositionContextHolder;", "Landroidx/compose/runtime/RememberObserver;", "ref", "Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "Landroidx/compose/runtime/ComposerImpl;", "<init>", "(Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;)V", "getRef", "()Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "onRemembered", "", "onAbandoned", "onForgotten", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class CompositionContextHolder implements RememberObserver {
        public static final int $stable = 8;
        private final CompositionContextImpl ref;

        public CompositionContextHolder(CompositionContextImpl compositionContextImpl) {
            this.ref = compositionContextImpl;
        }

        public final CompositionContextImpl getRef() {
            return this.ref;
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onAbandoned() {
            this.ref.dispose();
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onForgotten() {
            this.ref.dispose();
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onRemembered() {
        }
    }

    @Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B-\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\"\u001a\u00020#J\u0015\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020&H\u0010¢\u0006\u0002\b'J\u0015\u0010(\u001a\u00020#2\u0006\u0010%\u001a\u00020&H\u0010¢\u0006\u0002\b)J\u0015\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0010¢\u0006\u0002\b-J\u0015\u0010.\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0010¢\u0006\u0002\b/J\u0015\u00100\u001a\u00020#2\u0006\u00101\u001a\u000202H\u0010¢\u0006\u0002\b3J*\u0010<\u001a\u00020#2\u0006\u0010+\u001a\u00020,2\u0011\u0010=\u001a\r\u0012\u0004\u0012\u00020#0>¢\u0006\u0002\b?H\u0010¢\u0006\u0004\b@\u0010AJ8\u0010B\u001a\b\u0012\u0004\u0012\u0002020C2\u0006\u0010+\u001a\u00020,2\u0006\u0010D\u001a\u00020E2\u0011\u0010=\u001a\r\u0012\u0004\u0012\u00020#0>¢\u0006\u0002\b?H\u0010¢\u0006\u0004\bF\u0010GJ1\u0010H\u001a\b\u0012\u0004\u0012\u0002020C2\u0006\u0010+\u001a\u00020,2\u0006\u0010D\u001a\u00020E2\f\u0010I\u001a\b\u0012\u0004\u0012\u0002020CH\u0010¢\u0006\u0002\bJJ\u0015\u0010K\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0010¢\u0006\u0002\bLJ\u0015\u0010M\u001a\u00020#2\u0006\u00101\u001a\u000202H\u0010¢\u0006\u0002\bNJ\r\u0010R\u001a\u00020PH\u0010¢\u0006\u0002\bXJ\u000e\u0010Y\u001a\u00020#2\u0006\u00101\u001a\u00020PJ\u001b\u0010Z\u001a\u00020#2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0010¢\u0006\u0002\b\\J\r\u0010]\u001a\u00020#H\u0010¢\u0006\u0002\b^J\r\u0010_\u001a\u00020#H\u0010¢\u0006\u0002\b`J\u0015\u0010a\u001a\u00020#2\u0006\u0010b\u001a\u00020cH\u0010¢\u0006\u0002\bdJ\u0015\u0010e\u001a\u00020#2\u0006\u0010b\u001a\u00020cH\u0010¢\u0006\u0002\bfJ\u0017\u0010g\u001a\u0004\u0018\u00010h2\u0006\u0010b\u001a\u00020cH\u0010¢\u0006\u0002\biJ)\u0010j\u001a\u00020#2\u0006\u0010b\u001a\u00020c2\u0006\u0010k\u001a\u00020h2\n\u0010l\u001a\u0006\u0012\u0002\b\u00030mH\u0010¢\u0006\u0002\bnJ\u0015\u0010o\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0010¢\u0006\u0002\bpJ\u0016\u0010t\u001a\u00020u2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020#0>H\u0016R\u001a\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0090\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\u0006X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R(\u0010\u0014\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0014\u0010\u001e\u001a\u00020\u00068PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0010R\u0014\u0010 \u001a\u00020\u00068PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0010R\u0014\u00104\u001a\u0002058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u0002058PX\u0090\u0004¢\u0006\f\u0012\u0004\b9\u0010:\u001a\u0004\b;\u00107R+\u0010Q\u001a\u00020P2\u0006\u0010O\u001a\u00020P8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bV\u0010W\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR\u0014\u0010+\u001a\u00020q8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\br\u0010s¨\u0006w"}, d2 = {"Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "Landroidx/compose/runtime/CompositionContext;", "compositeKeyHashCode", "", "Landroidx/compose/runtime/CompositeKeyHashCode;", "collectingParameterInformation", "", "collectingSourceInformation", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "<init>", "(Landroidx/compose/runtime/ComposerImpl;JZZLandroidx/compose/runtime/CompositionObserverHolder;)V", "getCompositeKeyHashCode$runtime", "()J", "J", "getCollectingParameterInformation$runtime", "()Z", "getCollectingSourceInformation$runtime", "getObserverHolder$runtime", "()Landroidx/compose/runtime/CompositionObserverHolder;", "inspectionTables", "", "Landroidx/compose/runtime/tooling/CompositionData;", "getInspectionTables", "()Ljava/util/Set;", "setInspectionTables", "(Ljava/util/Set;)V", "composers", "Landroidx/compose/runtime/ComposerImpl;", "getComposers", "collectingCallByInformation", "getCollectingCallByInformation$runtime", "stackTraceEnabled", "getStackTraceEnabled$runtime", "dispose", "", "registerComposer", "composer", "Landroidx/compose/runtime/Composer;", "registerComposer$runtime", "unregisterComposer", "unregisterComposer$runtime", "registerComposition", "composition", "Landroidx/compose/runtime/ControlledComposition;", "registerComposition$runtime", "unregisterComposition", "unregisterComposition$runtime", "reportPausedScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "reportPausedScope$runtime", "effectCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getEffectCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "recomposeCoroutineContext", "getRecomposeCoroutineContext$runtime$annotations", "()V", "getRecomposeCoroutineContext$runtime", "composeInitial", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "composeInitial$runtime", "(Landroidx/compose/runtime/ControlledComposition;Lkotlin/jvm/functions/Function2;)V", "composeInitialPaused", "Landroidx/collection/ScatterSet;", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "composeInitialPaused$runtime", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ShouldPauseCallback;Lkotlin/jvm/functions/Function2;)Landroidx/collection/ScatterSet;", "recomposePaused", "invalidScopes", "recomposePaused$runtime", "invalidate", "invalidate$runtime", "invalidateScope", "invalidateScope$runtime", "<set-?>", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "compositionLocalScope", "getCompositionLocalScope", "()Landroidx/compose/runtime/PersistentCompositionLocalMap;", "setCompositionLocalScope", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;)V", "compositionLocalScope$delegate", "Landroidx/compose/runtime/MutableState;", "getCompositionLocalScope$runtime", "updateCompositionLocalScope", "recordInspectionTable", "table", "recordInspectionTable$runtime", "startComposing", "startComposing$runtime", "doneComposing", "doneComposing$runtime", "insertMovableContent", "reference", "Landroidx/compose/runtime/MovableContentStateReference;", "insertMovableContent$runtime", "deletedMovableContent", "deletedMovableContent$runtime", "movableContentStateResolve", "Landroidx/compose/runtime/MovableContentState;", "movableContentStateResolve$runtime", "movableContentStateReleased", ApkUtil.NAME_data, "applier", "Landroidx/compose/runtime/Applier;", "movableContentStateReleased$runtime", "reportRemovedComposition", "reportRemovedComposition$runtime", "Landroidx/compose/runtime/Composition;", "getComposition$runtime", "()Landroidx/compose/runtime/Composition;", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public final class CompositionContextImpl extends CompositionContext {
        private final boolean collectingParameterInformation;
        private final boolean collectingSourceInformation;
        private final long compositeKeyHashCode;
        private Set<Set<CompositionData>> inspectionTables;
        private final CompositionObserverHolder observerHolder;
        private final Set<ComposerImpl> composers = new LinkedHashSet();

        /* JADX INFO: renamed from: compositionLocalScope$delegate, reason: from kotlin metadata */
        private final MutableState compositionLocalScope = SnapshotStateKt.mutableStateOf(PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf(), SnapshotStateKt.referentialEqualityPolicy());

        public CompositionContextImpl(long j, boolean z, boolean z2, CompositionObserverHolder compositionObserverHolder) {
            this.compositeKeyHashCode = j;
            this.collectingParameterInformation = z;
            this.collectingSourceInformation = z2;
            this.observerHolder = compositionObserverHolder;
        }

        private final PersistentCompositionLocalMap getCompositionLocalScope() {
            return (PersistentCompositionLocalMap) this.compositionLocalScope.getValue();
        }

        public static /* synthetic */ void getRecomposeCoroutineContext$runtime$annotations() {
        }

        private final void setCompositionLocalScope(PersistentCompositionLocalMap persistentCompositionLocalMap) {
            this.compositionLocalScope.setValue(persistentCompositionLocalMap);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void composeInitial$runtime(ControlledComposition composition, Function2<? super Composer, ? super Integer, Unit> content) {
            ComposerImpl.this.parentContext.composeInitial$runtime(composition, content);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public ScatterSet<RecomposeScopeImpl> composeInitialPaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, Function2<? super Composer, ? super Integer, Unit> content) {
            return ComposerImpl.this.parentContext.composeInitialPaused$runtime(composition, shouldPause, content);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void deletedMovableContent$runtime(MovableContentStateReference reference) {
            ComposerImpl.this.parentContext.deletedMovableContent$runtime(reference);
        }

        public final void dispose() {
            if (this.composers.isEmpty()) {
                return;
            }
            Set<Set<CompositionData>> set = this.inspectionTables;
            if (set != null) {
                for (ComposerImpl composerImpl : this.composers) {
                    Iterator<Set<CompositionData>> it = set.iterator();
                    while (it.hasNext()) {
                        it.next().remove(composerImpl.getCompositionData());
                    }
                }
            }
            this.composers.clear();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void doneComposing$runtime() {
            ComposerImpl.this.childrenComposing--;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public boolean getCollectingCallByInformation$runtime() {
            return ComposerImpl.this.parentContext.getCollectingCallByInformation$runtime();
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getCollectingParameterInformation$runtime, reason: from getter */
        public boolean getCollectingParameterInformation() {
            return this.collectingParameterInformation;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getCollectingSourceInformation$runtime, reason: from getter */
        public boolean getCollectingSourceInformation() {
            return this.collectingSourceInformation;
        }

        public final Set<ComposerImpl> getComposers() {
            return this.composers;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getCompositeKeyHashCode$runtime, reason: from getter */
        public long getCompositeKeyHashCode() {
            return this.compositeKeyHashCode;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public Composition getComposition$runtime() {
            return ComposerImpl.this.getComposition();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public PersistentCompositionLocalMap getCompositionLocalScope$runtime() {
            return getCompositionLocalScope();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public CoroutineContext getEffectCoroutineContext() {
            return ComposerImpl.this.parentContext.getEffectCoroutineContext();
        }

        public final Set<Set<CompositionData>> getInspectionTables() {
            return this.inspectionTables;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getObserverHolder$runtime, reason: from getter */
        public CompositionObserverHolder getObserverHolder() {
            return this.observerHolder;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public CoroutineContext getRecomposeCoroutineContext$runtime() {
            return CompositionKt.getRecomposeCoroutineContext(ComposerImpl.this.getComposition());
        }

        @Override // androidx.compose.runtime.CompositionContext
        public boolean getStackTraceEnabled$runtime() {
            return ComposerImpl.this.parentContext.getStackTraceEnabled$runtime();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void insertMovableContent$runtime(MovableContentStateReference reference) {
            ComposerImpl.this.parentContext.insertMovableContent$runtime(reference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidate$runtime(ControlledComposition composition) {
            ComposerImpl.this.parentContext.invalidate$runtime(ComposerImpl.this.getComposition());
            ComposerImpl.this.parentContext.invalidate$runtime(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidateScope$runtime(RecomposeScopeImpl scope) {
            ComposerImpl.this.parentContext.invalidateScope$runtime(scope);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void movableContentStateReleased$runtime(MovableContentStateReference reference, MovableContentState data, Applier<?> applier) {
            ComposerImpl.this.parentContext.movableContentStateReleased$runtime(reference, data, applier);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public MovableContentState movableContentStateResolve$runtime(MovableContentStateReference reference) {
            return ComposerImpl.this.parentContext.movableContentStateResolve$runtime(reference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public ScatterSet<RecomposeScopeImpl> recomposePaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, ScatterSet<RecomposeScopeImpl> invalidScopes) {
            return ComposerImpl.this.parentContext.recomposePaused$runtime(composition, shouldPause, invalidScopes);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void recordInspectionTable$runtime(Set<CompositionData> table) {
            Set hashSet = this.inspectionTables;
            if (hashSet == null) {
                hashSet = new HashSet();
                this.inspectionTables = hashSet;
            }
            hashSet.add(table);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposer$runtime(Composer composer) {
            composer.getClass();
            super.registerComposer$runtime((ComposerImpl) composer);
            this.composers.add(composer);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposition$runtime(ControlledComposition composition) {
            ComposerImpl.this.parentContext.registerComposition$runtime(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void reportPausedScope$runtime(RecomposeScopeImpl scope) {
            ComposerImpl.this.parentContext.reportPausedScope$runtime(scope);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void reportRemovedComposition$runtime(ControlledComposition composition) {
            ComposerImpl.this.parentContext.reportRemovedComposition$runtime(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public CancellationHandle scheduleFrameEndCallback(Function0<Unit> action) {
            return ComposerImpl.this.parentContext.scheduleFrameEndCallback(action);
        }

        public final void setInspectionTables(Set<Set<CompositionData>> set) {
            this.inspectionTables = set;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void startComposing$runtime() {
            ComposerImpl.this.childrenComposing++;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposer$runtime(Composer composer) {
            Set<Set<CompositionData>> set = this.inspectionTables;
            if (set != null) {
                Iterator<T> it = set.iterator();
                while (it.hasNext()) {
                    Set set2 = (Set) it.next();
                    composer.getClass();
                    set2.remove(((ComposerImpl) composer).getCompositionData());
                }
            }
            TypeIntrinsics.asMutableCollection(this.composers).remove(composer);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposition$runtime(ControlledComposition composition) {
            ComposerImpl.this.parentContext.unregisterComposition$runtime(composition);
        }

        public final void updateCompositionLocalScope(PersistentCompositionLocalMap scope) {
            setCompositionLocalScope(scope);
        }
    }

    /* JADX WARN: Type inference failed for: r5v11, types: [androidx.compose.runtime.ComposerImpl$derivedStateObserver$1] */
    public ComposerImpl(Applier<?> applier, CompositionContext compositionContext, SlotTable slotTable, Set<RememberObserver> set, ChangeList changeList, ChangeList changeList2, CompositionObserverHolder compositionObserverHolder, CompositionImpl compositionImpl) {
        this.applier = applier;
        this.parentContext = compositionContext;
        this.slotTable = slotTable;
        this.abandonSet = set;
        this.changes = changeList;
        this.lateChanges = changeList2;
        this.observerHolder = compositionObserverHolder;
        this.composition = compositionImpl;
        this.sourceMarkersEnabled = compositionContext.getCollectingSourceInformation() || compositionContext.getCollectingCallByInformation$runtime();
        this.derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.ComposerImpl$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public void done(DerivedState<?> derivedState) {
                this.this$0.childrenComposing--;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public void start(DerivedState<?> derivedState) {
                this.this$0.childrenComposing++;
            }
        };
        this.invalidateStack = Stack.m2373constructorimpl$default(null, 1, null);
        SlotReader slotReaderOpenReader = slotTable.openReader();
        slotReaderOpenReader.close();
        this.reader = slotReaderOpenReader;
        SlotTable slotTable2 = new SlotTable();
        if (compositionContext.getCollectingSourceInformation()) {
            slotTable2.collectSourceInformation();
        }
        if (compositionContext.getCollectingCallByInformation$runtime()) {
            slotTable2.collectCalledByInformation();
        }
        this.insertTable = slotTable2;
        SlotWriter slotWriterOpenWriter = slotTable2.openWriter();
        slotWriterOpenWriter.close(true);
        this.writer = slotWriterOpenWriter;
        this.changeListWriter = new ComposerChangeListWriter(this, this.changes);
        SlotReader slotReaderOpenReader2 = this.insertTable.openReader();
        try {
            Anchor anchor = slotReaderOpenReader2.anchor(0);
            slotReaderOpenReader2.close();
            this.insertAnchor = anchor;
            this.insertFixups = new FixupList();
            this.errorContext = new CompositionErrorContextImpl(this);
            CoroutineContext effectCoroutineContext = compositionContext.getEffectCoroutineContext();
            EmptyCoroutineContext errorContext$runtime = getErrorContext$runtime();
            this.applyCoroutineContext = effectCoroutineContext.plus(errorContext$runtime == null ? EmptyCoroutineContext.INSTANCE : errorContext$runtime);
        } catch (Throwable th) {
            slotReaderOpenReader2.close();
            throw th;
        }
    }

    private final void abortRoot() {
        cleanUpCompose();
        Stack.m2371clearimpl(this.pendingStack);
        this.parentStateStack.clear();
        this.entersStack.clear();
        this.providersInvalidStack.clear();
        this.providerUpdates = null;
        this.insertFixups.clear();
        this.compositeKeyHashCode = 0L;
        this.childrenComposing = 0;
        this.nodeExpected = false;
        this.inserting = false;
        this.reusing = false;
        this.isComposing = false;
        this.forciblyRecompose = false;
        this.reusingGroup = -1;
        if (!this.reader.getClosed()) {
            this.reader.close();
        }
        if (this.writer.getClosed()) {
            return;
        }
        forceFreshInsertTable();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0063  */
    private final void addRecomposeScope() {
        RecomposeScopeImpl recomposeScopeImpl;
        boolean z;
        if (getInserting()) {
            CompositionImpl composition = getComposition();
            composition.getClass();
            RecomposeScopeImpl recomposeScopeImpl2 = new RecomposeScopeImpl(composition);
            Stack.m2383pushimpl(this.invalidateStack, recomposeScopeImpl2);
            updateValue(recomposeScopeImpl2);
            enterRecomposeScope(recomposeScopeImpl2);
            return;
        }
        Invalidation invalidationRemoveLocation = ComposerImplKt.removeLocation(this.invalidations, this.reader.getParent());
        Object next = this.reader.next();
        if (Intrinsics.areEqual(next, Composer.INSTANCE.getEmpty())) {
            CompositionImpl composition2 = getComposition();
            composition2.getClass();
            recomposeScopeImpl = new RecomposeScopeImpl(composition2);
            updateValue(recomposeScopeImpl);
        } else {
            next.getClass();
            recomposeScopeImpl = (RecomposeScopeImpl) next;
        }
        if (invalidationRemoveLocation != null) {
            z = true;
        } else {
            boolean forcedRecompose = recomposeScopeImpl.getForcedRecompose();
            if (forcedRecompose) {
                recomposeScopeImpl.setForcedRecompose(false);
            }
            if (forcedRecompose) {
                z = true;
            } else {
                z = false;
            }
        }
        recomposeScopeImpl.setRequiresRecompose(z);
        Stack.m2383pushimpl(this.invalidateStack, recomposeScopeImpl);
        enterRecomposeScope(recomposeScopeImpl);
        if (recomposeScopeImpl.getPaused()) {
            recomposeScopeImpl.setPaused(false);
            recomposeScopeImpl.setResuming(true);
            this.changeListWriter.startResumingScope(recomposeScopeImpl);
            if (this.reusing || !recomposeScopeImpl.getReusing()) {
                return;
            }
            this.reusing = true;
            recomposeScopeImpl.setResetReusing(true);
        }
    }

    public static ComposeStackTrace b(ComposerImpl composerImpl) {
        return composerImpl.currentStackTrace();
    }

    public static Unit c(MovableContent movableContent, Object obj, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1436390959, i, -1, "androidx.compose.runtime.ComposerImpl.invokeMovableContentLambda.<anonymous> (ComposerImpl.kt:2278)");
            }
            movableContent.getContent().invoke(obj, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    private final void cleanUpCompose() {
        this.pending = null;
        this.nodeIndex = 0;
        this.groupNodeCount = 0;
        this.compositeKeyHashCode = 0L;
        this.nodeExpected = false;
        this.changeListWriter.resetTransientState();
        Stack.m2371clearimpl(this.invalidateStack);
        clearUpdatedNodeCounts();
    }

    private final void clearUpdatedNodeCounts() {
        this.nodeCountOverrides = null;
        this.nodeCountVirtualOverrides = null;
    }

    private final long compositeKeyOf(int group, int recomposeGroup, long recomposeKey) {
        long jRotateLeft;
        int i = 3;
        long jRotateLeft2 = 0;
        int i2 = 0;
        while (group >= 0) {
            if (group == recomposeGroup) {
                jRotateLeft = Long.rotateLeft(recomposeKey, i2);
            } else {
                int iGroupCompositeKeyPart = groupCompositeKeyPart(this.reader, group);
                if (iGroupCompositeKeyPart == 126665345) {
                    jRotateLeft = Long.rotateLeft(iGroupCompositeKeyPart, i2);
                } else {
                    jRotateLeft2 = (jRotateLeft2 ^ Long.rotateLeft(iGroupCompositeKeyPart, i)) ^ Long.rotateLeft(this.reader.hasObjectKey(group) ? 0 : rGroupIndexOf(group), i2);
                    i = (i + 6) % 64;
                    i2 = (i2 + 6) % 64;
                    group = this.reader.parent(group);
                }
            }
            return jRotateLeft ^ jRotateLeft2;
        }
        return jRotateLeft2;
    }

    private final void createFreshInsertTable() {
        if (!this.writer.getClosed()) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        forceFreshInsertTable();
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope(int group) {
        PersistentCompositionLocalMap persistentCompositionLocalMap;
        if (getInserting() && this.writerHasAProvider) {
            int parent = this.writer.getParent();
            while (parent > 0) {
                if (this.writer.groupKey(parent) == 202 && Intrinsics.areEqual(this.writer.groupObjectKey(parent), ComposerKt.getCompositionLocalMap())) {
                    Object objGroupAux = this.writer.groupAux(parent);
                    objGroupAux.getClass();
                    PersistentCompositionLocalMap persistentCompositionLocalMap2 = (PersistentCompositionLocalMap) objGroupAux;
                    this.providerCache = persistentCompositionLocalMap2;
                    return persistentCompositionLocalMap2;
                }
                parent = this.writer.parent(parent);
            }
        }
        if (this.reader.getGroupsSize() > 0) {
            while (group > 0) {
                if (this.reader.groupKey(group) == 202 && Intrinsics.areEqual(this.reader.groupObjectKey(group), ComposerKt.getCompositionLocalMap())) {
                    MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap = this.providerUpdates;
                    if (mutableIntObjectMap == null || (persistentCompositionLocalMap = (PersistentCompositionLocalMap) mutableIntObjectMap.get(group)) == null) {
                        Object objGroupAux2 = this.reader.groupAux(group);
                        objGroupAux2.getClass();
                        persistentCompositionLocalMap = (PersistentCompositionLocalMap) objGroupAux2;
                    }
                    this.providerCache = persistentCompositionLocalMap;
                    return persistentCompositionLocalMap;
                }
                group = this.reader.parent(group);
            }
        }
        PersistentCompositionLocalMap persistentCompositionLocalMap3 = this.rootProvider;
        this.providerCache = persistentCompositionLocalMap3;
        return persistentCompositionLocalMap3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ComposeStackTrace currentStackTrace() {
        if (!this.parentContext.getStackTraceEnabled$runtime()) {
            return null;
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.addAll(ComposeStackTraceBuilderKt.buildTrace$default(this.writer, null, 0, null, 7, null));
        listCreateListBuilder.addAll(ComposeStackTraceBuilderKt.buildTrace(this.reader));
        listCreateListBuilder.addAll(parentStackTrace());
        return new ComposeStackTrace(CollectionsKt.build(listCreateListBuilder));
    }

    /* JADX INFO: renamed from: doCompose-aFTiNEg, reason: not valid java name */
    private final void m2325doComposeaFTiNEg(MutableScatterMap<Object, Object> invalidationsRequested, Function2<? super Composer, ? super Integer, Unit> content) {
        if (this.isComposing) {
            ComposerKt.composeImmediateRuntimeError("Reentrant composition is not supported");
        }
        CompositionObserver compositionObserverCurrent = this.observerHolder.current();
        Trace trace = Trace.INSTANCE;
        Object objBeginSection = trace.beginSection("Compose:recompose");
        try {
            this.compositionToken = Long.hashCode(SnapshotKt.currentSnapshot().getSnapshotId());
            this.providerUpdates = null;
            m2329updateComposerInvalidationsRY85e9Y(invalidationsRequested);
            this.nodeIndex = 0;
            this.isComposing = true;
            if (compositionObserverCurrent != null) {
                compositionObserverCurrent.onBeginComposition(getComposition());
            }
            try {
                startRoot();
                Object objNextSlot = nextSlot();
                if (objNextSlot != content && content != null) {
                    updateValue(content);
                }
                ComposerImpl$derivedStateObserver$1 composerImpl$derivedStateObserver$1 = this.derivedStateObserver;
                MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
                try {
                    mutableVectorDerivedStateObservers.add(composerImpl$derivedStateObserver$1);
                    if (content != null) {
                        startGroup(ComposerKt.invocationKey, ComposerKt.getInvocation());
                        Expect_jvmKt.invokeComposable(this, content);
                        endGroup();
                    } else if ((!this.forciblyRecompose && !this.providersInvalid) || objNextSlot == null || Intrinsics.areEqual(objNextSlot, Composer.INSTANCE.getEmpty())) {
                        skipCurrentGroup();
                    } else {
                        startGroup(ComposerKt.invocationKey, ComposerKt.getInvocation());
                        Expect_jvmKt.invokeComposable(this, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(objNextSlot, 2));
                        endGroup();
                    }
                    mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                    endRoot();
                    if (compositionObserverCurrent != null) {
                        compositionObserverCurrent.onEndComposition(getComposition());
                    }
                    this.isComposing = false;
                    this.invalidations.clear();
                    createFreshInsertTable();
                    Unit unit = Unit.INSTANCE;
                    trace.endSection(objBeginSection);
                } catch (Throwable th) {
                    mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    throw ComposeStackTraceKt.attachComposeStackTrace(th2, new Function0() { // from class: dn2
                        public final Object invoke() {
                            return this.b.currentStackTrace();
                        }
                    });
                } catch (Throwable th3) {
                    if (compositionObserverCurrent != null) {
                        compositionObserverCurrent.onEndComposition(getComposition());
                    }
                    this.isComposing = false;
                    this.invalidations.clear();
                    abortRoot();
                    createFreshInsertTable();
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            Trace.INSTANCE.endSection(objBeginSection);
            throw th4;
        }
    }

    private final void doRecordDownsFor(int group, int nearestCommonRoot) {
        if (group <= 0 || group == nearestCommonRoot) {
            return;
        }
        doRecordDownsFor(this.reader.parent(group), nearestCommonRoot);
        if (this.reader.isNode(group)) {
            this.changeListWriter.moveDown(nodeAt(this.reader, group));
        }
    }

    public static boolean e(Object obj, Object obj2) {
        if (obj2 == obj) {
            return true;
        }
        RememberObserverHolder rememberObserverHolder = obj2 instanceof RememberObserverHolder ? (RememberObserverHolder) obj2 : null;
        return (rememberObserverHolder != null ? rememberObserverHolder.getWrapped() : null) == obj;
    }

    private final void end(boolean isNode) {
        long jRotateRight;
        long j;
        int remainingSlots;
        List<KeyInfo> list;
        long jRotateRight2;
        long j2;
        int iPeek2 = this.parentStateStack.peek2() - 1;
        if (getInserting()) {
            int parent = this.writer.getParent();
            int iGroupKey = this.writer.groupKey(parent);
            Object objGroupObjectKey = this.writer.groupObjectKey(parent);
            Object objGroupAux = this.writer.groupAux(parent);
            if (objGroupObjectKey != null) {
                int iOrdinal = objGroupObjectKey instanceof Enum ? ((Enum) objGroupObjectKey).ordinal() : objGroupObjectKey.hashCode();
                jRotateRight2 = Long.rotateRight(getCompositeKeyHashCode(), 3);
                j2 = iOrdinal;
            } else if (objGroupAux == null || iGroupKey != 207 || Intrinsics.areEqual(objGroupAux, Composer.INSTANCE.getEmpty())) {
                jRotateRight2 = Long.rotateRight(getCompositeKeyHashCode() ^ ((long) iPeek2), 3);
                j2 = iGroupKey;
            } else {
                this.compositeKeyHashCode = Long.rotateRight(((long) objGroupAux.hashCode()) ^ Long.rotateRight(getCompositeKeyHashCode() ^ ((long) iPeek2), 3), 3);
            }
            this.compositeKeyHashCode = Long.rotateRight(jRotateRight2 ^ j2, 3);
        } else {
            int parent2 = this.reader.getParent();
            int iGroupKey2 = this.reader.groupKey(parent2);
            Object objGroupObjectKey2 = this.reader.groupObjectKey(parent2);
            Object objGroupAux2 = this.reader.groupAux(parent2);
            if (objGroupObjectKey2 != null) {
                int iOrdinal2 = objGroupObjectKey2 instanceof Enum ? ((Enum) objGroupObjectKey2).ordinal() : objGroupObjectKey2.hashCode();
                jRotateRight = Long.rotateRight(getCompositeKeyHashCode(), 3);
                j = iOrdinal2;
            } else if (objGroupAux2 == null || iGroupKey2 != 207 || Intrinsics.areEqual(objGroupAux2, Composer.INSTANCE.getEmpty())) {
                jRotateRight = Long.rotateRight(getCompositeKeyHashCode() ^ ((long) iPeek2), 3);
                j = iGroupKey2;
            } else {
                this.compositeKeyHashCode = Long.rotateRight(((long) objGroupAux2.hashCode()) ^ Long.rotateRight(getCompositeKeyHashCode() ^ ((long) iPeek2), 3), 3);
            }
            this.compositeKeyHashCode = Long.rotateRight(jRotateRight ^ j, 3);
        }
        int i = this.groupNodeCount;
        Pending pending = this.pending;
        if (pending != null && pending.getKeyInfos().size() > 0) {
            List<KeyInfo> keyInfos = pending.getKeyInfos();
            List<KeyInfo> used = pending.getUsed();
            Set setFastToSet = ListUtilsKt.fastToSet(used);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int size = used.size();
            int size2 = keyInfos.size();
            int i2 = 0;
            int i3 = 0;
            int iUpdatedNodeCountOf = 0;
            while (i2 < size2) {
                KeyInfo keyInfo = keyInfos.get(i2);
                if (setFastToSet.contains(keyInfo)) {
                    list = keyInfos;
                    if (!linkedHashSet.contains(keyInfo)) {
                        if (i3 < size) {
                            KeyInfo keyInfo2 = used.get(i3);
                            if (keyInfo2 != keyInfo) {
                                int iNodePositionOf = pending.nodePositionOf(keyInfo2);
                                linkedHashSet.add(keyInfo2);
                                if (iNodePositionOf != iUpdatedNodeCountOf) {
                                    int iUpdatedNodeCountOf2 = pending.updatedNodeCountOf(keyInfo2);
                                    this.changeListWriter.moveNode(pending.getStartIndex() + iNodePositionOf, iUpdatedNodeCountOf + pending.getStartIndex(), iUpdatedNodeCountOf2);
                                    pending.registerMoveNode(iNodePositionOf, iUpdatedNodeCountOf, iUpdatedNodeCountOf2);
                                }
                            } else {
                                i2++;
                            }
                            i3++;
                            iUpdatedNodeCountOf += pending.updatedNodeCountOf(keyInfo2);
                            keyInfos = list;
                            used = used;
                        }
                    }
                    keyInfos = list;
                } else {
                    this.changeListWriter.removeNode(pending.nodePositionOf(keyInfo) + pending.getStartIndex(), keyInfo.getNodes());
                    pending.updateNodeCount(keyInfo.getLocation(), 0);
                    this.changeListWriter.moveReaderRelativeTo(keyInfo.getLocation());
                    this.reader.reposition(keyInfo.getLocation());
                    recordDelete();
                    this.reader.skipGroup();
                    list = keyInfos;
                    ComposerImplKt.removeRange(this.invalidations, keyInfo.getLocation(), keyInfo.getLocation() + this.reader.groupSize(keyInfo.getLocation()));
                }
                i2++;
                keyInfos = list;
            }
            this.changeListWriter.endNodeMovement();
            if (keyInfos.size() > 0) {
                this.changeListWriter.moveReaderRelativeTo(this.reader.getGroupEnd());
                this.reader.skipToGroupEnd();
            }
        }
        boolean inserting = getInserting();
        if (!inserting && (remainingSlots = this.reader.getRemainingSlots()) > 0) {
            this.changeListWriter.trimValues(remainingSlots);
        }
        int i4 = this.nodeIndex;
        while (!this.reader.isGroupEnd()) {
            int currentGroup = this.reader.getCurrentGroup();
            recordDelete();
            this.changeListWriter.removeNode(i4, this.reader.skipGroup());
            ComposerImplKt.removeRange(this.invalidations, currentGroup, this.reader.getCurrentGroup());
        }
        if (inserting) {
            if (isNode) {
                this.insertFixups.endNodeInsert();
                i = 1;
            }
            this.reader.endEmpty();
            int parent3 = this.writer.getParent();
            this.writer.endGroup();
            if (!this.reader.getInEmpty()) {
                int iInsertedGroupVirtualIndex = insertedGroupVirtualIndex(parent3);
                this.writer.endInsert();
                this.writer.close(true);
                recordInsert(this.insertAnchor);
                this.inserting = false;
                if (!this.slotTable.isEmpty()) {
                    updateNodeCount(iInsertedGroupVirtualIndex, 0);
                    updateNodeCountOverrides(iInsertedGroupVirtualIndex, i);
                }
            }
        } else {
            if (isNode) {
                this.changeListWriter.moveUp();
            }
            this.changeListWriter.endCurrentGroup();
            int parent4 = this.reader.getParent();
            if (i != updatedNodeCount(parent4)) {
                updateNodeCountOverrides(parent4, i);
            }
            if (isNode) {
                i = 1;
            }
            this.reader.endGroup();
            this.changeListWriter.endNodeMovement();
        }
        exitGroup(i, inserting);
    }

    private final void endGroup() {
        end(false);
    }

    private final void endRoot() {
        endGroup();
        this.parentContext.doneComposing$runtime();
        endGroup();
        this.changeListWriter.endRoot();
        finalizeCompose();
        this.reader.close();
        this.forciblyRecompose = false;
        this.providersInvalid = ComposerImplKt.asBool(this.providersInvalidStack.pop());
    }

    private final void ensureWriter() {
        if (this.writer.getClosed()) {
            SlotWriter slotWriterOpenWriter = this.insertTable.openWriter();
            this.writer = slotWriterOpenWriter;
            slotWriterOpenWriter.skipToGroupEnd();
            this.writerHasAProvider = false;
            this.providerCache = null;
        }
    }

    private final void enterGroup(boolean isNode, Pending newPending) {
        Stack.m2383pushimpl(this.pendingStack, this.pending);
        this.pending = newPending;
        this.parentStateStack.push(this.groupNodeCount);
        this.parentStateStack.push(this.rGroupIndex);
        this.parentStateStack.push(this.nodeIndex);
        if (isNode) {
            this.nodeIndex = 0;
        }
        this.groupNodeCount = 0;
        this.rGroupIndex = 0;
    }

    private final void enterRecomposeScope(RecomposeScopeImpl scope) {
        scope.start(this.compositionToken);
        CompositionObserver compositionObserverCurrent = this.observerHolder.current();
        if (compositionObserverCurrent != null) {
            compositionObserverCurrent.onScopeEnter(scope);
        }
    }

    private final void exitGroup(int expectedNodeCount, boolean inserting) {
        Pending pending = (Pending) Stack.m2382popimpl(this.pendingStack);
        if (pending != null && !inserting) {
            pending.setGroupIndex(pending.getGroupIndex() + 1);
        }
        this.pending = pending;
        this.nodeIndex = this.parentStateStack.pop() + expectedNodeCount;
        this.rGroupIndex = this.parentStateStack.pop();
        this.groupNodeCount = this.parentStateStack.pop() + expectedNodeCount;
    }

    private final Function1<Composition, Unit> exitRecomposeScope(RecomposeScopeImpl scope) {
        CompositionObserver compositionObserverCurrent = this.observerHolder.current();
        if (compositionObserverCurrent != null) {
            compositionObserverCurrent.onScopeExit(scope);
        }
        return scope.end(this.compositionToken);
    }

    private final void finalizeCompose() {
        this.changeListWriter.finalizeComposition();
        if (!Stack.m2378isEmptyimpl(this.pendingStack)) {
            ComposerKt.composeImmediateRuntimeError("Start/end imbalance");
        }
        cleanUpCompose();
    }

    private final void forceFreshInsertTable() {
        SlotTable slotTable = new SlotTable();
        if (this.sourceMarkersEnabled) {
            slotTable.collectSourceInformation();
        }
        if (this.parentContext.getCollectingCallByInformation$runtime()) {
            slotTable.collectCalledByInformation();
        }
        this.insertTable = slotTable;
        SlotWriter slotWriterOpenWriter = slotTable.openWriter();
        slotWriterOpenWriter.close(true);
        this.writer = slotWriterOpenWriter;
    }

    public static /* synthetic */ void getCompositeKeyHashCode$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getDefaultsInvalid$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getInserting$annotations() {
    }

    private final Object getNode(SlotReader slotReader) {
        return slotReader.node(slotReader.getParent());
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getSkipping$annotations() {
    }

    private final int groupCompositeKeyPart(SlotReader slotReader, int i) {
        Object objGroupAux;
        if (!slotReader.hasObjectKey(i)) {
            int iGroupKey = slotReader.groupKey(i);
            return (iGroupKey != 207 || (objGroupAux = slotReader.groupAux(i)) == null || Intrinsics.areEqual(objGroupAux, Composer.INSTANCE.getEmpty())) ? iGroupKey : objGroupAux.hashCode();
        }
        Object objGroupObjectKey = slotReader.groupObjectKey(i);
        if (objGroupObjectKey == null) {
            return 0;
        }
        if (objGroupObjectKey instanceof Enum) {
            return ((Enum) objGroupObjectKey).ordinal();
        }
        return objGroupObjectKey instanceof MovableContent ? MovableContentKt.movableContentKey : objGroupObjectKey.hashCode();
    }

    private final void insertMovableContentGuarded(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) throws Throwable {
        ComposerChangeListWriter composerChangeListWriter;
        ChangeList changeList;
        SlotTable slotTable;
        Anchor anchor;
        SlotReader slotReader;
        ComposerChangeListWriter composerChangeListWriter2;
        ChangeList changeList2;
        SlotTable slotTable$runtime;
        List<Pair<MovableContentStateReference, MovableContentStateReference>> list = references;
        ComposerChangeListWriter composerChangeListWriter3 = this.changeListWriter;
        ChangeList changeList3 = this.lateChanges;
        ChangeList changeList4 = composerChangeListWriter3.getChangeList();
        try {
            composerChangeListWriter3.setChangeList(changeList3);
            this.changeListWriter.resetSlots();
            int size = list.size();
            int i = 0;
            int i2 = 0;
            while (i2 < size) {
                try {
                    Pair<MovableContentStateReference, MovableContentStateReference> pair = list.get(i2);
                    final MovableContentStateReference movableContentStateReference = (MovableContentStateReference) pair.component1();
                    MovableContentStateReference movableContentStateReference2 = (MovableContentStateReference) pair.component2();
                    Anchor anchor2 = movableContentStateReference.getAnchor();
                    int iAnchorIndex = movableContentStateReference.getSlotTable().anchorIndex(anchor2);
                    IntRef intRef = new IntRef(i, 1, null);
                    this.changeListWriter.determineMovableContentNodeIndex(intRef, anchor2);
                    if (movableContentStateReference2 == null) {
                        if (Intrinsics.areEqual(movableContentStateReference.getSlotTable(), this.insertTable)) {
                            createFreshInsertTable();
                        }
                        final SlotReader slotReaderOpenReader = movableContentStateReference.getSlotTable().openReader();
                        try {
                            slotReaderOpenReader.reposition(iAnchorIndex);
                            this.changeListWriter.moveReaderToAbsolute(iAnchorIndex);
                            final ChangeList changeList5 = new ChangeList();
                            recomposeMovableContent$default(this, null, null, null, null, new Function0() { // from class: an2
                                public final Object invoke() {
                                    return ComposerImpl.insertMovableContentGuarded$lambda$0$0$0$0(this.b, changeList5, slotReaderOpenReader, movableContentStateReference);
                                }
                            }, 15, null);
                            this.changeListWriter.includeOperationsIn(changeList5, intRef);
                            Unit unit = Unit.INSTANCE;
                            slotReaderOpenReader.close();
                        } catch (Throwable th) {
                            slotReaderOpenReader.close();
                            throw th;
                        }
                    } else {
                        MovableContentState movableContentStateMovableContentStateResolve$runtime = this.parentContext.movableContentStateResolve$runtime(movableContentStateReference2);
                        if (movableContentStateMovableContentStateResolve$runtime == null || (slotTable = movableContentStateMovableContentStateResolve$runtime.getSlotTable()) == null) {
                            slotTable = movableContentStateReference2.getSlotTable();
                        }
                        if (movableContentStateMovableContentStateResolve$runtime == null || (slotTable$runtime = movableContentStateMovableContentStateResolve$runtime.getSlotTable()) == null || (anchor = slotTable$runtime.anchor(0)) == null) {
                            anchor = movableContentStateReference2.getAnchor();
                        }
                        List<? extends Object> listCollectNodesFrom = ComposerImplKt.collectNodesFrom(slotTable, anchor);
                        if (!listCollectNodesFrom.isEmpty()) {
                            this.changeListWriter.copyNodesToNewAnchorLocation(listCollectNodesFrom, intRef);
                            if (Intrinsics.areEqual(movableContentStateReference.getSlotTable(), this.slotTable)) {
                                int iAnchorIndex2 = this.slotTable.anchorIndex(anchor2);
                                updateNodeCount(iAnchorIndex2, updatedNodeCount(iAnchorIndex2) + listCollectNodesFrom.size());
                            }
                        }
                        this.changeListWriter.copySlotTableToAnchorLocation(movableContentStateMovableContentStateResolve$runtime, this.parentContext, movableContentStateReference2, movableContentStateReference);
                        SlotReader slotReaderOpenReader2 = slotTable.openReader();
                        try {
                            SlotReader slotReader2 = this.reader;
                            int[] iArr = this.nodeCountOverrides;
                            MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap = this.providerUpdates;
                            this.nodeCountOverrides = null;
                            this.providerUpdates = null;
                            try {
                                this.reader = slotReaderOpenReader2;
                                int iAnchorIndex3 = slotTable.anchorIndex(anchor);
                                slotReaderOpenReader2.reposition(iAnchorIndex3);
                                this.changeListWriter.moveReaderToAbsolute(iAnchorIndex3);
                                ChangeList changeList6 = new ChangeList();
                                ComposerChangeListWriter composerChangeListWriter4 = this.changeListWriter;
                                ChangeList changeList7 = composerChangeListWriter4.getChangeList();
                                try {
                                    composerChangeListWriter4.setChangeList(changeList6);
                                    slotReader = slotReaderOpenReader2;
                                    try {
                                        ComposerChangeListWriter composerChangeListWriter5 = this.changeListWriter;
                                        boolean implicitRootStart = composerChangeListWriter5.getImplicitRootStart();
                                        try {
                                            composerChangeListWriter5.setImplicitRootStart(false);
                                            try {
                                                mutableIntObjectMap = mutableIntObjectMap;
                                                composerChangeListWriter2 = composerChangeListWriter4;
                                                changeList2 = changeList7;
                                                try {
                                                    recomposeMovableContent(movableContentStateReference2.getComposition(), movableContentStateReference.getComposition(), Integer.valueOf(slotReader.getCurrentGroup()), movableContentStateReference2.getInvalidations$runtime(), new Function0() { // from class: bn2
                                                        public final Object invoke() {
                                                            return ComposerImpl.insertMovableContentGuarded$lambda$0$0$1$0$0$0$0(this.b, movableContentStateReference);
                                                        }
                                                    });
                                                    try {
                                                        composerChangeListWriter5.setImplicitRootStart(implicitRootStart);
                                                        try {
                                                            composerChangeListWriter2.setChangeList(changeList2);
                                                            this.changeListWriter.includeOperationsIn(changeList6, intRef);
                                                            Unit unit2 = Unit.INSTANCE;
                                                            try {
                                                                this.reader = slotReader2;
                                                                this.nodeCountOverrides = iArr;
                                                                this.providerUpdates = mutableIntObjectMap;
                                                                slotReader.close();
                                                            } catch (Throwable th2) {
                                                                th = th2;
                                                                slotReader.close();
                                                                throw th;
                                                            }
                                                        } catch (Throwable th3) {
                                                            th = th3;
                                                            this.reader = slotReader2;
                                                            this.nodeCountOverrides = iArr;
                                                            this.providerUpdates = mutableIntObjectMap;
                                                            throw th;
                                                        }
                                                    } catch (Throwable th4) {
                                                        th = th4;
                                                        composerChangeListWriter2.setChangeList(changeList2);
                                                        throw th;
                                                    }
                                                } catch (Throwable th5) {
                                                    th = th5;
                                                    composerChangeListWriter5.setImplicitRootStart(implicitRootStart);
                                                    throw th;
                                                }
                                            } catch (Throwable th6) {
                                                th = th6;
                                                mutableIntObjectMap = mutableIntObjectMap;
                                                changeList2 = changeList7;
                                                composerChangeListWriter2 = composerChangeListWriter4;
                                            }
                                        } catch (Throwable th7) {
                                            th = th7;
                                            mutableIntObjectMap = mutableIntObjectMap;
                                            composerChangeListWriter2 = composerChangeListWriter4;
                                            changeList2 = changeList7;
                                        }
                                    } catch (Throwable th8) {
                                        th = th8;
                                        composerChangeListWriter2 = composerChangeListWriter4;
                                        changeList2 = changeList7;
                                        composerChangeListWriter2.setChangeList(changeList2);
                                        throw th;
                                    }
                                } catch (Throwable th9) {
                                    th = th9;
                                    slotReader = slotReaderOpenReader2;
                                }
                            } catch (Throwable th10) {
                                th = th10;
                                mutableIntObjectMap = mutableIntObjectMap;
                                slotReader = slotReaderOpenReader2;
                            }
                        } catch (Throwable th11) {
                            th = th11;
                            slotReader = slotReaderOpenReader2;
                        }
                    }
                    try {
                        this.changeListWriter.skipToEndOfCurrentGroup();
                        i2++;
                        list = references;
                        size = size;
                        composerChangeListWriter3 = composerChangeListWriter3;
                        changeList4 = changeList4;
                        i = 0;
                    } catch (Throwable th12) {
                        th = th12;
                        composerChangeListWriter = composerChangeListWriter3;
                        changeList = changeList4;
                        composerChangeListWriter.setChangeList(changeList);
                        throw th;
                    }
                } catch (Throwable th13) {
                    th = th13;
                    composerChangeListWriter3 = composerChangeListWriter3;
                    changeList4 = changeList4;
                }
            }
            ComposerChangeListWriter composerChangeListWriter6 = composerChangeListWriter3;
            ChangeList changeList8 = changeList4;
            this.changeListWriter.endMovableContentPlacement();
            this.changeListWriter.moveReaderToAbsolute(0);
            composerChangeListWriter6.setChangeList(changeList8);
        } catch (Throwable th14) {
            th = th14;
            composerChangeListWriter = composerChangeListWriter3;
            changeList = changeList4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit insertMovableContentGuarded$lambda$0$0$0$0(ComposerImpl composerImpl, ChangeList changeList, SlotReader slotReader, MovableContentStateReference movableContentStateReference) {
        ComposerChangeListWriter composerChangeListWriter = composerImpl.changeListWriter;
        ChangeList changeList2 = composerChangeListWriter.getChangeList();
        try {
            composerChangeListWriter.setChangeList(changeList);
            SlotReader slotReader2 = composerImpl.reader;
            int[] iArr = composerImpl.nodeCountOverrides;
            MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap = composerImpl.providerUpdates;
            composerImpl.nodeCountOverrides = null;
            composerImpl.providerUpdates = null;
            try {
                composerImpl.reader = slotReader;
                ComposerChangeListWriter composerChangeListWriter2 = composerImpl.changeListWriter;
                boolean implicitRootStart = composerChangeListWriter2.getImplicitRootStart();
                try {
                    composerChangeListWriter2.setImplicitRootStart(false);
                    composerImpl.invokeMovableContentLambda(movableContentStateReference.getContent$runtime(), movableContentStateReference.getLocals(), movableContentStateReference.getParameter(), true);
                    composerChangeListWriter2.setImplicitRootStart(implicitRootStart);
                    Unit unit = Unit.INSTANCE;
                    composerImpl.reader = slotReader2;
                    composerImpl.nodeCountOverrides = iArr;
                    composerImpl.providerUpdates = mutableIntObjectMap;
                    composerChangeListWriter.setChangeList(changeList2);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    composerChangeListWriter2.setImplicitRootStart(implicitRootStart);
                    throw th;
                }
            } catch (Throwable th2) {
                composerImpl.reader = slotReader2;
                composerImpl.nodeCountOverrides = iArr;
                composerImpl.providerUpdates = mutableIntObjectMap;
                throw th2;
            }
        } catch (Throwable th3) {
            composerChangeListWriter.setChangeList(changeList2);
            throw th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit insertMovableContentGuarded$lambda$0$0$1$0$0$0$0(ComposerImpl composerImpl, MovableContentStateReference movableContentStateReference) {
        composerImpl.invokeMovableContentLambda(movableContentStateReference.getContent$runtime(), movableContentStateReference.getLocals(), movableContentStateReference.getParameter(), true);
        return Unit.INSTANCE;
    }

    private final int insertedGroupVirtualIndex(int index) {
        return (-2) - index;
    }

    private final void invokeMovableContentLambda(final MovableContent<Object> content, PersistentCompositionLocalMap locals, final Object parameter, boolean force) {
        startMovableGroup(MovableContentKt.movableContentKey, content);
        updateSlot(parameter);
        long compositeKeyHashCode = getCompositeKeyHashCode();
        try {
            this.compositeKeyHashCode = 126665345L;
            boolean z = false;
            if (getInserting()) {
                SlotWriter.markGroup$default(this.writer, 0, 1, null);
            }
            if (!getInserting() && !Intrinsics.areEqual(this.reader.getGroupAux(), locals)) {
                z = true;
            }
            if (z) {
                recordProviderUpdate(locals);
            }
            m2326startBaiHCIY(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m2342getGroupULZAiWs(), locals);
            this.providerCache = null;
            if (!getInserting() || force || (ComposeRuntimeFlags.isMovableContentUsageTrackingEnabled && !content.getUsed())) {
                boolean z2 = this.providersInvalid;
                this.providersInvalid = z;
                content.setUsed$runtime(true);
                Expect_jvmKt.invokeComposable(this, ComposableLambdaKt.composableLambdaInstance(1436390959, true, new Function2() { // from class: ym2
                    public final Object invoke(Object obj, Object obj2) {
                        return ComposerImpl.c(content, parameter, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }));
                this.providersInvalid = z2;
            } else {
                this.writerHasAProvider = true;
                SlotWriter slotWriter = this.writer;
                this.parentContext.insertMovableContent$runtime(new MovableContentStateReference(content, parameter, getComposition(), this.insertTable, slotWriter.anchor(slotWriter.parent(slotWriter.getParent())), CollectionsKt.emptyList(), currentCompositionLocalScope(), null));
            }
            endGroup();
            this.providerCache = null;
            this.compositeKeyHashCode = compositeKeyHashCode;
            endMovableGroup();
        } catch (Throwable th) {
            try {
                throw ComposeStackTraceKt.attachComposeStackTrace(th, new Function0() { // from class: zm2
                    public final Object invoke() {
                        return ComposerImpl.b(this.b);
                    }
                });
            } catch (Throwable th2) {
                endGroup();
                this.providerCache = null;
                this.compositeKeyHashCode = compositeKeyHashCode;
                endMovableGroup();
                throw th2;
            }
        }
    }

    private final Object nodeAt(SlotReader slotReader, int i) {
        return slotReader.node(i);
    }

    private final int nodeIndexOf(int groupLocation, int group, int recomposeGroup, int recomposeIndex) {
        int iParent = this.reader.parent(group);
        while (iParent != recomposeGroup && !this.reader.isNode(iParent)) {
            iParent = this.reader.parent(iParent);
        }
        if (this.reader.isNode(iParent)) {
            recomposeIndex = 0;
        }
        if (iParent == group) {
            return recomposeIndex;
        }
        int iUpdatedNodeCount = (updatedNodeCount(iParent) - this.reader.nodeCount(group)) + recomposeIndex;
        loop1: while (recomposeIndex < iUpdatedNodeCount && iParent != groupLocation) {
            iParent++;
            while (iParent < groupLocation) {
                int iGroupSize = this.reader.groupSize(iParent) + iParent;
                if (groupLocation >= iGroupSize) {
                    recomposeIndex += this.reader.isNode(iParent) ? 1 : updatedNodeCount(iParent);
                    iParent = iGroupSize;
                }
            }
            break loop1;
        }
        return recomposeIndex;
    }

    private final int rGroupIndexOf(int group) {
        int iParent = this.reader.parent(group) + 1;
        int i = 0;
        while (iParent < group) {
            if (!this.reader.hasObjectKey(iParent)) {
                i++;
            }
            iParent += this.reader.groupSize(iParent);
        }
        return i;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0042 A[Catch: all -> 0x0029, TRY_LEAVE, TryCatch #0 {all -> 0x0029, blocks: (B:3:0x0005, B:5:0x0013, B:7:0x0025, B:11:0x002f, B:10:0x002b, B:14:0x0036, B:16:0x003c, B:18:0x0042), top: B:23:0x0005 }] */
    private final <R> R recomposeMovableContent(ControlledComposition from, ControlledComposition to, Integer index, List<? extends Pair<RecomposeScopeImpl, ? extends Object>> invalidations, Function0<? extends R> block) {
        R r;
        boolean z = this.isComposing;
        int i = this.nodeIndex;
        try {
            this.isComposing = true;
            this.nodeIndex = 0;
            int size = invalidations.size();
            for (int i2 = 0; i2 < size; i2++) {
                Pair<RecomposeScopeImpl, ? extends Object> pair = invalidations.get(i2);
                RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) pair.component1();
                Object objComponent2 = pair.component2();
                if (objComponent2 != null) {
                    tryImminentInvalidation$runtime(recomposeScopeImpl, objComponent2);
                } else {
                    tryImminentInvalidation$runtime(recomposeScopeImpl, null);
                }
            }
            if (from == null) {
                r = (R) block.invoke();
            } else {
                r = (R) from.delegateInvalidations(to, index != null ? index.intValue() : -1, block);
                if (r == null) {
                    r = (R) block.invoke();
                }
            }
            return r;
        } finally {
            this.isComposing = z;
            this.nodeIndex = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object recomposeMovableContent$default(ComposerImpl composerImpl, ControlledComposition controlledComposition, ControlledComposition controlledComposition2, Integer num, List list, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            controlledComposition = null;
        }
        if ((i & 2) != 0) {
            controlledComposition2 = null;
        }
        if ((i & 4) != 0) {
            num = null;
        }
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        return composerImpl.recomposeMovableContent(controlledComposition, controlledComposition2, num, list, function0);
    }

    private final void recomposeToGroupEnd() {
        boolean z = this.isComposing;
        this.isComposing = true;
        int parent = this.reader.getParent();
        int iGroupSize = this.reader.groupSize(parent) + parent;
        int i = this.nodeIndex;
        long compositeKeyHashCode = getCompositeKeyHashCode();
        int i2 = this.groupNodeCount;
        int i3 = this.rGroupIndex;
        Invalidation invalidationFirstInRange = ComposerImplKt.firstInRange(this.invalidations, this.reader.getCurrentGroup(), iGroupSize);
        boolean z2 = false;
        int i4 = parent;
        while (invalidationFirstInRange != null) {
            int location = invalidationFirstInRange.getLocation();
            RecomposeScopeImpl scope = invalidationFirstInRange.getScope();
            ComposerImplKt.removeLocation(this.invalidations, location);
            if (invalidationFirstInRange.isInvalid()) {
                this.reader.reposition(location);
                int currentGroup = this.reader.getCurrentGroup();
                recordUpsAndDowns(i4, currentGroup, parent);
                this.nodeIndex = nodeIndexOf(location, currentGroup, parent, i);
                this.rGroupIndex = rGroupIndexOf(currentGroup);
                this.compositeKeyHashCode = compositeKeyOf(this.reader.parent(currentGroup), parent, compositeKeyHashCode);
                this.providerCache = null;
                scope.compose(this);
                this.providerCache = null;
                this.reader.restoreParent(parent);
                z2 = true;
                i4 = currentGroup;
            } else {
                Stack.m2383pushimpl(this.invalidateStack, scope);
                CompositionObserver compositionObserverCurrent = this.observerHolder.current();
                if (compositionObserverCurrent != null) {
                    try {
                        compositionObserverCurrent.onScopeEnter(scope);
                        scope.rereadTrackedInstances();
                        compositionObserverCurrent.onScopeExit(scope);
                    } catch (Throwable th) {
                        compositionObserverCurrent.onScopeExit(scope);
                        throw th;
                    }
                } else {
                    scope.rereadTrackedInstances();
                }
                Stack.m2382popimpl(this.invalidateStack);
            }
            invalidationFirstInRange = ComposerImplKt.firstInRange(this.invalidations, this.reader.getCurrentGroup(), iGroupSize);
        }
        if (z2) {
            recordUpsAndDowns(i4, parent, parent);
            this.reader.skipToGroupEnd();
            int iUpdatedNodeCount = updatedNodeCount(parent);
            this.nodeIndex = i + iUpdatedNodeCount;
            this.groupNodeCount = i2 + iUpdatedNodeCount;
            this.rGroupIndex = i3;
        } else {
            skipReaderToGroupEnd();
        }
        this.compositeKeyHashCode = compositeKeyHashCode;
        this.isComposing = z;
    }

    private final void recordDelete() {
        reportFreeMovableContent(this.reader.getCurrentGroup());
        this.changeListWriter.removeCurrentGroup();
    }

    private final void recordInsert(Anchor anchor) {
        boolean zIsEmpty = this.insertFixups.isEmpty();
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        if (zIsEmpty) {
            composerChangeListWriter.insertSlots(anchor, this.insertTable);
        } else {
            composerChangeListWriter.insertSlots(anchor, this.insertTable, this.insertFixups);
            this.insertFixups = new FixupList();
        }
    }

    private final void recordProviderUpdate(PersistentCompositionLocalMap providers) {
        MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap = this.providerUpdates;
        if (mutableIntObjectMap == null) {
            mutableIntObjectMap = new MutableIntObjectMap<>(0, 1, (DefaultConstructorMarker) null);
            this.providerUpdates = mutableIntObjectMap;
        }
        mutableIntObjectMap.set(this.reader.getCurrentGroup(), providers);
    }

    private final void recordUpsAndDowns(int oldGroup, int newGroup, int commonRoot) {
        SlotReader slotReader = this.reader;
        int iNearestCommonRootOf = ComposerImplKt.nearestCommonRootOf(slotReader, oldGroup, newGroup, commonRoot);
        while (oldGroup > 0 && oldGroup != iNearestCommonRootOf) {
            if (slotReader.isNode(oldGroup)) {
                this.changeListWriter.moveUp();
            }
            oldGroup = slotReader.parent(oldGroup);
        }
        doRecordDownsFor(newGroup, iNearestCommonRootOf);
    }

    private final int rememberObserverGroupIndex() {
        return this.rGroupIndex - 1;
    }

    private final void reportAllMovableContent() {
        if (this.slotTable.containsMark()) {
            getComposition().updateMovingInvalidations$runtime();
            ChangeList changeList = new ChangeList();
            this.deferredChanges = changeList;
            SlotReader slotReaderOpenReader = this.slotTable.openReader();
            try {
                this.reader = slotReaderOpenReader;
                ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
                ChangeList changeList2 = composerChangeListWriter.getChangeList();
                try {
                    composerChangeListWriter.setChangeList(changeList);
                    reportFreeMovableContent(0);
                    this.changeListWriter.releaseMovableContent();
                    composerChangeListWriter.setChangeList(changeList2);
                    Unit unit = Unit.INSTANCE;
                    slotReaderOpenReader.close();
                } catch (Throwable th) {
                    composerChangeListWriter.setChangeList(changeList2);
                    throw th;
                }
            } catch (Throwable th2) {
                slotReaderOpenReader.close();
                throw th2;
            }
        }
    }

    private final void reportFreeMovableContent(int groupBeingRemoved) {
        boolean zIsNode = this.reader.isNode(groupBeingRemoved);
        if (zIsNode) {
            this.changeListWriter.endNodeMovement();
            this.changeListWriter.moveDown(this.reader.node(groupBeingRemoved));
        }
        reportFreeMovableContent$reportGroup(this, groupBeingRemoved, groupBeingRemoved, zIsNode, 0);
        this.changeListWriter.endNodeMovement();
        if (zIsNode) {
            this.changeListWriter.moveUp();
        }
    }

    private static final MovableContentStateReference reportFreeMovableContent$createMovableContentReferenceForGroup(ComposerImpl composerImpl, int i, List<MovableContentStateReference> list) {
        Object objGroupObjectKey = composerImpl.reader.groupObjectKey(i);
        objGroupObjectKey.getClass();
        MovableContent movableContent = (MovableContent) objGroupObjectKey;
        Object objGroupGet = composerImpl.reader.groupGet(i, 0);
        Anchor anchor = composerImpl.reader.anchor(i);
        int iGroupSize = composerImpl.reader.groupSize(i) + i;
        ArrayList arrayList = new ArrayList();
        List<Invalidation> list2 = composerImpl.invalidations;
        for (int iFindInsertLocation = ComposerImplKt.findInsertLocation(list2, i); iFindInsertLocation < list2.size(); iFindInsertLocation++) {
            Invalidation invalidation = list2.get(iFindInsertLocation);
            if (invalidation.getLocation() >= iGroupSize) {
                break;
            }
            arrayList.add(TuplesKt.to(invalidation.getScope(), invalidation.getInstances()));
        }
        return new MovableContentStateReference(movableContent, objGroupGet, composerImpl.getComposition(), composerImpl.slotTable, anchor, arrayList, composerImpl.currentCompositionLocalScope(i), list);
    }

    private static final MovableContentStateReference reportFreeMovableContent$movableContentReferenceFor(ComposerImpl composerImpl, int i) {
        int iGroupKey = composerImpl.reader.groupKey(i);
        Object objGroupObjectKey = composerImpl.reader.groupObjectKey(i);
        ArrayList arrayList = null;
        if (iGroupKey != 126665345 || !(objGroupObjectKey instanceof MovableContent)) {
            return null;
        }
        if (composerImpl.reader.containsMark(i)) {
            ArrayList arrayList2 = new ArrayList();
            reportFreeMovableContent$movableContentReferenceFor$traverseGroups(composerImpl, arrayList2, i);
            if (!arrayList2.isEmpty()) {
                arrayList = arrayList2;
            }
        }
        return reportFreeMovableContent$createMovableContentReferenceForGroup(composerImpl, i, arrayList);
    }

    private static final void reportFreeMovableContent$movableContentReferenceFor$traverseGroups(ComposerImpl composerImpl, List<MovableContentStateReference> list, int i) {
        int iGroupSize = composerImpl.reader.groupSize(i) + i;
        int iGroupSize2 = i + 1;
        while (iGroupSize2 < iGroupSize) {
            if (composerImpl.reader.hasMark(iGroupSize2)) {
                MovableContentStateReference movableContentStateReferenceReportFreeMovableContent$movableContentReferenceFor = reportFreeMovableContent$movableContentReferenceFor(composerImpl, iGroupSize2);
                if (movableContentStateReferenceReportFreeMovableContent$movableContentReferenceFor != null) {
                    list.add(movableContentStateReferenceReportFreeMovableContent$movableContentReferenceFor);
                }
            } else if (composerImpl.reader.containsMark(iGroupSize2)) {
                reportFreeMovableContent$movableContentReferenceFor$traverseGroups(composerImpl, list, iGroupSize2);
            }
            iGroupSize2 += composerImpl.reader.groupSize(iGroupSize2);
        }
    }

    private static final int reportFreeMovableContent$reportGroup(ComposerImpl composerImpl, int i, int i2, boolean z, int i3) {
        SlotReader slotReader = composerImpl.reader;
        if (!slotReader.hasMark(i2)) {
            if (!slotReader.containsMark(i2)) {
                if (slotReader.isNode(i2)) {
                    return 1;
                }
                return slotReader.nodeCount(i2);
            }
            int iGroupSize = slotReader.groupSize(i2) + i2;
            int iReportFreeMovableContent$reportGroup = 0;
            for (int iGroupSize2 = i2 + 1; iGroupSize2 < iGroupSize; iGroupSize2 += slotReader.groupSize(iGroupSize2)) {
                boolean zIsNode = slotReader.isNode(iGroupSize2);
                if (zIsNode) {
                    composerImpl.changeListWriter.endNodeMovement();
                    composerImpl.changeListWriter.moveDown(slotReader.node(iGroupSize2));
                }
                iReportFreeMovableContent$reportGroup += reportFreeMovableContent$reportGroup(composerImpl, i, iGroupSize2, zIsNode || z, zIsNode ? 0 : i3 + iReportFreeMovableContent$reportGroup);
                if (zIsNode) {
                    composerImpl.changeListWriter.endNodeMovement();
                    composerImpl.changeListWriter.moveUp();
                }
            }
            if (slotReader.isNode(i2)) {
                return 1;
            }
            return iReportFreeMovableContent$reportGroup;
        }
        int iGroupKey = slotReader.groupKey(i2);
        Object objGroupObjectKey = slotReader.groupObjectKey(i2);
        if (iGroupKey == 126665345 && (objGroupObjectKey instanceof MovableContent)) {
            MovableContentStateReference movableContentStateReferenceReportFreeMovableContent$movableContentReferenceFor = reportFreeMovableContent$movableContentReferenceFor(composerImpl, i2);
            if (movableContentStateReferenceReportFreeMovableContent$movableContentReferenceFor != null) {
                composerImpl.parentContext.deletedMovableContent$runtime(movableContentStateReferenceReportFreeMovableContent$movableContentReferenceFor);
                composerImpl.changeListWriter.recordSlotEditing();
                composerImpl.changeListWriter.releaseMovableGroupAtCurrent(composerImpl.getComposition(), composerImpl.parentContext, movableContentStateReferenceReportFreeMovableContent$movableContentReferenceFor);
            }
            if (!z || i2 == i) {
                return slotReader.nodeCount(i2);
            }
            composerImpl.changeListWriter.endNodeMovementAndDeleteNode(i3, i2);
            return 0;
        }
        if (iGroupKey != 206 || !Intrinsics.areEqual(objGroupObjectKey, ComposerKt.getReference())) {
            if (slotReader.isNode(i2)) {
                return 1;
            }
            return slotReader.nodeCount(i2);
        }
        Object objGroupGet = slotReader.groupGet(i2, 0);
        RememberObserverHolder rememberObserverHolder = objGroupGet instanceof RememberObserverHolder ? (RememberObserverHolder) objGroupGet : null;
        RememberObserver wrapped = rememberObserverHolder != null ? rememberObserverHolder.getWrapped() : null;
        CompositionContextHolder compositionContextHolder = wrapped instanceof CompositionContextHolder ? (CompositionContextHolder) wrapped : null;
        if (compositionContextHolder != null) {
            for (ComposerImpl composerImpl2 : compositionContextHolder.getRef().getComposers()) {
                composerImpl2.reportAllMovableContent();
                composerImpl.parentContext.reportRemovedComposition$runtime(composerImpl2.getComposition());
            }
        }
        return slotReader.nodeCount(i2);
    }

    private final void skipGroup() {
        this.groupNodeCount += this.reader.skipGroup();
    }

    private final void skipReaderToGroupEnd() {
        this.groupNodeCount = this.reader.getParentNodes();
        this.reader.skipToGroupEnd();
    }

    private final List<ComposeStackTraceFrame> stackTraceForGroup(int group, Integer dataOffset) {
        SlotReader slotReaderOpenReader = this.slotTable.openReader();
        try {
            return ComposeStackTraceBuilderKt.traceForGroup(slotReaderOpenReader, group, dataOffset);
        } finally {
            slotReaderOpenReader.close();
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x006d  */
    /* JADX WARN: Code duplicated, block: B:22:0x007b  */
    /* JADX WARN: Code duplicated, block: B:23:0x007d  */
    /* JADX WARN: Code duplicated, block: B:26:0x0085  */
    /* JADX WARN: Code duplicated, block: B:28:0x0092  */
    /* JADX WARN: Code duplicated, block: B:29:0x009e  */
    /* JADX WARN: Code duplicated, block: B:31:0x00a2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:32:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:34:0x00ae A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:35:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:39:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:42:0x00da  */
    /* JADX WARN: Code duplicated, block: B:48:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:51:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:57:0x0107  */
    /* JADX WARN: Code duplicated, block: B:60:0x011a  */
    /* JADX WARN: Code duplicated, block: B:67:0x015a  */
    /* JADX WARN: Code duplicated, block: B:69:0x0173  */
    /* JADX WARN: Code duplicated, block: B:70:0x017f  */
    /* JADX WARN: Code duplicated, block: B:72:0x0183 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x0185  */
    /* JADX WARN: Code duplicated, block: B:75:0x018f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:76:0x0191  */
    /* JADX WARN: Code duplicated, block: B:81:0x01c4  */
    /* JADX INFO: renamed from: start-BaiHCIY, reason: not valid java name */
    private final void m2326startBaiHCIY(int key, Object objectKey, int kind, Object data) {
        long jRotateLeft;
        GroupKind.Companion companion;
        boolean z;
        Pending pending;
        boolean z2;
        Pending pending2;
        SlotWriter slotWriter;
        int currentGroup;
        SlotWriter slotWriter2;
        Pending pending3;
        validateNodeNotExpected();
        int i = this.rGroupIndex;
        if (objectKey == null) {
            if (data == null || key != 207 || Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
                jRotateLeft = Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) key), 3) ^ ((long) i);
            } else {
                this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) data.hashCode()), 3) ^ ((long) i);
            }
            if (objectKey == null) {
                this.rGroupIndex++;
            }
            companion = GroupKind.INSTANCE;
            if (kind != companion.m2342getGroupULZAiWs()) {
                z = true;
            } else {
                z = false;
            }
            pending = null;
            if (getInserting()) {
                this.reader.beginEmpty();
                currentGroup = this.writer.getCurrentGroup();
                if (z) {
                    this.writer.startNode(key, Composer.INSTANCE.getEmpty());
                } else {
                    slotWriter2 = this.writer;
                    if (data != null) {
                        if (objectKey == null) {
                            objectKey = Composer.INSTANCE.getEmpty();
                        }
                        slotWriter2.startData(key, objectKey, data);
                    } else {
                        if (objectKey == null) {
                            objectKey = Composer.INSTANCE.getEmpty();
                        }
                        slotWriter2.startGroup(key, objectKey);
                    }
                }
                pending3 = this.pending;
                if (pending3 != null) {
                    KeyInfo keyInfo = new KeyInfo(key, -1, insertedGroupVirtualIndex(currentGroup), -1, 0);
                    pending3.registerInsert(keyInfo, this.nodeIndex - pending3.getStartIndex());
                    pending3.recordUsed(keyInfo);
                }
                enterGroup(z, null);
                return;
            }
            if (kind != companion.m2343getNodeULZAiWs() && this.reusing) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (this.pending == null) {
                int groupKey = this.reader.getGroupKey();
                if (z2 && groupKey == key && Intrinsics.areEqual(objectKey, this.reader.getGroupObjectKey())) {
                    startReaderGroup(z, data);
                } else {
                    this.pending = new Pending(this.reader.extractKeys(), this.nodeIndex);
                }
            }
            pending2 = this.pending;
            if (pending2 != null) {
                KeyInfo next = pending2.getNext(key, objectKey);
                if (!z2 || next == null) {
                    this.reader.beginEmpty();
                    this.inserting = true;
                    this.providerCache = null;
                    ensureWriter();
                    this.writer.beginInsert();
                    int currentGroup2 = this.writer.getCurrentGroup();
                    if (z) {
                        this.writer.startNode(key, Composer.INSTANCE.getEmpty());
                    } else {
                        slotWriter = this.writer;
                        if (data != null) {
                            if (objectKey == null) {
                                objectKey = Composer.INSTANCE.getEmpty();
                            }
                            slotWriter.startData(key, objectKey, data);
                        } else {
                            if (objectKey == null) {
                                objectKey = Composer.INSTANCE.getEmpty();
                            }
                            slotWriter.startGroup(key, objectKey);
                        }
                    }
                    this.insertAnchor = this.writer.anchor(currentGroup2);
                    KeyInfo keyInfo2 = new KeyInfo(key, -1, insertedGroupVirtualIndex(currentGroup2), -1, 0);
                    pending2.registerInsert(keyInfo2, this.nodeIndex - pending2.getStartIndex());
                    pending2.recordUsed(keyInfo2);
                    pending = new Pending(new ArrayList(), z ? 0 : this.nodeIndex);
                } else {
                    pending2.recordUsed(next);
                    int location = next.getLocation();
                    this.nodeIndex = pending2.nodePositionOf(next) + pending2.getStartIndex();
                    int iSlotPositionOf = pending2.slotPositionOf(next);
                    int groupIndex = iSlotPositionOf - pending2.getGroupIndex();
                    pending2.registerMoveSlot(iSlotPositionOf, pending2.getGroupIndex());
                    this.changeListWriter.moveReaderRelativeTo(location);
                    this.reader.reposition(location);
                    if (groupIndex > 0) {
                        this.changeListWriter.moveCurrentGroup(groupIndex);
                    }
                    startReaderGroup(z, data);
                }
            }
            enterGroup(z, pending);
        }
        jRotateLeft = Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) (objectKey instanceof Enum ? ((Enum) objectKey).ordinal() : objectKey.hashCode())), 3);
        this.compositeKeyHashCode = jRotateLeft;
        if (objectKey == null) {
            this.rGroupIndex++;
        }
        companion = GroupKind.INSTANCE;
        if (kind != companion.m2342getGroupULZAiWs()) {
            z = true;
        } else {
            z = false;
        }
        pending = null;
        if (getInserting()) {
            this.reader.beginEmpty();
            currentGroup = this.writer.getCurrentGroup();
            if (z) {
                this.writer.startNode(key, Composer.INSTANCE.getEmpty());
            } else {
                slotWriter2 = this.writer;
                if (data != null) {
                    if (objectKey == null) {
                        objectKey = Composer.INSTANCE.getEmpty();
                    }
                    slotWriter2.startData(key, objectKey, data);
                } else {
                    if (objectKey == null) {
                        objectKey = Composer.INSTANCE.getEmpty();
                    }
                    slotWriter2.startGroup(key, objectKey);
                }
            }
            pending3 = this.pending;
            if (pending3 != null) {
                KeyInfo keyInfo3 = new KeyInfo(key, -1, insertedGroupVirtualIndex(currentGroup), -1, 0);
                pending3.registerInsert(keyInfo3, this.nodeIndex - pending3.getStartIndex());
                pending3.recordUsed(keyInfo3);
            }
            enterGroup(z, null);
            return;
        }
        if (kind != companion.m2343getNodeULZAiWs()) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (this.pending == null) {
            int groupKey2 = this.reader.getGroupKey();
            if (z2) {
                this.pending = new Pending(this.reader.extractKeys(), this.nodeIndex);
            } else {
                this.pending = new Pending(this.reader.extractKeys(), this.nodeIndex);
            }
        }
        pending2 = this.pending;
        if (pending2 != null) {
            KeyInfo next2 = pending2.getNext(key, objectKey);
            if (z2) {
                this.reader.beginEmpty();
                this.inserting = true;
                this.providerCache = null;
                ensureWriter();
                this.writer.beginInsert();
                int currentGroup3 = this.writer.getCurrentGroup();
                if (z) {
                    this.writer.startNode(key, Composer.INSTANCE.getEmpty());
                } else {
                    slotWriter = this.writer;
                    if (data != null) {
                        if (objectKey == null) {
                            objectKey = Composer.INSTANCE.getEmpty();
                        }
                        slotWriter.startData(key, objectKey, data);
                    } else {
                        if (objectKey == null) {
                            objectKey = Composer.INSTANCE.getEmpty();
                        }
                        slotWriter.startGroup(key, objectKey);
                    }
                }
                this.insertAnchor = this.writer.anchor(currentGroup3);
                KeyInfo keyInfo4 = new KeyInfo(key, -1, insertedGroupVirtualIndex(currentGroup3), -1, 0);
                pending2.registerInsert(keyInfo4, this.nodeIndex - pending2.getStartIndex());
                pending2.recordUsed(keyInfo4);
                pending = new Pending(new ArrayList(), z ? 0 : this.nodeIndex);
            } else {
                this.reader.beginEmpty();
                this.inserting = true;
                this.providerCache = null;
                ensureWriter();
                this.writer.beginInsert();
                int currentGroup4 = this.writer.getCurrentGroup();
                if (z) {
                    this.writer.startNode(key, Composer.INSTANCE.getEmpty());
                } else {
                    slotWriter = this.writer;
                    if (data != null) {
                        if (objectKey == null) {
                            objectKey = Composer.INSTANCE.getEmpty();
                        }
                        slotWriter.startData(key, objectKey, data);
                    } else {
                        if (objectKey == null) {
                            objectKey = Composer.INSTANCE.getEmpty();
                        }
                        slotWriter.startGroup(key, objectKey);
                    }
                }
                this.insertAnchor = this.writer.anchor(currentGroup4);
                KeyInfo keyInfo5 = new KeyInfo(key, -1, insertedGroupVirtualIndex(currentGroup4), -1, 0);
                pending2.registerInsert(keyInfo5, this.nodeIndex - pending2.getStartIndex());
                pending2.recordUsed(keyInfo5);
                pending = new Pending(new ArrayList(), z ? 0 : this.nodeIndex);
            }
        }
        enterGroup(z, pending);
    }

    private final void startGroup(int key) {
        m2326startBaiHCIY(key, null, GroupKind.INSTANCE.m2342getGroupULZAiWs(), null);
    }

    private final void startReaderGroup(boolean isNode, Object data) {
        if (isNode) {
            this.reader.startNode();
            return;
        }
        if (data != null && this.reader.getGroupAux() != data) {
            this.changeListWriter.updateAuxData(data);
        }
        this.reader.startGroup();
    }

    private final void startRoot() {
        this.rGroupIndex = 0;
        this.reader = this.slotTable.openReader();
        startGroup(100);
        this.parentContext.startComposing$runtime();
        PersistentCompositionLocalMap compositionLocalScope$runtime = this.parentContext.getCompositionLocalScope$runtime();
        this.providersInvalidStack.push(ComposerImplKt.asInt(this.providersInvalid));
        this.providersInvalid = changed(compositionLocalScope$runtime);
        this.providerCache = null;
        if (!this.forceRecomposeScopes) {
            this.forceRecomposeScopes = this.parentContext.getCollectingParameterInformation();
        }
        if (!this.sourceMarkersEnabled) {
            this.sourceMarkersEnabled = this.parentContext.getCollectingSourceInformation();
        }
        if (this.sourceMarkersEnabled) {
            CompositionLocal<CompositionErrorContext> localCompositionErrorContext = CompositionErrorContextKt.getLocalCompositionErrorContext();
            localCompositionErrorContext.getClass();
            compositionLocalScope$runtime = compositionLocalScope$runtime.putValue(localCompositionErrorContext, new StaticValueHolder(getErrorContext$runtime()));
        }
        this.rootProvider = compositionLocalScope$runtime;
        Set<CompositionData> set = (Set) CompositionLocalMapKt.read(compositionLocalScope$runtime, InspectionTablesKt.getLocalInspectionTables());
        if (set != null) {
            set.add(getCompositionData());
            this.parentContext.recordInspectionTable$runtime(set);
        }
        startGroup(Long.hashCode(this.parentContext.getCompositeKeyHashCode()));
    }

    private final void updateCompositeKeyWhenWeEnterGroup(int groupKey, int rGroupIndex, Object dataKey, Object data) {
        if (dataKey != null) {
            if (dataKey instanceof Enum) {
                this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) ((Enum) dataKey).ordinal()), 3);
                return;
            } else {
                this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) dataKey.hashCode()), 3);
                return;
            }
        }
        if (data == null || groupKey != 207 || Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
            this.compositeKeyHashCode = ((long) rGroupIndex) ^ Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) groupKey), 3);
        } else {
            this.compositeKeyHashCode = ((long) rGroupIndex) ^ Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) data.hashCode()), 3);
        }
    }

    private final void updateCompositeKeyWhenWeEnterGroupKeyHash(int groupKey, int rGroupIndex) {
        this.compositeKeyHashCode = ((long) rGroupIndex) ^ Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) groupKey), 3);
    }

    private final void updateCompositeKeyWhenWeExitGroup(int groupKey, int rGroupIndex, Object dataKey, Object data) {
        if (dataKey != null) {
            if (dataKey instanceof Enum) {
                this.compositeKeyHashCode = Long.rotateRight(Long.rotateRight(getCompositeKeyHashCode(), 3) ^ ((long) ((Enum) dataKey).ordinal()), 3);
                return;
            } else {
                this.compositeKeyHashCode = Long.rotateRight(Long.rotateRight(getCompositeKeyHashCode(), 3) ^ ((long) dataKey.hashCode()), 3);
                return;
            }
        }
        if (data == null || groupKey != 207 || Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
            this.compositeKeyHashCode = Long.rotateRight(Long.rotateRight(getCompositeKeyHashCode() ^ ((long) rGroupIndex), 3) ^ ((long) groupKey), 3);
        } else {
            this.compositeKeyHashCode = Long.rotateRight(Long.rotateRight(getCompositeKeyHashCode() ^ ((long) rGroupIndex), 3) ^ ((long) data.hashCode()), 3);
        }
    }

    private final void updateCompositeKeyWhenWeExitGroupKeyHash(int groupKey, int rGroupIndex) {
        this.compositeKeyHashCode = Long.rotateRight(Long.rotateRight(getCompositeKeyHashCode() ^ ((long) rGroupIndex), 3) ^ ((long) groupKey), 3);
    }

    private final void updateNodeCount(int group, int count) {
        if (updatedNodeCount(group) != count) {
            if (group < 0) {
                MutableIntIntMap mutableIntIntMap = this.nodeCountVirtualOverrides;
                if (mutableIntIntMap == null) {
                    mutableIntIntMap = new MutableIntIntMap(0, 1, (DefaultConstructorMarker) null);
                    this.nodeCountVirtualOverrides = mutableIntIntMap;
                }
                mutableIntIntMap.set(group, count);
                return;
            }
            int[] iArr = this.nodeCountOverrides;
            if (iArr == null) {
                int[] iArr2 = new int[this.reader.getGroupsSize()];
                ArraysKt.fill$default(iArr2, -1, 0, 0, 6, (Object) null);
                this.nodeCountOverrides = iArr2;
                iArr = iArr2;
            }
            iArr[group] = count;
        }
    }

    private final void updateNodeCountOverrides(int group, int newCount) {
        int iUpdatedNodeCount = updatedNodeCount(group);
        if (iUpdatedNodeCount != newCount) {
            int i = newCount - iUpdatedNodeCount;
            int iM2376getSizeimpl = Stack.m2376getSizeimpl(this.pendingStack) - 1;
            while (group != -1) {
                int iUpdatedNodeCount2 = updatedNodeCount(group) + i;
                updateNodeCount(group, iUpdatedNodeCount2);
                for (int i2 = iM2376getSizeimpl; -1 < i2; i2--) {
                    Pending pending = (Pending) Stack.m2381peekimpl(this.pendingStack, i2);
                    if (pending != null && pending.updateNodeCount(group, iUpdatedNodeCount2)) {
                        iM2376getSizeimpl = i2 - 1;
                        break;
                    }
                }
                SlotReader slotReader = this.reader;
                if (group < 0) {
                    group = slotReader.getParent();
                } else if (slotReader.isNode(group)) {
                    return;
                } else {
                    group = this.reader.parent(group);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.compose.runtime.PersistentCompositionLocalMap, java.lang.Object] */
    private final PersistentCompositionLocalMap updateProviderMapGroup(PersistentCompositionLocalMap parentScope, PersistentCompositionLocalMap currentProviders) {
        PersistentMap.Builder<CompositionLocal<Object>, ValueHolder<Object>> builder = parentScope.builder2();
        builder.putAll(currentProviders);
        ?? Build = builder.build2();
        startGroup(ComposerKt.providerMapsKey, ComposerKt.getProviderMaps());
        updateSlot(Build);
        updateSlot(currentProviders);
        endGroup();
        return Build;
    }

    private final void updateSlot(Object value) {
        nextSlot();
        updateValue(value);
    }

    private final int updatedNodeCount(int group) {
        int i;
        if (group >= 0) {
            int[] iArr = this.nodeCountOverrides;
            return (iArr == null || (i = iArr[group]) < 0) ? this.reader.nodeCount(group) : i;
        }
        MutableIntIntMap mutableIntIntMap = this.nodeCountVirtualOverrides;
        if (mutableIntIntMap == null || !mutableIntIntMap.containsKey(group)) {
            return 0;
        }
        return mutableIntIntMap.get(group);
    }

    private final void validateNodeExpected() {
        if (!this.nodeExpected) {
            ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected was not expected");
        }
        this.nodeExpected = false;
    }

    private final void validateNodeNotExpected() {
        if (this.nodeExpected) {
            ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected");
        }
    }

    private final <R> R withReader(SlotReader reader, Function0<? extends R> block) {
        SlotReader slotReader = this.reader;
        int[] iArr = this.nodeCountOverrides;
        MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap = this.providerUpdates;
        this.nodeCountOverrides = null;
        this.providerUpdates = null;
        try {
            this.reader = reader;
            return (R) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            this.reader = slotReader;
            this.nodeCountOverrides = iArr;
            this.providerUpdates = mutableIntObjectMap;
            InlineMarker.finallyEnd(1);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public <V, T> void apply(V value, Function2<? super T, ? super V, Unit> block) {
        if (getInserting()) {
            this.insertFixups.updateNode(value, block);
        } else {
            this.changeListWriter.updateNode(value, block);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionContext buildContext() {
        ComposerImpl composerImpl;
        startGroup(ComposerKt.referenceKey, ComposerKt.getReference());
        if (getInserting()) {
            SlotWriter.markGroup$default(this.writer, 0, 1, null);
        }
        Object objNextSlot = nextSlot();
        RememberObserverHolder reusableRememberObserverHolder = objNextSlot instanceof RememberObserverHolder ? (RememberObserverHolder) objNextSlot : null;
        if (reusableRememberObserverHolder == null) {
            composerImpl = this;
            reusableRememberObserverHolder = new ReusableRememberObserverHolder(new CompositionContextHolder(composerImpl.new CompositionContextImpl(getCompositeKeyHashCode(), this.forceRecomposeScopes, this.sourceMarkersEnabled, getComposition().getObserverHolder())), -1);
            composerImpl.updateValue(reusableRememberObserverHolder);
        } else {
            composerImpl = this;
        }
        RememberObserver wrapped = reusableRememberObserverHolder.getWrapped();
        wrapped.getClass();
        CompositionContextHolder compositionContextHolder = (CompositionContextHolder) wrapped;
        compositionContextHolder.getRef().updateCompositionLocalScope(composerImpl.currentCompositionLocalScope());
        composerImpl.endGroup();
        return compositionContextHolder.getRef();
    }

    @ComposeCompilerApi
    public final <T> T cache(boolean invalid, Function0<? extends T> block) {
        T t = (T) nextSlotForCache();
        if (t != Composer.INSTANCE.getEmpty() && !invalid) {
            return t;
        }
        T t2 = (T) block.invoke();
        updateCachedValue(t2);
        return t2;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(float value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Float) && value == ((Number) objNextSlot).floatValue()) {
            return false;
        }
        updateValue(Float.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changedInstance(Object value) {
        if (nextSlot() == value) {
            return false;
        }
        updateValue(value);
        return true;
    }

    public final void changesApplied$runtime() {
        this.providerUpdates = null;
    }

    @Override // androidx.compose.runtime.Composer
    public void collectParameterInformation() {
        this.forceRecomposeScopes = true;
        this.sourceMarkersEnabled = true;
        this.slotTable.collectSourceInformation();
        this.insertTable.collectSourceInformation();
        this.writer.updateToTableMaps();
    }

    /* JADX INFO: renamed from: composeContent--ZbOJvo$runtime, reason: not valid java name */
    public final void m2327composeContentZbOJvo$runtime(MutableScatterMap<Object, Object> invalidationsRequested, Function2<? super Composer, ? super Integer, Unit> content, ShouldPauseCallback shouldPause) {
        if (!this.changes.isEmpty()) {
            ComposerKt.composeImmediateRuntimeError("Expected applyChanges() to have been called");
        }
        this.shouldPauseCallback = shouldPause;
        try {
            m2325doComposeaFTiNEg(invalidationsRequested, content);
        } finally {
            this.shouldPauseCallback = null;
        }
    }

    @Override // androidx.compose.runtime.Composer
    public <T> T consume(CompositionLocal<T> key) {
        return (T) CompositionLocalMapKt.read(currentCompositionLocalScope(), key);
    }

    @Override // androidx.compose.runtime.Composer
    public <T> void createNode(Function0<? extends T> factory) {
        validateNodeExpected();
        if (!getInserting()) {
            ComposerKt.composeImmediateRuntimeError("createNode() can only be called when inserting");
        }
        int iPeek = this.parentStateStack.peek();
        SlotWriter slotWriter = this.writer;
        Anchor anchor = slotWriter.anchor(slotWriter.getParent());
        this.groupNodeCount++;
        this.insertFixups.createAndInsertNode(factory, iPeek, anchor);
    }

    public final void deactivate$runtime() {
        Stack.m2371clearimpl(this.invalidateStack);
        this.invalidations.clear();
        this.changes.clear();
        this.providerUpdates = null;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void deactivateToEndGroup(boolean changed) {
        if (!(this.groupNodeCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("No nodes can be emitted before calling deactivateToEndGroup");
        }
        if (getInserting()) {
            return;
        }
        if (!changed) {
            skipReaderToGroupEnd();
            return;
        }
        int currentGroup = this.reader.getCurrentGroup();
        int currentEnd = this.reader.getCurrentEnd();
        this.changeListWriter.deactivateCurrentGroup();
        ComposerImplKt.removeRange(this.invalidations, currentGroup, currentEnd);
        this.reader.skipToGroupEnd();
    }

    @Override // androidx.compose.runtime.Composer
    public void disableReusing() {
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public void disableSourceInformation() {
        this.sourceMarkersEnabled = false;
    }

    public final void dispose$runtime() {
        Object objBeginSection = Trace.INSTANCE.beginSection("Compose:Composer.dispose");
        try {
            this.parentContext.unregisterComposer$runtime(this);
            deactivate$runtime();
            getApplier().clear();
            this.isDisposed = true;
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.INSTANCE.endSection(objBeginSection);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void enableReusing() {
        this.reusing = this.reusingGroup >= 0;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endDefaults() {
        endGroup();
        RecomposeScopeImpl currentRecomposeScope$runtime = getCurrentRecomposeScope$runtime();
        if (currentRecomposeScope$runtime == null || !currentRecomposeScope$runtime.getUsed()) {
            return;
        }
        currentRecomposeScope$runtime.setDefaultsInScope(true);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endMovableGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    public void endNode() {
        end(true);
    }

    @Override // androidx.compose.runtime.Composer
    public void endProvider() {
        endGroup();
        endGroup();
        this.providersInvalid = ComposerImplKt.asBool(this.providersInvalidStack.pop());
        this.providerCache = null;
    }

    @Override // androidx.compose.runtime.Composer
    public void endProviders() {
        endGroup();
        endGroup();
        this.providersInvalid = ComposerImplKt.asBool(this.providersInvalidStack.pop());
        this.providerCache = null;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endReplaceGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endReplaceableGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public ScopeUpdateScope endRestartGroup() {
        Anchor anchor;
        RecomposeScopeImpl recomposeScopeImpl = null;
        RecomposeScopeImpl recomposeScopeImpl2 = Stack.m2379isNotEmptyimpl(this.invalidateStack) ? (RecomposeScopeImpl) Stack.m2382popimpl(this.invalidateStack) : null;
        if (recomposeScopeImpl2 != null) {
            recomposeScopeImpl2.setRequiresRecompose(false);
            Function1<Composition, Unit> function1ExitRecomposeScope = exitRecomposeScope(recomposeScopeImpl2);
            if (function1ExitRecomposeScope != null) {
                this.changeListWriter.endCompositionScope(function1ExitRecomposeScope, getComposition());
            }
            if (recomposeScopeImpl2.getResuming()) {
                recomposeScopeImpl2.setResuming(false);
                this.changeListWriter.endResumingScope(recomposeScopeImpl2);
                recomposeScopeImpl2.setReusing(false);
                if (recomposeScopeImpl2.getResetReusing()) {
                    recomposeScopeImpl2.setResetReusing(false);
                    this.reusing = false;
                }
            }
        }
        if (recomposeScopeImpl2 != null && !recomposeScopeImpl2.getSkipped$runtime() && (recomposeScopeImpl2.getUsed() || this.forceRecomposeScopes)) {
            if (recomposeScopeImpl2.getAnchor() == null) {
                if (getInserting()) {
                    SlotWriter slotWriter = this.writer;
                    anchor = slotWriter.anchor(slotWriter.getParent());
                } else {
                    SlotReader slotReader = this.reader;
                    anchor = slotReader.anchor(slotReader.getParent());
                }
                recomposeScopeImpl2.setAnchor(anchor);
            }
            recomposeScopeImpl2.setDefaultsInvalid(false);
            recomposeScopeImpl = recomposeScopeImpl2;
        }
        end(false);
        return recomposeScopeImpl;
    }

    @Override // androidx.compose.runtime.Composer
    public void endReusableGroup() {
        if (this.reusing && this.reader.getParent() == this.reusingGroup) {
            this.reusingGroup = -1;
            this.reusing = false;
        }
        end(false);
    }

    public final void endReuseFromRoot() {
        if (!(!this.isComposing && this.reusingGroup == 100)) {
            PreconditionsKt.throwIllegalArgumentException("Cannot disable reuse from root if it was caused by other groups");
        }
        this.reusingGroup = -1;
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public void endToMarker(int marker) {
        if (marker < 0) {
            int i = -marker;
            SlotWriter slotWriter = this.writer;
            while (true) {
                int parent = slotWriter.getParent();
                if (parent <= i) {
                    return;
                } else {
                    end(slotWriter.isNode(parent));
                }
            }
        } else {
            if (getInserting()) {
                SlotWriter slotWriter2 = this.writer;
                while (getInserting()) {
                    end(slotWriter2.isNode(slotWriter2.getParent()));
                }
            }
            SlotReader slotReader = this.reader;
            while (true) {
                int parent2 = slotReader.getParent();
                if (parent2 <= marker) {
                    return;
                } else {
                    end(slotReader.isNode(parent2));
                }
            }
        }
    }

    public final boolean forceRecomposeScopes$runtime() {
        if (this.forceRecomposeScopes) {
            return false;
        }
        this.forceRecomposeScopes = true;
        this.forciblyRecompose = true;
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    public Applier<?> getApplier() {
        return this.applier;
    }

    @Override // androidx.compose.runtime.Composer
    public CoroutineContext getApplyCoroutineContext() {
        return this.applyCoroutineContext;
    }

    public final boolean getAreChildrenComposing$runtime() {
        return this.childrenComposing > 0;
    }

    public final int getChangeCount$runtime() {
        return this.changes.getSize();
    }

    @Override // androidx.compose.runtime.Composer
    public long getCompositeKeyHashCode() {
        return this.compositeKeyHashCode;
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionData getCompositionData() {
        CompositionData compositionData = this._compositionData;
        if (compositionData != null) {
            return compositionData;
        }
        CompositionDataImpl compositionDataImpl = new CompositionDataImpl(getComposition());
        this._compositionData = compositionDataImpl;
        return compositionDataImpl;
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionLocalMap getCurrentCompositionLocalMap() {
        return currentCompositionLocalScope();
    }

    @Override // androidx.compose.runtime.Composer
    public int getCurrentMarker() {
        return getInserting() ? -this.writer.getParent() : this.reader.getParent();
    }

    public final RecomposeScopeImpl getCurrentRecomposeScope$runtime() {
        ArrayList<RecomposeScopeImpl> arrayList = this.invalidateStack;
        if (this.childrenComposing == 0 && Stack.m2379isNotEmptyimpl(arrayList)) {
            return (RecomposeScopeImpl) Stack.m2380peekimpl(arrayList);
        }
        return null;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getDefaultsInvalid() {
        RecomposeScopeImpl currentRecomposeScope$runtime;
        return !getSkipping() || this.providersInvalid || ((currentRecomposeScope$runtime = getCurrentRecomposeScope$runtime()) != null && currentRecomposeScope$runtime.getDefaultsInvalid());
    }

    /* JADX INFO: renamed from: getDeferredChanges$runtime, reason: from getter */
    public final ChangeList getDeferredChanges() {
        return this.deferredChanges;
    }

    public final CompositionErrorContextImpl getErrorContext$runtime() {
        if (this.parentContext.getStackTraceEnabled$runtime()) {
            return this.errorContext;
        }
        return null;
    }

    public final boolean getHasInvalidations() {
        return !this.invalidations.isEmpty();
    }

    public final boolean getHasPendingChanges$runtime() {
        return this.changes.isNotEmpty();
    }

    /* JADX INFO: renamed from: getInsertTable$runtime, reason: from getter */
    public final SlotTable getInsertTable() {
        return this.insertTable;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getInserting() {
        return this.inserting;
    }

    /* JADX INFO: renamed from: getReader$runtime, reason: from getter */
    public final SlotReader getReader() {
        return this.reader;
    }

    @Override // androidx.compose.runtime.Composer
    public RecomposeScope getRecomposeScope() {
        return getCurrentRecomposeScope$runtime();
    }

    @Override // androidx.compose.runtime.Composer
    public Object getRecomposeScopeIdentity() {
        RecomposeScopeImpl currentRecomposeScope$runtime = getCurrentRecomposeScope$runtime();
        if (currentRecomposeScope$runtime != null) {
            return currentRecomposeScope$runtime.getAnchor();
        }
        return null;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getSkipping() {
        RecomposeScopeImpl currentRecomposeScope$runtime;
        return (getInserting() || this.reusing || this.providersInvalid || (currentRecomposeScope$runtime = getCurrentRecomposeScope$runtime()) == null || currentRecomposeScope$runtime.getRequiresRecompose() || this.forciblyRecompose) ? false : true;
    }

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContent(MovableContent<?> value, Object parameter) {
        value.getClass();
        invokeMovableContentLambda(value, currentCompositionLocalScope(), parameter, false);
    }

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContentReferences(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        try {
            insertMovableContentGuarded(references);
            cleanUpCompose();
        } catch (Throwable th) {
            abortRoot();
            throw th;
        }
    }

    /* JADX INFO: renamed from: isComposing$runtime, reason: from getter */
    public final boolean getIsComposing() {
        return this.isComposing;
    }

    /* JADX INFO: renamed from: isDisposed$runtime, reason: from getter */
    public final boolean getIsDisposed() {
        return this.isDisposed;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public Object joinKey(Object left, Object right) {
        Object key = ComposerImplKt.getKey(this.reader.getGroupObjectKey(), left, right);
        return key == null ? new JoinedKey(left, right) : key;
    }

    public final Object nextSlot() {
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object next = this.reader.next();
        return (!this.reusing || (next instanceof ReusableRememberObserverHolder)) ? next : Composer.INSTANCE.getEmpty();
    }

    public final Object nextSlotForCache() {
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object next = this.reader.next();
        if (!this.reusing || (next instanceof ReusableRememberObserverHolder)) {
            return next instanceof RememberObserverHolder ? ((RememberObserverHolder) next).getWrapped() : next;
        }
        return Composer.INSTANCE.getEmpty();
    }

    public final int parentKey$runtime() {
        if (getInserting()) {
            SlotWriter slotWriter = this.writer;
            return slotWriter.groupKey(slotWriter.getParent());
        }
        SlotReader slotReader = this.reader;
        return slotReader.groupKey(slotReader.getParent());
    }

    public final List<ComposeStackTraceFrame> parentStackTrace() {
        Integer numFindSubcompositionContextGroup;
        Composition composition$runtime = this.parentContext.getComposition$runtime();
        CompositionImpl compositionImpl = composition$runtime instanceof CompositionImpl ? (CompositionImpl) composition$runtime : null;
        if (compositionImpl != null && (numFindSubcompositionContextGroup = ComposeStackTraceBuilderKt.findSubcompositionContextGroup(compositionImpl.getSlotTable(), this.parentContext)) != null) {
            SlotReader slotReaderOpenReader = compositionImpl.getSlotTable().openReader();
            try {
                return CollectionsKt.plus(ComposeStackTraceBuilderKt.traceForGroup(slotReaderOpenReader, numFindSubcompositionContextGroup.intValue(), 0), compositionImpl.getComposer().parentStackTrace());
            } finally {
                slotReaderOpenReader.close();
            }
        }
        return CollectionsKt.emptyList();
    }

    public final void prepareCompose$runtime(Function0<Unit> block) {
        if (this.isComposing) {
            ComposerKt.composeImmediateRuntimeError("Preparing a composition while composing is not supported");
        }
        this.isComposing = true;
        try {
            block.invoke();
        } finally {
            this.isComposing = false;
        }
    }

    /* JADX INFO: renamed from: recompose-aFTiNEg$runtime, reason: not valid java name */
    public final boolean m2328recomposeaFTiNEg$runtime(MutableScatterMap<Object, Object> invalidationsRequested, ShouldPauseCallback shouldPause) {
        if (!this.changes.isEmpty()) {
            ComposerKt.composeImmediateRuntimeError("Expected applyChanges() to have been called");
        }
        if (ScopeMap.m2495getSizeimpl(invalidationsRequested) <= 0 && this.invalidations.isEmpty() && !this.forciblyRecompose) {
            return false;
        }
        this.shouldPauseCallback = shouldPause;
        try {
            m2325doComposeaFTiNEg(invalidationsRequested, null);
            return this.changes.isNotEmpty();
        } finally {
            this.shouldPauseCallback = null;
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void recordSideEffect(Function0<Unit> effect) {
        this.changeListWriter.sideEffect(effect);
    }

    @Override // androidx.compose.runtime.Composer
    public void recordUsed(RecomposeScope scope) {
        RecomposeScopeImpl recomposeScopeImpl = scope instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) scope : null;
        if (recomposeScopeImpl != null) {
            recomposeScopeImpl.setUsed(true);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public Object rememberedValue() {
        return nextSlotForCache();
    }

    @Override // androidx.compose.runtime.Composer
    public CancellationHandle scheduleFrameEndCallback(Function0<Unit> action) {
        return this.parentContext.scheduleFrameEndCallback(action);
    }

    public final void setDeferredChanges$runtime(ChangeList changeList) {
        this.deferredChanges = changeList;
    }

    public final void setInsertTable$runtime(SlotTable slotTable) {
        this.insertTable = slotTable;
    }

    public final void setReader$runtime(SlotReader slotReader) {
        this.reader = slotReader;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean shouldExecute(boolean parametersChanged, int flags) {
        RecomposeScopeImpl currentRecomposeScope$runtime;
        if ((flags & 1) != 0 || (!getInserting() && !this.reusing)) {
            return parametersChanged || !getSkipping();
        }
        ShouldPauseCallback shouldPauseCallback = this.shouldPauseCallback;
        if (shouldPauseCallback == null || (currentRecomposeScope$runtime = getCurrentRecomposeScope$runtime()) == null || !shouldPauseCallback.shouldPause() || currentRecomposeScope$runtime.getResuming()) {
            return true;
        }
        currentRecomposeScope$runtime.setUsed(true);
        currentRecomposeScope$runtime.setReusing(this.reusing);
        currentRecomposeScope$runtime.setPaused(true);
        this.changeListWriter.rememberPausingScope(currentRecomposeScope$runtime);
        this.parentContext.reportPausedScope$runtime(currentRecomposeScope$runtime);
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x008c  */
    /* JADX WARN: Code duplicated, block: B:30:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:32:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:34:0x00e3  */
    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void skipCurrentGroup() {
        long jRotateLeft;
        if (this.invalidations.isEmpty()) {
            skipGroup();
            return;
        }
        SlotReader slotReader = this.reader;
        int groupKey = slotReader.getGroupKey();
        Object groupObjectKey = slotReader.getGroupObjectKey();
        Object groupAux = slotReader.getGroupAux();
        int i = this.rGroupIndex;
        if (groupObjectKey == null) {
            if (groupAux == null || groupKey != 207 || Intrinsics.areEqual(groupAux, Composer.INSTANCE.getEmpty())) {
                jRotateLeft = Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) groupKey), 3) ^ ((long) i);
            } else {
                this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) groupAux.hashCode()), 3) ^ ((long) i);
            }
            startReaderGroup(slotReader.isNode(), null);
            recomposeToGroupEnd();
            slotReader.endGroup();
            if (groupObjectKey != null) {
                if (groupObjectKey instanceof Enum) {
                    this.compositeKeyHashCode = Long.rotateRight(Long.rotateRight(getCompositeKeyHashCode(), 3) ^ ((long) ((Enum) groupObjectKey).ordinal()), 3);
                } else {
                    this.compositeKeyHashCode = Long.rotateRight(Long.rotateRight(getCompositeKeyHashCode(), 3) ^ ((long) groupObjectKey.hashCode()), 3);
                }
            }
            if (groupAux == null && groupKey == 207 && !Intrinsics.areEqual(groupAux, Composer.INSTANCE.getEmpty())) {
                this.compositeKeyHashCode = Long.rotateRight(Long.rotateRight(getCompositeKeyHashCode() ^ ((long) i), 3) ^ ((long) groupAux.hashCode()), 3);
                return;
            } else {
                this.compositeKeyHashCode = Long.rotateRight(((long) groupKey) ^ Long.rotateRight(getCompositeKeyHashCode() ^ ((long) i), 3), 3);
            }
        }
        jRotateLeft = Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) (groupObjectKey instanceof Enum ? ((Enum) groupObjectKey).ordinal() : groupObjectKey.hashCode())), 3);
        this.compositeKeyHashCode = jRotateLeft;
        startReaderGroup(slotReader.isNode(), null);
        recomposeToGroupEnd();
        slotReader.endGroup();
        if (groupObjectKey != null) {
            if (groupAux == null) {
            }
            this.compositeKeyHashCode = Long.rotateRight(((long) groupKey) ^ Long.rotateRight(getCompositeKeyHashCode() ^ ((long) i), 3), 3);
        } else if (groupObjectKey instanceof Enum) {
            this.compositeKeyHashCode = Long.rotateRight(Long.rotateRight(getCompositeKeyHashCode(), 3) ^ ((long) ((Enum) groupObjectKey).ordinal()), 3);
        } else {
            this.compositeKeyHashCode = Long.rotateRight(Long.rotateRight(getCompositeKeyHashCode(), 3) ^ ((long) groupObjectKey.hashCode()), 3);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void skipToGroupEnd() {
        if (!(this.groupNodeCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("No nodes can be emitted before calling skipAndEndGroup");
        }
        if (getInserting()) {
            return;
        }
        RecomposeScopeImpl currentRecomposeScope$runtime = getCurrentRecomposeScope$runtime();
        if (currentRecomposeScope$runtime != null) {
            currentRecomposeScope$runtime.scopeSkipped();
        }
        if (this.invalidations.isEmpty()) {
            skipReaderToGroupEnd();
        } else {
            recomposeToGroupEnd();
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformation(String sourceInformation) {
        if (getInserting() && this.sourceMarkersEnabled) {
            this.writer.recordGroupSourceInformation(sourceInformation);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformationMarkerEnd() {
        if (getInserting() && this.sourceMarkersEnabled) {
            this.writer.recordGrouplessCallSourceInformationEnd();
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformationMarkerStart(int key, String sourceInformation) {
        if (getInserting() && this.sourceMarkersEnabled) {
            this.writer.recordGrouplessCallSourceInformationStart(key, sourceInformation);
        }
    }

    public final ComposeStackTrace stackTraceForValue$runtime(final Object value) {
        List listEmptyList;
        ObjectLocation objectLocationFindLocation = ComposeStackTraceBuilderKt.findLocation(this.slotTable, new Function1() { // from class: cn2
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ComposerImpl.e(value, obj));
            }
        });
        if (objectLocationFindLocation == null || (listEmptyList = CollectionsKt.plus(stackTraceForGroup(objectLocationFindLocation.getGroup(), objectLocationFindLocation.getDataOffset()), parentStackTrace())) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        return new ComposeStackTrace(listEmptyList);
    }

    public final int stacksSize$runtime() {
        return this.entersStack.tos + Stack.m2376getSizeimpl(this.invalidateStack) + this.providersInvalidStack.tos + Stack.m2376getSizeimpl(this.pendingStack) + this.parentStateStack.tos;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startDefaults() {
        m2326startBaiHCIY(ComposerKt.defaultsKey, null, GroupKind.INSTANCE.m2342getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startMovableGroup(int key, Object dataKey) {
        m2326startBaiHCIY(key, dataKey, GroupKind.INSTANCE.m2342getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    public void startNode() {
        m2326startBaiHCIY(125, null, GroupKind.INSTANCE.m2343getNodeULZAiWs(), null);
        this.nodeExpected = true;
    }

    @Override // androidx.compose.runtime.Composer
    public void startProvider(ProvidedValue<?> value) {
        ValueHolder<?> valueHolder;
        PersistentCompositionLocalMap persistentCompositionLocalMapCurrentCompositionLocalScope = currentCompositionLocalScope();
        startGroup(ComposerKt.providerKey, ComposerKt.getProvider());
        Object objRememberedValue = rememberedValue();
        if (Intrinsics.areEqual(objRememberedValue, Composer.INSTANCE.getEmpty())) {
            valueHolder = null;
        } else {
            objRememberedValue.getClass();
            valueHolder = (ValueHolder) objRememberedValue;
        }
        CompositionLocal<?> compositionLocal = value.getCompositionLocal();
        compositionLocal.getClass();
        ValueHolder<?> valueHolderUpdatedStateOf$runtime = compositionLocal.updatedStateOf$runtime(value, valueHolder);
        boolean zAreEqual = Intrinsics.areEqual(valueHolderUpdatedStateOf$runtime, valueHolder);
        if (!zAreEqual) {
            updateRememberedValue(valueHolderUpdatedStateOf$runtime);
        }
        boolean z = true;
        boolean z2 = false;
        if (getInserting()) {
            if (value.getCanOverride() || !CompositionLocalMapKt.contains(persistentCompositionLocalMapCurrentCompositionLocalScope, compositionLocal)) {
                persistentCompositionLocalMapCurrentCompositionLocalScope = persistentCompositionLocalMapCurrentCompositionLocalScope.putValue(compositionLocal, valueHolderUpdatedStateOf$runtime);
            }
            this.writerHasAProvider = true;
        } else {
            SlotReader slotReader = this.reader;
            Object objGroupAux = slotReader.groupAux(slotReader.getCurrentGroup());
            objGroupAux.getClass();
            PersistentCompositionLocalMap persistentCompositionLocalMap = (PersistentCompositionLocalMap) objGroupAux;
            if (!(getSkipping() && zAreEqual) && (value.getCanOverride() || !CompositionLocalMapKt.contains(persistentCompositionLocalMapCurrentCompositionLocalScope, compositionLocal))) {
                persistentCompositionLocalMapCurrentCompositionLocalScope = persistentCompositionLocalMapCurrentCompositionLocalScope.putValue(compositionLocal, valueHolderUpdatedStateOf$runtime);
            } else if ((zAreEqual && !this.providersInvalid) || !this.providersInvalid) {
                persistentCompositionLocalMapCurrentCompositionLocalScope = persistentCompositionLocalMap;
            }
            if (!this.reusing && persistentCompositionLocalMap == persistentCompositionLocalMapCurrentCompositionLocalScope) {
                z = false;
            }
            z2 = z;
        }
        if (z2 && !getInserting()) {
            recordProviderUpdate(persistentCompositionLocalMapCurrentCompositionLocalScope);
        }
        this.providersInvalidStack.push(ComposerImplKt.asInt(this.providersInvalid));
        this.providersInvalid = z2;
        this.providerCache = persistentCompositionLocalMapCurrentCompositionLocalScope;
        m2326startBaiHCIY(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m2342getGroupULZAiWs(), persistentCompositionLocalMapCurrentCompositionLocalScope);
    }

    @Override // androidx.compose.runtime.Composer
    public void startProviders(ProvidedValue<?>[] values) {
        PersistentCompositionLocalMap persistentCompositionLocalMapUpdateProviderMapGroup;
        PersistentCompositionLocalMap persistentCompositionLocalMapCurrentCompositionLocalScope = currentCompositionLocalScope();
        startGroup(ComposerKt.providerKey, ComposerKt.getProvider());
        boolean z = true;
        boolean z2 = false;
        if (getInserting()) {
            persistentCompositionLocalMapUpdateProviderMapGroup = updateProviderMapGroup(persistentCompositionLocalMapCurrentCompositionLocalScope, CompositionLocalMapKt.updateCompositionMap$default(values, persistentCompositionLocalMapCurrentCompositionLocalScope, null, 4, null));
            this.writerHasAProvider = true;
        } else {
            Object objGroupGet = this.reader.groupGet(0);
            objGroupGet.getClass();
            PersistentCompositionLocalMap persistentCompositionLocalMap = (PersistentCompositionLocalMap) objGroupGet;
            Object objGroupGet2 = this.reader.groupGet(1);
            objGroupGet2.getClass();
            PersistentCompositionLocalMap persistentCompositionLocalMap2 = (PersistentCompositionLocalMap) objGroupGet2;
            PersistentCompositionLocalMap persistentCompositionLocalMapUpdateCompositionMap = CompositionLocalMapKt.updateCompositionMap(values, persistentCompositionLocalMapCurrentCompositionLocalScope, persistentCompositionLocalMap2);
            if (getSkipping() && !this.reusing && Intrinsics.areEqual(persistentCompositionLocalMap2, persistentCompositionLocalMapUpdateCompositionMap)) {
                skipGroup();
                persistentCompositionLocalMapUpdateProviderMapGroup = persistentCompositionLocalMap;
            } else {
                persistentCompositionLocalMapUpdateProviderMapGroup = updateProviderMapGroup(persistentCompositionLocalMapCurrentCompositionLocalScope, persistentCompositionLocalMapUpdateCompositionMap);
                if (!this.reusing && Intrinsics.areEqual(persistentCompositionLocalMapUpdateProviderMapGroup, persistentCompositionLocalMap)) {
                    z = false;
                }
                z2 = z;
            }
        }
        if (z2 && !getInserting()) {
            recordProviderUpdate(persistentCompositionLocalMapUpdateProviderMapGroup);
        }
        this.providersInvalidStack.push(ComposerImplKt.asInt(this.providersInvalid));
        this.providersInvalid = z2;
        this.providerCache = persistentCompositionLocalMapUpdateProviderMapGroup;
        m2326startBaiHCIY(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m2342getGroupULZAiWs(), persistentCompositionLocalMapUpdateProviderMapGroup);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startReplaceGroup(int key) {
        if (this.pending != null) {
            m2326startBaiHCIY(key, null, GroupKind.INSTANCE.m2342getGroupULZAiWs(), null);
            return;
        }
        validateNodeNotExpected();
        this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft(getCompositeKeyHashCode(), 3) ^ ((long) key), 3) ^ ((long) this.rGroupIndex);
        this.rGroupIndex++;
        SlotReader slotReader = this.reader;
        if (getInserting()) {
            slotReader.beginEmpty();
            this.writer.startGroup(key, Composer.INSTANCE.getEmpty());
            enterGroup(false, null);
            return;
        }
        if (slotReader.getGroupKey() == key && !slotReader.getHasObjectKey()) {
            slotReader.startGroup();
            enterGroup(false, null);
            return;
        }
        if (!slotReader.isGroupEnd()) {
            int i = this.nodeIndex;
            int currentGroup = slotReader.getCurrentGroup();
            recordDelete();
            this.changeListWriter.removeNode(i, slotReader.skipGroup());
            ComposerImplKt.removeRange(this.invalidations, currentGroup, slotReader.getCurrentGroup());
        }
        slotReader.beginEmpty();
        this.inserting = true;
        this.providerCache = null;
        ensureWriter();
        SlotWriter slotWriter = this.writer;
        slotWriter.beginInsert();
        int currentGroup2 = slotWriter.getCurrentGroup();
        slotWriter.startGroup(key, Composer.INSTANCE.getEmpty());
        this.insertAnchor = slotWriter.anchor(currentGroup2);
        enterGroup(false, null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startReplaceableGroup(int key) {
        m2326startBaiHCIY(key, null, GroupKind.INSTANCE.m2342getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public Composer startRestartGroup(int key) {
        startReplaceGroup(key);
        addRecomposeScope();
        return this;
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableGroup(int key, Object dataKey) {
        if (!getInserting() && this.reader.getGroupKey() == key && !Intrinsics.areEqual(this.reader.getGroupAux(), dataKey) && this.reusingGroup < 0) {
            this.reusingGroup = this.reader.getCurrentGroup();
            this.reusing = true;
        }
        m2326startBaiHCIY(key, null, GroupKind.INSTANCE.m2342getGroupULZAiWs(), dataKey);
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableNode() {
        m2326startBaiHCIY(125, null, GroupKind.INSTANCE.m2344getReusableNodeULZAiWs(), null);
        this.nodeExpected = true;
    }

    public final void startReuseFromRoot() {
        this.reusingGroup = 100;
        this.reusing = true;
    }

    public final boolean tryImminentInvalidation$runtime(RecomposeScopeImpl scope, Object instance) {
        Anchor anchor = scope.getAnchor();
        if (anchor == null) {
            return false;
        }
        int indexFor = anchor.toIndexFor(this.reader.getTable());
        if (!this.isComposing || indexFor < this.reader.getCurrentGroup()) {
            return false;
        }
        ComposerImplKt.insertIfMissing(this.invalidations, indexFor, scope, instance);
        return true;
    }

    public final void updateCachedValue(Object value) {
        boolean z = value instanceof RememberObserver;
        Object obj = value;
        if (z) {
            RememberObserverHolder rememberObserverHolder = new RememberObserverHolder((RememberObserver) value, rememberObserverGroupIndex());
            if (getInserting()) {
                this.changeListWriter.remember(rememberObserverHolder);
            }
            this.abandonSet.add(value);
            obj = rememberObserverHolder;
        }
        updateValue(obj);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x009b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:32:0x009d A[LOOP:1: B:17:0x004b->B:32:0x009d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:40:0x00a0 A[EDGE_INSN: B:40:0x00a0->B:33:0x00a0 BREAK  A[LOOP:1: B:17:0x004b->B:32:0x009d], SYNTHETIC] */
    /* JADX INFO: renamed from: updateComposerInvalidations-RY85e9Y, reason: not valid java name */
    public final void m2329updateComposerInvalidationsRY85e9Y(MutableScatterMap<Object, Object> invalidationsRequested) {
        for (int lastIndex = CollectionsKt.getLastIndex(this.invalidations); -1 < lastIndex; lastIndex--) {
            Invalidation invalidation = this.invalidations.get(lastIndex);
            Anchor anchor = invalidation.getScope().getAnchor();
            if (anchor == null || !anchor.getValid()) {
                this.invalidations.remove(lastIndex);
            } else if (invalidation.getLocation() != anchor.getLocation()) {
                invalidation.setLocation(anchor.getLocation());
            }
        }
        Object[] objArr = ((ScatterMap) invalidationsRequested).keys;
        Object[] objArr2 = ((ScatterMap) invalidationsRequested).values;
        long[] jArr = ((ScatterMap) invalidationsRequested).metadata;
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
                            Object obj2 = objArr2[i4];
                            obj.getClass();
                            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj;
                            Anchor anchor2 = recomposeScopeImpl.getAnchor();
                            if (anchor2 != null) {
                                int location = anchor2.getLocation();
                                List<Invalidation> list = this.invalidations;
                                if (obj2 == ScopeInvalidated.INSTANCE) {
                                    obj2 = null;
                                }
                                list.add(new Invalidation(recomposeScopeImpl, location, obj2));
                            }
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
        CollectionsKt.sortWith(this.invalidations, ComposerImplKt.InvalidationLocationAscending);
    }

    @Override // androidx.compose.runtime.Composer
    public void updateRememberedValue(Object value) {
        updateCachedValue(value);
    }

    public final void updateValue(Object value) {
        if (getInserting()) {
            this.writer.update(value);
            return;
        }
        if (!this.reader.getHadNext()) {
            ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
            SlotReader slotReader = this.reader;
            composerChangeListWriter.appendValue(slotReader.anchor(slotReader.getParent()), value);
            return;
        }
        int groupSlotIndex = this.reader.getGroupSlotIndex() - 1;
        boolean pastParent = this.changeListWriter.getPastParent();
        ComposerChangeListWriter composerChangeListWriter2 = this.changeListWriter;
        if (!pastParent) {
            composerChangeListWriter2.updateValue(value, groupSlotIndex);
        } else {
            SlotReader slotReader2 = this.reader;
            composerChangeListWriter2.updateAnchoredValue(value, slotReader2.anchor(slotReader2.getParent()), groupSlotIndex);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void useNode() {
        validateNodeExpected();
        if (getInserting()) {
            ComposerKt.composeImmediateRuntimeError("useNode() called while inserting");
        }
        Object node = getNode(this.reader);
        this.changeListWriter.moveDown(node);
        if (this.reusing && (node instanceof ComposeNodeLifecycleCallback)) {
            this.changeListWriter.useNode(node);
        }
    }

    public final void verifyConsistent$runtime() {
        this.insertTable.verifyWellFormed();
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionImpl getComposition() {
        return this.composition;
    }

    private final void startGroup(int key, Object dataKey) {
        m2326startBaiHCIY(key, dataKey, GroupKind.INSTANCE.m2342getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(char value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Character) && value == ((Character) objNextSlot).charValue()) {
            return false;
        }
        updateValue(Character.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(byte value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Byte) && value == ((Number) objNextSlot).byteValue()) {
            return false;
        }
        updateValue(Byte.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(short value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Short) && value == ((Number) objNextSlot).shortValue()) {
            return false;
        }
        updateValue(Short.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(boolean value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Boolean) && value == ((Boolean) objNextSlot).booleanValue()) {
            return false;
        }
        updateValue(Boolean.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(Object value) {
        if (Intrinsics.areEqual(nextSlot(), value)) {
            return false;
        }
        updateValue(value);
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(long value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Long) && value == ((Number) objNextSlot).longValue()) {
            return false;
        }
        updateValue(Long.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(double value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Double) && value == ((Number) objNextSlot).doubleValue()) {
            return false;
        }
        updateValue(Double.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(int value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Integer) && value == ((Number) objNextSlot).intValue()) {
            return false;
        }
        updateValue(Integer.valueOf(value));
        return true;
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope() {
        PersistentCompositionLocalMap persistentCompositionLocalMap = this.providerCache;
        return persistentCompositionLocalMap != null ? persistentCompositionLocalMap : currentCompositionLocalScope(this.reader.getParent());
    }
}
