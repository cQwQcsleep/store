package j$.time.format;

/* loaded from: /workspace/unpacked/classes3.dex */
final class e implements g {
    private final char a;

    e(char c) {
        this.a = c;
    }

    @Override // j$.time.format.g
    public final boolean j(q qVar, StringBuilder sb) {
        sb.append(this.a);
        return true;
    }

    public final String toString() {
        char c = this.a;
        if (c == '\'') {
            return "''";
        }
        return "'" + c + "'";
    }
}
