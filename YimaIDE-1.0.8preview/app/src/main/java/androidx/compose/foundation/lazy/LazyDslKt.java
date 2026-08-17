package androidx.compose.foundation.lazy;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.OverscrollKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import com.intellij.util.io.IOUtil;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a©\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042%\b\n\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u00062%\b\n\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000623\b\u0004\u0010\f\u001a-\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0011\u001a\u0082\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042%\b\n\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u000623\b\u0004\u0010\f\u001a-\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0002\u0010\u0012\u001aè\u0001\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042:\b\n\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\r2:\b\u0006\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\r2H\b\u0004\u0010\f\u001aB\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0017\u001a¬\u0001\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042:\b\n\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\r2H\b\u0004\u0010\f\u001aB\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0002\u0010\u0018\u001a©\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00192%\b\n\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u00062%\b\n\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000623\b\u0004\u0010\f\u001a-\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u001a\u001a\u0082\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00192%\b\n\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u000623\b\u0004\u0010\f\u001a-\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0002\u0010\u001b\u001aè\u0001\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00192:\b\n\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\r2:\b\u0006\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\r2H\b\u0004\u0010\f\u001aB\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u001c\u001a¬\u0001\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00192:\b\n\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\r2H\b\u0004\u0010\f\u001aB\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0002\u0010\u001d\u001a\u0082\u0001\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020&2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u00101\u001a\u0082\u0001\u00102\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020&2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u00107\u001av\u00102\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020&2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u00108\u001al\u00102\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010+\u001a\u00020,2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u00109\u001av\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020&2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010:\u001al\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010;¨\u0006<"}, d2 = {"items", "", "T", "Landroidx/compose/foundation/lazy/LazyListScope;", "", "key", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "item", "", "contentType", "itemContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "itemsIndexed", "", "index", "Lkotlin/Function3;", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "LazyRow", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/LazyListState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "content", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/OverscrollEffect;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LazyColumn", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/OverscrollEffect;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyDslKt {
    /* JADX WARN: Code duplicated, block: B:100:0x010d  */
    /* JADX WARN: Code duplicated, block: B:103:0x0117  */
    /* JADX WARN: Code duplicated, block: B:105:0x011d  */
    /* JADX WARN: Code duplicated, block: B:106:0x0120  */
    /* JADX WARN: Code duplicated, block: B:110:0x0133  */
    /* JADX WARN: Code duplicated, block: B:111:0x0136  */
    /* JADX WARN: Code duplicated, block: B:114:0x013f  */
    /* JADX WARN: Code duplicated, block: B:116:0x014f  */
    /* JADX WARN: Code duplicated, block: B:132:0x0185 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:133:0x0187  */
    /* JADX WARN: Code duplicated, block: B:134:0x018a  */
    /* JADX WARN: Code duplicated, block: B:137:0x0190  */
    /* JADX WARN: Code duplicated, block: B:139:0x0199  */
    /* JADX WARN: Code duplicated, block: B:140:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:142:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:145:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:147:0x01af  */
    /* JADX WARN: Code duplicated, block: B:148:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:150:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:152:0x01be  */
    /* JADX WARN: Code duplicated, block: B:153:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:156:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:157:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:160:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:163:0x01de  */
    /* JADX WARN: Code duplicated, block: B:165:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:168:0x0203  */
    /* JADX WARN: Code duplicated, block: B:171:0x024c  */
    /* JADX WARN: Code duplicated, block: B:173:0x025f  */
    /* JADX WARN: Code duplicated, block: B:176:0x0276  */
    /* JADX WARN: Code duplicated, block: B:178:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0084  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x008f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0095  */
    /* JADX WARN: Code duplicated, block: B:59:0x009d  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:66:0x00af  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:78:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:83:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:88:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:94:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:97:0x0106 A[ADDED_TO_REGION] */
    public static final void LazyColumn(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, boolean z2, OverscrollEffect overscrollEffect, final Function1<? super LazyListScope, Unit> function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListStateRememberLazyListState;
        PaddingValues paddingValues2;
        int i4;
        boolean z3;
        int i5;
        Arrangement.Vertical vertical2;
        int i6;
        Alignment.Horizontal horizontal2;
        int i7;
        FlingBehavior flingBehavior2;
        int i8;
        int i9;
        boolean z4;
        Composer composer2;
        final Modifier modifier2;
        final boolean z5;
        final LazyListState lazyListState2;
        final PaddingValues paddingValues3;
        final boolean z6;
        final Arrangement.Vertical vertical3;
        final Alignment.Horizontal horizontal3;
        final FlingBehavior flingBehavior3;
        final OverscrollEffect overscrollEffect2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        PaddingValues paddingValuesM923PaddingValues0680j_4;
        Arrangement.Vertical bottom;
        Alignment.Horizontal start;
        FlingBehavior flingBehavior4;
        OverscrollEffect overscrollEffectRememberOverscrollEffect;
        LazyListState lazyListState3;
        PaddingValues paddingValues4;
        Arrangement.Vertical vertical4;
        Alignment.Horizontal horizontal4;
        boolean z7;
        boolean z8;
        int i10;
        FlingBehavior flingBehavior5;
        Arrangement arrangement;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(53695811);
        int i12 = i2 & 1;
        if (i12 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListStateRememberLazyListState = lazyListState;
                int i13 = composerStartRestartGroup.changed(lazyListStateRememberLazyListState) ? 32 : 16;
                i3 |= i13;
            } else {
                lazyListStateRememberLazyListState = lazyListState;
            }
            i3 |= i13;
        } else {
            lazyListStateRememberLazyListState = lazyListState;
        }
        int i14 = i2 & 4;
        if (i14 == 0) {
            if ((i & 384) == 0) {
                paddingValues2 = paddingValues;
                i3 |= composerStartRestartGroup.changed(paddingValues2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z3 = z;
                    if (composerStartRestartGroup.changed(z3)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        vertical2 = vertical;
                        int i15 = composerStartRestartGroup.changed(vertical2) ? 16384 : 8192;
                        i3 |= i15;
                    } else {
                        vertical2 = vertical;
                    }
                    i3 |= i15;
                } else {
                    vertical2 = vertical;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        horizontal2 = horizontal;
                        if (composerStartRestartGroup.changed(horizontal2)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            int i16 = composerStartRestartGroup.changed(flingBehavior2) ? IOUtil.MiB : 524288;
                            i3 |= i16;
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i8 = i2 & 128;
                    if (i8 != 0) {
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(z2)) {
                                i9 = 8388608;
                            } else {
                                i9 = 4194304;
                            }
                            i3 |= i9;
                        }
                        if ((i & 100663296) != 0) {
                            i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                        }
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function1)) {
                                i11 = 536870912;
                            } else {
                                i11 = 268435456;
                            }
                            i3 |= i11;
                        }
                        if ((i3 & 306783379) != 306783378) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i12 != 0) {
                                    modifier3 = Modifier.Companion;
                                } else {
                                    modifier3 = modifier;
                                }
                                if ((i2 & 2) != 0) {
                                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                    i3 &= -113;
                                }
                                if (i14 != 0) {
                                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                                } else {
                                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i4 != 0) {
                                    z3 = false;
                                }
                                if ((i2 & 16) != 0) {
                                    arrangement = Arrangement.INSTANCE;
                                    if (z3) {
                                        bottom = arrangement.getBottom();
                                    } else {
                                        bottom = arrangement.getTop();
                                    }
                                    i3 &= -57345;
                                } else {
                                    bottom = vertical2;
                                }
                                if (i6 != 0) {
                                    start = Alignment.Companion.getStart();
                                } else {
                                    start = horizontal2;
                                }
                                if ((i2 & 64) != 0) {
                                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i3 &= -3670017;
                                } else {
                                    flingBehavior4 = flingBehavior2;
                                }
                                boolean z9 = i8 == 0 ? z2 : true;
                                if ((i2 & 256) != 0) {
                                    i3 &= -234881025;
                                    overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                                } else {
                                    overscrollEffectRememberOverscrollEffect = overscrollEffect;
                                }
                                lazyListState3 = lazyListStateRememberLazyListState;
                                paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                                vertical4 = bottom;
                                horizontal4 = start;
                                z7 = z3;
                                z8 = z9;
                                i10 = 53695811;
                                flingBehavior5 = flingBehavior4;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 2) != 0) {
                                    i3 &= -113;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                }
                                if ((i2 & 256) != 0) {
                                    i3 &= -234881025;
                                }
                                modifier3 = modifier;
                                z8 = z2;
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                                z7 = z3;
                                vertical4 = vertical2;
                                horizontal4 = horizontal2;
                                flingBehavior5 = flingBehavior2;
                                i10 = 53695811;
                                lazyListState3 = lazyListStateRememberLazyListState;
                                paddingValues4 = paddingValues2;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                            }
                            int i17 = i3 >> 3;
                            composer2 = composerStartRestartGroup;
                            LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i17) | (3670016 & i17) | (i17 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier3;
                            lazyListState2 = lazyListState3;
                            paddingValues3 = paddingValues4;
                            z6 = z7;
                            flingBehavior3 = flingBehavior5;
                            z5 = z8;
                            overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                            horizontal3 = horizontal4;
                            vertical3 = vertical4;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            z5 = z2;
                            lazyListState2 = lazyListStateRememberLazyListState;
                            paddingValues3 = paddingValues2;
                            z6 = z3;
                            vertical3 = vertical2;
                            horizontal3 = horizontal2;
                            flingBehavior3 = flingBehavior2;
                            overscrollEffect2 = overscrollEffect;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                                public final Object invoke(Object obj, Object obj2) {
                                    return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 12582912;
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                    }
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = bottom;
                            horizontal4 = start;
                            z7 = z3;
                            z8 = z9;
                            i10 = 53695811;
                            flingBehavior5 = flingBehavior4;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = bottom;
                            horizontal4 = start;
                            z7 = z3;
                            z8 = z9;
                            i10 = 53695811;
                            flingBehavior5 = flingBehavior4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                        }
                        int i18 = i3 >> 3;
                        composer2 = composerStartRestartGroup;
                        LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i18) | (3670016 & i18) | (i18 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        lazyListState2 = lazyListState3;
                        paddingValues3 = paddingValues4;
                        z6 = z7;
                        flingBehavior3 = flingBehavior5;
                        z5 = z8;
                        overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                        horizontal3 = horizontal4;
                        vertical3 = vertical4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        vertical3 = vertical2;
                        horizontal3 = horizontal2;
                        flingBehavior3 = flingBehavior2;
                        overscrollEffect2 = overscrollEffect;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 196608;
                horizontal2 = horizontal;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                    }
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = bottom;
                            horizontal4 = start;
                            z7 = z3;
                            z8 = z9;
                            i10 = 53695811;
                            flingBehavior5 = flingBehavior4;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = bottom;
                            horizontal4 = start;
                            z7 = z3;
                            z8 = z9;
                            i10 = 53695811;
                            flingBehavior5 = flingBehavior4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                        }
                        int i19 = i3 >> 3;
                        composer2 = composerStartRestartGroup;
                        LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i19) | (3670016 & i19) | (i19 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        lazyListState2 = lazyListState3;
                        paddingValues3 = paddingValues4;
                        z6 = z7;
                        flingBehavior3 = flingBehavior5;
                        z5 = z8;
                        overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                        horizontal3 = horizontal4;
                        vertical3 = vertical4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        vertical3 = vertical2;
                        horizontal3 = horizontal2;
                        flingBehavior3 = flingBehavior2;
                        overscrollEffect2 = overscrollEffect;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                    }
                    int i110 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i110) | (3670016 & i110) | (i110 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    horizontal3 = horizontal4;
                    vertical3 = vertical4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z3 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    vertical2 = vertical;
                    if (composerStartRestartGroup.changed(vertical2)) {
                    }
                    i3 |= i15;
                } else {
                    vertical2 = vertical;
                }
                i3 |= i15;
            } else {
                vertical2 = vertical;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    horizontal2 = horizontal;
                    if (composerStartRestartGroup.changed(horizontal2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                    }
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = bottom;
                            horizontal4 = start;
                            z7 = z3;
                            z8 = z9;
                            i10 = 53695811;
                            flingBehavior5 = flingBehavior4;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = bottom;
                            horizontal4 = start;
                            z7 = z3;
                            z8 = z9;
                            i10 = 53695811;
                            flingBehavior5 = flingBehavior4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                        }
                        int i111 = i3 >> 3;
                        composer2 = composerStartRestartGroup;
                        LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i111) | (3670016 & i111) | (i111 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        lazyListState2 = lazyListState3;
                        paddingValues3 = paddingValues4;
                        z6 = z7;
                        flingBehavior3 = flingBehavior5;
                        z5 = z8;
                        overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                        horizontal3 = horizontal4;
                        vertical3 = vertical4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        vertical3 = vertical2;
                        horizontal3 = horizontal2;
                        flingBehavior3 = flingBehavior2;
                        overscrollEffect2 = overscrollEffect;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                    }
                    int i112 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i112) | (3670016 & i112) | (i112 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    horizontal3 = horizontal4;
                    vertical3 = vertical4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            horizontal2 = horizontal;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                    }
                    int i113 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i113) | (3670016 & i113) | (i113 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    horizontal3 = horizontal4;
                    vertical3 = vertical4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
            }
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i3 |= i11;
            }
            if ((i3 & 306783379) != 306783378) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical4 = bottom;
                    horizontal4 = start;
                    z7 = z3;
                    z8 = z9;
                    i10 = 53695811;
                    flingBehavior5 = flingBehavior4;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical4 = bottom;
                    horizontal4 = start;
                    z7 = z3;
                    z8 = z9;
                    i10 = 53695811;
                    flingBehavior5 = flingBehavior4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                }
                int i114 = i3 >> 3;
                composer2 = composerStartRestartGroup;
                LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i114) | (3670016 & i114) | (i114 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z6 = z7;
                flingBehavior3 = flingBehavior5;
                z5 = z8;
                overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                horizontal3 = horizontal4;
                vertical3 = vertical4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                vertical3 = vertical2;
                horizontal3 = horizontal2;
                flingBehavior3 = flingBehavior2;
                overscrollEffect2 = overscrollEffect;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 384;
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z3 = z;
                if (composerStartRestartGroup.changed(z3)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    vertical2 = vertical;
                    if (composerStartRestartGroup.changed(vertical2)) {
                    }
                    i3 |= i15;
                } else {
                    vertical2 = vertical;
                }
                i3 |= i15;
            } else {
                vertical2 = vertical;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    horizontal2 = horizontal;
                    if (composerStartRestartGroup.changed(horizontal2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                    }
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = bottom;
                            horizontal4 = start;
                            z7 = z3;
                            z8 = z9;
                            i10 = 53695811;
                            flingBehavior5 = flingBehavior4;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = bottom;
                            horizontal4 = start;
                            z7 = z3;
                            z8 = z9;
                            i10 = 53695811;
                            flingBehavior5 = flingBehavior4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                        }
                        int i115 = i3 >> 3;
                        composer2 = composerStartRestartGroup;
                        LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i115) | (3670016 & i115) | (i115 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        lazyListState2 = lazyListState3;
                        paddingValues3 = paddingValues4;
                        z6 = z7;
                        flingBehavior3 = flingBehavior5;
                        z5 = z8;
                        overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                        horizontal3 = horizontal4;
                        vertical3 = vertical4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        vertical3 = vertical2;
                        horizontal3 = horizontal2;
                        flingBehavior3 = flingBehavior2;
                        overscrollEffect2 = overscrollEffect;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                    }
                    int i116 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i116) | (3670016 & i116) | (i116 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    horizontal3 = horizontal4;
                    vertical3 = vertical4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            horizontal2 = horizontal;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                    }
                    int i117 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i117) | (3670016 & i117) | (i117 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    horizontal3 = horizontal4;
                    vertical3 = vertical4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
            }
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i3 |= i11;
            }
            if ((i3 & 306783379) != 306783378) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical4 = bottom;
                    horizontal4 = start;
                    z7 = z3;
                    z8 = z9;
                    i10 = 53695811;
                    flingBehavior5 = flingBehavior4;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical4 = bottom;
                    horizontal4 = start;
                    z7 = z3;
                    z8 = z9;
                    i10 = 53695811;
                    flingBehavior5 = flingBehavior4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                }
                int i118 = i3 >> 3;
                composer2 = composerStartRestartGroup;
                LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i118) | (3670016 & i118) | (i118 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z6 = z7;
                flingBehavior3 = flingBehavior5;
                z5 = z8;
                overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                horizontal3 = horizontal4;
                vertical3 = vertical4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                vertical3 = vertical2;
                horizontal3 = horizontal2;
                flingBehavior3 = flingBehavior2;
                overscrollEffect2 = overscrollEffect;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z3 = z;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                vertical2 = vertical;
                if (composerStartRestartGroup.changed(vertical2)) {
                }
                i3 |= i15;
            } else {
                vertical2 = vertical;
            }
            i3 |= i15;
        } else {
            vertical2 = vertical;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                horizontal2 = horizontal;
                if (composerStartRestartGroup.changed(horizontal2)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = bottom;
                        horizontal4 = start;
                        z7 = z3;
                        z8 = z9;
                        i10 = 53695811;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                    }
                    int i119 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i119) | (3670016 & i119) | (i119 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    horizontal3 = horizontal4;
                    vertical3 = vertical4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
            }
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i3 |= i11;
            }
            if ((i3 & 306783379) != 306783378) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical4 = bottom;
                    horizontal4 = start;
                    z7 = z3;
                    z8 = z9;
                    i10 = 53695811;
                    flingBehavior5 = flingBehavior4;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical4 = bottom;
                    horizontal4 = start;
                    z7 = z3;
                    z8 = z9;
                    i10 = 53695811;
                    flingBehavior5 = flingBehavior4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                }
                int i1110 = i3 >> 3;
                composer2 = composerStartRestartGroup;
                LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i1110) | (3670016 & i1110) | (i1110 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z6 = z7;
                flingBehavior3 = flingBehavior5;
                z5 = z8;
                overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                horizontal3 = horizontal4;
                vertical3 = vertical4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                vertical3 = vertical2;
                horizontal3 = horizontal2;
                flingBehavior3 = flingBehavior2;
                overscrollEffect2 = overscrollEffect;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 196608;
        horizontal2 = horizontal;
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                flingBehavior2 = flingBehavior;
                if (composerStartRestartGroup.changed(flingBehavior2)) {
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i3 |= i16;
        } else {
            flingBehavior2 = flingBehavior;
        }
        i8 = i2 & 128;
        if (i8 != 0) {
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i9 = 8388608;
                } else {
                    i9 = 4194304;
                }
                i3 |= i9;
            }
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
            }
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i3 |= i11;
            }
            if ((i3 & 306783379) != 306783378) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical4 = bottom;
                    horizontal4 = start;
                    z7 = z3;
                    z8 = z9;
                    i10 = 53695811;
                    flingBehavior5 = flingBehavior4;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical4 = bottom;
                    horizontal4 = start;
                    z7 = z3;
                    z8 = z9;
                    i10 = 53695811;
                    flingBehavior5 = flingBehavior4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                }
                int i1111 = i3 >> 3;
                composer2 = composerStartRestartGroup;
                LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i1111) | (3670016 & i1111) | (i1111 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z6 = z7;
                flingBehavior3 = flingBehavior5;
                z5 = z8;
                overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                horizontal3 = horizontal4;
                vertical3 = vertical4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                vertical3 = vertical2;
                horizontal3 = horizontal2;
                flingBehavior3 = flingBehavior2;
                overscrollEffect2 = overscrollEffect;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 12582912;
        if ((i & 100663296) != 0) {
            i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
        }
        if ((i & 805306368) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i11 = 536870912;
            } else {
                i11 = 268435456;
            }
            i3 |= i11;
        }
        if ((i3 & 306783379) != 306783378) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i14 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z3 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z3) {
                        bottom = arrangement.getTop();
                    } else {
                        bottom = arrangement.getBottom();
                    }
                    i3 &= -57345;
                } else {
                    bottom = vertical2;
                }
                if (i6 != 0) {
                    start = Alignment.Companion.getStart();
                } else {
                    start = horizontal2;
                }
                if ((i2 & 64) != 0) {
                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    flingBehavior4 = flingBehavior2;
                }
                if (i8 == 0) {
                }
                if ((i2 & 256) != 0) {
                    i3 &= -234881025;
                    overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                } else {
                    overscrollEffectRememberOverscrollEffect = overscrollEffect;
                }
                lazyListState3 = lazyListStateRememberLazyListState;
                paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                vertical4 = bottom;
                horizontal4 = start;
                z7 = z3;
                z8 = z9;
                i10 = 53695811;
                flingBehavior5 = flingBehavior4;
            } else {
                if (i12 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i14 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z3 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z3) {
                        bottom = arrangement.getTop();
                    } else {
                        bottom = arrangement.getBottom();
                    }
                    i3 &= -57345;
                } else {
                    bottom = vertical2;
                }
                if (i6 != 0) {
                    start = Alignment.Companion.getStart();
                } else {
                    start = horizontal2;
                }
                if ((i2 & 64) != 0) {
                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    flingBehavior4 = flingBehavior2;
                }
                if (i8 == 0) {
                }
                if ((i2 & 256) != 0) {
                    i3 &= -234881025;
                    overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                } else {
                    overscrollEffectRememberOverscrollEffect = overscrollEffect;
                }
                lazyListState3 = lazyListStateRememberLazyListState;
                paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                vertical4 = bottom;
                horizontal4 = start;
                z7 = z3;
                z8 = z9;
                i10 = 53695811;
                flingBehavior5 = flingBehavior4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
            }
            int i1112 = i3 >> 3;
            composer2 = composerStartRestartGroup;
            LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, true, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, horizontal4, vertical4, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i1112) | (3670016 & i1112) | (i1112 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            lazyListState2 = lazyListState3;
            paddingValues3 = paddingValues4;
            z6 = z7;
            flingBehavior3 = flingBehavior5;
            z5 = z8;
            overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
            horizontal3 = horizontal4;
            vertical3 = vertical4;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier2 = modifier;
            z5 = z2;
            lazyListState2 = lazyListStateRememberLazyListState;
            paddingValues3 = paddingValues2;
            z6 = z3;
            vertical3 = vertical2;
            horizontal3 = horizontal2;
            flingBehavior3 = flingBehavior2;
            overscrollEffect2 = overscrollEffect;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oq8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyDslKt.b(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x010d  */
    /* JADX WARN: Code duplicated, block: B:103:0x0117  */
    /* JADX WARN: Code duplicated, block: B:105:0x011d  */
    /* JADX WARN: Code duplicated, block: B:106:0x0120  */
    /* JADX WARN: Code duplicated, block: B:110:0x0133  */
    /* JADX WARN: Code duplicated, block: B:111:0x0136  */
    /* JADX WARN: Code duplicated, block: B:114:0x013f  */
    /* JADX WARN: Code duplicated, block: B:116:0x014f  */
    /* JADX WARN: Code duplicated, block: B:132:0x0185 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:133:0x0187  */
    /* JADX WARN: Code duplicated, block: B:134:0x018a  */
    /* JADX WARN: Code duplicated, block: B:137:0x0190  */
    /* JADX WARN: Code duplicated, block: B:139:0x0199  */
    /* JADX WARN: Code duplicated, block: B:140:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:142:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:145:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:147:0x01af  */
    /* JADX WARN: Code duplicated, block: B:148:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:150:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:152:0x01be  */
    /* JADX WARN: Code duplicated, block: B:153:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:156:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:157:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:160:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:163:0x01de  */
    /* JADX WARN: Code duplicated, block: B:165:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:168:0x0203  */
    /* JADX WARN: Code duplicated, block: B:171:0x024b  */
    /* JADX WARN: Code duplicated, block: B:173:0x025e  */
    /* JADX WARN: Code duplicated, block: B:176:0x0275  */
    /* JADX WARN: Code duplicated, block: B:178:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0084  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x008f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0095  */
    /* JADX WARN: Code duplicated, block: B:59:0x009d  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:66:0x00af  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:78:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:83:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:88:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:94:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:97:0x0106 A[ADDED_TO_REGION] */
    public static final void LazyRow(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, boolean z2, OverscrollEffect overscrollEffect, final Function1<? super LazyListScope, Unit> function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListStateRememberLazyListState;
        PaddingValues paddingValues2;
        int i4;
        boolean z3;
        int i5;
        Arrangement.Horizontal horizontal2;
        int i6;
        Alignment.Vertical vertical2;
        int i7;
        FlingBehavior flingBehavior2;
        int i8;
        int i9;
        boolean z4;
        Composer composer2;
        final Modifier modifier2;
        final boolean z5;
        final LazyListState lazyListState2;
        final PaddingValues paddingValues3;
        final boolean z6;
        final Arrangement.Horizontal horizontal3;
        final Alignment.Vertical vertical3;
        final FlingBehavior flingBehavior3;
        final OverscrollEffect overscrollEffect2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        PaddingValues paddingValuesM923PaddingValues0680j_4;
        Arrangement.Horizontal end;
        Alignment.Vertical top;
        FlingBehavior flingBehavior4;
        OverscrollEffect overscrollEffectRememberOverscrollEffect;
        LazyListState lazyListState3;
        PaddingValues paddingValues4;
        Arrangement.Horizontal horizontal4;
        Alignment.Vertical vertical4;
        boolean z7;
        boolean z8;
        int i10;
        FlingBehavior flingBehavior5;
        Arrangement arrangement;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1884325601);
        int i12 = i2 & 1;
        if (i12 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListStateRememberLazyListState = lazyListState;
                int i13 = composerStartRestartGroup.changed(lazyListStateRememberLazyListState) ? 32 : 16;
                i3 |= i13;
            } else {
                lazyListStateRememberLazyListState = lazyListState;
            }
            i3 |= i13;
        } else {
            lazyListStateRememberLazyListState = lazyListState;
        }
        int i14 = i2 & 4;
        if (i14 == 0) {
            if ((i & 384) == 0) {
                paddingValues2 = paddingValues;
                i3 |= composerStartRestartGroup.changed(paddingValues2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z3 = z;
                    if (composerStartRestartGroup.changed(z3)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        horizontal2 = horizontal;
                        int i15 = composerStartRestartGroup.changed(horizontal2) ? 16384 : 8192;
                        i3 |= i15;
                    } else {
                        horizontal2 = horizontal;
                    }
                    i3 |= i15;
                } else {
                    horizontal2 = horizontal;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        vertical2 = vertical;
                        if (composerStartRestartGroup.changed(vertical2)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            int i16 = composerStartRestartGroup.changed(flingBehavior2) ? IOUtil.MiB : 524288;
                            i3 |= i16;
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i8 = i2 & 128;
                    if (i8 != 0) {
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(z2)) {
                                i9 = 8388608;
                            } else {
                                i9 = 4194304;
                            }
                            i3 |= i9;
                        }
                        if ((i & 100663296) != 0) {
                            i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                        }
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function1)) {
                                i11 = 536870912;
                            } else {
                                i11 = 268435456;
                            }
                            i3 |= i11;
                        }
                        if ((i3 & 306783379) != 306783378) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i12 != 0) {
                                    modifier3 = Modifier.Companion;
                                } else {
                                    modifier3 = modifier;
                                }
                                if ((i2 & 2) != 0) {
                                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                    i3 &= -113;
                                }
                                if (i14 != 0) {
                                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                                } else {
                                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i4 != 0) {
                                    z3 = false;
                                }
                                if ((i2 & 16) != 0) {
                                    arrangement = Arrangement.INSTANCE;
                                    if (z3) {
                                        end = arrangement.getEnd();
                                    } else {
                                        end = arrangement.getStart();
                                    }
                                    i3 &= -57345;
                                } else {
                                    end = horizontal2;
                                }
                                if (i6 != 0) {
                                    top = Alignment.Companion.getTop();
                                } else {
                                    top = vertical2;
                                }
                                if ((i2 & 64) != 0) {
                                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i3 &= -3670017;
                                } else {
                                    flingBehavior4 = flingBehavior2;
                                }
                                boolean z9 = i8 == 0 ? z2 : true;
                                if ((i2 & 256) != 0) {
                                    i3 &= -234881025;
                                    overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                                } else {
                                    overscrollEffectRememberOverscrollEffect = overscrollEffect;
                                }
                                lazyListState3 = lazyListStateRememberLazyListState;
                                paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                                horizontal4 = end;
                                vertical4 = top;
                                z7 = z3;
                                z8 = z9;
                                i10 = -1884325601;
                                flingBehavior5 = flingBehavior4;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 2) != 0) {
                                    i3 &= -113;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                }
                                if ((i2 & 256) != 0) {
                                    i3 &= -234881025;
                                }
                                modifier3 = modifier;
                                z8 = z2;
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                                z7 = z3;
                                horizontal4 = horizontal2;
                                vertical4 = vertical2;
                                flingBehavior5 = flingBehavior2;
                                i10 = -1884325601;
                                lazyListState3 = lazyListStateRememberLazyListState;
                                paddingValues4 = paddingValues2;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                            }
                            int i17 = i3 >> 3;
                            composer2 = composerStartRestartGroup;
                            LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i17) | (3670016 & i17) | (i17 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier3;
                            lazyListState2 = lazyListState3;
                            paddingValues3 = paddingValues4;
                            z6 = z7;
                            flingBehavior3 = flingBehavior5;
                            z5 = z8;
                            overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                            vertical3 = vertical4;
                            horizontal3 = horizontal4;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            z5 = z2;
                            lazyListState2 = lazyListStateRememberLazyListState;
                            paddingValues3 = paddingValues2;
                            z6 = z3;
                            horizontal3 = horizontal2;
                            vertical3 = vertical2;
                            flingBehavior3 = flingBehavior2;
                            overscrollEffect2 = overscrollEffect;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                                public final Object invoke(Object obj, Object obj2) {
                                    return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 12582912;
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                    }
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = end;
                            vertical4 = top;
                            z7 = z3;
                            z8 = z9;
                            i10 = -1884325601;
                            flingBehavior5 = flingBehavior4;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = end;
                            vertical4 = top;
                            z7 = z3;
                            z8 = z9;
                            i10 = -1884325601;
                            flingBehavior5 = flingBehavior4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                        }
                        int i18 = i3 >> 3;
                        composer2 = composerStartRestartGroup;
                        LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i18) | (3670016 & i18) | (i18 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        lazyListState2 = lazyListState3;
                        paddingValues3 = paddingValues4;
                        z6 = z7;
                        flingBehavior3 = flingBehavior5;
                        z5 = z8;
                        overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                        vertical3 = vertical4;
                        horizontal3 = horizontal4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        horizontal3 = horizontal2;
                        vertical3 = vertical2;
                        flingBehavior3 = flingBehavior2;
                        overscrollEffect2 = overscrollEffect;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 196608;
                vertical2 = vertical;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                    }
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = end;
                            vertical4 = top;
                            z7 = z3;
                            z8 = z9;
                            i10 = -1884325601;
                            flingBehavior5 = flingBehavior4;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = end;
                            vertical4 = top;
                            z7 = z3;
                            z8 = z9;
                            i10 = -1884325601;
                            flingBehavior5 = flingBehavior4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                        }
                        int i19 = i3 >> 3;
                        composer2 = composerStartRestartGroup;
                        LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i19) | (3670016 & i19) | (i19 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        lazyListState2 = lazyListState3;
                        paddingValues3 = paddingValues4;
                        z6 = z7;
                        flingBehavior3 = flingBehavior5;
                        z5 = z8;
                        overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                        vertical3 = vertical4;
                        horizontal3 = horizontal4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        horizontal3 = horizontal2;
                        vertical3 = vertical2;
                        flingBehavior3 = flingBehavior2;
                        overscrollEffect2 = overscrollEffect;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                    }
                    int i110 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i110) | (3670016 & i110) | (i110 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    vertical3 = vertical4;
                    horizontal3 = horizontal4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z3 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    horizontal2 = horizontal;
                    if (composerStartRestartGroup.changed(horizontal2)) {
                    }
                    i3 |= i15;
                } else {
                    horizontal2 = horizontal;
                }
                i3 |= i15;
            } else {
                horizontal2 = horizontal;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    vertical2 = vertical;
                    if (composerStartRestartGroup.changed(vertical2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                    }
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = end;
                            vertical4 = top;
                            z7 = z3;
                            z8 = z9;
                            i10 = -1884325601;
                            flingBehavior5 = flingBehavior4;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = end;
                            vertical4 = top;
                            z7 = z3;
                            z8 = z9;
                            i10 = -1884325601;
                            flingBehavior5 = flingBehavior4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                        }
                        int i111 = i3 >> 3;
                        composer2 = composerStartRestartGroup;
                        LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i111) | (3670016 & i111) | (i111 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        lazyListState2 = lazyListState3;
                        paddingValues3 = paddingValues4;
                        z6 = z7;
                        flingBehavior3 = flingBehavior5;
                        z5 = z8;
                        overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                        vertical3 = vertical4;
                        horizontal3 = horizontal4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        horizontal3 = horizontal2;
                        vertical3 = vertical2;
                        flingBehavior3 = flingBehavior2;
                        overscrollEffect2 = overscrollEffect;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                    }
                    int i112 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i112) | (3670016 & i112) | (i112 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    vertical3 = vertical4;
                    horizontal3 = horizontal4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            vertical2 = vertical;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                    }
                    int i113 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i113) | (3670016 & i113) | (i113 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    vertical3 = vertical4;
                    horizontal3 = horizontal4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
            }
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i3 |= i11;
            }
            if ((i3 & 306783379) != 306783378) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal4 = end;
                    vertical4 = top;
                    z7 = z3;
                    z8 = z9;
                    i10 = -1884325601;
                    flingBehavior5 = flingBehavior4;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal4 = end;
                    vertical4 = top;
                    z7 = z3;
                    z8 = z9;
                    i10 = -1884325601;
                    flingBehavior5 = flingBehavior4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                }
                int i114 = i3 >> 3;
                composer2 = composerStartRestartGroup;
                LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i114) | (3670016 & i114) | (i114 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z6 = z7;
                flingBehavior3 = flingBehavior5;
                z5 = z8;
                overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                vertical3 = vertical4;
                horizontal3 = horizontal4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                horizontal3 = horizontal2;
                vertical3 = vertical2;
                flingBehavior3 = flingBehavior2;
                overscrollEffect2 = overscrollEffect;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 384;
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z3 = z;
                if (composerStartRestartGroup.changed(z3)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    horizontal2 = horizontal;
                    if (composerStartRestartGroup.changed(horizontal2)) {
                    }
                    i3 |= i15;
                } else {
                    horizontal2 = horizontal;
                }
                i3 |= i15;
            } else {
                horizontal2 = horizontal;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    vertical2 = vertical;
                    if (composerStartRestartGroup.changed(vertical2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                    }
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = end;
                            vertical4 = top;
                            z7 = z3;
                            z8 = z9;
                            i10 = -1884325601;
                            flingBehavior5 = flingBehavior4;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 == 0) {
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                            } else {
                                overscrollEffectRememberOverscrollEffect = overscrollEffect;
                            }
                            lazyListState3 = lazyListStateRememberLazyListState;
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = end;
                            vertical4 = top;
                            z7 = z3;
                            z8 = z9;
                            i10 = -1884325601;
                            flingBehavior5 = flingBehavior4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                        }
                        int i115 = i3 >> 3;
                        composer2 = composerStartRestartGroup;
                        LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i115) | (3670016 & i115) | (i115 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        lazyListState2 = lazyListState3;
                        paddingValues3 = paddingValues4;
                        z6 = z7;
                        flingBehavior3 = flingBehavior5;
                        z5 = z8;
                        overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                        vertical3 = vertical4;
                        horizontal3 = horizontal4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        horizontal3 = horizontal2;
                        vertical3 = vertical2;
                        flingBehavior3 = flingBehavior2;
                        overscrollEffect2 = overscrollEffect;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                    }
                    int i116 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i116) | (3670016 & i116) | (i116 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    vertical3 = vertical4;
                    horizontal3 = horizontal4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            vertical2 = vertical;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                    }
                    int i117 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i117) | (3670016 & i117) | (i117 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    vertical3 = vertical4;
                    horizontal3 = horizontal4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
            }
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i3 |= i11;
            }
            if ((i3 & 306783379) != 306783378) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal4 = end;
                    vertical4 = top;
                    z7 = z3;
                    z8 = z9;
                    i10 = -1884325601;
                    flingBehavior5 = flingBehavior4;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal4 = end;
                    vertical4 = top;
                    z7 = z3;
                    z8 = z9;
                    i10 = -1884325601;
                    flingBehavior5 = flingBehavior4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                }
                int i118 = i3 >> 3;
                composer2 = composerStartRestartGroup;
                LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i118) | (3670016 & i118) | (i118 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z6 = z7;
                flingBehavior3 = flingBehavior5;
                z5 = z8;
                overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                vertical3 = vertical4;
                horizontal3 = horizontal4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                horizontal3 = horizontal2;
                vertical3 = vertical2;
                flingBehavior3 = flingBehavior2;
                overscrollEffect2 = overscrollEffect;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z3 = z;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                horizontal2 = horizontal;
                if (composerStartRestartGroup.changed(horizontal2)) {
                }
                i3 |= i15;
            } else {
                horizontal2 = horizontal;
            }
            i3 |= i15;
        } else {
            horizontal2 = horizontal;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                vertical2 = vertical;
                if (composerStartRestartGroup.changed(vertical2)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
                }
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                if ((i3 & 306783379) != 306783378) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 == 0) {
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                        } else {
                            overscrollEffectRememberOverscrollEffect = overscrollEffect;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = end;
                        vertical4 = top;
                        z7 = z3;
                        z8 = z9;
                        i10 = -1884325601;
                        flingBehavior5 = flingBehavior4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                    }
                    int i119 = i3 >> 3;
                    composer2 = composerStartRestartGroup;
                    LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i119) | (3670016 & i119) | (i119 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z6 = z7;
                    flingBehavior3 = flingBehavior5;
                    z5 = z8;
                    overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                    vertical3 = vertical4;
                    horizontal3 = horizontal4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                    overscrollEffect2 = overscrollEffect;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
            }
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i3 |= i11;
            }
            if ((i3 & 306783379) != 306783378) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal4 = end;
                    vertical4 = top;
                    z7 = z3;
                    z8 = z9;
                    i10 = -1884325601;
                    flingBehavior5 = flingBehavior4;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal4 = end;
                    vertical4 = top;
                    z7 = z3;
                    z8 = z9;
                    i10 = -1884325601;
                    flingBehavior5 = flingBehavior4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                }
                int i1110 = i3 >> 3;
                composer2 = composerStartRestartGroup;
                LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i1110) | (3670016 & i1110) | (i1110 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z6 = z7;
                flingBehavior3 = flingBehavior5;
                z5 = z8;
                overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                vertical3 = vertical4;
                horizontal3 = horizontal4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                horizontal3 = horizontal2;
                vertical3 = vertical2;
                flingBehavior3 = flingBehavior2;
                overscrollEffect2 = overscrollEffect;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 196608;
        vertical2 = vertical;
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                flingBehavior2 = flingBehavior;
                if (composerStartRestartGroup.changed(flingBehavior2)) {
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i3 |= i16;
        } else {
            flingBehavior2 = flingBehavior;
        }
        i8 = i2 & 128;
        if (i8 != 0) {
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i9 = 8388608;
                } else {
                    i9 = 4194304;
                }
                i3 |= i9;
            }
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
            }
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i3 |= i11;
            }
            if ((i3 & 306783379) != 306783378) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal4 = end;
                    vertical4 = top;
                    z7 = z3;
                    z8 = z9;
                    i10 = -1884325601;
                    flingBehavior5 = flingBehavior4;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 == 0) {
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                    } else {
                        overscrollEffectRememberOverscrollEffect = overscrollEffect;
                    }
                    lazyListState3 = lazyListStateRememberLazyListState;
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal4 = end;
                    vertical4 = top;
                    z7 = z3;
                    z8 = z9;
                    i10 = -1884325601;
                    flingBehavior5 = flingBehavior4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                }
                int i1111 = i3 >> 3;
                composer2 = composerStartRestartGroup;
                LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i1111) | (3670016 & i1111) | (i1111 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z6 = z7;
                flingBehavior3 = flingBehavior5;
                z5 = z8;
                overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
                vertical3 = vertical4;
                horizontal3 = horizontal4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                horizontal3 = horizontal2;
                vertical3 = vertical2;
                flingBehavior3 = flingBehavior2;
                overscrollEffect2 = overscrollEffect;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 12582912;
        if ((i & 100663296) != 0) {
            i3 |= ((i2 & 256) == 0 || !composerStartRestartGroup.changed(overscrollEffect)) ? 33554432 : 67108864;
        }
        if ((i & 805306368) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i11 = 536870912;
            } else {
                i11 = 268435456;
            }
            i3 |= i11;
        }
        if ((i3 & 306783379) != 306783378) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i14 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z3 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z3) {
                        end = arrangement.getStart();
                    } else {
                        end = arrangement.getEnd();
                    }
                    i3 &= -57345;
                } else {
                    end = horizontal2;
                }
                if (i6 != 0) {
                    top = Alignment.Companion.getTop();
                } else {
                    top = vertical2;
                }
                if ((i2 & 64) != 0) {
                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    flingBehavior4 = flingBehavior2;
                }
                if (i8 == 0) {
                }
                if ((i2 & 256) != 0) {
                    i3 &= -234881025;
                    overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                } else {
                    overscrollEffectRememberOverscrollEffect = overscrollEffect;
                }
                lazyListState3 = lazyListStateRememberLazyListState;
                paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                horizontal4 = end;
                vertical4 = top;
                z7 = z3;
                z8 = z9;
                i10 = -1884325601;
                flingBehavior5 = flingBehavior4;
            } else {
                if (i12 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i14 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z3 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z3) {
                        end = arrangement.getStart();
                    } else {
                        end = arrangement.getEnd();
                    }
                    i3 &= -57345;
                } else {
                    end = horizontal2;
                }
                if (i6 != 0) {
                    top = Alignment.Companion.getTop();
                } else {
                    top = vertical2;
                }
                if ((i2 & 64) != 0) {
                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    flingBehavior4 = flingBehavior2;
                }
                if (i8 == 0) {
                }
                if ((i2 & 256) != 0) {
                    i3 &= -234881025;
                    overscrollEffectRememberOverscrollEffect = OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0);
                } else {
                    overscrollEffectRememberOverscrollEffect = overscrollEffect;
                }
                lazyListState3 = lazyListStateRememberLazyListState;
                paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                horizontal4 = end;
                vertical4 = top;
                z7 = z3;
                z8 = z9;
                i10 = -1884325601;
                flingBehavior5 = flingBehavior4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
            }
            int i1112 = i3 >> 3;
            composer2 = composerStartRestartGroup;
            LazyListKt.LazyList(modifier3, lazyListState3, paddingValues4, z7, false, flingBehavior5, z8, overscrollEffectRememberOverscrollEffect, 0, null, null, vertical4, horizontal4, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i1112) | (3670016 & i1112) | (i1112 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            lazyListState2 = lazyListState3;
            paddingValues3 = paddingValues4;
            z6 = z7;
            flingBehavior3 = flingBehavior5;
            z5 = z8;
            overscrollEffect2 = overscrollEffectRememberOverscrollEffect;
            vertical3 = vertical4;
            horizontal3 = horizontal4;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier2 = modifier;
            z5 = z2;
            lazyListState2 = lazyListStateRememberLazyListState;
            paddingValues3 = paddingValues2;
            z6 = z3;
            horizontal3 = horizontal2;
            vertical3 = vertical2;
            flingBehavior3 = flingBehavior2;
            overscrollEffect2 = overscrollEffect;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyDslKt.f(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyRow(modifier, lazyListState, paddingValues, z, horizontal, vertical, flingBehavior, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, boolean z2, OverscrollEffect overscrollEffect, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyColumn(modifier, lazyListState, paddingValues, z, vertical, horizontal, flingBehavior, z2, overscrollEffect, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit c(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, boolean z2, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyRow(modifier, lazyListState, paddingValues, z, horizontal, vertical, flingBehavior, z2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit d(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyColumn(modifier, lazyListState, paddingValues, z, vertical, horizontal, flingBehavior, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit e(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, boolean z2, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyColumn(modifier, lazyListState, paddingValues, z, vertical, horizontal, flingBehavior, z2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit f(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, boolean z2, OverscrollEffect overscrollEffect, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyRow(modifier, lazyListState, paddingValues, z, horizontal, vertical, flingBehavior, z2, overscrollEffect, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, List list, Function1 function1, Function1 function2, Function4 function4, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            function2 = AnonymousClass1.INSTANCE;
        }
        lazyListScope.items(list.size(), function1 != null ? new AnonymousClass2(function1, list) : null, new AnonymousClass3(function2, list), ComposableLambdaKt.composableLambdaInstance(802480018, true, new AnonymousClass4(function4, list)));
    }

    public static final <T> void itemsIndexed(LazyListScope lazyListScope, List<? extends T> list, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> function3, Function5<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5) {
        lazyListScope.items(list.size(), function2 != null ? new C01432(function2, list) : null, new C01443(function3, list), ComposableLambdaKt.composableLambdaInstance(2039820996, true, new C01454(function5, list)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, List list, Function2 function2, Function2 function3, Function5 function5, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            function3 = new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt.itemsIndexed.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).intValue(), obj3);
                }

                public final Void invoke(int i2, Object obj2) {
                    return null;
                }
            };
        }
        lazyListScope.items(list.size(), function2 != null ? new C01432(function2, list) : null, new C01443(function3, list), ComposableLambdaKt.composableLambdaInstance(2039820996, true, new C01454(function5, list)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass1 implements Function1 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public final Void invoke(Object obj) {
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$5, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass5 implements Function1 {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public final Void invoke(Object obj) {
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$6, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass6 implements Function1<Integer, Object> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ Function1<T, Object> $key;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(Function1<? super T, ? extends Object> function1, T[] tArr) {
            this.$key = function1;
            this.$items = tArr;
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i) {
            return this.$key.invoke(this.$items[i]);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$7, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass7 implements Function1<Integer, Object> {
        final /* synthetic */ Function1<T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass7(Function1<? super T, ? extends Object> function1, T[] tArr) {
            this.$contentType = function1;
            this.$items = tArr;
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i) {
            return this.$contentType.invoke(this.$items[i]);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$2, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass2 implements Function1<Integer, Object> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ Function1<T, Object> $key;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function1<? super T, ? extends Object> function1, List<? extends T> list) {
            this.$key = function1;
            this.$items = list;
        }

        public final Object invoke(int i) {
            return this.$key.invoke(this.$items.get(i));
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$3, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass3 implements Function1<Integer, Object> {
        final /* synthetic */ Function1<T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Function1<? super T, ? extends Object> function1, List<? extends T> list) {
            this.$contentType = function1;
            this.$items = list;
        }

        public final Object invoke(int i) {
            return this.$contentType.invoke(this.$items.get(i));
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$6, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class C01476 implements Function1<Integer, Object> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ Function2<Integer, T, Object> $key;

        /* JADX WARN: Multi-variable type inference failed */
        public C01476(Function2<? super Integer, ? super T, ? extends Object> function2, T[] tArr) {
            this.$key = function2;
            this.$items = tArr;
        }

        public final Object invoke(int i) {
            return this.$key.invoke(Integer.valueOf(i), this.$items[i]);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$7, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class C01487 implements Function1<Integer, Object> {
        final /* synthetic */ Function2<Integer, T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: Multi-variable type inference failed */
        public C01487(Function2<? super Integer, ? super T, ? extends Object> function2, T[] tArr) {
            this.$contentType = function2;
            this.$items = tArr;
        }

        public final Object invoke(int i) {
            return this.$contentType.invoke(Integer.valueOf(i), this.$items[i]);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class C01432 implements Function1<Integer, Object> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ Function2<Integer, T, Object> $key;

        /* JADX WARN: Multi-variable type inference failed */
        public C01432(Function2<? super Integer, ? super T, ? extends Object> function2, List<? extends T> list) {
            this.$key = function2;
            this.$items = list;
        }

        public final Object invoke(int i) {
            return this.$key.invoke(Integer.valueOf(i), this.$items.get(i));
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class C01443 implements Function1<Integer, Object> {
        final /* synthetic */ Function2<Integer, T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: Multi-variable type inference failed */
        public C01443(Function2<? super Integer, ? super T, ? extends Object> function2, List<? extends T> list) {
            this.$contentType = function2;
            this.$items = list;
        }

        public final Object invoke(int i) {
            return this.$contentType.invoke(Integer.valueOf(i), this.$items.get(i));
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    public static final <T> void itemsIndexed(LazyListScope lazyListScope, T[] tArr, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> function3, Function5<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5) {
        lazyListScope.items(tArr.length, function2 != null ? new C01476(function2, tArr) : null, new C01487(function3, tArr), ComposableLambdaKt.composableLambdaInstance(1763000017, true, new C01498(function5, tArr)));
    }

    public static final <T> void items(LazyListScope lazyListScope, T[] tArr, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> function2, Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4) {
        lazyListScope.items(tArr.length, function1 != null ? new AnonymousClass6(function1, tArr) : null, new AnonymousClass7(function2, tArr), ComposableLambdaKt.composableLambdaInstance(-1781742563, true, new AnonymousClass8(function4, tArr)));
    }

    public static final <T> void items(LazyListScope lazyListScope, List<? extends T> list, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> function2, Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4) {
        lazyListScope.items(list.size(), function1 != null ? new AnonymousClass2(function1, list) : null, new AnonymousClass3(function2, list), ComposableLambdaKt.composableLambdaInstance(802480018, true, new AnonymousClass4(function4, list)));
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, Object[] objArr, Function1 function1, Function1 function2, Function4 function4, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            function2 = AnonymousClass5.INSTANCE;
        }
        lazyListScope.items(objArr.length, function1 != null ? new AnonymousClass6(function1, objArr) : null, new AnonymousClass7(function2, objArr), ComposableLambdaKt.composableLambdaInstance(-1781742563, true, new AnonymousClass8(function4, objArr)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, Object[] objArr, Function2 function2, Function2 function3, Function5 function5, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            function3 = new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt.itemsIndexed.5
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).intValue(), obj3);
                }

                public final Void invoke(int i2, Object obj2) {
                    return null;
                }
            };
        }
        lazyListScope.items(objArr.length, function2 != null ? new C01476(function2, objArr) : null, new C01487(function3, objArr), ComposableLambdaKt.composableLambdaInstance(1763000017, true, new C01498(function5, objArr)));
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, List list, Function1 function1, Function4 function4, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        lazyListScope.items(list.size(), function1 != null ? new AnonymousClass2(function1, list) : null, new AnonymousClass3(AnonymousClass1.INSTANCE, list), ComposableLambdaKt.composableLambdaInstance(802480018, true, new AnonymousClass4(function4, list)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, List list, Function2 function2, Function5 function5, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        lazyListScope.items(list.size(), function2 != null ? new C01432(function2, list) : null, new LazyDslKt$itemsIndexed$$inlined$itemsIndexed$default$1(list), ComposableLambdaKt.composableLambdaInstance(2039820996, true, new C01454(function5, list)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, Object[] objArr, Function2 function2, Function5 function5, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        lazyListScope.items(objArr.length, function2 != null ? new C01476(function2, objArr) : null, new LazyDslKt$itemsIndexed$$inlined$itemsIndexed$default$2(objArr), ComposableLambdaKt.composableLambdaInstance(1763000017, true, new C01498(function5, objArr)));
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, Object[] objArr, Function1 function1, Function4 function4, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        lazyListScope.items(objArr.length, function1 != null ? new AnonymousClass6(function1, objArr) : null, new AnonymousClass7(AnonymousClass5.INSTANCE, objArr), ComposableLambdaKt.composableLambdaInstance(-1781742563, true, new AnonymousClass8(function4, objArr)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$8, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass8 implements Function4<LazyItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function4<LazyItemScope, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass8(Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4, T[] tArr) {
            this.$itemContent = function4;
            this.$items = tArr;
        }

        public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
            int i3;
            if ((i2 & 6) == 0) {
                i3 = (composer.changed(lazyItemScope) ? 4 : 2) | i2;
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
                ComposerKt.traceEventStart(-1781742563, i3, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:250)");
            }
            this.$itemContent.invoke(lazyItemScope, this.$items[i], composer, Integer.valueOf(i3 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$4, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass4 implements Function4<LazyItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function4<LazyItemScope, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4, List<? extends T> list) {
            this.$itemContent = function4;
            this.$items = list;
        }

        public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
            int i3;
            if ((i2 & 6) == 0) {
                i3 = (composer.changed(lazyItemScope) ? 4 : 2) | i2;
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
                ComposerKt.traceEventStart(802480018, i3, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:178)");
            }
            this.$itemContent.invoke(lazyItemScope, this.$items.get(i), composer, Integer.valueOf(i3 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$8, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class C01498 implements Function4<LazyItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function5<LazyItemScope, Integer, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: Multi-variable type inference failed */
        public C01498(Function5<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5, T[] tArr) {
            this.$itemContent = function5;
            this.$items = tArr;
        }

        public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
            int i3;
            if ((i2 & 6) == 0) {
                i3 = (composer.changed(lazyItemScope) ? 4 : 2) | i2;
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
                ComposerKt.traceEventStart(1763000017, i3, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:286)");
            }
            this.$itemContent.invoke(lazyItemScope, Integer.valueOf(i), this.$items[i], composer, Integer.valueOf(i3 & 126));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class C01454 implements Function4<LazyItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function5<LazyItemScope, Integer, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: Multi-variable type inference failed */
        public C01454(Function5<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5, List<? extends T> list) {
            this.$itemContent = function5;
            this.$items = list;
        }

        public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
            int i3;
            if ((i2 & 6) == 0) {
                i3 = (composer.changed(lazyItemScope) ? 4 : 2) | i2;
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
                ComposerKt.traceEventStart(2039820996, i3, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:214)");
            }
            this.$itemContent.invoke(lazyItemScope, Integer.valueOf(i), this.$items.get(i), composer, Integer.valueOf(i3 & 126));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0118  */
    /* JADX WARN: Code duplicated, block: B:103:0x0121  */
    /* JADX WARN: Code duplicated, block: B:105:0x012f  */
    /* JADX WARN: Code duplicated, block: B:118:0x015d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:119:0x015f  */
    /* JADX WARN: Code duplicated, block: B:120:0x0162  */
    /* JADX WARN: Code duplicated, block: B:123:0x0168  */
    /* JADX WARN: Code duplicated, block: B:125:0x0170  */
    /* JADX WARN: Code duplicated, block: B:126:0x017a  */
    /* JADX WARN: Code duplicated, block: B:128:0x017d  */
    /* JADX WARN: Code duplicated, block: B:131:0x0182  */
    /* JADX WARN: Code duplicated, block: B:133:0x0186  */
    /* JADX WARN: Code duplicated, block: B:134:0x018b  */
    /* JADX WARN: Code duplicated, block: B:136:0x0192  */
    /* JADX WARN: Code duplicated, block: B:138:0x0195  */
    /* JADX WARN: Code duplicated, block: B:139:0x019c  */
    /* JADX WARN: Code duplicated, block: B:142:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:143:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:145:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:147:0x01be  */
    /* JADX WARN: Code duplicated, block: B:150:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:153:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:155:0x0205  */
    /* JADX WARN: Code duplicated, block: B:158:0x021a  */
    /* JADX WARN: Code duplicated, block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0084  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x008f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0095  */
    /* JADX WARN: Code duplicated, block: B:59:0x009d  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:66:0x00af  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:78:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:83:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:88:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:94:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:95:0x0102  */
    /* JADX WARN: Code duplicated, block: B:99:0x0115  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    public static final /* synthetic */ void LazyRow(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, boolean z2, final Function1 function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListStateRememberLazyListState;
        PaddingValues paddingValues2;
        int i4;
        boolean z3;
        int i5;
        Arrangement.Horizontal horizontal2;
        int i6;
        Alignment.Vertical vertical2;
        int i7;
        FlingBehavior flingBehavior2;
        int i8;
        int i9;
        boolean z4;
        Composer composer2;
        final Modifier modifier2;
        final boolean z5;
        final LazyListState lazyListState2;
        final PaddingValues paddingValues3;
        final boolean z6;
        final Arrangement.Horizontal horizontal3;
        final Alignment.Vertical vertical3;
        final FlingBehavior flingBehavior3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        PaddingValues paddingValuesM923PaddingValues0680j_4;
        Arrangement.Horizontal end;
        Alignment.Vertical top;
        FlingBehavior flingBehavior4;
        boolean z7;
        FlingBehavior flingBehavior5;
        Modifier modifier4;
        int i10;
        Arrangement arrangement;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1724297413);
        int i12 = i2 & 1;
        if (i12 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListStateRememberLazyListState = lazyListState;
                int i13 = composerStartRestartGroup.changed(lazyListStateRememberLazyListState) ? 32 : 16;
                i3 |= i13;
            } else {
                lazyListStateRememberLazyListState = lazyListState;
            }
            i3 |= i13;
        } else {
            lazyListStateRememberLazyListState = lazyListState;
        }
        int i14 = i2 & 4;
        if (i14 == 0) {
            if ((i & 384) == 0) {
                paddingValues2 = paddingValues;
                i3 |= composerStartRestartGroup.changed(paddingValues2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z3 = z;
                    if (composerStartRestartGroup.changed(z3)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        horizontal2 = horizontal;
                        int i15 = composerStartRestartGroup.changed(horizontal2) ? 16384 : 8192;
                        i3 |= i15;
                    } else {
                        horizontal2 = horizontal;
                    }
                    i3 |= i15;
                } else {
                    horizontal2 = horizontal;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        vertical2 = vertical;
                        if (composerStartRestartGroup.changed(vertical2)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            int i16 = composerStartRestartGroup.changed(flingBehavior2) ? IOUtil.MiB : 524288;
                            i3 |= i16;
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i8 = i2 & 128;
                    if (i8 != 0) {
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(z2)) {
                                i9 = 8388608;
                            } else {
                                i9 = 4194304;
                            }
                            i3 |= i9;
                        }
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changedInstance(function1)) {
                                i11 = 67108864;
                            } else {
                                i11 = 33554432;
                            }
                            i3 |= i11;
                        }
                        if ((i3 & 38347923) != 38347922) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i12 != 0) {
                                    modifier3 = Modifier.Companion;
                                } else {
                                    modifier3 = modifier;
                                }
                                if ((i2 & 2) != 0) {
                                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                    i3 &= -113;
                                }
                                if (i14 != 0) {
                                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                                } else {
                                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i4 != 0) {
                                    z3 = false;
                                }
                                if ((i2 & 16) != 0) {
                                    arrangement = Arrangement.INSTANCE;
                                    if (z3) {
                                        end = arrangement.getEnd();
                                    } else {
                                        end = arrangement.getStart();
                                    }
                                    i3 &= -57345;
                                } else {
                                    end = horizontal2;
                                }
                                if (i6 != 0) {
                                    top = Alignment.Companion.getTop();
                                } else {
                                    top = vertical2;
                                }
                                if ((i2 & 64) != 0) {
                                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i3 &= -3670017;
                                } else {
                                    flingBehavior4 = flingBehavior2;
                                }
                                if (i8 != 0) {
                                    z7 = true;
                                } else {
                                    z7 = z2;
                                }
                                flingBehavior5 = flingBehavior4;
                                modifier4 = modifier3;
                                i10 = -1724297413;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 2) != 0) {
                                    i3 &= -113;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                }
                                z7 = z2;
                                lazyListStateRememberLazyListState = lazyListStateRememberLazyListState;
                                end = horizontal2;
                                top = vertical2;
                                flingBehavior5 = flingBehavior2;
                                i10 = -1724297413;
                                modifier4 = modifier;
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                                z3 = z3;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                            }
                            composer2 = composerStartRestartGroup;
                            LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier4;
                            lazyListState2 = lazyListStateRememberLazyListState;
                            paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                            z6 = z3;
                            horizontal3 = end;
                            vertical3 = top;
                            flingBehavior3 = flingBehavior5;
                            z5 = z7;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            z5 = z2;
                            lazyListState2 = lazyListStateRememberLazyListState;
                            paddingValues3 = paddingValues2;
                            z6 = z3;
                            horizontal3 = horizontal2;
                            vertical3 = vertical2;
                            flingBehavior3 = flingBehavior2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                                public final Object invoke(Object obj, Object obj2) {
                                    return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 12582912;
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 67108864;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -1724297413;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -1724297413;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                        }
                        composer2 = composerStartRestartGroup;
                        LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier4;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                        z6 = z3;
                        horizontal3 = end;
                        vertical3 = top;
                        flingBehavior3 = flingBehavior5;
                        z5 = z7;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        horizontal3 = horizontal2;
                        vertical3 = vertical2;
                        flingBehavior3 = flingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 196608;
                vertical2 = vertical;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 67108864;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -1724297413;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -1724297413;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                        }
                        composer2 = composerStartRestartGroup;
                        LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier4;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                        z6 = z3;
                        horizontal3 = end;
                        vertical3 = top;
                        flingBehavior3 = flingBehavior5;
                        z5 = z7;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        horizontal3 = horizontal2;
                        vertical3 = vertical2;
                        flingBehavior3 = flingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    horizontal3 = end;
                    vertical3 = top;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z3 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    horizontal2 = horizontal;
                    if (composerStartRestartGroup.changed(horizontal2)) {
                    }
                    i3 |= i15;
                } else {
                    horizontal2 = horizontal;
                }
                i3 |= i15;
            } else {
                horizontal2 = horizontal;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    vertical2 = vertical;
                    if (composerStartRestartGroup.changed(vertical2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 67108864;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -1724297413;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -1724297413;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                        }
                        composer2 = composerStartRestartGroup;
                        LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier4;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                        z6 = z3;
                        horizontal3 = end;
                        vertical3 = top;
                        flingBehavior3 = flingBehavior5;
                        z5 = z7;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        horizontal3 = horizontal2;
                        vertical3 = vertical2;
                        flingBehavior3 = flingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    horizontal3 = end;
                    vertical3 = top;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            vertical2 = vertical;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    horizontal3 = end;
                    vertical3 = top;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 67108864;
                } else {
                    i11 = 33554432;
                }
                i3 |= i11;
            }
            if ((i3 & 38347923) != 38347922) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -1724297413;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -1724297413;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                }
                composer2 = composerStartRestartGroup;
                LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                z6 = z3;
                horizontal3 = end;
                vertical3 = top;
                flingBehavior3 = flingBehavior5;
                z5 = z7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                horizontal3 = horizontal2;
                vertical3 = vertical2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 384;
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z3 = z;
                if (composerStartRestartGroup.changed(z3)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    horizontal2 = horizontal;
                    if (composerStartRestartGroup.changed(horizontal2)) {
                    }
                    i3 |= i15;
                } else {
                    horizontal2 = horizontal;
                }
                i3 |= i15;
            } else {
                horizontal2 = horizontal;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    vertical2 = vertical;
                    if (composerStartRestartGroup.changed(vertical2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 67108864;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -1724297413;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    end = arrangement.getStart();
                                } else {
                                    end = arrangement.getEnd();
                                }
                                i3 &= -57345;
                            } else {
                                end = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            } else {
                                top = vertical2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -1724297413;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                        }
                        composer2 = composerStartRestartGroup;
                        LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier4;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                        z6 = z3;
                        horizontal3 = end;
                        vertical3 = top;
                        flingBehavior3 = flingBehavior5;
                        z5 = z7;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        horizontal3 = horizontal2;
                        vertical3 = vertical2;
                        flingBehavior3 = flingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    horizontal3 = end;
                    vertical3 = top;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            vertical2 = vertical;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    horizontal3 = end;
                    vertical3 = top;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 67108864;
                } else {
                    i11 = 33554432;
                }
                i3 |= i11;
            }
            if ((i3 & 38347923) != 38347922) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -1724297413;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -1724297413;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                }
                composer2 = composerStartRestartGroup;
                LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                z6 = z3;
                horizontal3 = end;
                vertical3 = top;
                flingBehavior3 = flingBehavior5;
                z5 = z7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                horizontal3 = horizontal2;
                vertical3 = vertical2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z3 = z;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                horizontal2 = horizontal;
                if (composerStartRestartGroup.changed(horizontal2)) {
                }
                i3 |= i15;
            } else {
                horizontal2 = horizontal;
            }
            i3 |= i15;
        } else {
            horizontal2 = horizontal;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                vertical2 = vertical;
                if (composerStartRestartGroup.changed(vertical2)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                        } else {
                            end = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        } else {
                            top = vertical2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -1724297413;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    horizontal3 = end;
                    vertical3 = top;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    horizontal3 = horizontal2;
                    vertical3 = vertical2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 67108864;
                } else {
                    i11 = 33554432;
                }
                i3 |= i11;
            }
            if ((i3 & 38347923) != 38347922) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -1724297413;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -1724297413;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                }
                composer2 = composerStartRestartGroup;
                LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                z6 = z3;
                horizontal3 = end;
                vertical3 = top;
                flingBehavior3 = flingBehavior5;
                z5 = z7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                horizontal3 = horizontal2;
                vertical3 = vertical2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 196608;
        vertical2 = vertical;
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                flingBehavior2 = flingBehavior;
                if (composerStartRestartGroup.changed(flingBehavior2)) {
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i3 |= i16;
        } else {
            flingBehavior2 = flingBehavior;
        }
        i8 = i2 & 128;
        if (i8 != 0) {
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i9 = 8388608;
                } else {
                    i9 = 4194304;
                }
                i3 |= i9;
            }
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 67108864;
                } else {
                    i11 = 33554432;
                }
                i3 |= i11;
            }
            if ((i3 & 38347923) != 38347922) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -1724297413;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                    } else {
                        end = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    } else {
                        top = vertical2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -1724297413;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                }
                composer2 = composerStartRestartGroup;
                LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                z6 = z3;
                horizontal3 = end;
                vertical3 = top;
                flingBehavior3 = flingBehavior5;
                z5 = z7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                horizontal3 = horizontal2;
                vertical3 = vertical2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 12582912;
        if ((i & 100663296) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i11 = 67108864;
            } else {
                i11 = 33554432;
            }
            i3 |= i11;
        }
        if ((i3 & 38347923) != 38347922) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i14 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z3 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z3) {
                        end = arrangement.getStart();
                    } else {
                        end = arrangement.getEnd();
                    }
                    i3 &= -57345;
                } else {
                    end = horizontal2;
                }
                if (i6 != 0) {
                    top = Alignment.Companion.getTop();
                } else {
                    top = vertical2;
                }
                if ((i2 & 64) != 0) {
                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    flingBehavior4 = flingBehavior2;
                }
                if (i8 != 0) {
                    z7 = true;
                } else {
                    z7 = z2;
                }
                flingBehavior5 = flingBehavior4;
                modifier4 = modifier3;
                i10 = -1724297413;
            } else {
                if (i12 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i14 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z3 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z3) {
                        end = arrangement.getStart();
                    } else {
                        end = arrangement.getEnd();
                    }
                    i3 &= -57345;
                } else {
                    end = horizontal2;
                }
                if (i6 != 0) {
                    top = Alignment.Companion.getTop();
                } else {
                    top = vertical2;
                }
                if ((i2 & 64) != 0) {
                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    flingBehavior4 = flingBehavior2;
                }
                if (i8 != 0) {
                    z7 = true;
                } else {
                    z7 = z2;
                }
                flingBehavior5 = flingBehavior4;
                modifier4 = modifier3;
                i10 = -1724297413;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
            }
            composer2 = composerStartRestartGroup;
            LazyRow(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, end, top, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            lazyListState2 = lazyListStateRememberLazyListState;
            paddingValues3 = paddingValuesM923PaddingValues0680j_4;
            z6 = z3;
            horizontal3 = end;
            vertical3 = top;
            flingBehavior3 = flingBehavior5;
            z5 = z7;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier2 = modifier;
            z5 = z2;
            lazyListState2 = lazyListStateRememberLazyListState;
            paddingValues3 = paddingValues2;
            z6 = z3;
            horizontal3 = horizontal2;
            vertical3 = vertical2;
            flingBehavior3 = flingBehavior2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sq8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyDslKt.c(modifier2, lazyListState2, paddingValues3, z6, horizontal3, vertical3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0118  */
    /* JADX WARN: Code duplicated, block: B:103:0x0121  */
    /* JADX WARN: Code duplicated, block: B:105:0x012f  */
    /* JADX WARN: Code duplicated, block: B:118:0x015d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:119:0x015f  */
    /* JADX WARN: Code duplicated, block: B:120:0x0162  */
    /* JADX WARN: Code duplicated, block: B:123:0x0168  */
    /* JADX WARN: Code duplicated, block: B:125:0x0170  */
    /* JADX WARN: Code duplicated, block: B:126:0x017a  */
    /* JADX WARN: Code duplicated, block: B:128:0x017d  */
    /* JADX WARN: Code duplicated, block: B:131:0x0182  */
    /* JADX WARN: Code duplicated, block: B:133:0x0186  */
    /* JADX WARN: Code duplicated, block: B:134:0x018b  */
    /* JADX WARN: Code duplicated, block: B:136:0x0192  */
    /* JADX WARN: Code duplicated, block: B:138:0x0195  */
    /* JADX WARN: Code duplicated, block: B:139:0x019c  */
    /* JADX WARN: Code duplicated, block: B:142:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:143:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:145:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:147:0x01be  */
    /* JADX WARN: Code duplicated, block: B:150:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:153:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:155:0x0205  */
    /* JADX WARN: Code duplicated, block: B:158:0x021a  */
    /* JADX WARN: Code duplicated, block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0084  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x008f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0095  */
    /* JADX WARN: Code duplicated, block: B:59:0x009d  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:66:0x00af  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:78:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:83:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:88:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:94:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:95:0x0102  */
    /* JADX WARN: Code duplicated, block: B:99:0x0115  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    public static final /* synthetic */ void LazyColumn(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, boolean z2, final Function1 function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListStateRememberLazyListState;
        PaddingValues paddingValues2;
        int i4;
        boolean z3;
        int i5;
        Arrangement.Vertical vertical2;
        int i6;
        Alignment.Horizontal horizontal2;
        int i7;
        FlingBehavior flingBehavior2;
        int i8;
        int i9;
        boolean z4;
        Composer composer2;
        final Modifier modifier2;
        final boolean z5;
        final LazyListState lazyListState2;
        final PaddingValues paddingValues3;
        final boolean z6;
        final Arrangement.Vertical vertical3;
        final Alignment.Horizontal horizontal3;
        final FlingBehavior flingBehavior3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        PaddingValues paddingValuesM923PaddingValues0680j_4;
        Arrangement.Vertical bottom;
        Alignment.Horizontal start;
        FlingBehavior flingBehavior4;
        boolean z7;
        FlingBehavior flingBehavior5;
        Modifier modifier4;
        int i10;
        Arrangement arrangement;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-740714857);
        int i12 = i2 & 1;
        if (i12 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListStateRememberLazyListState = lazyListState;
                int i13 = composerStartRestartGroup.changed(lazyListStateRememberLazyListState) ? 32 : 16;
                i3 |= i13;
            } else {
                lazyListStateRememberLazyListState = lazyListState;
            }
            i3 |= i13;
        } else {
            lazyListStateRememberLazyListState = lazyListState;
        }
        int i14 = i2 & 4;
        if (i14 == 0) {
            if ((i & 384) == 0) {
                paddingValues2 = paddingValues;
                i3 |= composerStartRestartGroup.changed(paddingValues2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z3 = z;
                    if (composerStartRestartGroup.changed(z3)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        vertical2 = vertical;
                        int i15 = composerStartRestartGroup.changed(vertical2) ? 16384 : 8192;
                        i3 |= i15;
                    } else {
                        vertical2 = vertical;
                    }
                    i3 |= i15;
                } else {
                    vertical2 = vertical;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        horizontal2 = horizontal;
                        if (composerStartRestartGroup.changed(horizontal2)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            int i16 = composerStartRestartGroup.changed(flingBehavior2) ? IOUtil.MiB : 524288;
                            i3 |= i16;
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i8 = i2 & 128;
                    if (i8 != 0) {
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(z2)) {
                                i9 = 8388608;
                            } else {
                                i9 = 4194304;
                            }
                            i3 |= i9;
                        }
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changedInstance(function1)) {
                                i11 = 67108864;
                            } else {
                                i11 = 33554432;
                            }
                            i3 |= i11;
                        }
                        if ((i3 & 38347923) != 38347922) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i12 != 0) {
                                    modifier3 = Modifier.Companion;
                                } else {
                                    modifier3 = modifier;
                                }
                                if ((i2 & 2) != 0) {
                                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                    i3 &= -113;
                                }
                                if (i14 != 0) {
                                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                                } else {
                                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                                }
                                if (i4 != 0) {
                                    z3 = false;
                                }
                                if ((i2 & 16) != 0) {
                                    arrangement = Arrangement.INSTANCE;
                                    if (z3) {
                                        bottom = arrangement.getBottom();
                                    } else {
                                        bottom = arrangement.getTop();
                                    }
                                    i3 &= -57345;
                                } else {
                                    bottom = vertical2;
                                }
                                if (i6 != 0) {
                                    start = Alignment.Companion.getStart();
                                } else {
                                    start = horizontal2;
                                }
                                if ((i2 & 64) != 0) {
                                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                    i3 &= -3670017;
                                } else {
                                    flingBehavior4 = flingBehavior2;
                                }
                                if (i8 != 0) {
                                    z7 = true;
                                } else {
                                    z7 = z2;
                                }
                                flingBehavior5 = flingBehavior4;
                                modifier4 = modifier3;
                                i10 = -740714857;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 2) != 0) {
                                    i3 &= -113;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                }
                                z7 = z2;
                                lazyListStateRememberLazyListState = lazyListStateRememberLazyListState;
                                bottom = vertical2;
                                start = horizontal2;
                                flingBehavior5 = flingBehavior2;
                                i10 = -740714857;
                                modifier4 = modifier;
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                                z3 = z3;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                            }
                            composer2 = composerStartRestartGroup;
                            LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier4;
                            lazyListState2 = lazyListStateRememberLazyListState;
                            paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                            z6 = z3;
                            vertical3 = bottom;
                            horizontal3 = start;
                            flingBehavior3 = flingBehavior5;
                            z5 = z7;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            z5 = z2;
                            lazyListState2 = lazyListStateRememberLazyListState;
                            paddingValues3 = paddingValues2;
                            z6 = z3;
                            vertical3 = vertical2;
                            horizontal3 = horizontal2;
                            flingBehavior3 = flingBehavior2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                                public final Object invoke(Object obj, Object obj2) {
                                    return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 12582912;
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 67108864;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -740714857;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -740714857;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                        }
                        composer2 = composerStartRestartGroup;
                        LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier4;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                        z6 = z3;
                        vertical3 = bottom;
                        horizontal3 = start;
                        flingBehavior3 = flingBehavior5;
                        z5 = z7;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        vertical3 = vertical2;
                        horizontal3 = horizontal2;
                        flingBehavior3 = flingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 196608;
                horizontal2 = horizontal;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 67108864;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -740714857;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -740714857;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                        }
                        composer2 = composerStartRestartGroup;
                        LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier4;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                        z6 = z3;
                        vertical3 = bottom;
                        horizontal3 = start;
                        flingBehavior3 = flingBehavior5;
                        z5 = z7;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        vertical3 = vertical2;
                        horizontal3 = horizontal2;
                        flingBehavior3 = flingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    vertical3 = bottom;
                    horizontal3 = start;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z3 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    vertical2 = vertical;
                    if (composerStartRestartGroup.changed(vertical2)) {
                    }
                    i3 |= i15;
                } else {
                    vertical2 = vertical;
                }
                i3 |= i15;
            } else {
                vertical2 = vertical;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    horizontal2 = horizontal;
                    if (composerStartRestartGroup.changed(horizontal2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 67108864;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -740714857;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -740714857;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                        }
                        composer2 = composerStartRestartGroup;
                        LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier4;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                        z6 = z3;
                        vertical3 = bottom;
                        horizontal3 = start;
                        flingBehavior3 = flingBehavior5;
                        z5 = z7;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        vertical3 = vertical2;
                        horizontal3 = horizontal2;
                        flingBehavior3 = flingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    vertical3 = bottom;
                    horizontal3 = start;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            horizontal2 = horizontal;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    vertical3 = bottom;
                    horizontal3 = start;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 67108864;
                } else {
                    i11 = 33554432;
                }
                i3 |= i11;
            }
            if ((i3 & 38347923) != 38347922) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -740714857;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -740714857;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                }
                composer2 = composerStartRestartGroup;
                LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                z6 = z3;
                vertical3 = bottom;
                horizontal3 = start;
                flingBehavior3 = flingBehavior5;
                z5 = z7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                vertical3 = vertical2;
                horizontal3 = horizontal2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 384;
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z3 = z;
                if (composerStartRestartGroup.changed(z3)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    vertical2 = vertical;
                    if (composerStartRestartGroup.changed(vertical2)) {
                    }
                    i3 |= i15;
                } else {
                    vertical2 = vertical;
                }
                i3 |= i15;
            } else {
                vertical2 = vertical;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    horizontal2 = horizontal;
                    if (composerStartRestartGroup.changed(horizontal2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i16;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 67108864;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -740714857;
                        } else {
                            if (i12 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i14 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z3 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z3) {
                                    bottom = arrangement.getTop();
                                } else {
                                    bottom = arrangement.getBottom();
                                }
                                i3 &= -57345;
                            } else {
                                bottom = vertical2;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if ((i2 & 64) != 0) {
                                flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                flingBehavior4 = flingBehavior2;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z2;
                            }
                            flingBehavior5 = flingBehavior4;
                            modifier4 = modifier3;
                            i10 = -740714857;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                        }
                        composer2 = composerStartRestartGroup;
                        LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier4;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                        z6 = z3;
                        vertical3 = bottom;
                        horizontal3 = start;
                        flingBehavior3 = flingBehavior5;
                        z5 = z7;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        z5 = z2;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z6 = z3;
                        vertical3 = vertical2;
                        horizontal3 = horizontal2;
                        flingBehavior3 = flingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    vertical3 = bottom;
                    horizontal3 = start;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            horizontal2 = horizontal;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    vertical3 = bottom;
                    horizontal3 = start;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 67108864;
                } else {
                    i11 = 33554432;
                }
                i3 |= i11;
            }
            if ((i3 & 38347923) != 38347922) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -740714857;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -740714857;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                }
                composer2 = composerStartRestartGroup;
                LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                z6 = z3;
                vertical3 = bottom;
                horizontal3 = start;
                flingBehavior3 = flingBehavior5;
                z5 = z7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                vertical3 = vertical2;
                horizontal3 = horizontal2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z3 = z;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                vertical2 = vertical;
                if (composerStartRestartGroup.changed(vertical2)) {
                }
                i3 |= i15;
            } else {
                vertical2 = vertical;
            }
            i3 |= i15;
        } else {
            vertical2 = vertical;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                horizontal2 = horizontal;
                if (composerStartRestartGroup.changed(horizontal2)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i16;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 67108864;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    } else {
                        if (i12 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i14 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z3) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                        } else {
                            bottom = vertical2;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if ((i2 & 64) != 0) {
                            flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            flingBehavior4 = flingBehavior2;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z2;
                        }
                        flingBehavior5 = flingBehavior4;
                        modifier4 = modifier3;
                        i10 = -740714857;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                    z6 = z3;
                    vertical3 = bottom;
                    horizontal3 = start;
                    flingBehavior3 = flingBehavior5;
                    z5 = z7;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    z5 = z2;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z6 = z3;
                    vertical3 = vertical2;
                    horizontal3 = horizontal2;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 67108864;
                } else {
                    i11 = 33554432;
                }
                i3 |= i11;
            }
            if ((i3 & 38347923) != 38347922) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -740714857;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -740714857;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                }
                composer2 = composerStartRestartGroup;
                LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                z6 = z3;
                vertical3 = bottom;
                horizontal3 = start;
                flingBehavior3 = flingBehavior5;
                z5 = z7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                vertical3 = vertical2;
                horizontal3 = horizontal2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 196608;
        horizontal2 = horizontal;
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                flingBehavior2 = flingBehavior;
                if (composerStartRestartGroup.changed(flingBehavior2)) {
                }
                i3 |= i16;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i3 |= i16;
        } else {
            flingBehavior2 = flingBehavior;
        }
        i8 = i2 & 128;
        if (i8 != 0) {
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i9 = 8388608;
                } else {
                    i9 = 4194304;
                }
                i3 |= i9;
            }
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 67108864;
                } else {
                    i11 = 33554432;
                }
                i3 |= i11;
            }
            if ((i3 & 38347923) != 38347922) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -740714857;
                } else {
                    if (i12 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i14 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z3) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                    } else {
                        bottom = vertical2;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if ((i2 & 64) != 0) {
                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        flingBehavior4 = flingBehavior2;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z2;
                    }
                    flingBehavior5 = flingBehavior4;
                    modifier4 = modifier3;
                    i10 = -740714857;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                }
                composer2 = composerStartRestartGroup;
                LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValuesM923PaddingValues0680j_4;
                z6 = z3;
                vertical3 = bottom;
                horizontal3 = start;
                flingBehavior3 = flingBehavior5;
                z5 = z7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                z5 = z2;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z6 = z3;
                vertical3 = vertical2;
                horizontal3 = horizontal2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 12582912;
        if ((i & 100663296) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i11 = 67108864;
            } else {
                i11 = 33554432;
            }
            i3 |= i11;
        }
        if ((i3 & 38347923) != 38347922) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i14 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z3 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z3) {
                        bottom = arrangement.getTop();
                    } else {
                        bottom = arrangement.getBottom();
                    }
                    i3 &= -57345;
                } else {
                    bottom = vertical2;
                }
                if (i6 != 0) {
                    start = Alignment.Companion.getStart();
                } else {
                    start = horizontal2;
                }
                if ((i2 & 64) != 0) {
                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    flingBehavior4 = flingBehavior2;
                }
                if (i8 != 0) {
                    z7 = true;
                } else {
                    z7 = z2;
                }
                flingBehavior5 = flingBehavior4;
                modifier4 = modifier3;
                i10 = -740714857;
            } else {
                if (i12 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i14 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z3 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z3) {
                        bottom = arrangement.getTop();
                    } else {
                        bottom = arrangement.getBottom();
                    }
                    i3 &= -57345;
                } else {
                    bottom = vertical2;
                }
                if (i6 != 0) {
                    start = Alignment.Companion.getStart();
                } else {
                    start = horizontal2;
                }
                if ((i2 & 64) != 0) {
                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    flingBehavior4 = flingBehavior2;
                }
                if (i8 != 0) {
                    z7 = true;
                } else {
                    z7 = z2;
                }
                flingBehavior5 = flingBehavior4;
                modifier4 = modifier3;
                i10 = -740714857;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i10, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
            }
            composer2 = composerStartRestartGroup;
            LazyColumn(modifier4, lazyListStateRememberLazyListState, paddingValuesM923PaddingValues0680j_4, z3, bottom, start, flingBehavior5, z7, OverscrollKt.rememberOverscrollEffect(composerStartRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            lazyListState2 = lazyListStateRememberLazyListState;
            paddingValues3 = paddingValuesM923PaddingValues0680j_4;
            z6 = z3;
            vertical3 = bottom;
            horizontal3 = start;
            flingBehavior3 = flingBehavior5;
            z5 = z7;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier2 = modifier;
            z5 = z2;
            lazyListState2 = lazyListStateRememberLazyListState;
            paddingValues3 = paddingValues2;
            z6 = z3;
            vertical3 = vertical2;
            horizontal3 = horizontal2;
            flingBehavior3 = flingBehavior2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: rq8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyDslKt.e(modifier2, lazyListState2, paddingValues3, z6, vertical3, horizontal3, flingBehavior3, z5, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:108:0x0130 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:109:0x0132  */
    /* JADX WARN: Code duplicated, block: B:110:0x0135  */
    /* JADX WARN: Code duplicated, block: B:113:0x013b  */
    /* JADX WARN: Code duplicated, block: B:115:0x0144  */
    /* JADX WARN: Code duplicated, block: B:116:0x014e  */
    /* JADX WARN: Code duplicated, block: B:118:0x0151  */
    /* JADX WARN: Code duplicated, block: B:121:0x0156  */
    /* JADX WARN: Code duplicated, block: B:123:0x015a  */
    /* JADX WARN: Code duplicated, block: B:124:0x015f  */
    /* JADX WARN: Code duplicated, block: B:127:0x0168  */
    /* JADX WARN: Code duplicated, block: B:130:0x0173  */
    /* JADX WARN: Code duplicated, block: B:131:0x0184  */
    /* JADX WARN: Code duplicated, block: B:134:0x0195  */
    /* JADX WARN: Code duplicated, block: B:137:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:139:0x01de  */
    /* JADX WARN: Code duplicated, block: B:142:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:144:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x0063  */
    /* JADX WARN: Code duplicated, block: B:39:0x0068  */
    /* JADX WARN: Code duplicated, block: B:41:0x006c  */
    /* JADX WARN: Code duplicated, block: B:43:0x0074  */
    /* JADX WARN: Code duplicated, block: B:44:0x0077  */
    /* JADX WARN: Code duplicated, block: B:48:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0082  */
    /* JADX WARN: Code duplicated, block: B:52:0x008a  */
    /* JADX WARN: Code duplicated, block: B:53:0x008d  */
    /* JADX WARN: Code duplicated, block: B:56:0x0093  */
    /* JADX WARN: Code duplicated, block: B:59:0x009b  */
    /* JADX WARN: Code duplicated, block: B:61:0x009f  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:65:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:72:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:78:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:83:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:84:0x00df  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:92:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:94:0x0109  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    public static final /* synthetic */ void LazyRow(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, final Function1 function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListStateRememberLazyListState;
        PaddingValues paddingValues2;
        int i4;
        boolean z2;
        int i5;
        Arrangement.Horizontal horizontal2;
        int i6;
        Alignment.Vertical top;
        int i7;
        FlingBehavior flingBehavior2;
        boolean z3;
        Composer composer2;
        final Modifier modifier2;
        final LazyListState lazyListState2;
        final PaddingValues paddingValues3;
        final boolean z4;
        final Arrangement.Horizontal horizontal3;
        final Alignment.Vertical vertical2;
        final FlingBehavior flingBehavior3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        PaddingValues paddingValuesM923PaddingValues0680j_4;
        PaddingValues paddingValues4;
        Alignment.Vertical vertical3;
        boolean z5;
        Arrangement.Horizontal horizontal4;
        Modifier modifier4;
        LazyListState lazyListState3;
        Arrangement arrangement;
        Arrangement.Horizontal end;
        int i8;
        Composer composerStartRestartGroup = composer.startRestartGroup(407929823);
        int i9 = i2 & 1;
        if (i9 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListStateRememberLazyListState = lazyListState;
                int i10 = composerStartRestartGroup.changed(lazyListStateRememberLazyListState) ? 32 : 16;
                i3 |= i10;
            } else {
                lazyListStateRememberLazyListState = lazyListState;
            }
            i3 |= i10;
        } else {
            lazyListStateRememberLazyListState = lazyListState;
        }
        int i11 = i2 & 4;
        if (i11 == 0) {
            if ((i & 384) == 0) {
                paddingValues2 = paddingValues;
                i3 |= composerStartRestartGroup.changed(paddingValues2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        horizontal2 = horizontal;
                        int i12 = composerStartRestartGroup.changed(horizontal2) ? 16384 : 8192;
                        i3 |= i12;
                    } else {
                        horizontal2 = horizontal;
                    }
                    i3 |= i12;
                } else {
                    horizontal2 = horizontal;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        top = vertical;
                        if (composerStartRestartGroup.changed(top)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            int i13 = composerStartRestartGroup.changed(flingBehavior2) ? IOUtil.MiB : 524288;
                            i3 |= i13;
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i3 |= i13;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i8 = 8388608;
                        } else {
                            i8 = 4194304;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 4793491) != 4793490) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 2) != 0) {
                                i3 &= -113;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            if ((i2 & 64) != 0) {
                                i3 &= -3670017;
                            }
                            paddingValues4 = paddingValues2;
                            vertical3 = top;
                            z5 = z2;
                            horizontal4 = horizontal2;
                            modifier4 = modifier;
                        } else {
                            if (i9 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i11 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z2 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z2) {
                                    end = arrangement.getEnd();
                                } else {
                                    end = arrangement.getStart();
                                }
                                i3 &= -57345;
                                horizontal2 = end;
                            }
                            if (i6 != 0) {
                                top = Alignment.Companion.getTop();
                            }
                            if ((i2 & 64) != 0) {
                                i3 &= -3670017;
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                                horizontal4 = horizontal2;
                                vertical3 = top;
                                lazyListState3 = lazyListStateRememberLazyListState;
                                z5 = z2;
                                modifier4 = modifier3;
                            } else {
                                paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                                vertical3 = top;
                                z5 = z2;
                                horizontal4 = horizontal2;
                                modifier4 = modifier3;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(407929823, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:509)");
                            }
                            composer2 = composerStartRestartGroup;
                            LazyRow(modifier4, lazyListState3, paddingValues4, z5, horizontal4, vertical3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier4;
                            lazyListState2 = lazyListState3;
                            paddingValues3 = paddingValues4;
                            z4 = z5;
                            horizontal3 = horizontal4;
                            vertical2 = vertical3;
                            flingBehavior3 = flingBehavior2;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(407929823, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:509)");
                        }
                        composer2 = composerStartRestartGroup;
                        LazyRow(modifier4, lazyListState3, paddingValues4, z5, horizontal4, vertical3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier4;
                        lazyListState2 = lazyListState3;
                        paddingValues3 = paddingValues4;
                        z4 = z5;
                        horizontal3 = horizontal4;
                        vertical2 = vertical3;
                        flingBehavior3 = flingBehavior2;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z4 = z2;
                        horizontal3 = horizontal2;
                        vertical2 = top;
                        flingBehavior3 = flingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.a(modifier2, lazyListState2, paddingValues3, z4, horizontal3, vertical2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 196608;
                top = vertical;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i13;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i3 |= i8;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                            horizontal2 = end;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = horizontal2;
                            vertical3 = top;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical3 = top;
                            z5 = z2;
                            horizontal4 = horizontal2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                            horizontal2 = end;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = horizontal2;
                            vertical3 = top;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical3 = top;
                            z5 = z2;
                            horizontal4 = horizontal2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(407929823, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:509)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyRow(modifier4, lazyListState3, paddingValues4, z5, horizontal4, vertical3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z4 = z5;
                    horizontal3 = horizontal4;
                    vertical2 = vertical3;
                    flingBehavior3 = flingBehavior2;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z4 = z2;
                    horizontal3 = horizontal2;
                    vertical2 = top;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.a(modifier2, lazyListState2, paddingValues3, z4, horizontal3, vertical2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    horizontal2 = horizontal;
                    if (composerStartRestartGroup.changed(horizontal2)) {
                    }
                    i3 |= i12;
                } else {
                    horizontal2 = horizontal;
                }
                i3 |= i12;
            } else {
                horizontal2 = horizontal;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    top = vertical;
                    if (composerStartRestartGroup.changed(top)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i13;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i3 |= i8;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                            horizontal2 = end;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = horizontal2;
                            vertical3 = top;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical3 = top;
                            z5 = z2;
                            horizontal4 = horizontal2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                            horizontal2 = end;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = horizontal2;
                            vertical3 = top;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical3 = top;
                            z5 = z2;
                            horizontal4 = horizontal2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(407929823, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:509)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyRow(modifier4, lazyListState3, paddingValues4, z5, horizontal4, vertical3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z4 = z5;
                    horizontal3 = horizontal4;
                    vertical2 = vertical3;
                    flingBehavior3 = flingBehavior2;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z4 = z2;
                    horizontal3 = horizontal2;
                    vertical2 = top;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.a(modifier2, lazyListState2, paddingValues3, z4, horizontal3, vertical2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            top = vertical;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i13;
            } else {
                flingBehavior2 = flingBehavior;
            }
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i3 |= i8;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                        horizontal2 = end;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = horizontal2;
                        vertical3 = top;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical3 = top;
                        z5 = z2;
                        horizontal4 = horizontal2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                } else {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                        horizontal2 = end;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = horizontal2;
                        vertical3 = top;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical3 = top;
                        z5 = z2;
                        horizontal4 = horizontal2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(407929823, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:509)");
                }
                composer2 = composerStartRestartGroup;
                LazyRow(modifier4, lazyListState3, paddingValues4, z5, horizontal4, vertical3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z4 = z5;
                horizontal3 = horizontal4;
                vertical2 = vertical3;
                flingBehavior3 = flingBehavior2;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z4 = z2;
                horizontal3 = horizontal2;
                vertical2 = top;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.a(modifier2, lazyListState2, paddingValues3, z4, horizontal3, vertical2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 384;
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    horizontal2 = horizontal;
                    if (composerStartRestartGroup.changed(horizontal2)) {
                    }
                    i3 |= i12;
                } else {
                    horizontal2 = horizontal;
                }
                i3 |= i12;
            } else {
                horizontal2 = horizontal;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    top = vertical;
                    if (composerStartRestartGroup.changed(top)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i13;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i3 |= i8;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                            horizontal2 = end;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = horizontal2;
                            vertical3 = top;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical3 = top;
                            z5 = z2;
                            horizontal4 = horizontal2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                end = arrangement.getStart();
                            } else {
                                end = arrangement.getEnd();
                            }
                            i3 &= -57345;
                            horizontal2 = end;
                        }
                        if (i6 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal4 = horizontal2;
                            vertical3 = top;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical3 = top;
                            z5 = z2;
                            horizontal4 = horizontal2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(407929823, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:509)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyRow(modifier4, lazyListState3, paddingValues4, z5, horizontal4, vertical3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z4 = z5;
                    horizontal3 = horizontal4;
                    vertical2 = vertical3;
                    flingBehavior3 = flingBehavior2;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z4 = z2;
                    horizontal3 = horizontal2;
                    vertical2 = top;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.a(modifier2, lazyListState2, paddingValues3, z4, horizontal3, vertical2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            top = vertical;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i13;
            } else {
                flingBehavior2 = flingBehavior;
            }
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i3 |= i8;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                        horizontal2 = end;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = horizontal2;
                        vertical3 = top;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical3 = top;
                        z5 = z2;
                        horizontal4 = horizontal2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                } else {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                        horizontal2 = end;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = horizontal2;
                        vertical3 = top;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical3 = top;
                        z5 = z2;
                        horizontal4 = horizontal2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(407929823, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:509)");
                }
                composer2 = composerStartRestartGroup;
                LazyRow(modifier4, lazyListState3, paddingValues4, z5, horizontal4, vertical3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z4 = z5;
                horizontal3 = horizontal4;
                vertical2 = vertical3;
                flingBehavior3 = flingBehavior2;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z4 = z2;
                horizontal3 = horizontal2;
                vertical2 = top;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.a(modifier2, lazyListState2, paddingValues3, z4, horizontal3, vertical2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                horizontal2 = horizontal;
                if (composerStartRestartGroup.changed(horizontal2)) {
                }
                i3 |= i12;
            } else {
                horizontal2 = horizontal;
            }
            i3 |= i12;
        } else {
            horizontal2 = horizontal;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                top = vertical;
                if (composerStartRestartGroup.changed(top)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i13;
            } else {
                flingBehavior2 = flingBehavior;
            }
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i3 |= i8;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                        horizontal2 = end;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = horizontal2;
                        vertical3 = top;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical3 = top;
                        z5 = z2;
                        horizontal4 = horizontal2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                } else {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            end = arrangement.getStart();
                        } else {
                            end = arrangement.getEnd();
                        }
                        i3 &= -57345;
                        horizontal2 = end;
                    }
                    if (i6 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal4 = horizontal2;
                        vertical3 = top;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical3 = top;
                        z5 = z2;
                        horizontal4 = horizontal2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(407929823, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:509)");
                }
                composer2 = composerStartRestartGroup;
                LazyRow(modifier4, lazyListState3, paddingValues4, z5, horizontal4, vertical3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z4 = z5;
                horizontal3 = horizontal4;
                vertical2 = vertical3;
                flingBehavior3 = flingBehavior2;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z4 = z2;
                horizontal3 = horizontal2;
                vertical2 = top;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.a(modifier2, lazyListState2, paddingValues3, z4, horizontal3, vertical2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 196608;
        top = vertical;
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                flingBehavior2 = flingBehavior;
                if (composerStartRestartGroup.changed(flingBehavior2)) {
                }
                i3 |= i13;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i3 |= i13;
        } else {
            flingBehavior2 = flingBehavior;
        }
        if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i8 = 8388608;
            } else {
                i8 = 4194304;
            }
            i3 |= i8;
        }
        if ((i3 & 4793491) != 4793490) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i9 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i11 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z2 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z2) {
                        end = arrangement.getStart();
                    } else {
                        end = arrangement.getEnd();
                    }
                    i3 &= -57345;
                    horizontal2 = end;
                }
                if (i6 != 0) {
                    top = Alignment.Companion.getTop();
                }
                if ((i2 & 64) != 0) {
                    i3 &= -3670017;
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal4 = horizontal2;
                    vertical3 = top;
                    lazyListState3 = lazyListStateRememberLazyListState;
                    z5 = z2;
                    modifier4 = modifier3;
                } else {
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical3 = top;
                    z5 = z2;
                    horizontal4 = horizontal2;
                    modifier4 = modifier3;
                    lazyListState3 = lazyListStateRememberLazyListState;
                }
            } else {
                if (i9 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i11 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z2 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z2) {
                        end = arrangement.getStart();
                    } else {
                        end = arrangement.getEnd();
                    }
                    i3 &= -57345;
                    horizontal2 = end;
                }
                if (i6 != 0) {
                    top = Alignment.Companion.getTop();
                }
                if ((i2 & 64) != 0) {
                    i3 &= -3670017;
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal4 = horizontal2;
                    vertical3 = top;
                    lazyListState3 = lazyListStateRememberLazyListState;
                    z5 = z2;
                    modifier4 = modifier3;
                } else {
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical3 = top;
                    z5 = z2;
                    horizontal4 = horizontal2;
                    modifier4 = modifier3;
                    lazyListState3 = lazyListStateRememberLazyListState;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(407929823, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:509)");
            }
            composer2 = composerStartRestartGroup;
            LazyRow(modifier4, lazyListState3, paddingValues4, z5, horizontal4, vertical3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            lazyListState2 = lazyListState3;
            paddingValues3 = paddingValues4;
            z4 = z5;
            horizontal3 = horizontal4;
            vertical2 = vertical3;
            flingBehavior3 = flingBehavior2;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier2 = modifier;
            lazyListState2 = lazyListStateRememberLazyListState;
            paddingValues3 = paddingValues2;
            z4 = z2;
            horizontal3 = horizontal2;
            vertical2 = top;
            flingBehavior3 = flingBehavior2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tq8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyDslKt.a(modifier2, lazyListState2, paddingValues3, z4, horizontal3, vertical2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:108:0x0130 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:109:0x0132  */
    /* JADX WARN: Code duplicated, block: B:110:0x0135  */
    /* JADX WARN: Code duplicated, block: B:113:0x013b  */
    /* JADX WARN: Code duplicated, block: B:115:0x0144  */
    /* JADX WARN: Code duplicated, block: B:116:0x014e  */
    /* JADX WARN: Code duplicated, block: B:118:0x0151  */
    /* JADX WARN: Code duplicated, block: B:121:0x0156  */
    /* JADX WARN: Code duplicated, block: B:123:0x015a  */
    /* JADX WARN: Code duplicated, block: B:124:0x015f  */
    /* JADX WARN: Code duplicated, block: B:127:0x0168  */
    /* JADX WARN: Code duplicated, block: B:130:0x0173  */
    /* JADX WARN: Code duplicated, block: B:131:0x0184  */
    /* JADX WARN: Code duplicated, block: B:134:0x0195  */
    /* JADX WARN: Code duplicated, block: B:137:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:139:0x01de  */
    /* JADX WARN: Code duplicated, block: B:142:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:144:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x0063  */
    /* JADX WARN: Code duplicated, block: B:39:0x0068  */
    /* JADX WARN: Code duplicated, block: B:41:0x006c  */
    /* JADX WARN: Code duplicated, block: B:43:0x0074  */
    /* JADX WARN: Code duplicated, block: B:44:0x0077  */
    /* JADX WARN: Code duplicated, block: B:48:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0082  */
    /* JADX WARN: Code duplicated, block: B:52:0x008a  */
    /* JADX WARN: Code duplicated, block: B:53:0x008d  */
    /* JADX WARN: Code duplicated, block: B:56:0x0093  */
    /* JADX WARN: Code duplicated, block: B:59:0x009b  */
    /* JADX WARN: Code duplicated, block: B:61:0x009f  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:65:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:72:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:78:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:83:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:84:0x00df  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:92:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:94:0x0109  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    public static final /* synthetic */ void LazyColumn(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, final Function1 function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListStateRememberLazyListState;
        PaddingValues paddingValues2;
        int i4;
        boolean z2;
        int i5;
        Arrangement.Vertical vertical2;
        int i6;
        Alignment.Horizontal start;
        int i7;
        FlingBehavior flingBehavior2;
        boolean z3;
        Composer composer2;
        final Modifier modifier2;
        final LazyListState lazyListState2;
        final PaddingValues paddingValues3;
        final boolean z4;
        final Arrangement.Vertical vertical3;
        final Alignment.Horizontal horizontal2;
        final FlingBehavior flingBehavior3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        PaddingValues paddingValuesM923PaddingValues0680j_4;
        PaddingValues paddingValues4;
        Alignment.Horizontal horizontal3;
        boolean z5;
        Arrangement.Vertical vertical4;
        Modifier modifier4;
        LazyListState lazyListState3;
        Arrangement arrangement;
        Arrangement.Vertical bottom;
        int i8;
        Composer composerStartRestartGroup = composer.startRestartGroup(-563353797);
        int i9 = i2 & 1;
        if (i9 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListStateRememberLazyListState = lazyListState;
                int i10 = composerStartRestartGroup.changed(lazyListStateRememberLazyListState) ? 32 : 16;
                i3 |= i10;
            } else {
                lazyListStateRememberLazyListState = lazyListState;
            }
            i3 |= i10;
        } else {
            lazyListStateRememberLazyListState = lazyListState;
        }
        int i11 = i2 & 4;
        if (i11 == 0) {
            if ((i & 384) == 0) {
                paddingValues2 = paddingValues;
                i3 |= composerStartRestartGroup.changed(paddingValues2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        vertical2 = vertical;
                        int i12 = composerStartRestartGroup.changed(vertical2) ? 16384 : 8192;
                        i3 |= i12;
                    } else {
                        vertical2 = vertical;
                    }
                    i3 |= i12;
                } else {
                    vertical2 = vertical;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        start = horizontal;
                        if (composerStartRestartGroup.changed(start)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            int i13 = composerStartRestartGroup.changed(flingBehavior2) ? IOUtil.MiB : 524288;
                            i3 |= i13;
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i3 |= i13;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i8 = 8388608;
                        } else {
                            i8 = 4194304;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 4793491) != 4793490) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 2) != 0) {
                                i3 &= -113;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            if ((i2 & 64) != 0) {
                                i3 &= -3670017;
                            }
                            paddingValues4 = paddingValues2;
                            horizontal3 = start;
                            z5 = z2;
                            vertical4 = vertical2;
                            modifier4 = modifier;
                        } else {
                            if (i9 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i2 & 2) != 0) {
                                lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                                i3 &= -113;
                            }
                            if (i11 != 0) {
                                paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                            } else {
                                paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                            }
                            if (i4 != 0) {
                                z2 = false;
                            }
                            if ((i2 & 16) != 0) {
                                arrangement = Arrangement.INSTANCE;
                                if (z2) {
                                    bottom = arrangement.getBottom();
                                } else {
                                    bottom = arrangement.getTop();
                                }
                                i3 &= -57345;
                                vertical2 = bottom;
                            }
                            if (i6 != 0) {
                                start = Alignment.Companion.getStart();
                            }
                            if ((i2 & 64) != 0) {
                                i3 &= -3670017;
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                                paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                                vertical4 = vertical2;
                                horizontal3 = start;
                                lazyListState3 = lazyListStateRememberLazyListState;
                                z5 = z2;
                                modifier4 = modifier3;
                            } else {
                                paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                                horizontal3 = start;
                                z5 = z2;
                                vertical4 = vertical2;
                                modifier4 = modifier3;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-563353797, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:455)");
                            }
                            composer2 = composerStartRestartGroup;
                            LazyColumn(modifier4, lazyListState3, paddingValues4, z5, vertical4, horizontal3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier4;
                            lazyListState2 = lazyListState3;
                            paddingValues3 = paddingValues4;
                            z4 = z5;
                            vertical3 = vertical4;
                            horizontal2 = horizontal3;
                            flingBehavior3 = flingBehavior2;
                        }
                        lazyListState3 = lazyListStateRememberLazyListState;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-563353797, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:455)");
                        }
                        composer2 = composerStartRestartGroup;
                        LazyColumn(modifier4, lazyListState3, paddingValues4, z5, vertical4, horizontal3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier4;
                        lazyListState2 = lazyListState3;
                        paddingValues3 = paddingValues4;
                        z4 = z5;
                        vertical3 = vertical4;
                        horizontal2 = horizontal3;
                        flingBehavior3 = flingBehavior2;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        lazyListState2 = lazyListStateRememberLazyListState;
                        paddingValues3 = paddingValues2;
                        z4 = z2;
                        vertical3 = vertical2;
                        horizontal2 = start;
                        flingBehavior3 = flingBehavior2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pq8
                            public final Object invoke(Object obj, Object obj2) {
                                return LazyDslKt.d(modifier2, lazyListState2, paddingValues3, z4, vertical3, horizontal2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 196608;
                start = horizontal;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i13;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i3 |= i8;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                            vertical2 = bottom;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = vertical2;
                            horizontal3 = start;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal3 = start;
                            z5 = z2;
                            vertical4 = vertical2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                            vertical2 = bottom;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = vertical2;
                            horizontal3 = start;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal3 = start;
                            z5 = z2;
                            vertical4 = vertical2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-563353797, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:455)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyColumn(modifier4, lazyListState3, paddingValues4, z5, vertical4, horizontal3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z4 = z5;
                    vertical3 = vertical4;
                    horizontal2 = horizontal3;
                    flingBehavior3 = flingBehavior2;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z4 = z2;
                    vertical3 = vertical2;
                    horizontal2 = start;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.d(modifier2, lazyListState2, paddingValues3, z4, vertical3, horizontal2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    vertical2 = vertical;
                    if (composerStartRestartGroup.changed(vertical2)) {
                    }
                    i3 |= i12;
                } else {
                    vertical2 = vertical;
                }
                i3 |= i12;
            } else {
                vertical2 = vertical;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    start = horizontal;
                    if (composerStartRestartGroup.changed(start)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i13;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i3 |= i8;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                            vertical2 = bottom;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = vertical2;
                            horizontal3 = start;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal3 = start;
                            z5 = z2;
                            vertical4 = vertical2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                            vertical2 = bottom;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = vertical2;
                            horizontal3 = start;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal3 = start;
                            z5 = z2;
                            vertical4 = vertical2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-563353797, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:455)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyColumn(modifier4, lazyListState3, paddingValues4, z5, vertical4, horizontal3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z4 = z5;
                    vertical3 = vertical4;
                    horizontal2 = horizontal3;
                    flingBehavior3 = flingBehavior2;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z4 = z2;
                    vertical3 = vertical2;
                    horizontal2 = start;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.d(modifier2, lazyListState2, paddingValues3, z4, vertical3, horizontal2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            start = horizontal;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i13;
            } else {
                flingBehavior2 = flingBehavior;
            }
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i3 |= i8;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                        vertical2 = bottom;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = vertical2;
                        horizontal3 = start;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal3 = start;
                        z5 = z2;
                        vertical4 = vertical2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                } else {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                        vertical2 = bottom;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = vertical2;
                        horizontal3 = start;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal3 = start;
                        z5 = z2;
                        vertical4 = vertical2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-563353797, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:455)");
                }
                composer2 = composerStartRestartGroup;
                LazyColumn(modifier4, lazyListState3, paddingValues4, z5, vertical4, horizontal3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z4 = z5;
                vertical3 = vertical4;
                horizontal2 = horizontal3;
                flingBehavior3 = flingBehavior2;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z4 = z2;
                vertical3 = vertical2;
                horizontal2 = start;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.d(modifier2, lazyListState2, paddingValues3, z4, vertical3, horizontal2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 384;
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    vertical2 = vertical;
                    if (composerStartRestartGroup.changed(vertical2)) {
                    }
                    i3 |= i12;
                } else {
                    vertical2 = vertical;
                }
                i3 |= i12;
            } else {
                vertical2 = vertical;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    start = horizontal;
                    if (composerStartRestartGroup.changed(start)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        flingBehavior2 = flingBehavior;
                        if (composerStartRestartGroup.changed(flingBehavior2)) {
                        }
                        i3 |= i13;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i3 |= i8;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                            vertical2 = bottom;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = vertical2;
                            horizontal3 = start;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal3 = start;
                            z5 = z2;
                            vertical4 = vertical2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i2 & 2) != 0) {
                            lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                            i3 &= -113;
                        }
                        if (i11 != 0) {
                            paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                        } else {
                            paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                        }
                        if (i4 != 0) {
                            z2 = false;
                        }
                        if ((i2 & 16) != 0) {
                            arrangement = Arrangement.INSTANCE;
                            if (z2) {
                                bottom = arrangement.getTop();
                            } else {
                                bottom = arrangement.getBottom();
                            }
                            i3 &= -57345;
                            vertical2 = bottom;
                        }
                        if (i6 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            vertical4 = vertical2;
                            horizontal3 = start;
                            lazyListState3 = lazyListStateRememberLazyListState;
                            z5 = z2;
                            modifier4 = modifier3;
                        } else {
                            paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                            horizontal3 = start;
                            z5 = z2;
                            vertical4 = vertical2;
                            modifier4 = modifier3;
                            lazyListState3 = lazyListStateRememberLazyListState;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-563353797, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:455)");
                    }
                    composer2 = composerStartRestartGroup;
                    LazyColumn(modifier4, lazyListState3, paddingValues4, z5, vertical4, horizontal3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier4;
                    lazyListState2 = lazyListState3;
                    paddingValues3 = paddingValues4;
                    z4 = z5;
                    vertical3 = vertical4;
                    horizontal2 = horizontal3;
                    flingBehavior3 = flingBehavior2;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    lazyListState2 = lazyListStateRememberLazyListState;
                    paddingValues3 = paddingValues2;
                    z4 = z2;
                    vertical3 = vertical2;
                    horizontal2 = start;
                    flingBehavior3 = flingBehavior2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pq8
                        public final Object invoke(Object obj, Object obj2) {
                            return LazyDslKt.d(modifier2, lazyListState2, paddingValues3, z4, vertical3, horizontal2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            start = horizontal;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i13;
            } else {
                flingBehavior2 = flingBehavior;
            }
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i3 |= i8;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                        vertical2 = bottom;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = vertical2;
                        horizontal3 = start;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal3 = start;
                        z5 = z2;
                        vertical4 = vertical2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                } else {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                        vertical2 = bottom;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = vertical2;
                        horizontal3 = start;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal3 = start;
                        z5 = z2;
                        vertical4 = vertical2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-563353797, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:455)");
                }
                composer2 = composerStartRestartGroup;
                LazyColumn(modifier4, lazyListState3, paddingValues4, z5, vertical4, horizontal3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z4 = z5;
                vertical3 = vertical4;
                horizontal2 = horizontal3;
                flingBehavior3 = flingBehavior2;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z4 = z2;
                vertical3 = vertical2;
                horizontal2 = start;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.d(modifier2, lazyListState2, paddingValues3, z4, vertical3, horizontal2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                vertical2 = vertical;
                if (composerStartRestartGroup.changed(vertical2)) {
                }
                i3 |= i12;
            } else {
                vertical2 = vertical;
            }
            i3 |= i12;
        } else {
            vertical2 = vertical;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                start = horizontal;
                if (composerStartRestartGroup.changed(start)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    flingBehavior2 = flingBehavior;
                    if (composerStartRestartGroup.changed(flingBehavior2)) {
                    }
                    i3 |= i13;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                i3 |= i13;
            } else {
                flingBehavior2 = flingBehavior;
            }
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i3 |= i8;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                        vertical2 = bottom;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = vertical2;
                        horizontal3 = start;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal3 = start;
                        z5 = z2;
                        vertical4 = vertical2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                } else {
                    if (i9 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i2 & 2) != 0) {
                        lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                        i3 &= -113;
                    }
                    if (i11 != 0) {
                        paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                    } else {
                        paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                    }
                    if (i4 != 0) {
                        z2 = false;
                    }
                    if ((i2 & 16) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (z2) {
                            bottom = arrangement.getTop();
                        } else {
                            bottom = arrangement.getBottom();
                        }
                        i3 &= -57345;
                        vertical2 = bottom;
                    }
                    if (i6 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        vertical4 = vertical2;
                        horizontal3 = start;
                        lazyListState3 = lazyListStateRememberLazyListState;
                        z5 = z2;
                        modifier4 = modifier3;
                    } else {
                        paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                        horizontal3 = start;
                        z5 = z2;
                        vertical4 = vertical2;
                        modifier4 = modifier3;
                        lazyListState3 = lazyListStateRememberLazyListState;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-563353797, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:455)");
                }
                composer2 = composerStartRestartGroup;
                LazyColumn(modifier4, lazyListState3, paddingValues4, z5, vertical4, horizontal3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
                lazyListState2 = lazyListState3;
                paddingValues3 = paddingValues4;
                z4 = z5;
                vertical3 = vertical4;
                horizontal2 = horizontal3;
                flingBehavior3 = flingBehavior2;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                lazyListState2 = lazyListStateRememberLazyListState;
                paddingValues3 = paddingValues2;
                z4 = z2;
                vertical3 = vertical2;
                horizontal2 = start;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pq8
                    public final Object invoke(Object obj, Object obj2) {
                        return LazyDslKt.d(modifier2, lazyListState2, paddingValues3, z4, vertical3, horizontal2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 196608;
        start = horizontal;
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                flingBehavior2 = flingBehavior;
                if (composerStartRestartGroup.changed(flingBehavior2)) {
                }
                i3 |= i13;
            } else {
                flingBehavior2 = flingBehavior;
            }
            i3 |= i13;
        } else {
            flingBehavior2 = flingBehavior;
        }
        if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i8 = 8388608;
            } else {
                i8 = 4194304;
            }
            i3 |= i8;
        }
        if ((i3 & 4793491) != 4793490) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i9 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i11 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z2 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z2) {
                        bottom = arrangement.getTop();
                    } else {
                        bottom = arrangement.getBottom();
                    }
                    i3 &= -57345;
                    vertical2 = bottom;
                }
                if (i6 != 0) {
                    start = Alignment.Companion.getStart();
                }
                if ((i2 & 64) != 0) {
                    i3 &= -3670017;
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical4 = vertical2;
                    horizontal3 = start;
                    lazyListState3 = lazyListStateRememberLazyListState;
                    z5 = z2;
                    modifier4 = modifier3;
                } else {
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal3 = start;
                    z5 = z2;
                    vertical4 = vertical2;
                    modifier4 = modifier3;
                    lazyListState3 = lazyListStateRememberLazyListState;
                }
            } else {
                if (i9 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if ((i2 & 2) != 0) {
                    lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
                    i3 &= -113;
                }
                if (i11 != 0) {
                    paddingValuesM923PaddingValues0680j_4 = PaddingKt.m923PaddingValues0680j_4(Dp.constructor-impl(0.0f));
                } else {
                    paddingValuesM923PaddingValues0680j_4 = paddingValues2;
                }
                if (i4 != 0) {
                    z2 = false;
                }
                if ((i2 & 16) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (z2) {
                        bottom = arrangement.getTop();
                    } else {
                        bottom = arrangement.getBottom();
                    }
                    i3 &= -57345;
                    vertical2 = bottom;
                }
                if (i6 != 0) {
                    start = Alignment.Companion.getStart();
                }
                if ((i2 & 64) != 0) {
                    i3 &= -3670017;
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    vertical4 = vertical2;
                    horizontal3 = start;
                    lazyListState3 = lazyListStateRememberLazyListState;
                    z5 = z2;
                    modifier4 = modifier3;
                } else {
                    paddingValues4 = paddingValuesM923PaddingValues0680j_4;
                    horizontal3 = start;
                    z5 = z2;
                    vertical4 = vertical2;
                    modifier4 = modifier3;
                    lazyListState3 = lazyListStateRememberLazyListState;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-563353797, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:455)");
            }
            composer2 = composerStartRestartGroup;
            LazyColumn(modifier4, lazyListState3, paddingValues4, z5, vertical4, horizontal3, flingBehavior2, true, null, function1, composer2, (i3 & 14) | 12582912 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            lazyListState2 = lazyListState3;
            paddingValues3 = paddingValues4;
            z4 = z5;
            vertical3 = vertical4;
            horizontal2 = horizontal3;
            flingBehavior3 = flingBehavior2;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier2 = modifier;
            lazyListState2 = lazyListStateRememberLazyListState;
            paddingValues3 = paddingValues2;
            z4 = z2;
            vertical3 = vertical2;
            horizontal2 = start;
            flingBehavior3 = flingBehavior2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pq8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyDslKt.d(modifier2, lazyListState2, paddingValues3, z4, vertical3, horizontal2, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
