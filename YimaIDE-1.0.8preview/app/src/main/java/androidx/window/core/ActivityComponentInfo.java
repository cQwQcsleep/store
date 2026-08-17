package androidx.window.core;

import android.content.ComponentName;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0013"}, d2 = {"Landroidx/window/core/ActivityComponentInfo;", "", "packageName", "", "className", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/ComponentName;)V", "getPackageName", "()Ljava/lang/String;", "getClassName", "equals", "", "other", "hashCode", "", "toString", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ActivityComponentInfo {
    private final String className;
    private final String packageName;

    /* JADX WARN: Illegal instructions before constructor call */
    public ActivityComponentInfo(ComponentName componentName) {
        componentName.getClass();
        String packageName = componentName.getPackageName();
        packageName.getClass();
        String className = componentName.getClassName();
        className.getClass();
        this(packageName, className);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(ActivityComponentInfo.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        ActivityComponentInfo activityComponentInfo = (ActivityComponentInfo) other;
        return Intrinsics.areEqual(this.packageName, activityComponentInfo.packageName) && Intrinsics.areEqual(this.className, activityComponentInfo.className);
    }

    public final String getClassName() {
        return this.className;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public int hashCode() {
        return (this.packageName.hashCode() * 31) + this.className.hashCode();
    }

    public String toString() {
        return "ClassInfo { packageName: " + this.packageName + ", className: " + this.className + " }";
    }

    public ActivityComponentInfo(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.packageName = str;
        this.className = str2;
    }
}
