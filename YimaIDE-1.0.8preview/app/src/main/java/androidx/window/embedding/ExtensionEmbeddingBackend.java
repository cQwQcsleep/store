package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.collection.ArraySet;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.util.Consumer;
import androidx.window.WindowProperties;
import androidx.window.WindowSdkExtensions;
import androidx.window.core.BuildConfig;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.PredicateAdapter;
import androidx.window.core.VerificationMode;
import androidx.window.embedding.ExtensionEmbeddingBackend;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 b2\u00020\u0001:\u0005bcdefB\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0017J\u0016\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0017J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0019H\u0017J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0019H\u0017J,\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0'0&H\u0016J\u001c\u0010)\u001a\u00020\u001b2\u0012\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0'0&H\u0016J\b\u0010+\u001a\u00020,H\u0002J\u0010\u00103\u001a\u00020,2\u0006\u0010!\u001a\u00020\"H\u0016J\u0018\u00104\u001a\u00020,2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0017J\u0010\u00109\u001a\u00020\u001b2\u0006\u00105\u001a\u000206H\u0017J\u001c\u0010:\u001a\u00020\u001b2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020>0<H\u0017J\b\u0010?\u001a\u00020\u001bH\u0017J\u0012\u0010@\u001a\u0004\u0018\u00010A2\u0006\u0010!\u001a\u00020\"H\u0016J\u0012\u0010B\u001a\u0004\u0018\u00010A2\u0006\u0010!\u001a\u00020\"H\u0003J\u0018\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020AH\u0017J\u0018\u0010G\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010H\u001a\u00020IH\u0017J\u0016\u0010J\u001a\u00020\u001b2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020A0\u0018H\u0017J\u0010\u0010L\u001a\u00020\u001b2\u0006\u0010M\u001a\u00020NH\u0017J\b\u0010O\u001a\u00020\u001bH\u0017J\u0018\u0010P\u001a\u00020\u001b2\u0006\u0010Q\u001a\u00020(2\u0006\u0010R\u001a\u00020>H\u0017J\u001c\u0010S\u001a\u00020\u001b2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020U0<H\u0017J\b\u0010V\u001a\u00020\u001bH\u0017J\u0018\u0010W\u001a\u00020\u001b2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020UH\u0017J&\u0010[\u001a\u00020\u001b2\u0006\u0010X\u001a\u00020Y2\u0006\u0010#\u001a\u00020$2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0&H\u0017J\u0016\u0010^\u001a\u00020\u001b2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0&H\u0017J\u001e\u0010_\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020`0&H\u0017J\u0016\u0010a\u001a\u00020\u001b2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020`0&H\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00060\u0014R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00168\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001b\u0010-\u001a\u00020.8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b/\u00100¨\u0006g"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend;", "Landroidx/window/embedding/EmbeddingBackend;", "applicationContext", "Landroid/content/Context;", "embeddingExtension", "Landroidx/window/embedding/EmbeddingInterfaceCompat;", "<init>", "(Landroid/content/Context;Landroidx/window/embedding/EmbeddingInterfaceCompat;)V", "getEmbeddingExtension", "()Landroidx/window/embedding/EmbeddingInterfaceCompat;", "setEmbeddingExtension", "(Landroidx/window/embedding/EmbeddingInterfaceCompat;)V", "splitChangeCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/window/embedding/ExtensionEmbeddingBackend$SplitListenerWrapper;", "getSplitChangeCallbacks$annotations", "()V", "getSplitChangeCallbacks", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "embeddingCallback", "Landroidx/window/embedding/ExtensionEmbeddingBackend$EmbeddingCallbackImpl;", "ruleTracker", "Landroidx/window/embedding/ExtensionEmbeddingBackend$RuleTracker;", "getRules", "", "Landroidx/window/embedding/EmbeddingRule;", "setRules", "", "rules", "addRule", "rule", "removeRule", "addSplitListenerForActivity", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "removeSplitListenerForActivity", "consumer", "areExtensionsAvailable", "", "splitSupportStatus", "Landroidx/window/embedding/SplitController$SplitSupportStatus;", "getSplitSupportStatus", "()Landroidx/window/embedding/SplitController$SplitSupportStatus;", "splitSupportStatus$delegate", "Lkotlin/Lazy;", "isActivityEmbedded", "pinTopActivityStack", "taskId", "", "splitPinRule", "Landroidx/window/embedding/SplitPinRule;", "unpinTopActivityStack", "setSplitAttributesCalculator", "calculator", "Lkotlin/Function1;", "Landroidx/window/embedding/SplitAttributesCalculatorParams;", "Landroidx/window/embedding/SplitAttributes;", "clearSplitAttributesCalculator", "getActivityStack", "Landroidx/window/embedding/ActivityStack;", "getActivityStackFromSplitInfoList", "setLaunchingActivityStack", "Landroid/os/Bundle;", "options", "activityStack", "setOverlayCreateParams", "overlayCreateParams", "Landroidx/window/embedding/OverlayCreateParams;", "finishActivityStacks", "activityStacks", "setEmbeddingConfiguration", "embeddingConfig", "Landroidx/window/embedding/EmbeddingConfiguration;", "invalidateVisibleActivityStacks", "updateSplitAttributes", "splitInfo", "splitAttributes", "setOverlayAttributesCalculator", "Landroidx/window/embedding/OverlayAttributesCalculatorParams;", "Landroidx/window/embedding/OverlayAttributes;", "clearOverlayAttributesCalculator", "updateOverlayAttributes", "overlayTag", "", "overlayAttributes", "addOverlayInfoCallback", "overlayInfoCallback", "Landroidx/window/embedding/OverlayInfo;", "removeOverlayInfoCallback", "addEmbeddedActivityWindowInfoCallbackForActivity", "Landroidx/window/embedding/EmbeddedActivityWindowInfo;", "removeEmbeddedActivityWindowInfoCallbackForActivity", "Companion", "RuleTracker", "SplitListenerWrapper", "EmbeddingCallbackImpl", "Api31Impl", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ExtensionEmbeddingBackend implements EmbeddingBackend {
    private static final String TAG = "EmbeddingBackend";
    private static volatile ExtensionEmbeddingBackend globalInstance;
    private final Context applicationContext;
    private final EmbeddingCallbackImpl embeddingCallback;
    private EmbeddingInterfaceCompat embeddingExtension;
    private final RuleTracker ruleTracker;
    private final CopyOnWriteArrayList<SplitListenerWrapper> splitChangeCallbacks;

    /* JADX INFO: renamed from: splitSupportStatus$delegate, reason: from kotlin metadata */
    private final Lazy splitSupportStatus;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ReentrantLock globalLock = new ReentrantLock();

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$Api31Impl;", "", "<init>", "()V", "isSplitPropertyEnabled", "Landroidx/window/embedding/SplitController$SplitSupportStatus;", "context", "Landroid/content/Context;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Api31Impl {
        public static final Api31Impl INSTANCE = new Api31Impl();

        private Api31Impl() {
        }

        public final SplitController.SplitSupportStatus isSplitPropertyEnabled(Context context) {
            context.getClass();
            try {
                PackageManager.Property property = context.getPackageManager().getProperty(WindowProperties.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED, context.getPackageName());
                property.getClass();
                if (property.isBoolean()) {
                    return property.getBoolean() ? SplitController.SplitSupportStatus.SPLIT_AVAILABLE : SplitController.SplitSupportStatus.SPLIT_UNAVAILABLE;
                }
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                    Log.w(ExtensionEmbeddingBackend.TAG, "android.window.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED must have a boolean value");
                }
                return SplitController.SplitSupportStatus.SPLIT_ERROR_PROPERTY_NOT_DECLARED;
            } catch (PackageManager.NameNotFoundException unused) {
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                    Log.w(ExtensionEmbeddingBackend.TAG, "android.window.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED must be set and enabled in AndroidManifest.xml to use splits APIs.");
                }
                return SplitController.SplitSupportStatus.SPLIT_ERROR_PROPERTY_NOT_DECLARED;
            } catch (Exception e) {
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                    Log.e(ExtensionEmbeddingBackend.TAG, "PackageManager.getProperty is not supported", e);
                }
                return SplitController.SplitSupportStatus.SPLIT_ERROR_PROPERTY_NOT_DECLARED;
            }
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016J\u0016\u0010\u0012\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0005H\u0016R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0014"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$EmbeddingCallbackImpl;", "Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "<init>", "(Landroidx/window/embedding/ExtensionEmbeddingBackend;)V", "lastInfo", "", "Landroidx/window/embedding/SplitInfo;", "getLastInfo", "()Ljava/util/List;", "setLastInfo", "(Ljava/util/List;)V", "lastActivityStacks", "Landroidx/window/embedding/ActivityStack;", "getLastActivityStacks", "setLastActivityStacks", "onSplitInfoChanged", "", "splitInfo", "onActivityStackChanged", "activityStacks", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public final class EmbeddingCallbackImpl implements EmbeddingInterfaceCompat.EmbeddingCallbackInterface {
        private List<SplitInfo> lastInfo = CollectionsKt.emptyList();
        private List<ActivityStack> lastActivityStacks = CollectionsKt.emptyList();

        public EmbeddingCallbackImpl() {
        }

        public final List<ActivityStack> getLastActivityStacks() {
            return this.lastActivityStacks;
        }

        public final List<SplitInfo> getLastInfo() {
            return this.lastInfo;
        }

        @Override // androidx.window.embedding.EmbeddingInterfaceCompat.EmbeddingCallbackInterface
        public void onActivityStackChanged(List<ActivityStack> activityStacks) {
            activityStacks.getClass();
            this.lastActivityStacks = activityStacks;
        }

        @Override // androidx.window.embedding.EmbeddingInterfaceCompat.EmbeddingCallbackInterface
        public void onSplitInfoChanged(List<SplitInfo> splitInfo) {
            splitInfo.getClass();
            this.lastInfo = splitInfo;
            Iterator<SplitListenerWrapper> it = ExtensionEmbeddingBackend.this.getSplitChangeCallbacks().iterator();
            it.getClass();
            while (it.hasNext()) {
                it.next().accept(splitInfo);
            }
        }

        public final void setLastActivityStacks(List<ActivityStack> list) {
            list.getClass();
            this.lastActivityStacks = list;
        }

        public final void setLastInfo(List<SplitInfo> list) {
            list.getClass();
            this.lastInfo = list;
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010J\u0006\u0010\u0011\u001a\u00020\u000eJ\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0006J\u0011\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u0006H\u0086\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR*\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\nj\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0006`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$RuleTracker;", "", "<init>", "()V", "splitRules", "Landroidx/collection/ArraySet;", "Landroidx/window/embedding/EmbeddingRule;", "getSplitRules", "()Landroidx/collection/ArraySet;", "tagRuleMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "setRules", "", "rules", "", "clearRules", "addOrUpdateRule", "rule", "throwOnDuplicateTag", "", "removeRule", "contains", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class RuleTracker {
        private final ArraySet<EmbeddingRule> splitRules = new ArraySet<>(0, 1, (DefaultConstructorMarker) null);
        private final HashMap<String, EmbeddingRule> tagRuleMap = new HashMap<>();

        public static /* synthetic */ void addOrUpdateRule$default(RuleTracker ruleTracker, EmbeddingRule embeddingRule, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            ruleTracker.addOrUpdateRule(embeddingRule, z);
        }

        public final void addOrUpdateRule(EmbeddingRule rule, boolean throwOnDuplicateTag) {
            rule.getClass();
            if (this.splitRules.contains(rule)) {
                return;
            }
            String tag = rule.getTag();
            if (tag == null) {
                this.splitRules.add(rule);
                return;
            }
            if (!this.tagRuleMap.containsKey(tag)) {
                this.tagRuleMap.put(tag, rule);
                this.splitRules.add(rule);
            } else {
                if (throwOnDuplicateTag) {
                    kg9.a("Duplicated tag: ", tag, ". Tag must be unique among all registered rules");
                    return;
                }
                TypeIntrinsics.asMutableCollection(this.splitRules).remove(this.tagRuleMap.get(tag));
                this.tagRuleMap.put(tag, rule);
                this.splitRules.add(rule);
            }
        }

        public final void clearRules() {
            this.splitRules.clear();
            this.tagRuleMap.clear();
        }

        public final boolean contains(EmbeddingRule rule) {
            rule.getClass();
            return this.splitRules.contains(rule);
        }

        public final ArraySet<EmbeddingRule> getSplitRules() {
            return this.splitRules;
        }

        public final void removeRule(EmbeddingRule rule) {
            rule.getClass();
            if (this.splitRules.contains(rule)) {
                this.splitRules.remove(rule);
                if (rule.getTag() != null) {
                    this.tagRuleMap.remove(rule.getTag());
                }
            }
        }

        public final void setRules(Set<? extends EmbeddingRule> rules) {
            rules.getClass();
            clearRules();
            Iterator<T> it = rules.iterator();
            while (it.hasNext()) {
                addOrUpdateRule((EmbeddingRule) it.next(), true);
            }
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$SplitListenerWrapper;", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "<init>", "(Landroid/app/Activity;Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;)V", "getCallback", "()Landroidx/core/util/Consumer;", "lastValue", "accept", "", "splitInfoList", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class SplitListenerWrapper {
        private final Activity activity;
        private final Consumer<List<SplitInfo>> callback;
        private final Executor executor;
        private List<SplitInfo> lastValue;

        public SplitListenerWrapper(Activity activity, Executor executor, Consumer<List<SplitInfo>> consumer) {
            activity.getClass();
            executor.getClass();
            consumer.getClass();
            this.activity = activity;
            this.executor = executor;
            this.callback = consumer;
        }

        public static void a(SplitListenerWrapper splitListenerWrapper, List list) {
            splitListenerWrapper.callback.accept(list);
        }

        public final void accept(List<SplitInfo> splitInfoList) {
            splitInfoList.getClass();
            final ArrayList arrayList = new ArrayList();
            for (Object obj : splitInfoList) {
                if (((SplitInfo) obj).contains(this.activity)) {
                    arrayList.add(obj);
                }
            }
            if (Intrinsics.areEqual(arrayList, this.lastValue)) {
                return;
            }
            this.lastValue = arrayList;
            this.executor.execute(new Runnable() { // from class: hj4
                @Override // java.lang.Runnable
                public final void run() {
                    ExtensionEmbeddingBackend.SplitListenerWrapper.a(this.b, arrayList);
                }
            });
        }

        public final Consumer<List<SplitInfo>> getCallback() {
            return this.callback;
        }
    }

    public ExtensionEmbeddingBackend(Context context, EmbeddingInterfaceCompat embeddingInterfaceCompat) {
        context.getClass();
        this.applicationContext = context;
        this.embeddingExtension = embeddingInterfaceCompat;
        EmbeddingCallbackImpl embeddingCallbackImpl = new EmbeddingCallbackImpl();
        this.embeddingCallback = embeddingCallbackImpl;
        this.splitChangeCallbacks = new CopyOnWriteArrayList<>();
        EmbeddingInterfaceCompat embeddingInterfaceCompat2 = this.embeddingExtension;
        if (embeddingInterfaceCompat2 != null) {
            embeddingInterfaceCompat2.setEmbeddingCallback(embeddingCallbackImpl);
        }
        this.ruleTracker = new RuleTracker();
        this.splitSupportStatus = LazyKt.lazy(new Function0() { // from class: gj4
            public final Object invoke() {
                return ExtensionEmbeddingBackend.a(this.b);
            }
        });
    }

    public static SplitController.SplitSupportStatus a(ExtensionEmbeddingBackend extensionEmbeddingBackend) {
        return !extensionEmbeddingBackend.areExtensionsAvailable() ? SplitController.SplitSupportStatus.SPLIT_UNAVAILABLE : Api31Impl.INSTANCE.isSplitPropertyEnabled(extensionEmbeddingBackend.applicationContext);
    }

    private final boolean areExtensionsAvailable() {
        return this.embeddingExtension != null;
    }

    private final ActivityStack getActivityStackFromSplitInfoList(Activity activity) {
        for (SplitInfo splitInfo : this.embeddingCallback.getLastInfo()) {
            if (splitInfo.contains(activity)) {
                if (splitInfo.getPrimaryActivityStack().contains(activity)) {
                    return splitInfo.getPrimaryActivityStack();
                }
                if (splitInfo.getSecondaryActivityStack().contains(activity)) {
                    return splitInfo.getSecondaryActivityStack();
                }
            }
        }
        return null;
    }

    public static /* synthetic */ void getSplitChangeCallbacks$annotations() {
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void addEmbeddedActivityWindowInfoCallbackForActivity(Activity activity, Consumer<EmbeddedActivityWindowInfo> callback) {
        activity.getClass();
        callback.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.addEmbeddedActivityWindowInfoCallbackForActivity(activity, callback);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void addOverlayInfoCallback(String overlayTag, Executor executor, Consumer<OverlayInfo> overlayInfoCallback) {
        overlayTag.getClass();
        executor.getClass();
        overlayInfoCallback.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.addOverlayInfoCallback(overlayTag, executor, overlayInfoCallback);
        } else {
            overlayInfoCallback.accept(new OverlayInfo(overlayTag, null, null));
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void addRule(EmbeddingRule rule) {
        rule.getClass();
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            if (!this.ruleTracker.contains(rule)) {
                RuleTracker.addOrUpdateRule$default(this.ruleTracker, rule, false, 2, null);
                EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
                if (embeddingInterfaceCompat != null) {
                    embeddingInterfaceCompat.setRules(getRules());
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void addSplitListenerForActivity(Activity activity, Executor executor, Consumer<List<SplitInfo>> callback) {
        activity.getClass();
        executor.getClass();
        callback.getClass();
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            if (this.embeddingExtension == null) {
                Log.v(TAG, "Extension not loaded, skipping callback registration.");
                callback.accept(CollectionsKt.emptyList());
            } else {
                SplitListenerWrapper splitListenerWrapper = new SplitListenerWrapper(activity, executor, callback);
                this.splitChangeCallbacks.add(splitListenerWrapper);
                splitListenerWrapper.accept(this.embeddingCallback.getLastInfo());
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void clearOverlayAttributesCalculator() {
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.clearOverlayAttributesCalculator();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void clearSplitAttributesCalculator() {
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
            if (embeddingInterfaceCompat != null) {
                embeddingInterfaceCompat.clearSplitAttributesCalculator();
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void finishActivityStacks(Set<ActivityStack> activityStacks) {
        activityStacks.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.finishActivityStacks(activityStacks);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public ActivityStack getActivityStack(Activity activity) {
        Object next;
        activity.getClass();
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            Iterator<T> it = this.embeddingCallback.getLastActivityStacks().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!((ActivityStack) next).contains(activity));
            ActivityStack activityStackFromSplitInfoList = (ActivityStack) next;
            if (activityStackFromSplitInfoList == null) {
                activityStackFromSplitInfoList = getActivityStackFromSplitInfoList(activity);
            }
            return activityStackFromSplitInfoList;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final EmbeddingInterfaceCompat getEmbeddingExtension() {
        return this.embeddingExtension;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public Set<EmbeddingRule> getRules() {
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            return CollectionsKt.toSet(this.ruleTracker.getSplitRules());
        } finally {
            reentrantLock.unlock();
        }
    }

    public final CopyOnWriteArrayList<SplitListenerWrapper> getSplitChangeCallbacks() {
        return this.splitChangeCallbacks;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public SplitController.SplitSupportStatus getSplitSupportStatus() {
        return (SplitController.SplitSupportStatus) this.splitSupportStatus.getValue();
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void invalidateVisibleActivityStacks() {
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.invalidateVisibleActivityStacks();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public boolean isActivityEmbedded(Activity activity) {
        activity.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            return embeddingInterfaceCompat.isActivityEmbedded(activity);
        }
        return false;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public boolean pinTopActivityStack(int taskId, SplitPinRule splitPinRule) {
        splitPinRule.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            return embeddingInterfaceCompat.pinTopActivityStack(taskId, splitPinRule);
        }
        return false;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void removeEmbeddedActivityWindowInfoCallbackForActivity(Consumer<EmbeddedActivityWindowInfo> callback) {
        callback.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.removeEmbeddedActivityWindowInfoCallbackForActivity(callback);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void removeOverlayInfoCallback(Consumer<OverlayInfo> overlayInfoCallback) {
        overlayInfoCallback.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.removeOverlayInfoCallback(overlayInfoCallback);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void removeRule(EmbeddingRule rule) {
        rule.getClass();
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            if (this.ruleTracker.contains(rule)) {
                this.ruleTracker.removeRule(rule);
                EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
                if (embeddingInterfaceCompat != null) {
                    embeddingInterfaceCompat.setRules(getRules());
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void removeSplitListenerForActivity(Consumer<List<SplitInfo>> consumer) {
        consumer.getClass();
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            Iterator<SplitListenerWrapper> it = this.splitChangeCallbacks.iterator();
            it.getClass();
            while (it.hasNext()) {
                SplitListenerWrapper next = it.next();
                if (Intrinsics.areEqual(next.getCallback(), consumer)) {
                    this.splitChangeCallbacks.remove(next);
                    break;
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void setEmbeddingConfiguration(EmbeddingConfiguration embeddingConfig) {
        embeddingConfig.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.setEmbeddingConfiguration(embeddingConfig);
        }
    }

    public final void setEmbeddingExtension(EmbeddingInterfaceCompat embeddingInterfaceCompat) {
        this.embeddingExtension = embeddingInterfaceCompat;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public Bundle setLaunchingActivityStack(Bundle options, ActivityStack activityStack) {
        Bundle launchingActivityStack;
        options.getClass();
        activityStack.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        return (embeddingInterfaceCompat == null || (launchingActivityStack = embeddingInterfaceCompat.setLaunchingActivityStack(options, activityStack)) == null) ? options : launchingActivityStack;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void setOverlayAttributesCalculator(Function1<? super OverlayAttributesCalculatorParams, OverlayAttributes> calculator) {
        calculator.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.setOverlayAttributesCalculator(calculator);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public Bundle setOverlayCreateParams(Bundle options, OverlayCreateParams overlayCreateParams) {
        Bundle overlayCreateParams2;
        options.getClass();
        overlayCreateParams.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        return (embeddingInterfaceCompat == null || (overlayCreateParams2 = embeddingInterfaceCompat.setOverlayCreateParams(options, overlayCreateParams)) == null) ? options : overlayCreateParams2;
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void setRules(Set<? extends EmbeddingRule> rules) {
        rules.getClass();
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            this.ruleTracker.setRules(rules);
            EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
            if (embeddingInterfaceCompat != null) {
                embeddingInterfaceCompat.setRules(getRules());
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void setSplitAttributesCalculator(Function1<? super SplitAttributesCalculatorParams, SplitAttributes> calculator) {
        calculator.getClass();
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
            if (embeddingInterfaceCompat != null) {
                embeddingInterfaceCompat.setSplitAttributesCalculator(calculator);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void unpinTopActivityStack(int taskId) {
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.unpinTopActivityStack(taskId);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void updateOverlayAttributes(String overlayTag, OverlayAttributes overlayAttributes) {
        overlayTag.getClass();
        overlayAttributes.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.updateOverlayAttributes(overlayTag, overlayAttributes);
        }
    }

    @Override // androidx.window.embedding.EmbeddingBackend
    public void updateSplitAttributes(SplitInfo splitInfo, SplitAttributes splitAttributes) {
        splitInfo.getClass();
        splitAttributes.getClass();
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            embeddingInterfaceCompat.updateSplitAttributes(splitInfo, splitAttributes);
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0017\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007¢\u0006\u0002\u0010\u0015R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$Companion;", "", "<init>", "()V", "globalInstance", "Landroidx/window/embedding/ExtensionEmbeddingBackend;", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "TAG", "", "getInstance", "Landroidx/window/embedding/EmbeddingBackend;", "context", "Landroid/content/Context;", "initAndVerifyEmbeddingExtension", "Landroidx/window/embedding/EmbeddingInterfaceCompat;", "applicationContext", "isExtensionVersionSupported", "", "extensionVersion", "", "(Ljava/lang/Integer;)Z", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final EmbeddingInterfaceCompat initAndVerifyEmbeddingExtension(Context applicationContext) {
            ClassLoader classLoader;
            int extensionVersion = WindowSdkExtensions.INSTANCE.getInstance().getExtensionVersion();
            EmbeddingCompat embeddingCompat = null;
            try {
                if (isExtensionVersionSupported(Integer.valueOf(extensionVersion))) {
                    EmbeddingCompat.Companion companion = EmbeddingCompat.INSTANCE;
                    if (companion.isEmbeddingAvailable() && (classLoader = EmbeddingBackend.class.getClassLoader()) != null) {
                        ActivityEmbeddingComponent activityEmbeddingComponentEmbeddingComponent = companion.embeddingComponent();
                        EmbeddingAdapter embeddingAdapter = new EmbeddingAdapter(new PredicateAdapter(classLoader));
                        embeddingCompat = new EmbeddingCompat(activityEmbeddingComponentEmbeddingComponent, embeddingAdapter, new ConsumerAdapter(classLoader), applicationContext, extensionVersion >= 8 ? new OverlayControllerImpl(activityEmbeddingComponentEmbeddingComponent, embeddingAdapter) : null, extensionVersion >= 6 ? new ActivityWindowInfoCallbackController(activityEmbeddingComponentEmbeddingComponent) : null);
                    }
                }
            } catch (Throwable th) {
                Log.d(ExtensionEmbeddingBackend.TAG, "Failed to load embedding extension: " + th);
            }
            if (embeddingCompat == null) {
                Log.d(ExtensionEmbeddingBackend.TAG, "No supported embedding extension found");
            }
            return embeddingCompat;
        }

        public final EmbeddingBackend getInstance(Context context) {
            context.getClass();
            if (ExtensionEmbeddingBackend.globalInstance == null) {
                ReentrantLock reentrantLock = ExtensionEmbeddingBackend.globalLock;
                reentrantLock.lock();
                try {
                    if (ExtensionEmbeddingBackend.globalInstance == null) {
                        Context applicationContext = context.getApplicationContext();
                        Companion companion = ExtensionEmbeddingBackend.INSTANCE;
                        applicationContext.getClass();
                        ExtensionEmbeddingBackend.globalInstance = new ExtensionEmbeddingBackend(applicationContext, companion.initAndVerifyEmbeddingExtension(applicationContext));
                    }
                    Unit unit = Unit.INSTANCE;
                    reentrantLock.unlock();
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            }
            ExtensionEmbeddingBackend extensionEmbeddingBackend = ExtensionEmbeddingBackend.globalInstance;
            extensionEmbeddingBackend.getClass();
            return extensionEmbeddingBackend;
        }

        public final boolean isExtensionVersionSupported(Integer extensionVersion) {
            return extensionVersion != null && extensionVersion.intValue() >= 1;
        }

        private Companion() {
        }
    }
}
