package androidx.compose.compiler.plugins.kotlin.inference;

import androidx.collection.ScatterMapKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\u0003J\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/SchemeStringSerializationReader;", "", "value", "", "<init>", "(Ljava/lang/String;)V", "current", "", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/ItemKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/ItemKind;", "end", "", "number", "token", "expect", "ch", "", "getCh", "()C", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class SchemeStringSerializationReader {
    private int current;
    private final String value;

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ItemKind.values().length];
            try {
                iArr[ItemKind.Open.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ItemKind.Close.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ItemKind.ResultPrefix.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ItemKind.AnyParameters.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ItemKind.Token.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ItemKind.Number.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ItemKind.End.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SchemeStringSerializationReader(String str) {
        str.getClass();
        this.value = str;
    }

    private final char getCh() {
        if (this.current < this.value.length()) {
            return this.value.charAt(this.current);
        }
        return (char) 0;
    }

    public final void end() {
        if (getKind() == ItemKind.End) {
            return;
        }
        SchemeKt.schemeParseError();
        wq6.a();
    }

    public final void expect(ItemKind kind) {
        kind.getClass();
        if (kind != ItemKind.Invalid) {
            if (getKind() != kind) {
                SchemeKt.schemeParseError();
                wq6.a();
                return;
            }
            switch (WhenMappings.$EnumSwitchMapping$0[getKind().ordinal()]) {
                case 1:
                    expect('[');
                    break;
                case 2:
                    expect(']');
                    break;
                case 3:
                    expect(':');
                    break;
                case 4:
                    expect('*');
                    break;
                case 5:
                    token();
                    break;
                case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                    number();
                    break;
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                    end();
                    break;
                default:
                    SchemeKt.schemeParseError();
                    wq6.a();
                    break;
            }
        }
    }

    public final ItemKind getKind() {
        char ch = getCh();
        if (ch == '\"') {
            return ItemKind.Token;
        }
        if (ch == '*') {
            return ItemKind.AnyParameters;
        }
        if (ch == ':') {
            return ItemKind.ResultPrefix;
        }
        if (ch == '[') {
            return ItemKind.Open;
        }
        if (ch == ']') {
            return ItemKind.Close;
        }
        if (ch == '_') {
            return ItemKind.Number;
        }
        if (Character.isLetter(ch)) {
            return ItemKind.Token;
        }
        if (Character.isDigit(ch)) {
            return ItemKind.Number;
        }
        return ch == 0 ? ItemKind.End : ItemKind.Invalid;
    }

    public final int number() throws SchemeParseError {
        char ch = getCh();
        int i = this.current;
        if (ch == '_') {
            this.current = i + 1;
            return -1;
        }
        while (Character.isDigit(getCh())) {
            this.current++;
        }
        try {
            return Integer.parseUnsignedInt(this.value.substring(i, this.current), 10);
        } catch (NumberFormatException unused) {
            SchemeKt.schemeParseError();
            wq6.a();
            return 0;
        }
    }

    public final String token() throws SchemeParseError {
        int i;
        int i2 = this.current;
        String str = "";
        if (getCh() == '\"') {
            i2 = this.current + 1;
            this.current = i2;
            while (getCh() != '\"' && getCh() != 0) {
                if (getCh() == '\\') {
                    str = str + ((Object) this.value.subSequence(i2, this.current));
                    i2 = this.current + 1;
                    this.current = i2;
                    if (getCh() != '\"' && getCh() != '\\') {
                        SchemeKt.schemeParseError();
                        wq6.a();
                        return null;
                    }
                    this.current++;
                } else {
                    this.current++;
                }
            }
            i = this.current;
            this.current = i + 1;
        } else {
            while (true) {
                char ch = getCh();
                if (ch != '.' && !Character.isLetter(ch)) {
                    break;
                }
                this.current++;
            }
            i = this.current;
        }
        return str + ((Object) this.value.subSequence(i2, i));
    }

    private final void expect(char ch) throws SchemeParseError {
        if (this.current < this.value.length() && this.value.charAt(this.current) == ch) {
            this.current++;
        } else {
            SchemeKt.schemeParseError();
            wq6.a();
        }
    }
}
