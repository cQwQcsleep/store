package net.schmizz.sshj.userauth.password;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface PasswordUpdateProvider {
    char[] provideNewPassword(Resource<?> resource, String str);

    boolean shouldRetry(Resource<?> resource);
}
