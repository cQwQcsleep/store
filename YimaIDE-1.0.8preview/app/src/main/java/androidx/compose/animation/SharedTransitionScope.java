package androidx.compose.animation;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.ApproachMeasureScope;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadScope;
import androidx.compose.ui.layout.LookaheadScopeKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001:\u0005./#0-J\u001c\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH&J\u001c\u0010\t\u001a\u00020\u0006*\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0016J&\u0010\n\u001a\u00020\u0006*\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\bH&JN\u0010\u000e\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H&Jl\u0010\u001a\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H&JN\u0010!\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%H&J\u0015\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020(H\u0017¢\u0006\u0002\u0010)J\u001d\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020(2\u0006\u0010*\u001a\u00020+H\u0017¢\u0006\u0002\u0010,J\b\u0010-\u001a\u00020+H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00061À\u0006\u0001"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope;", "Landroidx/compose/ui/layout/LookaheadScope;", "isTransitionActive", "", "()Z", "skipToLookaheadSize", "Landroidx/compose/ui/Modifier;", "enabled", "Lkotlin/Function0;", "skipToLookaheadPosition", "renderInSharedTransitionScopeOverlay", "zIndexInOverlay", "", "renderInOverlay", "sharedElement", "sharedContentState", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "animatedVisibilityScope", "Landroidx/compose/animation/AnimatedVisibilityScope;", "boundsTransform", "Landroidx/compose/animation/BoundsTransform;", "placeholderSize", "Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "renderInOverlayDuringTransition", "clipInOverlayDuringTransition", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "sharedBounds", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "resizeMode", "Landroidx/compose/animation/SharedTransitionScope$ResizeMode;", "sharedElementWithCallerManagedVisibility", "visible", "OverlayClip", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "rememberSharedContentState", "key", "", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "config", "Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;", "(Ljava/lang/Object;Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "SharedContentConfig", "PlaceholderSize", "ResizeMode", "SharedContentState", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SharedTransitionScope extends LookaheadScope {

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "", "getClipPath", "Landroidx/compose/ui/graphics/Path;", "sharedContentState", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface OverlayClip {
        Path getClipPath(SharedContentState sharedContentState, Rect bounds, LayoutDirection layoutDirection, Density density);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bæ\u0080\u0001\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H&¢\u0006\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "", "calculateSize", "Landroidx/compose/ui/unit/IntSize;", "contentSize", "animatedSize", "calculateSize-JyjRU_E", "(JJ)J", "Companion", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface PlaceholderSize {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize$Companion;", "", "<init>", "()V", "AnimatedSize", "Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "getAnimatedSize", "()Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "ContentSize", "getContentSize", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static final PlaceholderSize AnimatedSize = new PlaceholderSize() { // from class: androidx.compose.animation.SharedTransitionScope$PlaceholderSize$Companion$AnimatedSize$1
                @Override // androidx.compose.animation.SharedTransitionScope.PlaceholderSize
                /* JADX INFO: renamed from: calculateSize-JyjRU_E */
                public final long mo146calculateSizeJyjRU_E(long j, long j2) {
                    return j2;
                }
            };
            private static final PlaceholderSize ContentSize = new PlaceholderSize() { // from class: androidx.compose.animation.SharedTransitionScope$PlaceholderSize$Companion$ContentSize$1
                @Override // androidx.compose.animation.SharedTransitionScope.PlaceholderSize
                /* JADX INFO: renamed from: calculateSize-JyjRU_E */
                public final long mo146calculateSizeJyjRU_E(long j, long j2) {
                    return j;
                }
            };

            private Companion() {
            }

            public final PlaceholderSize getAnimatedSize() {
                return AnimatedSize;
            }

            public final PlaceholderSize getContentSize() {
                return ContentSize;
            }
        }

        /* JADX INFO: renamed from: calculateSize-JyjRU_E, reason: not valid java name */
        long mo146calculateSizeJyjRU_E(long contentSize, long animatedSize);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002\u0082\u0001\u0002\u0003\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$ResizeMode;", "", "Companion", "Landroidx/compose/animation/RemeasureImpl;", "Landroidx/compose/animation/ScaleToBoundsImpl;", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface ResizeMode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$ResizeMode$Companion;", "", "<init>", "()V", "RemeasureToBounds", "Landroidx/compose/animation/SharedTransitionScope$ResizeMode;", "getRemeasureToBounds", "()Landroidx/compose/animation/SharedTransitionScope$ResizeMode;", "scaleToBounds", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alignment", "Landroidx/compose/ui/Alignment;", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static final ResizeMode RemeasureToBounds = RemeasureImpl.INSTANCE;

            private Companion() {
            }

            public static /* synthetic */ ResizeMode scaleToBounds$default(Companion companion, ContentScale contentScale, Alignment alignment, int i, Object obj) {
                if ((i & 1) != 0) {
                    contentScale = ContentScale.Companion.getFillWidth();
                }
                if ((i & 2) != 0) {
                    alignment = Alignment.Companion.getCenter();
                }
                return companion.scaleToBounds(contentScale, alignment);
            }

            public final ResizeMode getRemeasureToBounds() {
                return RemeasureToBounds;
            }

            public final ResizeMode scaleToBounds(ContentScale contentScale, Alignment alignment) {
                return SharedTransitionScopeKt.ScaleToBoundsCached(contentScale, alignment);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J%\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;", "", "isEnabled", "", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "(Landroidx/compose/animation/SharedTransitionScope$SharedContentState;)Z", "shouldKeepEnabledForOngoingAnimation", "getShouldKeepEnabledForOngoingAnimation", "()Z", "alternativeTargetBoundsInTransitionScopeAfterRemoval", "Landroidx/compose/ui/geometry/Rect;", "targetBoundsBeforeRemoval", "sharedTransitionLayoutSize", "Landroidx/compose/ui/geometry/Size;", "alternativeTargetBoundsInTransitionScopeAfterRemoval-cSwnlzA", "(Landroidx/compose/animation/SharedTransitionScope$SharedContentState;Landroidx/compose/ui/geometry/Rect;J)Landroidx/compose/ui/geometry/Rect;", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface SharedContentConfig {
        /* JADX INFO: renamed from: alternativeTargetBoundsInTransitionScopeAfterRemoval-cSwnlzA, reason: not valid java name */
        default Rect m147alternativeTargetBoundsInTransitionScopeAfterRemovalcSwnlzA(SharedContentState sharedContentState, Rect rect, long j) {
            return null;
        }

        default boolean getShouldKeepEnabledForOngoingAnimation() {
            return true;
        }

        default boolean isEnabled(SharedContentState sharedContentState) {
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Modifier renderInSharedTransitionScopeOverlay$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, float f, Function0 function0, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: renderInSharedTransitionScopeOverlay");
            return null;
        }
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            function0 = new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScope.renderInSharedTransitionScopeOverlay.1
                {
                    super(0);
                }

                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                public final Boolean m149invoke() {
                    return Boolean.valueOf(SharedTransitionScope.this.isTransitionActive());
                }
            };
        }
        return sharedTransitionScope.renderInSharedTransitionScopeOverlay(modifier, f, function0);
    }

    static /* synthetic */ Modifier sharedBounds$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, SharedContentState sharedContentState, AnimatedVisibilityScope animatedVisibilityScope, EnterTransition enterTransition, ExitTransition exitTransition, BoundsTransform boundsTransform, ResizeMode resizeMode, PlaceholderSize placeholderSize, boolean z, float f, OverlayClip overlayClip, int i, Object obj) {
        if (obj == null) {
            return sharedTransitionScope.sharedBounds(modifier, sharedContentState, animatedVisibilityScope, (i & 4) != 0 ? EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null) : enterTransition, (i & 8) != 0 ? EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null) : exitTransition, (i & 16) != 0 ? SharedTransitionDefaults.INSTANCE.getBoundsTransform() : boundsTransform, (i & 32) != 0 ? ResizeMode.INSTANCE.scaleToBounds(ContentScale.Companion.getFillWidth(), Alignment.Companion.getCenter()) : resizeMode, (i & 64) != 0 ? PlaceholderSize.INSTANCE.getContentSize() : placeholderSize, (i & 128) != 0 ? true : z, (i & 256) != 0 ? 0.0f : f, (i & 512) != 0 ? SharedTransitionScopeKt.ParentClip : overlayClip);
        }
        c41.a("Super calls with default arguments not supported in this target, function: sharedBounds");
        return null;
    }

    static /* synthetic */ Modifier sharedElement$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, SharedContentState sharedContentState, AnimatedVisibilityScope animatedVisibilityScope, BoundsTransform boundsTransform, PlaceholderSize placeholderSize, boolean z, float f, OverlayClip overlayClip, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: sharedElement");
            return null;
        }
        if ((i & 4) != 0) {
            boundsTransform = SharedTransitionDefaults.INSTANCE.getBoundsTransform();
        }
        BoundsTransform boundsTransform2 = boundsTransform;
        if ((i & 8) != 0) {
            placeholderSize = PlaceholderSize.INSTANCE.getContentSize();
        }
        return sharedTransitionScope.sharedElement(modifier, sharedContentState, animatedVisibilityScope, boundsTransform2, placeholderSize, (i & 16) != 0 ? true : z, (i & 32) != 0 ? 0.0f : f, (i & 64) != 0 ? SharedTransitionScopeKt.ParentClip : overlayClip);
    }

    static /* synthetic */ Modifier sharedElementWithCallerManagedVisibility$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, SharedContentState sharedContentState, boolean z, BoundsTransform boundsTransform, PlaceholderSize placeholderSize, boolean z2, float f, OverlayClip overlayClip, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: sharedElementWithCallerManagedVisibility");
            return null;
        }
        if ((i & 4) != 0) {
            boundsTransform = SharedTransitionDefaults.INSTANCE.getBoundsTransform();
        }
        BoundsTransform boundsTransform2 = boundsTransform;
        if ((i & 8) != 0) {
            placeholderSize = PlaceholderSize.INSTANCE.getContentSize();
        }
        return sharedTransitionScope.sharedElementWithCallerManagedVisibility(modifier, sharedContentState, z, boundsTransform2, placeholderSize, (i & 16) != 0 ? true : z2, (i & 32) != 0 ? 0.0f : f, (i & 64) != 0 ? SharedTransitionScopeKt.ParentClip : overlayClip);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Modifier skipToLookaheadPosition$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, Function0 function0, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: skipToLookaheadPosition");
            return null;
        }
        if ((i & 1) != 0) {
            function0 = new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScope.skipToLookaheadPosition.1
                {
                    super(0);
                }

                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                public final Boolean m150invoke() {
                    return Boolean.valueOf(SharedTransitionScope.this.isTransitionActive());
                }
            };
        }
        return sharedTransitionScope.skipToLookaheadPosition(modifier, function0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Modifier skipToLookaheadSize$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, Function0 function0, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: skipToLookaheadSize");
            return null;
        }
        if ((i & 1) != 0) {
            function0 = new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScope.skipToLookaheadSize.1
                {
                    super(0);
                }

                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                public final Boolean m153invoke() {
                    return Boolean.valueOf(SharedTransitionScope.this.isTransitionActive());
                }
            };
        }
        return sharedTransitionScope.skipToLookaheadSize(modifier, function0);
    }

    OverlayClip OverlayClip(Shape clipShape);

    default SharedContentConfig SharedContentConfig() {
        return CachedSharedContentConfig.INSTANCE;
    }

    boolean isTransitionActive();

    default SharedContentState rememberSharedContentState(Object obj, SharedContentConfig sharedContentConfig, Composer composer, int i) {
        composer.startReplaceGroup(-148945892);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-148945892, i, -1, "androidx.compose.animation.SharedTransitionScope.rememberSharedContentState (SharedTransitionScope.kt:818)");
        }
        boolean zChanged = composer.changed(obj);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new SharedContentState(obj, sharedContentConfig);
            composer.updateRememberedValue(objRememberedValue);
        }
        SharedContentState sharedContentState = (SharedContentState) objRememberedValue;
        sharedContentState.setConfig$animation(sharedContentConfig);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return sharedContentState;
    }

    Modifier renderInSharedTransitionScopeOverlay(Modifier modifier, float f, Function0<Boolean> function0);

    Modifier sharedBounds(Modifier modifier, SharedContentState sharedContentState, AnimatedVisibilityScope animatedVisibilityScope, EnterTransition enterTransition, ExitTransition exitTransition, BoundsTransform boundsTransform, ResizeMode resizeMode, PlaceholderSize placeholderSize, boolean z, float f, OverlayClip overlayClip);

    Modifier sharedElement(Modifier modifier, SharedContentState sharedContentState, AnimatedVisibilityScope animatedVisibilityScope, BoundsTransform boundsTransform, PlaceholderSize placeholderSize, boolean z, float f, OverlayClip overlayClip);

    Modifier sharedElementWithCallerManagedVisibility(Modifier modifier, SharedContentState sharedContentState, boolean z, BoundsTransform boundsTransform, PlaceholderSize placeholderSize, boolean z2, float f, OverlayClip overlayClip);

    default Modifier skipToLookaheadPosition(Modifier modifier, final Function0<Boolean> function0) {
        return LookaheadScopeKt.approachLayout(modifier, new Function1<IntSize, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScope.skipToLookaheadPosition.2
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m151invokeozmzZPI(((IntSize) obj).unbox-impl());
            }

            /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
            public final Boolean m151invokeozmzZPI(long j) {
                return Boolean.FALSE;
            }
        }, new Function2<Placeable.PlacementScope, LayoutCoordinates, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScope.skipToLookaheadPosition.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            public final Boolean invoke(Placeable.PlacementScope placementScope, LayoutCoordinates layoutCoordinates) {
                return (Boolean) function0.invoke();
            }
        }, new Function3<ApproachMeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.SharedTransitionScope.skipToLookaheadPosition.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return m152invoke3p2s80s((ApproachMeasureScope) obj, (Measurable) obj2, ((Constraints) obj3).unbox-impl());
            }

            /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m152invoke3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j) {
                final Placeable placeable = measurable.measure-BRTryo0(j);
                final Function0<Boolean> function1 = function0;
                final SharedTransitionScope sharedTransitionScope = this;
                return MeasureScope.layout$default(approachMeasureScope, placeable.getWidth(), placeable.getHeight(), (Map) null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SharedTransitionScope$skipToLookaheadPosition$4$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public final void invoke(Placeable.PlacementScope placementScope) {
                        if (!((Boolean) function1.invoke()).booleanValue()) {
                            Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
                            return;
                        }
                        LayoutCoordinates coordinates = placementScope.getCoordinates();
                        if (coordinates == null) {
                            Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
                            return;
                        }
                        SharedTransitionScope sharedTransitionScope2 = sharedTransitionScope;
                        Placeable.PlacementScope.place-70tqf50$default(placementScope, placeable, IntOffsetKt.round-k-4lQ0M(Offset.minus-MK-Hz9U(coordinates.localPositionOf-R5De75A(sharedTransitionScope2.getLookaheadScopeCoordinates(placementScope), Offset.minus-MK-Hz9U(LookaheadScope.m1857localLookaheadPositionOfauaQtc$default(sharedTransitionScope2, sharedTransitionScope2.getLookaheadScopeCoordinates(placementScope), coordinates, 0L, false, 6, null), LayoutCoordinates.localPositionOf-S_NoaFU$default(sharedTransitionScope2.getLookaheadScopeCoordinates(placementScope), coordinates, 0L, false, 6, (Object) null))), LayoutCoordinates.localPositionOf-S_NoaFU$default(coordinates, sharedTransitionScope2.getLookaheadScopeCoordinates(placementScope), 0L, false, 6, (Object) null))), 0.0f, 2, (Object) null);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((Placeable.PlacementScope) obj);
                        return Unit.INSTANCE;
                    }
                }, 4, (Object) null);
            }
        });
    }

    Modifier skipToLookaheadSize(Modifier modifier, Function0<Boolean> function0);

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00048@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\fR\u0011\u0010\u0016\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\fR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0013\u0010!\u001a\u0004\u0018\u00010\u00008F¢\u0006\u0006\u001a\u0004\b\"\u0010#R/\u0010%\u001a\u0004\u0018\u00010$2\b\u0010\r\u001a\u0004\u0018\u00010$8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010\u0013\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010'¨\u0006-"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "", "key", "config", "Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;", "<init>", "(Ljava/lang/Object;Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;)V", "getKey", "()Ljava/lang/Object;", "isAnimating", "", "isAnimating$animation", "()Z", "<set-?>", "getConfig$animation", "()Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;", "setConfig$animation", "(Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;)V", "config$delegate", "Landroidx/compose/runtime/MutableState;", "isEnabledByUser", "isEnabledByUser$animation", "isMatchFound", "prepareTransitionWithInitialVelocity", "", "initialVelocity", "Landroidx/compose/ui/unit/Velocity;", "prepareTransitionWithInitialVelocity-TH1AsA0", "(J)V", "clipPathInOverlay", "Landroidx/compose/ui/graphics/Path;", "getClipPathInOverlay", "()Landroidx/compose/ui/graphics/Path;", "parentSharedContentState", "getParentSharedContentState", "()Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "Landroidx/compose/animation/SharedElementEntry;", "internalState", "getInternalState$animation", "()Landroidx/compose/animation/SharedElementEntry;", "setInternalState$animation", "(Landroidx/compose/animation/SharedElementEntry;)V", "internalState$delegate", "nonNullInternalState", "getNonNullInternalState", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class SharedContentState {
        public static final int $stable = 8;

        /* JADX INFO: renamed from: config$delegate, reason: from kotlin metadata */
        private final MutableState config;

        /* JADX INFO: renamed from: internalState$delegate, reason: from kotlin metadata */
        private final MutableState internalState;
        private final Object key;

        public SharedContentState(Object obj, SharedContentConfig sharedContentConfig) {
            this.key = obj;
            this.config = SnapshotStateKt.mutableStateOf$default(sharedContentConfig, (SnapshotMutationPolicy) null, 2, (Object) null);
            this.internalState = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        }

        private final SharedElementEntry getNonNullInternalState() {
            SharedElementEntry internalState$animation = getInternalState$animation();
            if (internalState$animation != null) {
                return internalState$animation;
            }
            w01.a("Error: SharedContentState has not been added to a sharedElement/sharedBoundsmodifier yet. Therefore the internal state has not been initialized.");
            return null;
        }

        public final Path getClipPathInOverlay() {
            return getNonNullInternalState().getClipPathInOverlay();
        }

        public final SharedContentConfig getConfig$animation() {
            return (SharedContentConfig) this.config.getValue();
        }

        public final SharedElementEntry getInternalState$animation() {
            return (SharedElementEntry) this.internalState.getValue();
        }

        public final Object getKey() {
            return this.key;
        }

        public final SharedContentState getParentSharedContentState() {
            SharedElementEntry parentState = getNonNullInternalState().getParentState();
            if (parentState != null) {
                return parentState.getUserState();
            }
            return null;
        }

        public final boolean isAnimating$animation() {
            BoundsAnimation boundsAnimation;
            SharedElementEntry internalState$animation = getInternalState$animation();
            return ((internalState$animation == null || (boundsAnimation = internalState$animation.getBoundsAnimation()) == null) ? null : boundsAnimation.getAnimationState()) != null;
        }

        public final boolean isEnabledByUser$animation() {
            SharedContentConfig config$animation = getConfig$animation();
            if (config$animation.isEnabled(this)) {
                return true;
            }
            return isAnimating$animation() && config$animation.getShouldKeepEnabledForOngoingAnimation();
        }

        public final boolean isMatchFound() {
            SharedElement sharedElement;
            SharedElementEntry internalState$animation = getInternalState$animation();
            if (internalState$animation == null || (sharedElement = internalState$animation.getSharedElement()) == null) {
                return false;
            }
            return sharedElement.getFoundMatch();
        }

        /* JADX INFO: renamed from: prepareTransitionWithInitialVelocity-TH1AsA0, reason: not valid java name */
        public final void m148prepareTransitionWithInitialVelocityTH1AsA0(long initialVelocity) {
            SharedElementEntry nonNullInternalState = getNonNullInternalState();
            if (getConfig$animation().isEnabled(this)) {
                nonNullInternalState.getSharedElement().m142updateExitVelocityTH1AsA0$animation(initialVelocity);
            }
        }

        public final void setConfig$animation(SharedContentConfig sharedContentConfig) {
            this.config.setValue(sharedContentConfig);
        }

        public final void setInternalState$animation(SharedElementEntry sharedElementEntry) {
            this.internalState.setValue(sharedElementEntry);
        }

        public /* synthetic */ SharedContentState(Object obj, SharedContentConfig sharedContentConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, (i & 2) != 0 ? SharedTransitionDefaults.SharedContentConfig.INSTANCE : sharedContentConfig);
        }
    }

    default SharedContentState rememberSharedContentState(Object obj, Composer composer, int i) {
        composer.startReplaceGroup(800730162);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(800730162, i, -1, "androidx.compose.animation.SharedTransitionScope.rememberSharedContentState (SharedTransitionScope.kt:796)");
        }
        SharedContentState sharedContentStateRememberSharedContentState = rememberSharedContentState(obj, SharedTransitionDefaults.SharedContentConfig.INSTANCE, composer, ((i << 3) & 896) | (i & 14) | 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return sharedContentStateRememberSharedContentState;
    }
}
