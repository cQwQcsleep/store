package org.eclipse.tm4e.languageconfiguration.internal.model;

import java.util.function.Consumer;
import org.eclipse.tm4e.core.internal.utils.StringUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class IndentForEnter {
    public final String afterEnter;
    public final String beforeEnter;

    public IndentForEnter(String str, String str2) {
        this.beforeEnter = str;
        this.afterEnter = str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$toString$0(StringBuilder sb) {
        sb.append("beforeEnter=");
        sb.append(this.beforeEnter);
        sb.append(", ");
        sb.append("afterEnter=");
        sb.append(this.afterEnter);
    }

    public String toString() {
        return StringUtils.toString(this, new Consumer() { // from class: wm6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.lambda$toString$0((StringBuilder) obj);
            }
        });
    }
}
