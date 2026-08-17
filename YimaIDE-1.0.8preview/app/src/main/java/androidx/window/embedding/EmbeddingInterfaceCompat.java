package androidx.window.embedding;

import android.app.Activity;
import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.util.Consumer;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001:\u0001?J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H'J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H'J\u001c\u0010\u0014\u001a\u00020\u00032\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016H'J\b\u0010\u0019\u001a\u00020\u0003H'J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH'J\u0018\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!H'J\u0016\u0010\"\u001a\u00020\u00032\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0005H'J\u0010\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&H'J\b\u0010'\u001a\u00020\u0003H'J\u0018\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0018H'J\u001c\u0010,\u001a\u00020\u00032\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020.0\u0016H'J\b\u0010/\u001a\u00020\u0003H'J\u0018\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020.H'J&\u00104\u001a\u00020\u00032\u0006\u00101\u001a\u0002022\u0006\u00105\u001a\u0002062\f\u00107\u001a\b\u0012\u0004\u0012\u00020908H'J\u0016\u0010:\u001a\u00020\u00032\f\u00107\u001a\b\u0012\u0004\u0012\u00020908H'J\u001e\u0010;\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020=08H'J\u0016\u0010>\u001a\u00020\u00032\f\u0010<\u001a\b\u0012\u0004\u0012\u00020=08H'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006@À\u0006\u0001"}, d2 = {"Landroidx/window/embedding/EmbeddingInterfaceCompat;", "", "setRules", "", "rules", "", "Landroidx/window/embedding/EmbeddingRule;", "setEmbeddingCallback", "embeddingCallback", "Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "isActivityEmbedded", "", "activity", "Landroid/app/Activity;", "pinTopActivityStack", "taskId", "", "splitPinRule", "Landroidx/window/embedding/SplitPinRule;", "unpinTopActivityStack", "setSplitAttributesCalculator", "calculator", "Lkotlin/Function1;", "Landroidx/window/embedding/SplitAttributesCalculatorParams;", "Landroidx/window/embedding/SplitAttributes;", "clearSplitAttributesCalculator", "setLaunchingActivityStack", "Landroid/os/Bundle;", "options", "activityStack", "Landroidx/window/embedding/ActivityStack;", "setOverlayCreateParams", "overlayCreateParams", "Landroidx/window/embedding/OverlayCreateParams;", "finishActivityStacks", "activityStacks", "setEmbeddingConfiguration", "embeddingConfig", "Landroidx/window/embedding/EmbeddingConfiguration;", "invalidateVisibleActivityStacks", "updateSplitAttributes", "splitInfo", "Landroidx/window/embedding/SplitInfo;", "splitAttributes", "setOverlayAttributesCalculator", "Landroidx/window/embedding/OverlayAttributesCalculatorParams;", "Landroidx/window/embedding/OverlayAttributes;", "clearOverlayAttributesCalculator", "updateOverlayAttributes", "overlayTag", "", "overlayAttributes", "addOverlayInfoCallback", "executor", "Ljava/util/concurrent/Executor;", "overlayInfoCallback", "Landroidx/core/util/Consumer;", "Landroidx/window/embedding/OverlayInfo;", "removeOverlayInfoCallback", "addEmbeddedActivityWindowInfoCallbackForActivity", "callback", "Landroidx/window/embedding/EmbeddedActivityWindowInfo;", "removeEmbeddedActivityWindowInfoCallbackForActivity", "EmbeddingCallbackInterface", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface EmbeddingInterfaceCompat {

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "", "onSplitInfoChanged", "", "splitInfo", "", "Landroidx/window/embedding/SplitInfo;", "onActivityStackChanged", "activityStacks", "Landroidx/window/embedding/ActivityStack;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public interface EmbeddingCallbackInterface {
        void onActivityStackChanged(List<ActivityStack> activityStacks);

        void onSplitInfoChanged(List<SplitInfo> splitInfo);
    }

    void addEmbeddedActivityWindowInfoCallbackForActivity(Activity activity, Consumer<EmbeddedActivityWindowInfo> callback);

    void addOverlayInfoCallback(String overlayTag, Executor executor, Consumer<OverlayInfo> overlayInfoCallback);

    void clearOverlayAttributesCalculator();

    void clearSplitAttributesCalculator();

    void finishActivityStacks(Set<ActivityStack> activityStacks);

    void invalidateVisibleActivityStacks();

    boolean isActivityEmbedded(Activity activity);

    boolean pinTopActivityStack(int taskId, SplitPinRule splitPinRule);

    void removeEmbeddedActivityWindowInfoCallbackForActivity(Consumer<EmbeddedActivityWindowInfo> callback);

    void removeOverlayInfoCallback(Consumer<OverlayInfo> overlayInfoCallback);

    void setEmbeddingCallback(EmbeddingCallbackInterface embeddingCallback);

    void setEmbeddingConfiguration(EmbeddingConfiguration embeddingConfig);

    Bundle setLaunchingActivityStack(Bundle options, ActivityStack activityStack);

    void setOverlayAttributesCalculator(Function1<? super OverlayAttributesCalculatorParams, OverlayAttributes> calculator);

    Bundle setOverlayCreateParams(Bundle options, OverlayCreateParams overlayCreateParams);

    void setRules(Set<? extends EmbeddingRule> rules);

    void setSplitAttributesCalculator(Function1<? super SplitAttributesCalculatorParams, SplitAttributes> calculator);

    void unpinTopActivityStack(int taskId);

    void updateOverlayAttributes(String overlayTag, OverlayAttributes overlayAttributes);

    void updateSplitAttributes(SplitInfo splitInfo, SplitAttributes splitAttributes);
}
