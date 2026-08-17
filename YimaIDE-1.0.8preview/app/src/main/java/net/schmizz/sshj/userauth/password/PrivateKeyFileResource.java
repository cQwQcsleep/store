package net.schmizz.sshj.userauth.password;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class PrivateKeyFileResource extends Resource<File> {
    public PrivateKeyFileResource(File file) {
        super(file);
    }

    @Override // net.schmizz.sshj.userauth.password.Resource
    public Reader getReader() throws IOException {
        return new InputStreamReader(new FileInputStream(getDetail()), "UTF-8");
    }
}
