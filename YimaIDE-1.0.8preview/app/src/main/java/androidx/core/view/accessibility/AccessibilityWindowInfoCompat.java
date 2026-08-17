package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.os.LocaleList;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.os.LocaleListCompat;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class AccessibilityWindowInfoCompat {
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_MAGNIFICATION_OVERLAY = 6;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    private final AccessibilityWindowInfo mInfo;

    public static class Api24Impl {
        private Api24Impl() {
        }

        public static AccessibilityNodeInfo getAnchor(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getAnchor();
        }

        public static CharSequence getTitle(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getTitle();
        }
    }

    public static class Api26Impl {
        private Api26Impl() {
        }

        public static boolean isInPictureInPictureMode(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isInPictureInPictureMode();
        }
    }

    public static class Api30Impl {
        private Api30Impl() {
        }

        public static AccessibilityWindowInfo instantiateAccessibilityWindowInfo() {
            return new AccessibilityWindowInfo();
        }
    }

    public static class Api33Impl {
        private Api33Impl() {
        }

        public static int getDisplayId(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getDisplayId();
        }

        public static void getRegionInScreen(AccessibilityWindowInfo accessibilityWindowInfo, Region region) {
            accessibilityWindowInfo.getRegionInScreen(region);
        }

        public static AccessibilityNodeInfoCompat getRoot(Object obj, int i) {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo) obj).getRoot(i));
        }
    }

    public static class Api34Impl {
        private Api34Impl() {
        }

        public static LocaleList getLocales(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getLocales();
        }

        public static long getTransitionTimeMillis(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getTransitionTimeMillis();
        }
    }

    public AccessibilityWindowInfoCompat() {
        this.mInfo = Api30Impl.instantiateAccessibilityWindowInfo();
    }

    public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat accessibilityWindowInfoCompat) {
        if (accessibilityWindowInfoCompat == null) {
            return null;
        }
        return wrapNonNullInstance(AccessibilityWindowInfo.obtain(accessibilityWindowInfoCompat.mInfo));
    }

    private static String typeToString(int i) {
        if (i == 1) {
            return "TYPE_APPLICATION";
        }
        if (i == 2) {
            return "TYPE_INPUT_METHOD";
        }
        if (i != 3) {
            return i != 4 ? "<UNKNOWN>" : "TYPE_ACCESSIBILITY_OVERLAY";
        }
        return "TYPE_SYSTEM";
    }

    public static AccessibilityWindowInfoCompat wrapNonNullInstance(AccessibilityWindowInfo accessibilityWindowInfo) {
        if (accessibilityWindowInfo != null) {
            return new AccessibilityWindowInfoCompat(accessibilityWindowInfo);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityWindowInfoCompat)) {
            return false;
        }
        AccessibilityWindowInfo accessibilityWindowInfo = this.mInfo;
        AccessibilityWindowInfo accessibilityWindowInfo2 = ((AccessibilityWindowInfoCompat) obj).mInfo;
        if (accessibilityWindowInfo == null) {
            return accessibilityWindowInfo2 == null;
        }
        return accessibilityWindowInfo.equals(accessibilityWindowInfo2);
    }

    public AccessibilityNodeInfoCompat getAnchor() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(Api24Impl.getAnchor(this.mInfo));
    }

    public void getBoundsInScreen(Rect rect) {
        this.mInfo.getBoundsInScreen(rect);
    }

    public AccessibilityWindowInfoCompat getChild(int i) {
        return wrapNonNullInstance(this.mInfo.getChild(i));
    }

    public int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public int getDisplayId() {
        return Api33Impl.getDisplayId(this.mInfo);
    }

    public int getId() {
        return this.mInfo.getId();
    }

    public int getLayer() {
        return this.mInfo.getLayer();
    }

    public LocaleListCompat getLocales() {
        return Build.VERSION.SDK_INT >= 34 ? LocaleListCompat.wrap(Api34Impl.getLocales(this.mInfo)) : LocaleListCompat.getEmptyLocaleList();
    }

    public AccessibilityWindowInfoCompat getParent() {
        return wrapNonNullInstance(this.mInfo.getParent());
    }

    public void getRegionInScreen(Region region) {
        Api33Impl.getRegionInScreen(this.mInfo, region);
    }

    public AccessibilityNodeInfoCompat getRoot() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getRoot());
    }

    public CharSequence getTitle() {
        return Api24Impl.getTitle(this.mInfo);
    }

    public long getTransitionTimeMillis() {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getTransitionTimeMillis(this.mInfo);
        }
        return 0L;
    }

    public int getType() {
        return this.mInfo.getType();
    }

    public int hashCode() {
        AccessibilityWindowInfo accessibilityWindowInfo = this.mInfo;
        if (accessibilityWindowInfo == null) {
            return 0;
        }
        return accessibilityWindowInfo.hashCode();
    }

    public boolean isAccessibilityFocused() {
        return this.mInfo.isAccessibilityFocused();
    }

    public boolean isActive() {
        return this.mInfo.isActive();
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public boolean isInPictureInPictureMode() {
        return Api26Impl.isInPictureInPictureMode(this.mInfo);
    }

    @Deprecated
    public void recycle() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AccessibilityWindowInfo[id=");
        Rect rect = new Rect();
        getBoundsInScreen(rect);
        sb.append(getId());
        sb.append(", type=");
        sb.append(typeToString(getType()));
        sb.append(", layer=");
        sb.append(getLayer());
        sb.append(", bounds=");
        sb.append(rect);
        sb.append(", focused=");
        sb.append(isFocused());
        sb.append(", active=");
        sb.append(isActive());
        sb.append(", hasParent=");
        sb.append(getParent() != null);
        sb.append(", hasChildren=");
        sb.append(getChildCount() > 0);
        sb.append(", transitionTime=");
        sb.append(getTransitionTimeMillis());
        sb.append(", locales=");
        sb.append(getLocales());
        sb.append(']');
        return sb.toString();
    }

    public AccessibilityWindowInfo unwrap() {
        return this.mInfo;
    }

    private AccessibilityWindowInfoCompat(AccessibilityWindowInfo accessibilityWindowInfo) {
        this.mInfo = accessibilityWindowInfo;
    }

    public AccessibilityNodeInfoCompat getRoot(int i) {
        return Api33Impl.getRoot(this.mInfo, i);
    }

    public static AccessibilityWindowInfoCompat obtain() {
        return wrapNonNullInstance(AccessibilityWindowInfo.obtain());
    }
}
