package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.core.ActivityComponentInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B#\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bB#\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\fJ\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015J\u0016\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0006H\u0016J\u0013\u0010\u001b\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001f"}, d2 = {"Landroidx/window/embedding/SplitPairFilter;", "", "_primaryActivityName", "Landroidx/window/core/ActivityComponentInfo;", "_secondaryActivityName", "secondaryActivityIntentAction", "", "<init>", "(Landroidx/window/core/ActivityComponentInfo;Landroidx/window/core/ActivityComponentInfo;Ljava/lang/String;)V", "primaryActivityName", "Landroid/content/ComponentName;", "secondaryActivityName", "(Landroid/content/ComponentName;Landroid/content/ComponentName;Ljava/lang/String;)V", "getSecondaryActivityIntentAction", "()Ljava/lang/String;", "getPrimaryActivityName", "()Landroid/content/ComponentName;", "getSecondaryActivityName", "matchesActivityPair", "", "primaryActivity", "Landroid/app/Activity;", "secondaryActivity", "matchesActivityIntentPair", "secondaryActivityIntent", "Landroid/content/Intent;", "toString", "equals", "other", "hashCode", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SplitPairFilter {
    private final ActivityComponentInfo _primaryActivityName;
    private final ActivityComponentInfo _secondaryActivityName;

    /* JADX INFO: renamed from: secondaryActivityIntentAction, reason: from kotlin metadata and from toString */
    private final String secondaryActivityAction;

    public SplitPairFilter(ActivityComponentInfo activityComponentInfo, ActivityComponentInfo activityComponentInfo2, String str) {
        activityComponentInfo.getClass();
        activityComponentInfo2.getClass();
        this._primaryActivityName = activityComponentInfo;
        this._secondaryActivityName = activityComponentInfo2;
        this.secondaryActivityAction = str;
        MatcherUtils matcherUtils = MatcherUtils.INSTANCE;
        matcherUtils.validateComponentName$window_release(activityComponentInfo.getPackageName(), activityComponentInfo.getClassName());
        matcherUtils.validateComponentName$window_release(activityComponentInfo2.getPackageName(), activityComponentInfo2.getClassName());
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(SplitPairFilter.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        SplitPairFilter splitPairFilter = (SplitPairFilter) other;
        return Intrinsics.areEqual(this._primaryActivityName, splitPairFilter._primaryActivityName) && Intrinsics.areEqual(this._secondaryActivityName, splitPairFilter._secondaryActivityName) && Intrinsics.areEqual(this.secondaryActivityAction, splitPairFilter.secondaryActivityAction);
    }

    public final ComponentName getPrimaryActivityName() {
        return new ComponentName(this._primaryActivityName.getPackageName(), this._primaryActivityName.getClassName());
    }

    /* JADX INFO: renamed from: getSecondaryActivityIntentAction, reason: from getter */
    public final String getSecondaryActivityAction() {
        return this.secondaryActivityAction;
    }

    public final ComponentName getSecondaryActivityName() {
        return new ComponentName(this._secondaryActivityName.getPackageName(), this._secondaryActivityName.getClassName());
    }

    public int hashCode() {
        int iHashCode = ((this._primaryActivityName.hashCode() * 31) + this._secondaryActivityName.hashCode()) * 31;
        String str = this.secondaryActivityAction;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public final boolean matchesActivityIntentPair(Activity primaryActivity, Intent secondaryActivityIntent) {
        primaryActivity.getClass();
        secondaryActivityIntent.getClass();
        MatcherUtils matcherUtils = MatcherUtils.INSTANCE;
        if (!matcherUtils.isActivityMatching$window_release(primaryActivity, this._primaryActivityName) || !matcherUtils.isIntentMatching$window_release(secondaryActivityIntent, this._secondaryActivityName)) {
            return false;
        }
        String str = this.secondaryActivityAction;
        return str == null || Intrinsics.areEqual(str, secondaryActivityIntent.getAction());
    }

    public final boolean matchesActivityPair(Activity primaryActivity, Activity secondaryActivity) {
        primaryActivity.getClass();
        secondaryActivity.getClass();
        MatcherUtils matcherUtils = MatcherUtils.INSTANCE;
        if (!matcherUtils.isActivityMatching$window_release(primaryActivity, this._primaryActivityName) || !matcherUtils.isActivityMatching$window_release(secondaryActivity, this._secondaryActivityName)) {
            return false;
        }
        String str = this.secondaryActivityAction;
        if (str == null) {
            return true;
        }
        Intent intent = secondaryActivity.getIntent();
        return Intrinsics.areEqual(str, intent != null ? intent.getAction() : null);
    }

    public String toString() {
        return "SplitPairFilter{primaryActivityName=" + getPrimaryActivityName() + ", secondaryActivityName=" + getSecondaryActivityName() + ", secondaryActivityAction=" + this.secondaryActivityAction + '}';
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SplitPairFilter(ComponentName componentName, ComponentName componentName2, String str) {
        this(new ActivityComponentInfo(componentName), new ActivityComponentInfo(componentName2), str);
        componentName.getClass();
        componentName2.getClass();
    }
}
