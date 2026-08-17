package com.intellij.util;

import com.intellij.psi.PsiModifierList;
import com.intellij.ui.IconManager;
import com.intellij.ui.PlatformIcons;
import com.intellij.ui.icons.RowIcon;
import javax.swing.Icon;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public final class VisibilityIcons {
    public static void setVisibilityIcon(PsiModifierList psiModifierList, RowIcon rowIcon) {
        if (psiModifierList == null) {
            rowIcon.setIcon(IconManager.getInstance().createEmptyIcon(IconManager.getInstance().getPlatformIcon(PlatformIcons.Public)), 1);
            return;
        }
        if (psiModifierList.hasModifierProperty("public")) {
            setVisibilityIcon(4, rowIcon);
            return;
        }
        if (psiModifierList.hasModifierProperty("private")) {
            setVisibilityIcon(1, rowIcon);
            return;
        }
        if (psiModifierList.hasModifierProperty("protected")) {
            setVisibilityIcon(3, rowIcon);
        } else if (psiModifierList.hasModifierProperty("packageLocal")) {
            setVisibilityIcon(2, rowIcon);
        } else {
            rowIcon.setIcon(IconManager.getInstance().createEmptyIcon(IconManager.getInstance().getPlatformIcon(PlatformIcons.Public)), 1);
        }
    }

    public static void setVisibilityIcon(int i, RowIcon rowIcon) {
        Icon platformIcon;
        IconManager iconManager = IconManager.getInstance();
        if (i == 1) {
            platformIcon = iconManager.getPlatformIcon(PlatformIcons.Private);
        } else if (i == 2) {
            platformIcon = iconManager.getPlatformIcon(PlatformIcons.Local);
        } else if (i == 3) {
            platformIcon = iconManager.getPlatformIcon(PlatformIcons.Protected);
        } else if (i != 4) {
            platformIcon = iconManager.createEmptyIcon(iconManager.getPlatformIcon(PlatformIcons.Public));
        } else {
            platformIcon = iconManager.getPlatformIcon(PlatformIcons.Public);
        }
        rowIcon.setIcon(platformIcon, 1);
    }
}
