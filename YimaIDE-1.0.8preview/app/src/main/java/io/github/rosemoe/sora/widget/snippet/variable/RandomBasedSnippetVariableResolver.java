package io.github.rosemoe.sora.widget.snippet.variable;

import java.util.Random;
import java.util.UUID;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class RandomBasedSnippetVariableResolver implements ISnippetVariableResolver {
    @Override // io.github.rosemoe.sora.widget.snippet.variable.ISnippetVariableResolver
    public String[] getResolvableNames() {
        return new String[]{"RANDOM", "RANDOM_HEX", "UUID"};
    }

    @Override // io.github.rosemoe.sora.widget.snippet.variable.ISnippetVariableResolver
    public String resolve(String str) {
        str.getClass();
        switch (str) {
            case "RANDOM":
                return Integer.toString(new Random().nextInt());
            case "UUID":
                return UUID.randomUUID().toString();
            case "RANDOM_HEX":
                return Integer.toString(new Random().nextInt(), 16);
            default:
                w01.a("Unsupported variable name:".concat(str));
                return null;
        }
    }
}
