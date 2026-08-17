package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.window.core.ActivityComponentInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u001b\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\nJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015J\u0013\u0010\u0018\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0005H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001d"}, d2 = {"Landroidx/window/embedding/ActivityFilter;", "", "activityComponentInfo", "Landroidx/window/core/ActivityComponentInfo;", "intentAction", "", "<init>", "(Landroidx/window/core/ActivityComponentInfo;Ljava/lang/String;)V", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/ComponentName;Ljava/lang/String;)V", "getActivityComponentInfo$window_release", "()Landroidx/window/core/ActivityComponentInfo;", "getIntentAction", "()Ljava/lang/String;", "matchesIntent", "", AccessibilityNodeInfoCompat.MathInfoCompat.MATH_ATTRIBUTE_INTENT, "Landroid/content/Intent;", "matchesActivity", "activity", "Landroid/app/Activity;", "getComponentName", "()Landroid/content/ComponentName;", "equals", "other", "hashCode", "", "toString", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ActivityFilter {
    private final ActivityComponentInfo activityComponentInfo;
    private final String intentAction;

    public ActivityFilter(ActivityComponentInfo activityComponentInfo, String str) {
        activityComponentInfo.getClass();
        this.activityComponentInfo = activityComponentInfo;
        this.intentAction = str;
        MatcherUtils.INSTANCE.validateComponentName$window_release(activityComponentInfo.getPackageName(), activityComponentInfo.getClassName());
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActivityFilter)) {
            return false;
        }
        ActivityFilter activityFilter = (ActivityFilter) other;
        return Intrinsics.areEqual(this.activityComponentInfo, activityFilter.activityComponentInfo) && Intrinsics.areEqual(this.intentAction, activityFilter.intentAction);
    }

    /* JADX INFO: renamed from: getActivityComponentInfo$window_release, reason: from getter */
    public final ActivityComponentInfo getActivityComponentInfo() {
        return this.activityComponentInfo;
    }

    public final ComponentName getComponentName() {
        return new ComponentName(this.activityComponentInfo.getPackageName(), this.activityComponentInfo.getClassName());
    }

    public final String getIntentAction() {
        return this.intentAction;
    }

    public int hashCode() {
        int iHashCode = this.activityComponentInfo.hashCode() * 31;
        String str = this.intentAction;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public final boolean matchesActivity(Activity activity) {
        activity.getClass();
        if (!MatcherUtils.INSTANCE.isActivityMatching$window_release(activity, this.activityComponentInfo)) {
            return false;
        }
        String str = this.intentAction;
        if (str == null) {
            return true;
        }
        Intent intent = activity.getIntent();
        return Intrinsics.areEqual(str, intent != null ? intent.getAction() : null);
    }

    public final boolean matchesIntent(Intent intent) {
        intent.getClass();
        if (!MatcherUtils.INSTANCE.isIntentMatching$window_release(intent, this.activityComponentInfo)) {
            return false;
        }
        String str = this.intentAction;
        return str == null || Intrinsics.areEqual(str, intent.getAction());
    }

    public String toString() {
        return "ActivityFilter(componentName=" + this.activityComponentInfo + ", intentAction=" + this.intentAction + ')';
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ActivityFilter(ComponentName componentName, String str) {
        this(new ActivityComponentInfo(componentName), str);
        componentName.getClass();
    }
}
