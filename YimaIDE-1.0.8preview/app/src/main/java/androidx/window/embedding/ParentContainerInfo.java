package androidx.window.embedding;

import android.content.res.Configuration;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.core.Bounds;
import androidx.window.layout.WindowLayoutInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Landroidx/window/embedding/ParentContainerInfo;", "", "windowBounds", "Landroidx/window/core/Bounds;", "windowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "configuration", "Landroid/content/res/Configuration;", "density", "", "<init>", "(Landroidx/window/core/Bounds;Landroidx/window/layout/WindowLayoutInfo;Landroid/content/res/Configuration;F)V", "getWindowBounds", "()Landroidx/window/core/Bounds;", "getWindowLayoutInfo", "()Landroidx/window/layout/WindowLayoutInfo;", "getConfiguration", "()Landroid/content/res/Configuration;", "getDensity", "()F", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final /* data */ class ParentContainerInfo {
    private final Configuration configuration;
    private final float density;
    private final Bounds windowBounds;
    private final WindowLayoutInfo windowLayoutInfo;

    public ParentContainerInfo(Bounds bounds, WindowLayoutInfo windowLayoutInfo, Configuration configuration, float f) {
        bounds.getClass();
        windowLayoutInfo.getClass();
        configuration.getClass();
        this.windowBounds = bounds;
        this.windowLayoutInfo = windowLayoutInfo;
        this.configuration = configuration;
        this.density = f;
    }

    public static /* synthetic */ ParentContainerInfo copy$default(ParentContainerInfo parentContainerInfo, Bounds bounds, WindowLayoutInfo windowLayoutInfo, Configuration configuration, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            bounds = parentContainerInfo.windowBounds;
        }
        if ((i & 2) != 0) {
            windowLayoutInfo = parentContainerInfo.windowLayoutInfo;
        }
        if ((i & 4) != 0) {
            configuration = parentContainerInfo.configuration;
        }
        if ((i & 8) != 0) {
            f = parentContainerInfo.density;
        }
        return parentContainerInfo.copy(bounds, windowLayoutInfo, configuration, f);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Bounds getWindowBounds() {
        return this.windowBounds;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final WindowLayoutInfo getWindowLayoutInfo() {
        return this.windowLayoutInfo;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Configuration getConfiguration() {
        return this.configuration;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getDensity() {
        return this.density;
    }

    public final ParentContainerInfo copy(Bounds windowBounds, WindowLayoutInfo windowLayoutInfo, Configuration configuration, float density) {
        windowBounds.getClass();
        windowLayoutInfo.getClass();
        configuration.getClass();
        return new ParentContainerInfo(windowBounds, windowLayoutInfo, configuration, density);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParentContainerInfo)) {
            return false;
        }
        ParentContainerInfo parentContainerInfo = (ParentContainerInfo) other;
        return Intrinsics.areEqual(this.windowBounds, parentContainerInfo.windowBounds) && Intrinsics.areEqual(this.windowLayoutInfo, parentContainerInfo.windowLayoutInfo) && Intrinsics.areEqual(this.configuration, parentContainerInfo.configuration) && Float.compare(this.density, parentContainerInfo.density) == 0;
    }

    public final Configuration getConfiguration() {
        return this.configuration;
    }

    public final float getDensity() {
        return this.density;
    }

    public final Bounds getWindowBounds() {
        return this.windowBounds;
    }

    public final WindowLayoutInfo getWindowLayoutInfo() {
        return this.windowLayoutInfo;
    }

    public int hashCode() {
        return (((((this.windowBounds.hashCode() * 31) + this.windowLayoutInfo.hashCode()) * 31) + this.configuration.hashCode()) * 31) + Float.hashCode(this.density);
    }

    public String toString() {
        return "ParentContainerInfo(windowBounds=" + this.windowBounds + ", windowLayoutInfo=" + this.windowLayoutInfo + ", configuration=" + this.configuration + ", density=" + this.density + ')';
    }
}
