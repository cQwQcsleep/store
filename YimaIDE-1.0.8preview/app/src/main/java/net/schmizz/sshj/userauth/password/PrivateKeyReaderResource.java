package net.schmizz.sshj.userauth.password;

import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class PrivateKeyReaderResource extends Resource<Reader> {
    public PrivateKeyReaderResource(Reader reader) {
        super(reader);
    }

    @Override // net.schmizz.sshj.userauth.password.Resource
    public Reader getReader() throws IOException {
        return getDetail();
    }
}
