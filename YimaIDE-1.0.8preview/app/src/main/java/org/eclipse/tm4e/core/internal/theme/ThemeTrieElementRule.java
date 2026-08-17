package org.eclipse.tm4e.core.internal.theme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ThemeTrieElementRule {
    public int background;
    public int fontStyle;
    public int foreground;
    public final List<String> parentScopes;
    public int scopeDepth;

    public ThemeTrieElementRule(int i, List<String> list, int i2, int i3, int i4) {
        this.scopeDepth = i;
        this.parentScopes = list;
        this.fontStyle = i2;
        this.foreground = i3;
        this.background = i4;
    }

    public static List<ThemeTrieElementRule> cloneArr(List<ThemeTrieElementRule> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<ThemeTrieElementRule> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().clone());
        }
        return arrayList;
    }

    public void acceptOverwrite(int i, int i2, int i3, int i4) {
        if (this.scopeDepth <= i) {
            this.scopeDepth = i;
        }
        if (i2 != -1) {
            this.fontStyle = i2;
        }
        if (i3 != 0) {
            this.foreground = i3;
        }
        if (i4 != 0) {
            this.background = i4;
        }
    }

    public ThemeTrieElementRule clone() {
        return new ThemeTrieElementRule(this.scopeDepth, this.parentScopes, this.fontStyle, this.foreground, this.background);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ThemeTrieElementRule) {
            ThemeTrieElementRule themeTrieElementRule = (ThemeTrieElementRule) obj;
            if (this.scopeDepth == themeTrieElementRule.scopeDepth && this.background == themeTrieElementRule.background && this.fontStyle == themeTrieElementRule.fontStyle && this.foreground == themeTrieElementRule.foreground && Objects.equals(this.parentScopes, themeTrieElementRule.parentScopes)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((((this.background + 31) * 31) + this.fontStyle) * 31) + this.foreground) * 31) + Objects.hashCode(this.parentScopes)) * 31) + this.scopeDepth;
    }
}
