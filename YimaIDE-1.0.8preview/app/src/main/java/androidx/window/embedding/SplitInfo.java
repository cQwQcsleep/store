package androidx.window.embedding;

import android.app.Activity;
import android.os.IBinder;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.WindowSdkExtensions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B5\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fB)\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\rB)\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\u000eB!\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\u000fJ\r\u0010\u0017\u001a\u00020\bH\u0001¢\u0006\u0002\b\u0018J\r\u0010\u0019\u001a\u00020\nH\u0001¢\u0006\u0002\b\u001aJ\u0011\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0086\u0002J\u0013\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020$H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0015\u0010\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Landroidx/window/embedding/SplitInfo;", "", "primaryActivityStack", "Landroidx/window/embedding/ActivityStack;", "secondaryActivityStack", "splitAttributes", "Landroidx/window/embedding/SplitAttributes;", "binder", "Landroid/os/IBinder;", "token", "Landroidx/window/extensions/embedding/SplitInfo$Token;", "<init>", "(Landroidx/window/embedding/ActivityStack;Landroidx/window/embedding/ActivityStack;Landroidx/window/embedding/SplitAttributes;Landroid/os/IBinder;Landroidx/window/extensions/embedding/SplitInfo$Token;)V", "(Landroidx/window/embedding/ActivityStack;Landroidx/window/embedding/ActivityStack;Landroidx/window/embedding/SplitAttributes;Landroidx/window/extensions/embedding/SplitInfo$Token;)V", "(Landroidx/window/embedding/ActivityStack;Landroidx/window/embedding/ActivityStack;Landroidx/window/embedding/SplitAttributes;Landroid/os/IBinder;)V", "(Landroidx/window/embedding/ActivityStack;Landroidx/window/embedding/ActivityStack;Landroidx/window/embedding/SplitAttributes;)V", "getPrimaryActivityStack", "()Landroidx/window/embedding/ActivityStack;", "getSecondaryActivityStack", "getSplitAttributes", "()Landroidx/window/embedding/SplitAttributes;", "getBinder$annotations", "()V", "getBinder", "getBinder$window_release", "getToken", "getToken$window_release", "contains", "", "activity", "Landroid/app/Activity;", "equals", "other", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SplitInfo {
    private final IBinder binder;
    private final ActivityStack primaryActivityStack;
    private final ActivityStack secondaryActivityStack;
    private final SplitAttributes splitAttributes;
    private final androidx.window.extensions.embedding.SplitInfo.Token token;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SplitInfo(ActivityStack activityStack, ActivityStack activityStack2, SplitAttributes splitAttributes, IBinder iBinder) {
        this(activityStack, activityStack2, splitAttributes, iBinder, null);
        activityStack.getClass();
        activityStack2.getClass();
        splitAttributes.getClass();
        iBinder.getClass();
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(new IntRange(3, 4));
    }

    @Deprecated(message = "Use [token] instead", replaceWith = @ReplaceWith(expression = "SplitInfo.token", imports = {"androidx.window.embedding.SplitInfo"}))
    private static /* synthetic */ void getBinder$annotations() {
    }

    public final boolean contains(Activity activity) {
        activity.getClass();
        return this.primaryActivityStack.contains(activity) || this.secondaryActivityStack.contains(activity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SplitInfo)) {
            return false;
        }
        SplitInfo splitInfo = (SplitInfo) other;
        return Intrinsics.areEqual(this.primaryActivityStack, splitInfo.primaryActivityStack) && Intrinsics.areEqual(this.secondaryActivityStack, splitInfo.secondaryActivityStack) && Intrinsics.areEqual(this.splitAttributes, splitInfo.splitAttributes) && Intrinsics.areEqual(this.token, splitInfo.token) && Intrinsics.areEqual(this.binder, splitInfo.binder);
    }

    public final IBinder getBinder$window_release() {
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(new IntRange(3, 4));
        IBinder iBinder = this.binder;
        if (iBinder != null) {
            return iBinder;
        }
        w01.a("Required value was null.");
        return null;
    }

    public final ActivityStack getPrimaryActivityStack() {
        return this.primaryActivityStack;
    }

    public final ActivityStack getSecondaryActivityStack() {
        return this.secondaryActivityStack;
    }

    public final SplitAttributes getSplitAttributes() {
        return this.splitAttributes;
    }

    public final androidx.window.extensions.embedding.SplitInfo.Token getToken$window_release() {
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(5);
        androidx.window.extensions.embedding.SplitInfo.Token token = this.token;
        if (token != null) {
            return token;
        }
        w01.a("Required value was null.");
        return null;
    }

    public int hashCode() {
        int iHashCode = ((((this.primaryActivityStack.hashCode() * 31) + this.secondaryActivityStack.hashCode()) * 31) + this.splitAttributes.hashCode()) * 31;
        androidx.window.extensions.embedding.SplitInfo.Token token = this.token;
        int iHashCode2 = (iHashCode + (token != null ? token.hashCode() : 0)) * 31;
        IBinder iBinder = this.binder;
        return iHashCode2 + (iBinder != null ? iBinder.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SplitInfo:{");
        sb.append("primaryActivityStack=" + this.primaryActivityStack + ", ");
        sb.append("secondaryActivityStack=" + this.secondaryActivityStack + ", ");
        sb.append("splitAttributes=" + this.splitAttributes + ", ");
        if (this.token != null) {
            sb.append("token=" + this.token);
        }
        if (this.binder != null) {
            sb.append("binder=" + this.binder);
        }
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SplitInfo(ActivityStack activityStack, ActivityStack activityStack2, SplitAttributes splitAttributes, androidx.window.extensions.embedding.SplitInfo.Token token) {
        this(activityStack, activityStack2, splitAttributes, null, token);
        activityStack.getClass();
        activityStack2.getClass();
        splitAttributes.getClass();
        token.getClass();
    }

    private SplitInfo(ActivityStack activityStack, ActivityStack activityStack2, SplitAttributes splitAttributes, IBinder iBinder, androidx.window.extensions.embedding.SplitInfo.Token token) {
        this.primaryActivityStack = activityStack;
        this.secondaryActivityStack = activityStack2;
        this.splitAttributes = splitAttributes;
        this.binder = iBinder;
        this.token = token;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SplitInfo(ActivityStack activityStack, ActivityStack activityStack2, SplitAttributes splitAttributes) {
        this(activityStack, activityStack2, splitAttributes, null, null);
        activityStack.getClass();
        activityStack2.getClass();
        splitAttributes.getClass();
    }
}
