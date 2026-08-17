package com.reandroid.xml;

import com.intellij.psi.PsiKeyword;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLDebugStringBuilder {
    private static final int MAX_INDENT = 10;
    private static final int STRING_LIMIT = 300;

    private static String addIndent(StringBuilder sb) {
        int length = sb.length();
        if (length == 0) {
            return "";
        }
        String string = length > 1 ? sb.toString() : "";
        if (length < 10) {
            sb.append(' ');
        }
        return string;
    }

    private static void appendDocDeclaration(StringBuilder sb, XMLDocType xMLDocType) {
        if (limitReached(sb)) {
            return;
        }
        sb.append("<!DOCTYPE ");
        sb.append(xMLDocType.getName());
        sb.append(">\n");
    }

    private static void appendDocument(StringBuilder sb, StringBuilder sb2, XMLDocument xMLDocument) {
        if (limitReached(sb2)) {
            return;
        }
        if (sb.length() > 1) {
            sb2.append(addIndent(sb));
        }
        XMLDocDeclaration declaration = xMLDocument.getDeclaration();
        if (declaration.isValid()) {
            sb2.append(declaration.toString());
        }
        Iterator<XMLNode> it = xMLDocument.iterator();
        boolean z = false;
        while (it.hasNext() && !limitReached(sb2)) {
            appendNode(sb, sb2, it.next());
            z = true;
        }
        String strSubIndent = subIndent(sb);
        if (z) {
            sb2.append(strSubIndent);
        }
    }

    private static void appendElement(StringBuilder sb, StringBuilder sb2, XMLElement xMLElement) {
        if (limitReached(sb2)) {
            return;
        }
        sb2.append(addIndent(sb));
        sb2.append('<');
        String name = xMLElement.getName(true);
        if (name == null) {
            name = PsiKeyword.NULL;
        }
        sb2.append(name);
        Iterator<? extends XMLAttribute> attributes = xMLElement.getAttributes();
        while (attributes.hasNext() && !limitReached(sb2)) {
            sb2.append(' ');
            sb2.append(attributes.next());
        }
        boolean z = false;
        boolean z2 = false;
        for (XMLNode xMLNode : xMLElement) {
            if (!z) {
                sb2.append('>');
                z = true;
                z2 = true;
            }
            if (z2) {
                z2 = xMLNode instanceof XMLText;
            }
            if (limitReached(sb2)) {
                break;
            } else {
                appendNode(sb, sb2, xMLNode);
            }
        }
        String strSubIndent = subIndent(sb);
        if (xMLElement.isVoidHtml()) {
            sb2.append('>');
            return;
        }
        if (!z) {
            sb2.append("/>");
            return;
        }
        if (!z2) {
            sb2.append(strSubIndent);
        }
        sb2.append("</");
        sb2.append(name);
        sb2.append('>');
    }

    private static void appendNode(StringBuilder sb, StringBuilder sb2, XMLNode xMLNode) {
        if (limitReached(sb2)) {
            return;
        }
        if (xMLNode instanceof XMLDocument) {
            appendDocument(sb, sb2, (XMLDocument) xMLNode);
            return;
        }
        if (xMLNode instanceof XMLElement) {
            appendElement(sb, sb2, (XMLElement) xMLNode);
            return;
        }
        if (xMLNode instanceof XMLText) {
            appendText(sb2, (XMLText) xMLNode);
        } else if (xMLNode instanceof XMLDocType) {
            appendDocDeclaration(sb2, (XMLDocType) xMLNode);
        } else if (xMLNode != null) {
            sb2.append(xMLNode);
        }
    }

    private static void appendText(StringBuilder sb, XMLText xMLText) {
        if (limitReached(sb)) {
            return;
        }
        String text = xMLText.getText();
        if (text == null) {
            text = PsiKeyword.NULL;
        }
        int length = 300 - sb.length();
        if (length <= 0) {
            return;
        }
        if (length < text.length()) {
            text = text.substring(0, length).concat(" ...");
        }
        sb.append(text);
    }

    public static String build(XMLNode xMLNode) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        StringBuilder sb2 = new StringBuilder();
        appendNode(sb, sb2, xMLNode);
        return sb2.toString();
    }

    private static boolean limitReached(StringBuilder sb) {
        return sb.length() >= 300;
    }

    private static String subIndent(StringBuilder sb) {
        int length = sb.length();
        if (length == 0) {
            return "";
        }
        if (length > 1) {
            sb.deleteCharAt(length - 1);
        }
        return sb.toString();
    }
}
