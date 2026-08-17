package net.schmizz.sshj.userauth.password;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class AccountResource extends Resource<String> {
    public AccountResource(String str, String str2) {
        super(str + "@" + str2);
    }

    @Override // net.schmizz.sshj.userauth.password.Resource
    public Reader getReader() throws IOException {
        return new StringReader(getDetail());
    }
}
