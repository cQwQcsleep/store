package com.sun.tools.javac.util;

import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Options;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Options {
    public static final Context.Key<Options> optionsKey = new Context.Key<>();
    private static final long serialVersionUID = 0;
    private boolean initialized;
    private List<Runnable> listeners = List.nil();
    private final LinkedHashMap<String, String> values = new LinkedHashMap<>();

    public Options(Context context) {
        context.put(optionsKey, this);
    }

    public static /* synthetic */ Boolean b(Options options, String str, boolean z) {
        options.getClass();
        Optional optionalOf = Optional.of(str);
        final LinkedHashMap<String, String> linkedHashMap = options.values;
        Objects.requireNonNull(linkedHashMap);
        return (Boolean) optionalOf.map(new Function() { // from class: ara
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (String) linkedHashMap.get((String) obj);
            }
        }).map(new Function() { // from class: bra
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(Boolean.parseBoolean((String) obj));
            }
        }).orElse(Boolean.valueOf(z));
    }

    private <T> T computeIfReady(final Supplier<T> supplier, final T t, final String str) {
        if (this.initialized) {
            return supplier.get();
        }
        addListener(new Runnable() { // from class: lra
            @Override // java.lang.Runnable
            public final void run() {
                Assert.check(Objects.equals(supplier.get(), t), (Supplier<String>) new Supplier() { // from class: era
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Options.e(str);
                    }
                });
            }
        });
        return t;
    }

    public static /* synthetic */ Boolean d(Options options, Option option, String str) {
        LinkedHashMap<String, String> linkedHashMap = options.values;
        StringBuilder sb = new StringBuilder();
        sb.append(option.primaryName);
        sb.append(str);
        return Boolean.valueOf(linkedHashMap.get(sb.toString()) != null);
    }

    public static /* synthetic */ String e(String str) {
        return "ignored flag: " + str;
    }

    public static /* synthetic */ void g(Options options, Consumer consumer) {
        options.getClass();
        consumer.accept(options);
    }

    public static /* synthetic */ boolean i(Options options, Option option, String str) {
        options.getClass();
        return options.isSet(option, "-" + str);
    }

    public static Options instance(Context context) {
        Options options = (Options) context.get(optionsKey);
        return options == null ? new Options(context) : options;
    }

    public void addListener(Runnable runnable) {
        this.listeners = this.listeners.prepend(runnable);
    }

    public void clear() {
        this.values.clear();
        this.listeners = List.nil();
        this.initialized = false;
    }

    public String get(final String str) {
        return (String) computeIfReady(new Supplier() { // from class: hra
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.b.values.get(str);
            }
        }, null, Option.XD.primaryName + str);
    }

    public boolean getBoolean(final String str, final boolean z) {
        return ((Boolean) computeIfReady(new Supplier() { // from class: kra
            @Override // java.util.function.Supplier
            public final Object get() {
                return Options.b(this.b, str, z);
            }
        }, Boolean.valueOf(z), Option.XD.primaryName + str)).booleanValue();
    }

    public EnumSet<Lint.LintCategory> getLintCategoriesOf(Option option, Supplier<? extends EnumSet<Lint.LintCategory>> supplier) {
        Option lintCustom = option.getLintCustom();
        EnumSet<Lint.LintCategory> enumSetAllOf = (isSet(option) || isSet(lintCustom, "all")) ? EnumSet.allOf(Lint.LintCategory.class) : isSet(lintCustom, Option.LINT_CUSTOM_NONE) ? EnumSet.noneOf(Lint.LintCategory.class) : supplier.get();
        for (Lint.LintCategory lintCategory : Lint.LintCategory.values()) {
            if (isExplicitlyEnabled(option, lintCategory)) {
                enumSetAllOf.add(lintCategory);
            } else if (isExplicitlyDisabled(option, lintCategory)) {
                enumSetAllOf.remove(lintCategory);
            }
        }
        return enumSetAllOf;
    }

    public void initialize() {
        this.initialized = true;
    }

    public boolean isDisabled(Option option, Lint.LintCategory lintCategory) {
        return isExplicitlyDisabled(option, lintCategory) || isSet(option.getLintCustom(), Option.LINT_CUSTOM_NONE);
    }

    public boolean isEnabled(Option option, Lint.LintCategory lintCategory) {
        Option lintCustom = option.getLintCustom();
        return isExplicitlyEnabled(option, lintCategory) || isSet(lintCustom) || isSet(lintCustom, "all");
    }

    public boolean isExplicitlyDisabled(Option option, Lint.LintCategory lintCategory) {
        final Option lintCustom = option.getLintCustom();
        return lintCategory.optionList.stream().anyMatch(new Predicate() { // from class: fra
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Options.i(this.b, lintCustom, (String) obj);
            }
        });
    }

    public boolean isExplicitlyEnabled(Option option, Lint.LintCategory lintCategory) {
        final Option lintCustom = option.getLintCustom();
        return lintCategory.optionList.stream().anyMatch(new Predicate() { // from class: gra
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.isSet(lintCustom, (String) obj);
            }
        });
    }

    public boolean isSet(final String str) {
        return ((Boolean) computeIfReady(new Supplier() { // from class: zqa
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(this.b.values.get(str) != null);
            }
        }, Boolean.FALSE, Option.XD.primaryName + str)).booleanValue();
    }

    public boolean isUnset(String str) {
        return !isSet(str);
    }

    public Set<String> keySet() {
        return this.values.keySet();
    }

    public void notifyListeners() {
        this.initialized = true;
        Iterator<Runnable> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        this.listeners = List.nil();
    }

    public void put(Option option, String str) {
        this.values.put(option.primaryName, str);
        this.initialized = true;
    }

    public void putAll(Options options) {
        this.values.putAll(options.values);
        this.initialized = true;
    }

    public void remove(String str) {
        this.values.remove(str);
        this.initialized = true;
    }

    public int size() {
        return this.values.size();
    }

    public void whenReady(final Consumer<? super Options> consumer) {
        if (this.initialized) {
            consumer.accept(this);
        } else {
            addListener(new Runnable() { // from class: ira
                @Override // java.lang.Runnable
                public final void run() {
                    Options.g(this.b, consumer);
                }
            });
        }
    }

    public boolean isUnset(Option option) {
        return !isSet(option);
    }

    public boolean isUnset(Option option, String str) {
        return !isSet(option, str);
    }

    public void put(String str, String str2) {
        this.values.put(str, str2);
        this.initialized = true;
    }

    public String get(final Option option) {
        return (String) computeIfReady(new Supplier() { // from class: dra
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.b.values.get(option.primaryName);
            }
        }, null, option.primaryName);
    }

    public boolean isSet(final Option option) {
        return ((Boolean) computeIfReady(new Supplier() { // from class: jra
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(this.b.values.get(option.primaryName) != null);
            }
        }, Boolean.FALSE, option.primaryName)).booleanValue();
    }

    public boolean isSet(final Option option, final String str) {
        return ((Boolean) computeIfReady(new Supplier() { // from class: cra
            @Override // java.util.function.Supplier
            public final Object get() {
                return Options.d(this.b, option, str);
            }
        }, Boolean.FALSE, option.primaryName + str)).booleanValue();
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }
}
