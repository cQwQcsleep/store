package net.schmizz.sshj.userauth.keyprovider;

import java.io.File;
import java.io.Reader;
import net.schmizz.sshj.userauth.password.PasswordFinder;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface FileKeyProvider extends KeyProvider {
    void init(File file);

    void init(File file, PasswordFinder passwordFinder);

    void init(Reader reader);

    void init(Reader reader, PasswordFinder passwordFinder);

    void init(String str, String str2);

    void init(String str, String str2, PasswordFinder passwordFinder);
}
