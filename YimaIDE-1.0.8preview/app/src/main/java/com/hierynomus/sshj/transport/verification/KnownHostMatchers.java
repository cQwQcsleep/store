package com.hierynomus.sshj.transport.verification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import net.schmizz.sshj.common.SSHException;
import org.jetbrains.kotlin.psi.KtCodeFragment;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class KnownHostMatchers {

    public static class AnyHostMatcher implements HostMatcher {
        private final List<HostMatcher> matchers = new ArrayList();

        public AnyHostMatcher(String str) throws SSHException {
            for (String str2 : str.split(KtCodeFragment.IMPORT_SEPARATOR)) {
                this.matchers.add(KnownHostMatchers.createMatcher(str2));
            }
        }

        @Override // com.hierynomus.sshj.transport.verification.KnownHostMatchers.HostMatcher
        public boolean match(String str) throws IOException {
            Iterator<HostMatcher> it2 = this.matchers.iterator();
            while (it2.hasNext()) {
                if (it2.next().match(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class EquiHostMatcher implements HostMatcher {
        private String host;

        public EquiHostMatcher(String str) {
            this.host = str;
        }

        @Override // com.hierynomus.sshj.transport.verification.KnownHostMatchers.HostMatcher
        public boolean match(String str) {
            return this.host.equals(str);
        }
    }

    public interface HostMatcher {
        boolean match(String str) throws IOException;
    }

    public static class NegateHostMatcher implements HostMatcher {
        private final HostMatcher matcher;

        public NegateHostMatcher(String str) throws SSHException {
            this.matcher = KnownHostMatchers.createMatcher(str.substring(1));
        }

        @Override // com.hierynomus.sshj.transport.verification.KnownHostMatchers.HostMatcher
        public boolean match(String str) throws IOException {
            return !this.matcher.match(str);
        }
    }

    public static class WildcardHostMatcher implements HostMatcher {
        private final Pattern pattern;

        public WildcardHostMatcher(String str) {
            this.pattern = Pattern.compile("^" + str.replace("[", "\\[").replace("]", "\\]").replace(".", "\\.").replace("*", ".*").replace("?", ".") + "$");
        }

        @Override // com.hierynomus.sshj.transport.verification.KnownHostMatchers.HostMatcher
        public boolean match(String str) throws IOException {
            return this.pattern.matcher(str).matches();
        }

        public String toString() {
            return "WildcardHostMatcher[" + this.pattern + ']';
        }
    }

    public static HostMatcher createMatcher(String str) throws SSHException {
        if (str.contains(KtCodeFragment.IMPORT_SEPARATOR)) {
            return new AnyHostMatcher(str);
        }
        if (str.startsWith("!")) {
            return new NegateHostMatcher(str);
        }
        if (str.startsWith("|1|")) {
            return new HashedHostMatcher(str);
        }
        return (str.contains("*") || str.contains("?")) ? new WildcardHostMatcher(str) : new EquiHostMatcher(str);
    }
}
