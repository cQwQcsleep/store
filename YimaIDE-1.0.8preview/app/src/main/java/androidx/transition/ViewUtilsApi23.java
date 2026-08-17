package androidx.transition;

import android.view.View;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
class ViewUtilsApi23 extends ViewUtilsApi22 {
    private static boolean sTryHiddenSetTransitionVisibility = true;

    public static class Api29Impl {
        private Api29Impl() {
        }

        public static void setTransitionVisibility(View view, int i) {
            view.setTransitionVisibility(i);
        }
    }

    @Override // androidx.transition.ViewUtilsApi19
    public void setTransitionVisibility(View view, int i) {
        if (sTryHiddenSetTransitionVisibility) {
            try {
                Api29Impl.setTransitionVisibility(view, i);
            } catch (NoSuchMethodError unused) {
                sTryHiddenSetTransitionVisibility = false;
            }
        }
    }
}
