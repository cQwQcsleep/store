package com.shadow.kotlin.text;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.ranges.IntRange;
import com.shadow.kotlin.ranges.RangesKt;
import java.util.regex.Matcher;

/* loaded from: /workspace/unpacked/classes2.dex */
final class MatcherMatchResult implements MatchResult {
    private final CharSequence input;
    private final Matcher matcher;

    public MatcherMatchResult(Matcher matcher, CharSequence charSequence) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        this.matcher = matcher;
        this.input = charSequence;
    }

    public final IntRange getRange() {
        Matcher matcher = this.matcher;
        return RangesKt.b(matcher.start(), matcher.end());
    }

    public final MatchResult next() {
        Matcher matcher = this.matcher;
        int iEnd = matcher.end() + (matcher.end() == matcher.start() ? 1 : 0);
        CharSequence charSequence = this.input;
        if (iEnd > charSequence.length()) {
            return null;
        }
        Matcher matcher2 = matcher.pattern().matcher(charSequence);
        CloseableKt.checkNotNullExpressionValue(matcher2, "matcher(...)");
        if (matcher2.find(iEnd)) {
            return new MatcherMatchResult(matcher2, charSequence);
        }
        return null;
    }
}
