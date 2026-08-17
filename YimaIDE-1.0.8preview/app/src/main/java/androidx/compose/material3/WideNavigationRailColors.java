package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ?\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000f\u0010\u000bR\u0013\u0010\u0007\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u0019"}, d2 = {"Landroidx/compose/material3/WideNavigationRailColors;", "", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "modalContainerColor", "modalScrimColor", "modalContentColor", "<init>", "(JJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getContainerColor-0d7_KjU", "()J", "J", "getContentColor-0d7_KjU", "getModalContainerColor-0d7_KjU", "getModalScrimColor-0d7_KjU", "getModalContentColor-0d7_KjU", "copy", "copy-t635Npw", "(JJJJJ)Landroidx/compose/material3/WideNavigationRailColors;", "equals", "", "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WideNavigationRailColors {
    public static final int $stable = 0;
    private final long containerColor;
    private final long contentColor;
    private final long modalContainerColor;
    private final long modalContentColor;
    private final long modalScrimColor;

    private WideNavigationRailColors(long j, long j2, long j3, long j4, long j5) {
        this.containerColor = j;
        this.contentColor = j2;
        this.modalContainerColor = j3;
        this.modalScrimColor = j4;
        this.modalContentColor = j5;
    }

    /* JADX INFO: renamed from: copy-t635Npw$default, reason: not valid java name */
    public static /* synthetic */ WideNavigationRailColors m1326copyt635Npw$default(WideNavigationRailColors wideNavigationRailColors, long j, long j2, long j3, long j4, long j5, int i, Object obj) {
        if ((i & 1) != 0) {
            j = wideNavigationRailColors.containerColor;
        }
        long j6 = j;
        if ((i & 2) != 0) {
            j2 = wideNavigationRailColors.contentColor;
        }
        return wideNavigationRailColors.m1327copyt635Npw(j6, j2, (i & 4) != 0 ? wideNavigationRailColors.modalContainerColor : j3, (i & 8) != 0 ? wideNavigationRailColors.modalScrimColor : j4, (i & 16) != 0 ? wideNavigationRailColors.modalContentColor : j5);
    }

    /* JADX INFO: renamed from: copy-t635Npw, reason: not valid java name */
    public final WideNavigationRailColors m1327copyt635Npw(long containerColor, long contentColor, long modalContainerColor, long modalScrimColor, long modalContentColor) {
        return new WideNavigationRailColors(containerColor != 16 ? containerColor : this.containerColor, contentColor != 16 ? contentColor : this.contentColor, modalContainerColor != 16 ? modalContainerColor : this.modalContainerColor, modalScrimColor != 16 ? modalScrimColor : this.modalScrimColor, modalContentColor != 16 ? modalContentColor : this.modalContentColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof WideNavigationRailColors)) {
            return false;
        }
        WideNavigationRailColors wideNavigationRailColors = (WideNavigationRailColors) other;
        return Color.m3135equalsimpl0(this.containerColor, wideNavigationRailColors.containerColor) && Color.m3135equalsimpl0(this.contentColor, wideNavigationRailColors.contentColor) && Color.m3135equalsimpl0(this.modalContainerColor, wideNavigationRailColors.modalContainerColor) && Color.m3135equalsimpl0(this.modalScrimColor, wideNavigationRailColors.modalScrimColor);
    }

    /* JADX INFO: renamed from: getContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getContainerColor() {
        return this.containerColor;
    }

    /* JADX INFO: renamed from: getContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getContentColor() {
        return this.contentColor;
    }

    /* JADX INFO: renamed from: getModalContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getModalContainerColor() {
        return this.modalContainerColor;
    }

    /* JADX INFO: renamed from: getModalContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getModalContentColor() {
        return this.modalContentColor;
    }

    /* JADX INFO: renamed from: getModalScrimColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getModalScrimColor() {
        return this.modalScrimColor;
    }

    public int hashCode() {
        return (((((((Color.m3141hashCodeimpl(this.containerColor) * 31) + Color.m3141hashCodeimpl(this.contentColor)) * 31) + Color.m3141hashCodeimpl(this.modalContainerColor)) * 31) + Color.m3141hashCodeimpl(this.modalScrimColor)) * 31) + Color.m3141hashCodeimpl(this.modalContentColor);
    }

    public /* synthetic */ WideNavigationRailColors(long j, long j2, long j3, long j4, long j5, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5);
    }
}
