package org.jetbrains.kotlin.js.parser.sourcemaps;

import java.io.IOException;
import java.io.Writer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonBoolean;", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonNode;", "value", "", "<init>", "(Z)V", "getValue", "()Z", "stringValue", "", "write", "", "writer", "Ljava/io/Writer;", "Companion", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsonBoolean extends JsonNode {
    private final String stringValue;
    private final boolean value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final JsonBoolean TRUE = new JsonBoolean(true);
    private static final JsonBoolean FALSE = new JsonBoolean(false);

    private JsonBoolean(boolean z) {
        super(null);
        this.value = z;
        this.stringValue = String.valueOf(z);
    }

    public final boolean getValue() {
        return this.value;
    }

    @Override // org.jetbrains.kotlin.js.parser.sourcemaps.JsonNode
    public void write(Writer writer) throws IOException {
        writer.getClass();
        writer.append((CharSequence) this.stringValue);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonBoolean$Companion;", "", "<init>", "()V", "TRUE", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonBoolean;", "getTRUE", "()Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonBoolean;", "FALSE", "getFALSE", "of", "value", "", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final JsonBoolean getFALSE() {
            return JsonBoolean.FALSE;
        }

        public final JsonBoolean getTRUE() {
            return JsonBoolean.TRUE;
        }

        public final JsonBoolean of(boolean value) {
            return value ? getTRUE() : getFALSE();
        }

        private Companion() {
        }
    }
}
