package net.schmizz.sshj.userauth.method;

import java.util.List;
import net.schmizz.sshj.userauth.password.Resource;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface ChallengeResponseProvider {
    char[] getResponse(String str, boolean z);

    List<String> getSubmethods();

    void init(Resource resource, String str, String str2);

    boolean shouldRetry();
}
