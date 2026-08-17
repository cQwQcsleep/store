package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.util.Consumer;
import androidx.window.embedding.EmbeddingBackend;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 I2\u00020\u0001:\u0001IJ\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0006H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0006H&J,\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011H&J\u001c\u0010\u0014\u001a\u00020\u00032\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011H&J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H'J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eH'J\u001c\u0010\"\u001a\u00020\u00032\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0$H'J\b\u0010'\u001a\u00020\u0003H'J\u0012\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020)H'J\u0018\u0010.\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\u0006\u0010/\u001a\u000200H'J\u0016\u00101\u001a\u00020\u00032\f\u00102\u001a\b\u0012\u0004\u0012\u00020)0\u0005H'J\u0010\u00103\u001a\u00020\u00032\u0006\u00104\u001a\u000205H'J\b\u00106\u001a\u00020\u0003H'J\u0018\u00107\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020&H'J\u001c\u0010:\u001a\u00020\u00032\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020<0$H'J\b\u0010=\u001a\u00020\u0003H'J\u0018\u0010>\u001a\u00020\u00032\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020<H'J&\u0010B\u001a\u00020\u00032\u0006\u0010?\u001a\u00020@2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u0011H'J\u0016\u0010E\u001a\u00020\u00032\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u0011H'J\u001e\u0010F\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020G0\u0011H'J\u0016\u0010H\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020G0\u0011H'R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006JÀ\u0006\u0001"}, d2 = {"Landroidx/window/embedding/EmbeddingBackend;", "", "setRules", "", "rules", "", "Landroidx/window/embedding/EmbeddingRule;", "getRules", "addRule", "rule", "removeRule", "addSplitListenerForActivity", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "removeSplitListenerForActivity", "consumer", "splitSupportStatus", "Landroidx/window/embedding/SplitController$SplitSupportStatus;", "getSplitSupportStatus", "()Landroidx/window/embedding/SplitController$SplitSupportStatus;", "isActivityEmbedded", "", "pinTopActivityStack", "taskId", "", "splitPinRule", "Landroidx/window/embedding/SplitPinRule;", "unpinTopActivityStack", "setSplitAttributesCalculator", "calculator", "Lkotlin/Function1;", "Landroidx/window/embedding/SplitAttributesCalculatorParams;", "Landroidx/window/embedding/SplitAttributes;", "clearSplitAttributesCalculator", "getActivityStack", "Landroidx/window/embedding/ActivityStack;", "setLaunchingActivityStack", "Landroid/os/Bundle;", "options", "activityStack", "setOverlayCreateParams", "overlayCreateParams", "Landroidx/window/embedding/OverlayCreateParams;", "finishActivityStacks", "activityStacks", "setEmbeddingConfiguration", "embeddingConfig", "Landroidx/window/embedding/EmbeddingConfiguration;", "invalidateVisibleActivityStacks", "updateSplitAttributes", "splitInfo", "splitAttributes", "setOverlayAttributesCalculator", "Landroidx/window/embedding/OverlayAttributesCalculatorParams;", "Landroidx/window/embedding/OverlayAttributes;", "clearOverlayAttributesCalculator", "updateOverlayAttributes", "overlayTag", "", "overlayAttributes", "addOverlayInfoCallback", "overlayInfoCallback", "Landroidx/window/embedding/OverlayInfo;", "removeOverlayInfoCallback", "addEmbeddedActivityWindowInfoCallbackForActivity", "Landroidx/window/embedding/EmbeddedActivityWindowInfo;", "removeEmbeddedActivityWindowInfoCallbackForActivity", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface EmbeddingBackend {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\b\u0010\u000e\u001a\u00020\u000bH\u0007R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/window/embedding/EmbeddingBackend$Companion;", "", "<init>", "()V", "decorator", "Lkotlin/Function1;", "Landroidx/window/embedding/EmbeddingBackend;", "getInstance", "context", "Landroid/content/Context;", "overrideDecorator", "", "overridingDecorator", "Landroidx/window/embedding/EmbeddingBackendDecorator;", "reset", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static Function1<? super EmbeddingBackend, ? extends EmbeddingBackend> decorator = new Function1() { // from class: va4
            public final Object invoke(Object obj) {
                return EmbeddingBackend.Companion.a((EmbeddingBackend) obj);
            }
        };

        private Companion() {
        }

        public static EmbeddingBackend a(EmbeddingBackend embeddingBackend) {
            embeddingBackend.getClass();
            return embeddingBackend;
        }

        public static EmbeddingBackend b(EmbeddingBackend embeddingBackend) {
            embeddingBackend.getClass();
            return embeddingBackend;
        }

        @JvmStatic
        public final EmbeddingBackend getInstance(Context context) {
            context.getClass();
            return (EmbeddingBackend) decorator.invoke(ExtensionEmbeddingBackend.INSTANCE.getInstance(context));
        }

        @JvmStatic
        public final void overrideDecorator(EmbeddingBackendDecorator overridingDecorator) {
            overridingDecorator.getClass();
            decorator = new EmbeddingBackend$Companion$overrideDecorator$1(overridingDecorator);
        }

        @JvmStatic
        public final void reset() {
            decorator = new Function1() { // from class: ua4
                public final Object invoke(Object obj) {
                    return EmbeddingBackend.Companion.b((EmbeddingBackend) obj);
                }
            };
        }
    }

    @JvmStatic
    static EmbeddingBackend getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }

    @JvmStatic
    static void overrideDecorator(EmbeddingBackendDecorator embeddingBackendDecorator) {
        INSTANCE.overrideDecorator(embeddingBackendDecorator);
    }

    @JvmStatic
    static void reset() {
        INSTANCE.reset();
    }

    void addEmbeddedActivityWindowInfoCallbackForActivity(Activity activity, Consumer<EmbeddedActivityWindowInfo> callback);

    void addOverlayInfoCallback(String overlayTag, Executor executor, Consumer<OverlayInfo> overlayInfoCallback);

    void addRule(EmbeddingRule rule);

    void addSplitListenerForActivity(Activity activity, Executor executor, Consumer<List<SplitInfo>> callback);

    void clearOverlayAttributesCalculator();

    void clearSplitAttributesCalculator();

    void finishActivityStacks(Set<ActivityStack> activityStacks);

    ActivityStack getActivityStack(Activity activity);

    Set<EmbeddingRule> getRules();

    SplitController.SplitSupportStatus getSplitSupportStatus();

    void invalidateVisibleActivityStacks();

    boolean isActivityEmbedded(Activity activity);

    boolean pinTopActivityStack(int taskId, SplitPinRule splitPinRule);

    void removeEmbeddedActivityWindowInfoCallbackForActivity(Consumer<EmbeddedActivityWindowInfo> callback);

    void removeOverlayInfoCallback(Consumer<OverlayInfo> overlayInfoCallback);

    void removeRule(EmbeddingRule rule);

    void removeSplitListenerForActivity(Consumer<List<SplitInfo>> consumer);

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
