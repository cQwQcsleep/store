package androidx.window.embedding;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.WindowMetrics;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.util.Consumer;
import androidx.window.WindowSdkExtensions;
import androidx.window.embedding.OverlayControllerImpl;
import androidx.window.extensions.core.util.function.Function;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import androidx.window.extensions.embedding.ActivityStackAttributes;
import androidx.window.extensions.embedding.ActivityStackAttributesCalculatorParams;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetricsCalculator;
import androidx.window.layout.adapter.extensions.ExtensionsWindowLayoutInfoAdapter;
import androidx.window.layout.util.DensityCompatHelper;
import androidx.window.reflection.Consumer2;
import defpackage.nab;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0011\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150\"H\u0003J7\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u00152\b\u0010%\u001a\u0004\u0018\u00010\r2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0001¢\u0006\u0002\b,J\u0017\u0010-\u001a\u0004\u0018\u00010\r2\u0006\u0010.\u001a\u00020\u0015H\u0011¢\u0006\u0002\b/J\u001d\u00100\u001a\u00020 2\u0006\u0010.\u001a\u00020\u00152\u0006\u00101\u001a\u00020\rH\u0010¢\u0006\u0002\b2J\u0014\u00103\u001a\u000204*\u00020\r2\u0006\u00105\u001a\u000206H\u0002J\u0018\u00107\u001a\b\u0012\u0004\u0012\u00020\u00190\u001e*\b\u0012\u0004\u0012\u00020\u00190\u001eH\u0002J&\u00108\u001a\u00020 2\u0006\u0010.\u001a\u00020\u00152\u0006\u00109\u001a\u00020:2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016J\f\u0010<\u001a\u00020\u001c*\u00020\u0019H\u0002J\u0016\u0010=\u001a\u00020 2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000RB\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b8@@@X\u0081\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\r0\u00148\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\r0\u00178\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00190\u00178\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R.\u0010\u001a\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u001e0\u001d0\u00178\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Landroidx/window/embedding/OverlayControllerImpl;", "", "embeddingExtension", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "adapter", "Landroidx/window/embedding/EmbeddingAdapter;", "<init>", "(Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;Landroidx/window/embedding/EmbeddingAdapter;)V", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "value", "Lkotlin/Function1;", "Landroidx/window/embedding/OverlayAttributesCalculatorParams;", "Landroidx/window/embedding/OverlayAttributes;", "overlayAttributesCalculator", "getOverlayAttributesCalculator$window_release", "()Lkotlin/jvm/functions/Function1;", "setOverlayAttributesCalculator$window_release", "(Lkotlin/jvm/functions/Function1;)V", "overlayTagToDefaultAttributesMap", "", "", "overlayTagToCurrentAttributesMap", "Landroid/util/ArrayMap;", "overlayTagToContainerMap", "Landroidx/window/extensions/embedding/ActivityStack;", "overlayInfoToActivityStackCallbackMap", "Landroidx/core/util/Consumer;", "Landroidx/window/embedding/OverlayInfo;", "Landroidx/window/extensions/core/util/function/Consumer;", "", "cleanUpDismissedOverlayContainerRecords", "", "lastOverlayTags", "", "calculateOverlayAttributes", "tag", "initialOverlayAttrs", "windowMetrics", "Landroidx/window/layout/WindowMetrics;", "configuration", "Landroid/content/res/Configuration;", "windowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "calculateOverlayAttributes$window_release", "getUpdatedOverlayAttributes", "overlayTag", "getUpdatedOverlayAttributes$window_release", "updateOverlayAttributes", "overlayAttributes", "updateOverlayAttributes$window_release", "toActivityStackAttributes", "Landroidx/window/extensions/embedding/ActivityStackAttributes;", "parentContainerInfo", "Landroidx/window/extensions/embedding/ParentContainerInfo;", "getOverlayContainers", "addOverlayInfoCallback", "executor", "Ljava/util/concurrent/Executor;", "overlayInfoCallback", "toOverlayInfo", "removeOverlayInfoCallback", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public class OverlayControllerImpl {
    private final EmbeddingAdapter adapter;
    private final ActivityEmbeddingComponent embeddingExtension;
    private final ReentrantLock globalLock;
    private Function1<? super OverlayAttributesCalculatorParams, OverlayAttributes> overlayAttributesCalculator;
    private final ArrayMap<Consumer<OverlayInfo>, androidx.window.extensions.core.util.function.Consumer<List<androidx.window.extensions.embedding.ActivityStack>>> overlayInfoToActivityStackCallbackMap;
    private final ArrayMap<String, androidx.window.extensions.embedding.ActivityStack> overlayTagToContainerMap;
    private final ArrayMap<String, OverlayAttributes> overlayTagToCurrentAttributesMap;
    private final Map<String, OverlayAttributes> overlayTagToDefaultAttributesMap;

    public OverlayControllerImpl(ActivityEmbeddingComponent activityEmbeddingComponent, EmbeddingAdapter embeddingAdapter) {
        activityEmbeddingComponent.getClass();
        embeddingAdapter.getClass();
        this.embeddingExtension = activityEmbeddingComponent;
        this.adapter = embeddingAdapter;
        this.globalLock = new ReentrantLock();
        this.overlayTagToDefaultAttributesMap = new ArrayMap();
        this.overlayTagToCurrentAttributesMap = new ArrayMap<>();
        this.overlayTagToContainerMap = new ArrayMap<>();
        this.overlayInfoToActivityStackCallbackMap = new ArrayMap<>();
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(8);
        activityEmbeddingComponent.setActivityStackAttributesCalculator(new Function() { // from class: zsa
        });
        activityEmbeddingComponent.registerActivityStackCallback(new nab(), new OverlayControllerActivityStackConsumer(new Function1() { // from class: ata
            public final Object invoke(Object obj) {
                return OverlayControllerImpl._init_$lambda$6(this.b, (List) obj);
            }
        }));
    }

    private static final ActivityStackAttributes _init_$lambda$3(OverlayControllerImpl overlayControllerImpl, ActivityStackAttributesCalculatorParams activityStackAttributesCalculatorParams) {
        ReentrantLock reentrantLock = overlayControllerImpl.globalLock;
        reentrantLock.lock();
        try {
            androidx.window.extensions.embedding.ParentContainerInfo parentContainerInfo = activityStackAttributesCalculatorParams.getParentContainerInfo();
            parentContainerInfo.getClass();
            DensityCompatHelper companion = DensityCompatHelper.INSTANCE.getInstance();
            Configuration configuration = parentContainerInfo.getConfiguration();
            configuration.getClass();
            WindowMetrics windowMetrics = parentContainerInfo.getWindowMetrics();
            windowMetrics.getClass();
            float fDensity = companion.density(configuration, windowMetrics);
            WindowMetricsCalculator.Companion companion2 = WindowMetricsCalculator.INSTANCE;
            WindowMetrics windowMetrics2 = parentContainerInfo.getWindowMetrics();
            windowMetrics2.getClass();
            androidx.window.layout.WindowMetrics windowMetricsTranslateWindowMetrics$window_release = companion2.translateWindowMetrics$window_release(windowMetrics2, fDensity);
            String activityStackTag = activityStackAttributesCalculatorParams.getActivityStackTag();
            activityStackTag.getClass();
            ActivityEmbeddingOptionsImpl activityEmbeddingOptionsImpl = ActivityEmbeddingOptionsImpl.INSTANCE;
            Bundle launchOptions = activityStackAttributesCalculatorParams.getLaunchOptions();
            launchOptions.getClass();
            OverlayAttributes overlayAttributes$window_release = activityEmbeddingOptionsImpl.getOverlayAttributes$window_release(launchOptions);
            WindowMetrics windowMetrics3 = activityStackAttributesCalculatorParams.getParentContainerInfo().getWindowMetrics();
            windowMetrics3.getClass();
            androidx.window.layout.WindowMetrics windowMetricsTranslateWindowMetrics$window_release2 = companion2.translateWindowMetrics$window_release(windowMetrics3, fDensity);
            Configuration configuration2 = activityStackAttributesCalculatorParams.getParentContainerInfo().getConfiguration();
            configuration2.getClass();
            ExtensionsWindowLayoutInfoAdapter extensionsWindowLayoutInfoAdapter = ExtensionsWindowLayoutInfoAdapter.INSTANCE;
            WindowLayoutInfo windowLayoutInfo = parentContainerInfo.getWindowLayoutInfo();
            windowLayoutInfo.getClass();
            OverlayAttributes overlayAttributesCalculateOverlayAttributes$window_release = overlayControllerImpl.calculateOverlayAttributes$window_release(activityStackTag, overlayAttributes$window_release, windowMetricsTranslateWindowMetrics$window_release2, configuration2, extensionsWindowLayoutInfoAdapter.translate$window_release(windowMetricsTranslateWindowMetrics$window_release, windowLayoutInfo));
            Bundle launchOptions2 = activityStackAttributesCalculatorParams.getLaunchOptions();
            launchOptions2.getClass();
            activityEmbeddingOptionsImpl.putActivityStackAlignment$window_release(launchOptions2, overlayAttributesCalculateOverlayAttributes$window_release.getBounds());
            return overlayControllerImpl.toActivityStackAttributes(overlayAttributesCalculateOverlayAttributes$window_release, parentContainerInfo);
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$6(OverlayControllerImpl overlayControllerImpl, List list) {
        list.getClass();
        ReentrantLock reentrantLock = overlayControllerImpl.globalLock;
        reentrantLock.lock();
        try {
            Set<String> setKeySet = overlayControllerImpl.overlayTagToContainerMap.keySet();
            setKeySet.getClass();
            overlayControllerImpl.overlayTagToContainerMap.clear();
            ArrayMap<String, androidx.window.extensions.embedding.ActivityStack> arrayMap = overlayControllerImpl.overlayTagToContainerMap;
            List<androidx.window.extensions.embedding.ActivityStack> overlayContainers = overlayControllerImpl.getOverlayContainers(list);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overlayContainers, 10));
            for (androidx.window.extensions.embedding.ActivityStack activityStack : overlayContainers) {
                String tag = activityStack.getTag();
                tag.getClass();
                arrayList.add(new Pair(tag, activityStack));
            }
            MapsKt.putAll(arrayMap, arrayList);
            overlayControllerImpl.cleanUpDismissedOverlayContainerRecords(setKeySet);
            Unit unit = Unit.INSTANCE;
            return Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addOverlayInfoCallback$lambda$11$lambda$10(String str, OverlayControllerImpl overlayControllerImpl, Consumer consumer, List list) {
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (Intrinsics.areEqual(((androidx.window.extensions.embedding.ActivityStack) obj).getTag(), str)) {
                arrayList.add(obj);
            }
        }
        if (arrayList.size() <= 1) {
            consumer.accept(arrayList.isEmpty() ? new OverlayInfo(str, null, null) : overlayControllerImpl.toOverlayInfo((androidx.window.extensions.embedding.ActivityStack) CollectionsKt.first(arrayList)));
        } else {
            qu7.a("There must be at most one overlay ActivityStack with ", str);
        }
    }

    private final void cleanUpDismissedOverlayContainerRecords(Set<String> lastOverlayTags) {
        if (lastOverlayTags.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Set<String> setKeySet = this.overlayTagToContainerMap.keySet();
        setKeySet.getClass();
        for (String str : lastOverlayTags) {
            if (!setKeySet.contains(str) && this.embeddingExtension.getActivityStackToken(str) == null) {
                arrayList.add(str);
            }
        }
        Iterator it = arrayList.iterator();
        it.getClass();
        while (it.hasNext()) {
            Object next = it.next();
            next.getClass();
            String str2 = (String) next;
            this.overlayTagToDefaultAttributesMap.remove(str2);
            this.overlayTagToCurrentAttributesMap.remove(str2);
        }
    }

    private final List<androidx.window.extensions.embedding.ActivityStack> getOverlayContainers(List<? extends androidx.window.extensions.embedding.ActivityStack> list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((androidx.window.extensions.embedding.ActivityStack) obj).getTag() != null) {
                arrayList.add(obj);
            }
        }
        return CollectionsKt.toList(arrayList);
    }

    private final ActivityStackAttributes toActivityStackAttributes(OverlayAttributes overlayAttributes, androidx.window.extensions.embedding.ParentContainerInfo parentContainerInfo) {
        ActivityStackAttributes activityStackAttributesBuild = new ActivityStackAttributes.Builder().setRelativeBounds(EmbeddingBounds.INSTANCE.translateEmbeddingBounds$window_release(overlayAttributes.getBounds(), this.adapter.translate$window_release(parentContainerInfo)).toRect()).setWindowAttributes(this.adapter.translateWindowAttributes$window_release()).build();
        activityStackAttributesBuild.getClass();
        return activityStackAttributesBuild;
    }

    private final OverlayInfo toOverlayInfo(androidx.window.extensions.embedding.ActivityStack activityStack) {
        String tag = activityStack.getTag();
        tag.getClass();
        ArrayMap<String, OverlayAttributes> arrayMap = this.overlayTagToCurrentAttributesMap;
        String tag2 = activityStack.getTag();
        tag2.getClass();
        return new OverlayInfo(tag, arrayMap.get(tag2), this.adapter.translate$window_release(activityStack));
    }

    public void addOverlayInfoCallback(final String overlayTag, Executor executor, final Consumer<OverlayInfo> overlayInfoCallback) {
        overlayTag.getClass();
        executor.getClass();
        overlayInfoCallback.getClass();
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            Consumer2 consumer2 = new Consumer2() { // from class: bta
                @Override // androidx.window.reflection.Consumer2
                public final void accept(Object obj) {
                    OverlayControllerImpl.addOverlayInfoCallback$lambda$11$lambda$10(overlayTag, this, overlayInfoCallback, (List) obj);
                }
            };
            this.overlayInfoToActivityStackCallbackMap.put(overlayInfoCallback, consumer2);
            this.embeddingExtension.registerActivityStackCallback(executor, consumer2);
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final OverlayAttributes calculateOverlayAttributes$window_release(String tag, OverlayAttributes initialOverlayAttrs, androidx.window.layout.WindowMetrics windowMetrics, Configuration configuration, androidx.window.layout.WindowLayoutInfo windowLayoutInfo) {
        OverlayAttributes overlayAttributes;
        String str;
        tag.getClass();
        windowMetrics.getClass();
        configuration.getClass();
        windowLayoutInfo.getClass();
        OverlayAttributes updatedOverlayAttributes$window_release = getUpdatedOverlayAttributes$window_release(tag);
        if (updatedOverlayAttributes$window_release != null) {
            overlayAttributes = updatedOverlayAttributes$window_release;
        } else {
            if (initialOverlayAttrs == null) {
                w01.a("Can't retrieve overlay attributes from launch options");
                return null;
            }
            overlayAttributes = initialOverlayAttrs;
        }
        Function1<OverlayAttributesCalculatorParams, OverlayAttributes> overlayAttributesCalculator$window_release = getOverlayAttributesCalculator$window_release();
        if (overlayAttributesCalculator$window_release != null) {
            str = tag;
            OverlayAttributes overlayAttributes2 = (OverlayAttributes) overlayAttributesCalculator$window_release.invoke(new OverlayAttributesCalculatorParams(windowMetrics, configuration, windowLayoutInfo, str, overlayAttributes));
            if (overlayAttributes2 != null) {
                overlayAttributes = overlayAttributes2;
            }
        } else {
            str = tag;
        }
        this.overlayTagToCurrentAttributesMap.put(str, overlayAttributes);
        return overlayAttributes;
    }

    public final Function1<OverlayAttributesCalculatorParams, OverlayAttributes> getOverlayAttributesCalculator$window_release() {
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            return this.overlayAttributesCalculator;
        } finally {
            reentrantLock.unlock();
        }
    }

    public OverlayAttributes getUpdatedOverlayAttributes$window_release(String overlayTag) {
        overlayTag.getClass();
        return this.overlayTagToDefaultAttributesMap.get(overlayTag);
    }

    public void removeOverlayInfoCallback(Consumer<OverlayInfo> overlayInfoCallback) {
        overlayInfoCallback.getClass();
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            androidx.window.extensions.core.util.function.Consumer<List<androidx.window.extensions.embedding.ActivityStack>> consumerRemove = this.overlayInfoToActivityStackCallbackMap.remove(overlayInfoCallback);
            if (consumerRemove != null) {
                this.embeddingExtension.unregisterActivityStackCallback(consumerRemove);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void setOverlayAttributesCalculator$window_release(Function1<? super OverlayAttributesCalculatorParams, OverlayAttributes> function1) {
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            this.overlayAttributesCalculator = function1;
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void updateOverlayAttributes$window_release(String overlayTag, OverlayAttributes overlayAttributes) {
        androidx.window.extensions.embedding.ActivityStack.Token activityStackToken;
        overlayTag.getClass();
        overlayAttributes.getClass();
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            androidx.window.extensions.embedding.ActivityStack activityStack = this.overlayTagToContainerMap.get(overlayTag);
            if (activityStack == null || (activityStackToken = activityStack.getActivityStackToken()) == null) {
                activityStackToken = this.embeddingExtension.getActivityStackToken(overlayTag);
                if (activityStackToken == null) {
                    return;
                }
            }
            ActivityEmbeddingComponent activityEmbeddingComponent = this.embeddingExtension;
            androidx.window.extensions.embedding.ParentContainerInfo parentContainerInfo = activityEmbeddingComponent.getParentContainerInfo(activityStackToken);
            parentContainerInfo.getClass();
            activityEmbeddingComponent.updateActivityStackAttributes(activityStackToken, toActivityStackAttributes(overlayAttributes, parentContainerInfo));
            this.overlayTagToDefaultAttributesMap.put(overlayTag, overlayAttributes);
            this.overlayTagToCurrentAttributesMap.put(overlayTag, overlayAttributes);
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }
}
