package net.schmizz.sshj.userauth.password;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class PrivateKeyStringResource extends Resource<String> {
    public PrivateKeyStringResource(String str) {
        super(str);
    }

    @Override // net.schmizz.sshj.userauth.password.Resource
    public Reader getReader() throws IOException {
        return new StringReader(getDetail());
    }

    @Override // net.schmizz.sshj.userauth.password.Resource
    public String toString() {
        return "[" + getClass().getSimpleName() + "]";
    }
}
