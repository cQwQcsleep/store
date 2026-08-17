package org.jetbrains.kotlin.cli.common.messages;

import com.intellij.openapi.util.text.StringUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class XmlMessageRenderer implements MessageRenderer {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "message";
        } else if (i != 2) {
            objArr[0] = "severity";
        } else {
            objArr[0] = "usage";
        }
        objArr[1] = "org/jetbrains/kotlin/cli/common/messages/XmlMessageRenderer";
        if (i != 2) {
            objArr[2] = "render";
        } else {
            objArr[2] = "renderUsage";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    private static String e(String str) {
        return StringUtil.escapeXmlEntities(str);
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
    public String getName() {
        return "XML";
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
    public String render(CompilerMessageSeverity compilerMessageSeverity, String str, CompilerMessageSourceLocation compilerMessageSourceLocation) {
        if (compilerMessageSeverity == null) {
            $$$reportNull$$$0(0);
        }
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        StringBuilder sb = new StringBuilder("<");
        String presentableName = compilerMessageSeverity.getPresentableName();
        sb.append(presentableName);
        if (compilerMessageSourceLocation != null) {
            sb.append(" path=\"");
            sb.append(e(compilerMessageSourceLocation.getPath()));
            sb.append("\" line=\"");
            sb.append(compilerMessageSourceLocation.getLine());
            sb.append("\" column=\"");
            sb.append(compilerMessageSourceLocation.getColumn());
            sb.append("\"");
        }
        sb.append(">");
        sb.append(e(str));
        sb.append("</");
        sb.append(presentableName);
        sb.append(">\n");
        return sb.toString();
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
    public String renderConclusion() {
        return "</MESSAGES>";
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
    public String renderPreamble() {
        return "<MESSAGES>";
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
    public String renderUsage(String str) {
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        return render(CompilerMessageSeverity.STRONG_WARNING, str, null);
    }
}
