package org.eclipse.tm4e.core.internal.theme;

import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ParsedThemeRule {
    public final String background;
    public final int fontStyle;
    public final String foreground;
    public final int index;
    public final List<String> parentScopes;
    public final String scope;

    public ParsedThemeRule(String str, List<String> list, int i, int i2, String str2, String str3) {
        this.scope = str;
        this.parentScopes = list;
        this.index = i;
        this.fontStyle = i2;
        this.foreground = str2;
        this.background = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ParsedThemeRule) {
            ParsedThemeRule parsedThemeRule = (ParsedThemeRule) obj;
            if (this.fontStyle == parsedThemeRule.fontStyle && this.index == parsedThemeRule.index && Objects.equals(this.background, parsedThemeRule.background) && Objects.equals(this.foreground, parsedThemeRule.foreground) && Objects.equals(this.parentScopes, parsedThemeRule.parentScopes) && Objects.equals(this.scope, parsedThemeRule.scope)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((this.fontStyle + 31) * 31) + this.index) * 31) + Objects.hashCode(this.background)) * 31) + Objects.hashCode(this.foreground)) * 31) + Objects.hashCode(this.parentScopes)) * 31) + Objects.hashCode(this.scope);
    }

    public String toString() {
        return "ParsedThemeRule [scope=" + this.scope + ", parentScopes=" + this.parentScopes + ", index=" + this.index + ", fontStyle=" + this.fontStyle + ", foreground=" + this.foreground + ", background=" + this.background + "]";
    }
}
