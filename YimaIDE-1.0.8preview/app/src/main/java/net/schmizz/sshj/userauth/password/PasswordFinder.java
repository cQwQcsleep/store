package net.schmizz.sshj.userauth.password;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface PasswordFinder {
    char[] reqPassword(Resource<?> resource);

    boolean shouldRetry(Resource<?> resource);
}
