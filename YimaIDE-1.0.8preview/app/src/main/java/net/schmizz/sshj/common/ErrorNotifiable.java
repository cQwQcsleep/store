package net.schmizz.sshj.common;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface ErrorNotifiable {
    void notifyError(SSHException sSHException);

    public static class Util {
        public static void alertAll(SSHException sSHException, Collection<? extends ErrorNotifiable> collection) {
            Iterator<? extends ErrorNotifiable> it = collection.iterator();
            while (it.hasNext()) {
                it.next().notifyError(sSHException);
            }
        }

        public static void alertAll(SSHException sSHException, ErrorNotifiable... errorNotifiableArr) {
            for (ErrorNotifiable errorNotifiable : errorNotifiableArr) {
                errorNotifiable.notifyError(sSHException);
            }
        }
    }
}
