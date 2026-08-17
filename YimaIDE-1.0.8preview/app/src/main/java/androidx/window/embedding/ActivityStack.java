package androidx.window.embedding;

import android.app.Activity;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.WindowSdkExtensions;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B)\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nB\u001f\b\u0017\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\u000bJ\r\u0010\u000f\u001a\u00020\bH\u0001¢\u0006\u0002\b\u0010J\u0011\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004H\u0086\u0002J\u0013\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000eR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/window/embedding/ActivityStack;", "", "activitiesInProcess", "", "Landroid/app/Activity;", "isEmpty", "", "token", "Landroidx/window/extensions/embedding/ActivityStack$Token;", "<init>", "(Ljava/util/List;ZLandroidx/window/extensions/embedding/ActivityStack$Token;)V", "(Ljava/util/List;Z)V", "getActivitiesInProcess$window_release", "()Ljava/util/List;", "()Z", "getToken", "getToken$window_release", "contains", "activity", "equals", "other", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ActivityStack {
    private final List<Activity> activitiesInProcess;
    private final boolean isEmpty;
    private final androidx.window.extensions.embedding.ActivityStack.Token token;

    /* JADX WARN: Multi-variable type inference failed */
    public ActivityStack(List<? extends Activity> list, boolean z, androidx.window.extensions.embedding.ActivityStack.Token token) {
        list.getClass();
        this.activitiesInProcess = list;
        this.isEmpty = z;
        this.token = token;
    }

    public final boolean contains(Activity activity) {
        activity.getClass();
        return this.activitiesInProcess.contains(activity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActivityStack)) {
            return false;
        }
        ActivityStack activityStack = (ActivityStack) other;
        return Intrinsics.areEqual(this.activitiesInProcess, activityStack.activitiesInProcess) && this.isEmpty == activityStack.isEmpty && Intrinsics.areEqual(this.token, activityStack.token);
    }

    public final List<Activity> getActivitiesInProcess$window_release() {
        return this.activitiesInProcess;
    }

    public final androidx.window.extensions.embedding.ActivityStack.Token getToken$window_release() {
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(5);
        androidx.window.extensions.embedding.ActivityStack.Token token = this.token;
        token.getClass();
        return token;
    }

    public int hashCode() {
        int iHashCode = ((this.activitiesInProcess.hashCode() * 31) + Boolean.hashCode(this.isEmpty)) * 31;
        androidx.window.extensions.embedding.ActivityStack.Token token = this.token;
        return iHashCode + (token != null ? token.hashCode() : 0);
    }

    /* JADX INFO: renamed from: isEmpty, reason: from getter */
    public final boolean getIsEmpty() {
        return this.isEmpty;
    }

    public String toString() {
        return "ActivityStack{activitiesInProcess=" + this.activitiesInProcess + ", isEmpty=" + this.isEmpty + ", token=" + this.token + '}';
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ActivityStack(List<? extends Activity> list, boolean z) {
        this(list, z, null);
        list.getClass();
    }
}
