package org.eclipse.tm4e.core.internal.grammar.dependencies;

import org.eclipse.tm4e.core.internal.grammar.raw.RawRepository;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class IncludeReference {
    public static final IncludeReference BASE;
    public static final IncludeReference SELF;
    public final Kind kind;
    public final String ruleName;
    public final String scopeName;

    public enum Kind {
        Base,
        Self,
        RelativeReference,
        TopLevelReference,
        TopLevelRepositoryReference
    }

    static {
        Kind kind = Kind.Base;
        BASE = new IncludeReference(kind, RawRepository.DOLLAR_BASE, "");
        SELF = new IncludeReference(kind, RawRepository.DOLLAR_SELF, "");
    }

    private IncludeReference(Kind kind, String str, String str2) {
        this.kind = kind;
        this.scopeName = str;
        this.ruleName = str2;
    }

    public static IncludeReference parseInclude(String str) {
        str.getClass();
        if (str.equals(RawRepository.DOLLAR_BASE)) {
            return BASE;
        }
        if (str.equals(RawRepository.DOLLAR_SELF)) {
            return SELF;
        }
        int iIndexOf = str.indexOf("#");
        if (iIndexOf == -1) {
            return new IncludeReference(Kind.TopLevelReference, str, "");
        }
        if (iIndexOf == 0) {
            return new IncludeReference(Kind.RelativeReference, "", str.substring(1));
        }
        return new IncludeReference(Kind.TopLevelRepositoryReference, str.substring(0, iIndexOf), str.substring(iIndexOf + 1));
    }
}
