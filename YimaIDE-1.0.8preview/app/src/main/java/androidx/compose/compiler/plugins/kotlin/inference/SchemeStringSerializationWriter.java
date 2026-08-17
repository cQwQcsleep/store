package androidx.compose.compiler.plugins.kotlin.inference;

import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\bJ\u0006\u0010\u000f\u001a\u00020\bJ\u0006\u0010\u0010\u001a\u00020\bJ\u0006\u0010\u0011\u001a\u00020\bJ\n\u0010\u0012\u001a\u00020\nH\u0096\u0080\u0004J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0002R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/SchemeStringSerializationWriter;", "", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "<init>", "(Ljava/lang/StringBuilder;)V", "writeToken", "", "token", "", "writeNumber", "number", "", "writeOpen", "writeClose", "writeResultPrefix", "writeAnyParameters", "toString", "isNormal", "", "value", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SchemeStringSerializationWriter {
    private final StringBuilder builder;

    public SchemeStringSerializationWriter(StringBuilder sb) {
        sb.getClass();
        this.builder = sb;
    }

    private final boolean isNormal(String value) {
        for (int i = 0; i < value.length(); i++) {
            char cCharAt = value.charAt(i);
            if (cCharAt != '.' && !Character.isLetter(cCharAt)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return this.builder.toString();
    }

    public final void writeAnyParameters() {
        this.builder.append('*');
    }

    public final void writeClose() {
        this.builder.append(']');
    }

    public final void writeNumber(int number) {
        StringBuilder sb = this.builder;
        if (number < 0) {
            sb.append('_');
        } else {
            sb.append(number);
        }
    }

    public final void writeOpen() {
        this.builder.append('[');
    }

    public final void writeResultPrefix() {
        this.builder.append(':');
    }

    public final void writeToken(String token) {
        token.getClass();
        boolean zIsNormal = isNormal(token);
        StringBuilder sb = this.builder;
        if (zIsNormal) {
            sb.append(token);
            return;
        }
        sb.append('\"');
        this.builder.append(StringsKt.replace$default(StringsKt.replace$default(token, "\\", "\\\\", false, 4, (Object) null), "\"", "\\\"", false, 4, (Object) null));
        this.builder.append('\"');
    }
}
