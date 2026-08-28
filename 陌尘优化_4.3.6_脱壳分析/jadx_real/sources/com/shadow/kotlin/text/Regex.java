package com.shadow.kotlin.text;

import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.coroutines.intrinsics.CoroutineSingletons;
import com.shadow.kotlin.coroutines.jvm.internal.DebugMetadata;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.sequences.Sequence;
import com.shadow.kotlin.sequences.SequencesKt;
import com.shadow.kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.sequences.SequenceScope;
import kotlin.text.Regex;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class Regex implements Serializable {
    public static final Companion Companion = new Companion();
    private Set<? extends kotlin.text.RegexOption> _options;
    private final Pattern nativePattern;

    public final class Companion {
    }

    public static final class Serialized implements Serializable {
        public static final Companion Companion = new Companion();
        private static final long serialVersionUID = 0;
        private final int flags;
        private final String pattern;

        public final class Companion {
        }

        public Serialized(String str, int i) {
            CloseableKt.checkNotNullParameter(str, "pattern");
            this.pattern = str;
            this.flags = i;
        }

        private final Object readResolve() {
            Pattern patternCompile = Pattern.compile(this.pattern, this.flags);
            CloseableKt.checkNotNullExpressionValue(patternCompile, "compile(...)");
            return new Regex(patternCompile);
        }

        public final int getFlags() {
            return this.flags;
        }

        public final String getPattern() {
            return this.pattern;
        }
    }

    /* renamed from: com.shadow.kotlin.text.Regex$findAll$2, reason: invalid class name */
    public /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<kotlin.text.MatchResult, kotlin.text.MatchResult> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1, MatchResult.class, "next", "next()Lkotlin/text/MatchResult;", 0);
        }

        public final MatchResult invoke(MatchResult matchResult) {
            CloseableKt.checkNotNullParameter(matchResult, "p0");
            return ((MatcherMatchResult) matchResult).next();
        }
    }

    @DebugMetadata(c = "kotlin.text.Regex$splitToSequence$1", f = "Regex.kt", l = {275, 283, 287}, m = "invokeSuspend")
    /* renamed from: com.shadow.kotlin.text.Regex$splitToSequence$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00541 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
        final /* synthetic */ CharSequence $input;
        final /* synthetic */ int $limit;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ Regex this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00541(kotlin.text.Regex regex, CharSequence charSequence, int i, Continuation<? super Regex.splitToSequence.1> continuation) {
            super(2, continuation);
            this.this$0 = regex;
            this.$input = charSequence;
            this.$limit = i;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00541 c00541 = new C00541(this.this$0, this.$input, this.$limit, continuation);
            c00541.L$0 = obj;
            return c00541;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0070 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x007b  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x009c A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x009d A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x006e -> B:21:0x0071). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) throws Throwable {
            com.shadow.kotlin.sequences.SequenceScope sequenceScope;
            Matcher matcher;
            int i;
            String string;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i2 = this.label;
            com.shadow.kotlin.Unit unit = com.shadow.kotlin.Unit.INSTANCE;
            if (i2 == 0) {
                LazyKt.throwOnFailure(obj);
                com.shadow.kotlin.sequences.SequenceScope sequenceScope2 = (com.shadow.kotlin.sequences.SequenceScope) this.L$0;
                Matcher matcher2 = this.this$0.nativePattern.matcher(this.$input);
                if (this.$limit == 1 || !matcher2.find()) {
                    String string2 = this.$input.toString();
                    this.label = 1;
                    if (sequenceScope2.yield(string2, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    int iEnd = 0;
                    sequenceScope = sequenceScope2;
                    matcher = matcher2;
                    i = 0;
                    string = this.$input.subSequence(iEnd, matcher.start()).toString();
                    this.L$0 = sequenceScope;
                    this.L$1 = matcher;
                    this.I$0 = i;
                    this.label = 2;
                    if (sequenceScope.yield(string, this) == coroutineSingletons) {
                    }
                    iEnd = matcher.end();
                    i++;
                    if (i != this.$limit - 1) {
                    }
                    CharSequence charSequence = this.$input;
                    String string3 = charSequence.subSequence(iEnd, charSequence.length()).toString();
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 3;
                    if (sequenceScope.yield(string3, this) == coroutineSingletons) {
                    }
                }
            } else if (i2 == 1) {
                LazyKt.throwOnFailure(obj);
            } else {
                if (i2 != 2) {
                    if (i2 == 3) {
                        LazyKt.throwOnFailure(obj);
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                Matcher matcher3 = (Matcher) this.L$1;
                sequenceScope = (com.shadow.kotlin.sequences.SequenceScope) this.L$0;
                LazyKt.throwOnFailure(obj);
                matcher = matcher3;
                iEnd = matcher.end();
                i++;
                if (i != this.$limit - 1 || !matcher.find()) {
                    CharSequence charSequence2 = this.$input;
                    String string32 = charSequence2.subSequence(iEnd, charSequence2.length()).toString();
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 3;
                    return sequenceScope.yield(string32, this) == coroutineSingletons ? coroutineSingletons : unit;
                }
                string = this.$input.subSequence(iEnd, matcher.start()).toString();
                this.L$0 = sequenceScope;
                this.L$1 = matcher;
                this.I$0 = i;
                this.label = 2;
                if (sequenceScope.yield(string, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
                iEnd = matcher.end();
                i++;
                if (i != this.$limit - 1) {
                }
                CharSequence charSequence22 = this.$input;
                String string322 = charSequence22.subSequence(iEnd, charSequence22.length()).toString();
                this.L$0 = null;
                this.L$1 = null;
                this.label = 3;
                if (sequenceScope.yield(string322, this) == coroutineSingletons) {
                }
            }
            return unit;
        }

        public final Object invoke(SequenceScope<? super String> sequenceScope, Continuation<? super Unit> continuation) {
            return create(sequenceScope, continuation).invokeSuspend(com.shadow.kotlin.Unit.INSTANCE);
        }
    }

    public Regex(Pattern pattern) {
        CloseableKt.checkNotNullParameter(pattern, "nativePattern");
        this.nativePattern = pattern;
    }

    public static /* synthetic */ MatchResult find$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.find(charSequence, i);
    }

    public static /* synthetic */ Sequence findAll$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.findAll(charSequence, i);
    }

    public static /* synthetic */ List split$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.split(charSequence, i);
    }

    public static /* synthetic */ Sequence splitToSequence$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.splitToSequence(charSequence, i);
    }

    private final Object writeReplace() {
        String strPattern = this.nativePattern.pattern();
        CloseableKt.checkNotNullExpressionValue(strPattern, "pattern(...)");
        return new Serialized(strPattern, this.nativePattern.flags());
    }

    public final boolean containsMatchIn(CharSequence charSequence) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        return this.nativePattern.matcher(charSequence).find();
    }

    public final MatchResult find(CharSequence charSequence, int i) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        CloseableKt.checkNotNullExpressionValue(matcher, "matcher(...)");
        if (matcher.find(i)) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    public final kotlin.sequences.Sequence<kotlin.text.MatchResult> findAll(final CharSequence charSequence, final int i) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        if (i >= 0 && i <= charSequence.length()) {
            return SequencesKt.a(new Function0<kotlin.text.MatchResult>() { // from class: com.shadow.kotlin.text.Regex.findAll.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* renamed from: invoke, reason: merged with bridge method [inline-methods] */
                public final MatchResult m13invoke() {
                    return Regex.this.find(charSequence, i);
                }
            }, AnonymousClass2.INSTANCE);
        }
        throw new IndexOutOfBoundsException("Start index out of bounds: " + i + ", input length: " + charSequence.length());
    }

    public final Set<kotlin.text.RegexOption> getOptions() {
        Set<? extends kotlin.text.RegexOption> set = this._options;
        if (set != null) {
            return set;
        }
        final int iFlags = this.nativePattern.flags();
        EnumSet enumSetAllOf = EnumSet.allOf(RegexOption.class);
        CloseableKt.checkNotNull(enumSetAllOf);
        Function1<kotlin.text.RegexOption, Boolean> function1 = new Function1<kotlin.text.RegexOption, Boolean>() { // from class: com.shadow.kotlin.text.Regex$special$$inlined$fromInt$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Boolean invoke(kotlin.text.RegexOption regexOption) {
                FlagEnum flagEnum = (FlagEnum) regexOption;
                return Boolean.valueOf((iFlags & flagEnum.getMask()) == flagEnum.getValue());
            }
        };
        Iterator it = enumSetAllOf.iterator();
        while (it.hasNext()) {
            if (!((Boolean) function1.invoke(it.next())).booleanValue()) {
                it.remove();
            }
        }
        Set<? extends kotlin.text.RegexOption> setUnmodifiableSet = Collections.unmodifiableSet(enumSetAllOf);
        CloseableKt.checkNotNullExpressionValue(setUnmodifiableSet, "unmodifiableSet(...)");
        this._options = setUnmodifiableSet;
        return setUnmodifiableSet;
    }

    public final String getPattern() {
        String strPattern = this.nativePattern.pattern();
        CloseableKt.checkNotNullExpressionValue(strPattern, "pattern(...)");
        return strPattern;
    }

    public final MatchResult matchAt(CharSequence charSequence, int i) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        Matcher matcherRegion = this.nativePattern.matcher(charSequence).useAnchoringBounds(false).useTransparentBounds(true).region(i, charSequence.length());
        if (matcherRegion.lookingAt()) {
            return new MatcherMatchResult(matcherRegion, charSequence);
        }
        return null;
    }

    public final MatchResult matchEntire(CharSequence charSequence) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        CloseableKt.checkNotNullExpressionValue(matcher, "matcher(...)");
        if (matcher.matches()) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    public final boolean matches(CharSequence charSequence) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        return this.nativePattern.matcher(charSequence).matches();
    }

    public final boolean matchesAt(CharSequence charSequence, int i) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        return this.nativePattern.matcher(charSequence).useAnchoringBounds(false).useTransparentBounds(true).region(i, charSequence.length()).lookingAt();
    }

    public final String replace(CharSequence charSequence, String str) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        CloseableKt.checkNotNullParameter(str, "replacement");
        String strReplaceAll = this.nativePattern.matcher(charSequence).replaceAll(str);
        CloseableKt.checkNotNullExpressionValue(strReplaceAll, "replaceAll(...)");
        return strReplaceAll;
    }

    public final String replaceFirst(CharSequence charSequence, String str) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        CloseableKt.checkNotNullParameter(str, "replacement");
        String strReplaceFirst = this.nativePattern.matcher(charSequence).replaceFirst(str);
        CloseableKt.checkNotNullExpressionValue(strReplaceFirst, "replaceFirst(...)");
        return strReplaceFirst;
    }

    public final List<String> split(CharSequence charSequence, int i) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        StringsKt.requireNonNegativeLimit(i);
        Matcher matcher = this.nativePattern.matcher(charSequence);
        if (i == 1 || !matcher.find()) {
            return CollectionsKt.d(charSequence.toString());
        }
        int i2 = 10;
        if (i > 0 && i <= 10) {
            i2 = i;
        }
        ArrayList arrayList = new ArrayList(i2);
        int i3 = i - 1;
        int iEnd = 0;
        do {
            arrayList.add(charSequence.subSequence(iEnd, matcher.start()).toString());
            iEnd = matcher.end();
            if (i3 >= 0 && arrayList.size() == i3) {
                break;
            }
        } while (matcher.find());
        arrayList.add(charSequence.subSequence(iEnd, charSequence.length()).toString());
        return arrayList;
    }

    public final kotlin.sequences.Sequence<String> splitToSequence(CharSequence charSequence, int i) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        StringsKt.requireNonNegativeLimit(i);
        return new SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1(new C00541(this, charSequence, i, null));
    }

    public final Pattern toPattern() {
        return this.nativePattern;
    }

    public String toString() {
        String string = this.nativePattern.toString();
        CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final String replace(CharSequence charSequence, Function1<? super kotlin.text.MatchResult, ? extends CharSequence> function1) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        CloseableKt.checkNotNullParameter(function1, "transform");
        int last = 0;
        MatchResult matchResultFind$default = find$default(this, charSequence, 0, 2, null);
        if (matchResultFind$default == null) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        do {
            MatcherMatchResult matcherMatchResult = (MatcherMatchResult) matchResultFind$default;
            sb.append(charSequence, last, matcherMatchResult.getRange().getFirst());
            sb.append((CharSequence) function1.invoke(matchResultFind$default));
            last = matcherMatchResult.getRange().getLast() + 1;
            matchResultFind$default = matcherMatchResult.next();
            if (last >= length) {
                break;
            }
        } while (matchResultFind$default != null);
        if (last < length) {
            sb.append(charSequence, last, length);
        }
        String string = sb.toString();
        CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str) {
        CloseableKt.checkNotNullParameter(str, "pattern");
        Pattern patternCompile = Pattern.compile(str);
        CloseableKt.checkNotNullExpressionValue(patternCompile, "compile(...)");
        this(patternCompile);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str, RegexOption regexOption) {
        CloseableKt.checkNotNullParameter(str, "pattern");
        CloseableKt.checkNotNullParameter(regexOption, "option");
        Companion companion = Companion;
        int value = regexOption.getValue();
        companion.getClass();
        Pattern patternCompile = Pattern.compile(str, (value & 2) != 0 ? value | 64 : value);
        CloseableKt.checkNotNullExpressionValue(patternCompile, "compile(...)");
        this(patternCompile);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str, Set<? extends kotlin.text.RegexOption> set) {
        CloseableKt.checkNotNullParameter(str, "pattern");
        CloseableKt.checkNotNullParameter(set, "options");
        Companion companion = Companion;
        Iterator<T> it = set.iterator();
        int value = 0;
        while (it.hasNext()) {
            value |= ((FlagEnum) it.next()).getValue();
        }
        companion.getClass();
        Pattern patternCompile = Pattern.compile(str, (value & 2) != 0 ? value | 64 : value);
        CloseableKt.checkNotNullExpressionValue(patternCompile, "compile(...)");
        this(patternCompile);
    }
}
