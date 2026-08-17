package net.schmizz.sshj.userauth.password;

import java.io.Console;
import java.util.IllegalFormatException;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class ConsolePasswordFinder implements PasswordFinder {
    public static final String DEFAULT_FORMAT = "Enter passphrase for %s:";
    private final Console console;
    private final int maxTries;
    private int numTries;
    private final String promptFormat;

    public ConsolePasswordFinder(Console console, String str, int i) {
        checkFormatString(str);
        this.console = console;
        this.promptFormat = str;
        this.maxTries = i;
        this.numTries = 0;
    }

    private static void checkFormatString(String str) {
        try {
            String.format(str, HttpUrl.FRAGMENT_ENCODE_SET);
        } catch (IllegalFormatException e) {
            nrd.a("promptFormat must have no more than one %s and no other markers", e);
        }
    }

    @Override // net.schmizz.sshj.userauth.password.PasswordFinder
    public char[] reqPassword(Resource<?> resource) {
        this.numTries++;
        Console console = this.console;
        if (console == null) {
            return null;
        }
        return console.readPassword(this.promptFormat, resource.toString());
    }

    @Override // net.schmizz.sshj.userauth.password.PasswordFinder
    public boolean shouldRetry(Resource<?> resource) {
        return this.numTries < this.maxTries;
    }

    public ConsolePasswordFinder(Console console) {
        this(console, DEFAULT_FORMAT, 3);
    }

    public ConsolePasswordFinder() {
        this(System.console());
    }
}
