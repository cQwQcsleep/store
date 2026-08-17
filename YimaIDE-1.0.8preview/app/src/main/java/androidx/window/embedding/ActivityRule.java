package androidx.window.embedding;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0018B+\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0006H\u0080\u0002¢\u0006\u0002\b\u0011J\u0013\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0003H\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Landroidx/window/embedding/ActivityRule;", "Landroidx/window/embedding/EmbeddingRule;", "tag", "", "filters", "", "Landroidx/window/embedding/ActivityFilter;", "alwaysExpand", "", "<init>", "(Ljava/lang/String;Ljava/util/Set;Z)V", "getFilters", "()Ljava/util/Set;", "getAlwaysExpand", "()Z", "plus", "filter", "plus$window_release", "equals", "other", "", "hashCode", "", "toString", "Builder", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ActivityRule extends EmbeddingRule {
    private final boolean alwaysExpand;
    private final Set<ActivityFilter> filters;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\f\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0006\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/window/embedding/ActivityRule$Builder;", "", "filters", "", "Landroidx/window/embedding/ActivityFilter;", "<init>", "(Ljava/util/Set;)V", "tag", "", "alwaysExpand", "", "setAlwaysExpand", "setTag", "build", "Landroidx/window/embedding/ActivityRule;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Builder {
        private boolean alwaysExpand;
        private final Set<ActivityFilter> filters;
        private String tag;

        public Builder(Set<ActivityFilter> set) {
            set.getClass();
            this.filters = set;
        }

        public final ActivityRule build() {
            return new ActivityRule(this.tag, this.filters, this.alwaysExpand);
        }

        public final Builder setAlwaysExpand(boolean alwaysExpand) {
            this.alwaysExpand = alwaysExpand;
            return this;
        }

        public final Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityRule(String str, Set<ActivityFilter> set, boolean z) {
        super(str);
        set.getClass();
        this.filters = set;
        this.alwaysExpand = z;
    }

    @Override // androidx.window.embedding.EmbeddingRule
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActivityRule) || !super.equals(other)) {
            return false;
        }
        ActivityRule activityRule = (ActivityRule) other;
        return Intrinsics.areEqual(this.filters, activityRule.filters) && this.alwaysExpand == activityRule.alwaysExpand;
    }

    public final boolean getAlwaysExpand() {
        return this.alwaysExpand;
    }

    public final Set<ActivityFilter> getFilters() {
        return this.filters;
    }

    @Override // androidx.window.embedding.EmbeddingRule
    public int hashCode() {
        return (((super.hashCode() * 31) + this.filters.hashCode()) * 31) + Boolean.hashCode(this.alwaysExpand);
    }

    public final ActivityRule plus$window_release(ActivityFilter filter) {
        filter.getClass();
        return new ActivityRule(getTag(), SetsKt.plus(this.filters, filter), this.alwaysExpand);
    }

    public String toString() {
        return "ActivityRule:{tag={" + getTag() + "},filters={" + this.filters + "}, alwaysExpand={" + this.alwaysExpand + "}}";
    }

    public /* synthetic */ ActivityRule(String str, Set set, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, set, (i & 4) != 0 ? false : z);
    }
}
