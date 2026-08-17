package com.sun.source.doctree;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DocTree {
    <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d);

    Kind getKind();

    public enum Kind {
        ATTRIBUTE,
        AUTHOR("author"),
        CODE("code"),
        COMMENT,
        DEPRECATED("deprecated"),
        DOC_COMMENT,
        DOC_ROOT("docRoot"),
        DOC_TYPE,
        END_ELEMENT,
        ENTITY,
        ERRONEOUS,
        ESCAPE,
        EXCEPTION("exception"),
        HIDDEN("hidden"),
        IDENTIFIER,
        INDEX("index"),
        INHERIT_DOC("inheritDoc"),
        LINK("link"),
        LINK_PLAIN("linkplain"),
        LITERAL("literal"),
        MARKDOWN,
        PARAM(Constants.ELEMNAME_PARAMVARIABLE_STRING),
        PROVIDES(PsiKeyword.PROVIDES),
        REFERENCE,
        RETURN(PsiKeyword.RETURN),
        SEE("see"),
        SERIAL("serial"),
        SERIAL_DATA("serialData"),
        SERIAL_FIELD("serialField"),
        SINCE("since"),
        SNIPPET("snippet"),
        SPEC("spec"),
        START_ELEMENT,
        SYSTEM_PROPERTY("systemProperty"),
        SUMMARY("summary"),
        TEXT,
        THROWS(PsiKeyword.THROWS),
        UNKNOWN_BLOCK_TAG,
        UNKNOWN_INLINE_TAG,
        USES(PsiKeyword.USES),
        VALUE("value"),
        VERSION("version"),
        OTHER;

        public final String tagName;

        Kind() {
            this.tagName = null;
        }

        Kind(String str) {
            this.tagName = str;
        }
    }
}
