package org.eclipse.tm4e.languageconfiguration.internal.model;

import java.util.Objects;
import java.util.function.Consumer;
import org.eclipse.tm4e.core.internal.utils.StringUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class CharacterPair {
    public final String close;
    public final String open;

    public CharacterPair(String str, String str2) {
        this.open = str;
        this.close = str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$toString$0(StringBuilder sb) {
        sb.append("open=");
        sb.append(this.open);
        sb.append(", ");
        sb.append("close=");
        sb.append(this.close);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            CharacterPair characterPair = (CharacterPair) obj;
            if (Objects.equals(this.open, characterPair.open) && Objects.equals(this.close, characterPair.close)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.open, this.close);
    }

    public String toString() {
        return StringUtils.toString(this, new Consumer() { // from class: yg1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.lambda$toString$0((StringBuilder) obj);
            }
        });
    }
}
