package org.eclipse.tm4e.core.internal.grammar;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.tm4e.core.TMException;
import org.eclipse.tm4e.core.internal.utils.NullSafetyHelper;
import org.eclipse.tm4e.core.internal.utils.RegexSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
final class BasicScopeAttributesProvider {
    private final BasicScopeAttributes _defaultAttributes;
    private final ScopeMatcher<Integer> _embeddedLanguagesMatcher;
    private final Map<String, BasicScopeAttributes> cache = new ConcurrentHashMap();
    private static final BasicScopeAttributes _NULL_SCOPE_METADATA = new BasicScopeAttributes(0, 0);
    private static final Pattern STANDARD_TOKEN_TYPE_REGEXP = Pattern.compile("\\b(comment|string|regex|meta\\.embedded)\\b");

    public static final class ScopeMatcher<TValue> {
        private final Pattern scopesRegExp;
        private final Map<String, TValue> values;

        public ScopeMatcher(Map<String, TValue> map) {
            if (map.isEmpty()) {
                this.values = Collections.EMPTY_MAP;
                this.scopesRegExp = null;
                return;
            }
            this.values = new HashMap(map);
            this.scopesRegExp = Pattern.compile("^((" + String.join(")|(", (String[]) map.keySet().stream().map(new Function() { // from class: zp0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RegexSource.escapeRegExpCharacters((String) obj);
                }
            }).sorted(Collections.reverseOrder()).toArray(new IntFunction() { // from class: org.eclipse.tm4e.core.internal.grammar.b
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return BasicScopeAttributesProvider.ScopeMatcher.a(i);
                }
            })) + "))($|\\.)");
        }

        public static /* synthetic */ String[] a(int i) {
            return new String[i];
        }

        public TValue match(String str) {
            Pattern pattern = this.scopesRegExp;
            if (pattern == null) {
                return null;
            }
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                return this.values.get(matcher.group(1));
            }
            return null;
        }
    }

    public BasicScopeAttributesProvider(int i, Map<String, Integer> map) {
        this._defaultAttributes = new BasicScopeAttributes(i, 8);
        this._embeddedLanguagesMatcher = new ScopeMatcher<>((Map) NullSafetyHelper.defaultIfNull(map, Collections.EMPTY_MAP));
    }

    private int _scopeToLanguage(String str) {
        return ((Integer) NullSafetyHelper.defaultIfNull((int) this._embeddedLanguagesMatcher.match(str), 0)).intValue();
    }

    private static int _toStandardTokenType(String str) {
        Matcher matcher = STANDARD_TOKEN_TYPE_REGEXP.matcher(str);
        if (!matcher.find()) {
            return 8;
        }
        String strGroup = matcher.group(1);
        strGroup.getClass();
        switch (strGroup) {
            case "string":
                return 2;
            case "regex":
                return 3;
            case "comment":
                return 1;
            case "meta.embedded":
                return 0;
            default:
                throw new TMException("Unexpected match for standard token type: ".concat(strGroup));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ BasicScopeAttributes lambda$getBasicScopeAttributes$0(String str, String str2) {
        return new BasicScopeAttributes(_scopeToLanguage(str), _toStandardTokenType(str));
    }

    public BasicScopeAttributes getBasicScopeAttributes(final String str) {
        return str == null ? _NULL_SCOPE_METADATA : this.cache.computeIfAbsent(str, new Function() { // from class: org.eclipse.tm4e.core.internal.grammar.a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.lambda$getBasicScopeAttributes$0(str, (String) obj);
            }
        });
    }

    public BasicScopeAttributes getDefaultAttributes() {
        return this._defaultAttributes;
    }
}
