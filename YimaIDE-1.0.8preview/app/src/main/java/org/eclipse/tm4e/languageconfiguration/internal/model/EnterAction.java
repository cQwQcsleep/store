package org.eclipse.tm4e.languageconfiguration.internal.model;

import java.util.function.Consumer;
import org.eclipse.tm4e.core.internal.utils.StringUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class EnterAction {
    public String appendText;
    public final IndentAction indentAction;
    public final Integer removeText;

    public enum IndentAction {
        None,
        Indent,
        IndentOutdent,
        Outdent;

        public static IndentAction get(String str) {
            if (str == null) {
                return None;
            }
            switch (str) {
                case "indentOutdent":
                    return IndentOutdent;
                case "indent":
                    return Indent;
                case "outdent":
                    return Outdent;
                case "none":
                    return None;
                default:
                    return None;
            }
        }
    }

    public EnterAction(IndentAction indentAction, String str, Integer num) {
        this.indentAction = indentAction;
        this.appendText = str;
        this.removeText = num;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$toString$0(StringBuilder sb) {
        sb.append("indentAction=");
        sb.append(this.indentAction);
        sb.append(", ");
        sb.append("appendText=");
        sb.append(this.appendText);
        sb.append(", ");
        sb.append("removeText=");
        sb.append(this.removeText);
    }

    public String toString() {
        return StringUtils.toString(this, new Consumer() { // from class: pb4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.lambda$toString$0((StringBuilder) obj);
            }
        });
    }

    public EnterAction(IndentAction indentAction) {
        this(indentAction, null, null);
    }
}
