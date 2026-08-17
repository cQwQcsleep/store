package net.schmizz.sshj.xfer.scp;

import java.util.LinkedHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class ScpCommandLine {
    private static final String SCP_COMMAND = "scp";
    private LinkedHashMap<Arg, String> arguments = new LinkedHashMap<>();
    private EscapeMode mode;
    private String path;

    public enum Arg {
        SOURCE('f'),
        SINK('t'),
        RECURSIVE('r'),
        VERBOSE('v'),
        PRESERVE_TIMES('p'),
        QUIET('q'),
        LIMIT('l');

        private final char a;

        Arg(char c) {
            this.a = c;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "-" + this.a;
        }
    }

    public enum EscapeMode {
        NoEscape,
        Space { // from class: net.schmizz.sshj.xfer.scp.ScpCommandLine.EscapeMode.1
            @Override // net.schmizz.sshj.xfer.scp.ScpCommandLine.EscapeMode
            public String escapedPath(String str) {
                return str.replace(" ", "\\ ");
            }
        },
        DoubleQuote { // from class: net.schmizz.sshj.xfer.scp.ScpCommandLine.EscapeMode.2
            @Override // net.schmizz.sshj.xfer.scp.ScpCommandLine.EscapeMode
            public String escapedPath(String str) {
                return "\"" + str.replace("\"", "\\\"") + "\"";
            }
        },
        SingleQuote { // from class: net.schmizz.sshj.xfer.scp.ScpCommandLine.EscapeMode.3
            @Override // net.schmizz.sshj.xfer.scp.ScpCommandLine.EscapeMode
            public String escapedPath(String str) {
                return "'" + str.replace("'", "\\'") + "'";
            }
        };

        public String escapedPath(String str) {
            return str;
        }
    }

    private void addArgument(Arg arg, String str, boolean z) {
        if (z) {
            this.arguments.put(arg, str);
        }
    }

    public static ScpCommandLine with(Arg arg, String str, boolean z) {
        ScpCommandLine scpCommandLine = new ScpCommandLine();
        scpCommandLine.addArgument(arg, str, z);
        return scpCommandLine;
    }

    public ScpCommandLine and(Arg arg) {
        addArgument(arg, null, true);
        return this;
    }

    public String toCommandLine() {
        StringBuilder sb = new StringBuilder(SCP_COMMAND);
        for (Arg arg : this.arguments.keySet()) {
            sb.append(" ");
            sb.append(arg);
            String str = this.arguments.get(arg);
            if (str != null && !str.trim().isEmpty()) {
                sb.append(str);
            }
        }
        sb.append(" ");
        String str2 = this.path;
        if (str2 == null || str2.trim().isEmpty()) {
            sb.append(".");
        } else {
            sb.append(this.mode.escapedPath(this.path));
        }
        return sb.toString();
    }

    public ScpCommandLine withPath(String str, EscapeMode escapeMode) {
        this.path = str;
        this.mode = escapeMode;
        return this;
    }

    public ScpCommandLine and(Arg arg, String str) {
        addArgument(arg, str, true);
        return this;
    }

    public ScpCommandLine and(Arg arg, boolean z) {
        addArgument(arg, null, z);
        return this;
    }

    public ScpCommandLine and(Arg arg, String str, boolean z) {
        addArgument(arg, str, z);
        return this;
    }

    public static ScpCommandLine with(Arg arg, String str) {
        return with(arg, str, true);
    }

    public static ScpCommandLine with(Arg arg, boolean z) {
        return with(arg, null, z);
    }

    public static ScpCommandLine with(Arg arg) {
        return with(arg, null, true);
    }
}
