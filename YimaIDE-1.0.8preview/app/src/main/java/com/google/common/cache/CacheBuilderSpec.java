package com.google.common.cache;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class CacheBuilderSpec {
    private static final Splitter KEYS_SPLITTER = Splitter.on(',').trimResults();
    private static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
    private static final ImmutableMap<String, ValueParser> VALUE_PARSERS;
    long accessExpirationDuration;
    TimeUnit accessExpirationTimeUnit;
    Integer concurrencyLevel;
    Integer initialCapacity;
    LocalCache.Strength keyStrength;
    Long maximumSize;
    Long maximumWeight;
    Boolean recordStats;
    long refreshDuration;
    TimeUnit refreshTimeUnit;
    private final String specification;
    LocalCache.Strength valueStrength;
    long writeExpirationDuration;
    TimeUnit writeExpirationTimeUnit;

    /* JADX INFO: renamed from: com.google.common.cache.CacheBuilderSpec$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$cache$LocalCache$Strength;

        static {
            int[] iArr = new int[LocalCache.Strength.values().length];
            $SwitchMap$com$google$common$cache$LocalCache$Strength = iArr;
            try {
                iArr[LocalCache.Strength.WEAK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$cache$LocalCache$Strength[LocalCache.Strength.SOFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static final class KeyStrengthParser implements ValueParser {
        private final LocalCache.Strength strength;

        public KeyStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument(str2 == null, "key %s does not take values", str);
            LocalCache.Strength strength = cacheBuilderSpec.keyStrength;
            Preconditions.checkArgument(strength == null, "%s was already set to %s", str, strength);
            cacheBuilderSpec.keyStrength = this.strength;
        }
    }

    public interface ValueParser {
        void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2);
    }

    public static final class ValueStrengthParser implements ValueParser {
        private final LocalCache.Strength strength;

        public ValueStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument(str2 == null, "key %s does not take values", str);
            LocalCache.Strength strength = cacheBuilderSpec.valueStrength;
            Preconditions.checkArgument(strength == null, "%s was already set to %s", str, strength);
            cacheBuilderSpec.valueStrength = this.strength;
        }
    }

    static {
        AnonymousClass1 anonymousClass1 = null;
        ImmutableMap.Builder builderPut = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser(anonymousClass1)).put("maximumSize", new MaximumSizeParser(anonymousClass1)).put("maximumWeight", new MaximumWeightParser(anonymousClass1)).put("concurrencyLevel", new ConcurrencyLevelParser(anonymousClass1));
        LocalCache.Strength strength = LocalCache.Strength.WEAK;
        VALUE_PARSERS = builderPut.put("weakKeys", new KeyStrengthParser(strength)).put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT)).put("weakValues", new ValueStrengthParser(strength)).put("recordStats", new RecordStatsParser(anonymousClass1)).put("expireAfterAccess", new AccessDurationParser(anonymousClass1)).put("expireAfterWrite", new WriteDurationParser(anonymousClass1)).put("refreshAfterWrite", new RefreshDurationParser(anonymousClass1)).put("refreshInterval", new RefreshDurationParser(anonymousClass1)).buildOrThrow();
    }

    private CacheBuilderSpec(String str) {
        this.specification = str;
    }

    public static CacheBuilderSpec disableCaching() {
        return parse("maximumSize=0");
    }

    private static Long durationInNanos(long j, TimeUnit timeUnit) {
        if (timeUnit == null) {
            return null;
        }
        return Long.valueOf(timeUnit.toNanos(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String format(String str, Object... objArr) {
        return String.format(Locale.ROOT, str, objArr);
    }

    public static CacheBuilderSpec parse(String str) {
        CacheBuilderSpec cacheBuilderSpec = new CacheBuilderSpec(str);
        if (!str.isEmpty()) {
            for (String str2 : KEYS_SPLITTER.split(str)) {
                ImmutableList immutableListCopyOf = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(str2));
                Preconditions.checkArgument(!immutableListCopyOf.isEmpty(), "blank key-value pair");
                Preconditions.checkArgument(immutableListCopyOf.size() <= 2, "key-value pair %s with more than one equals sign", str2);
                String str3 = (String) immutableListCopyOf.get(0);
                ValueParser valueParser = (ValueParser) VALUE_PARSERS.get(str3);
                Preconditions.checkArgument(valueParser != null, "unknown key %s", str3);
                valueParser.parse(cacheBuilderSpec, str3, immutableListCopyOf.size() == 1 ? null : (String) immutableListCopyOf.get(1));
            }
        }
        return cacheBuilderSpec;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheBuilderSpec)) {
            return false;
        }
        CacheBuilderSpec cacheBuilderSpec = (CacheBuilderSpec) obj;
        return Objects.equals(this.initialCapacity, cacheBuilderSpec.initialCapacity) && Objects.equals(this.maximumSize, cacheBuilderSpec.maximumSize) && Objects.equals(this.maximumWeight, cacheBuilderSpec.maximumWeight) && Objects.equals(this.concurrencyLevel, cacheBuilderSpec.concurrencyLevel) && Objects.equals(this.keyStrength, cacheBuilderSpec.keyStrength) && Objects.equals(this.valueStrength, cacheBuilderSpec.valueStrength) && Objects.equals(this.recordStats, cacheBuilderSpec.recordStats) && Objects.equals(durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(cacheBuilderSpec.writeExpirationDuration, cacheBuilderSpec.writeExpirationTimeUnit)) && Objects.equals(durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(cacheBuilderSpec.accessExpirationDuration, cacheBuilderSpec.accessExpirationTimeUnit)) && Objects.equals(durationInNanos(this.refreshDuration, this.refreshTimeUnit), durationInNanos(cacheBuilderSpec.refreshDuration, cacheBuilderSpec.refreshTimeUnit));
    }

    public int hashCode() {
        return Objects.hash(this.initialCapacity, this.maximumSize, this.maximumWeight, this.concurrencyLevel, this.keyStrength, this.valueStrength, this.recordStats, durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(this.refreshDuration, this.refreshTimeUnit));
    }

    public CacheBuilder<Object, Object> toCacheBuilder() {
        CacheBuilder<Object, Object> cacheBuilderNewBuilder = CacheBuilder.newBuilder();
        Integer num = this.initialCapacity;
        if (num != null) {
            cacheBuilderNewBuilder.initialCapacity(num.intValue());
        }
        Long l = this.maximumSize;
        if (l != null) {
            cacheBuilderNewBuilder.maximumSize(l.longValue());
        }
        Long l2 = this.maximumWeight;
        if (l2 != null) {
            cacheBuilderNewBuilder.maximumWeight(l2.longValue());
        }
        Integer num2 = this.concurrencyLevel;
        if (num2 != null) {
            cacheBuilderNewBuilder.concurrencyLevel(num2.intValue());
        }
        LocalCache.Strength strength = this.keyStrength;
        if (strength != null) {
            if (AnonymousClass1.$SwitchMap$com$google$common$cache$LocalCache$Strength[strength.ordinal()] != 1) {
                x1f.a();
                return null;
            }
            cacheBuilderNewBuilder.weakKeys();
        }
        LocalCache.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            int i = AnonymousClass1.$SwitchMap$com$google$common$cache$LocalCache$Strength[strength2.ordinal()];
            if (i == 1) {
                cacheBuilderNewBuilder.weakValues();
            } else {
                if (i != 2) {
                    x1f.a();
                    return null;
                }
                cacheBuilderNewBuilder.softValues();
            }
        }
        Boolean bool = this.recordStats;
        if (bool != null && bool.booleanValue()) {
            cacheBuilderNewBuilder.recordStats();
        }
        TimeUnit timeUnit = this.writeExpirationTimeUnit;
        if (timeUnit != null) {
            cacheBuilderNewBuilder.expireAfterWrite(this.writeExpirationDuration, timeUnit);
        }
        TimeUnit timeUnit2 = this.accessExpirationTimeUnit;
        if (timeUnit2 != null) {
            cacheBuilderNewBuilder.expireAfterAccess(this.accessExpirationDuration, timeUnit2);
        }
        TimeUnit timeUnit3 = this.refreshTimeUnit;
        if (timeUnit3 != null) {
            cacheBuilderNewBuilder.refreshAfterWrite(this.refreshDuration, timeUnit3);
        }
        return cacheBuilderNewBuilder;
    }

    public String toParsableString() {
        return this.specification;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(toParsableString()).toString();
    }

    public static abstract class DurationParser implements ValueParser {
        private DurationParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            TimeUnit timeUnit;
            if (Strings.isNullOrEmpty(str2)) {
                kg9.a("value of key ", str, " omitted");
                return;
            }
            try {
                char cCharAt = str2.charAt(str2.length() - 1);
                if (cCharAt == 'd') {
                    timeUnit = TimeUnit.DAYS;
                } else if (cCharAt == 'h') {
                    timeUnit = TimeUnit.HOURS;
                } else if (cCharAt == 'm') {
                    timeUnit = TimeUnit.MINUTES;
                } else {
                    if (cCharAt != 's') {
                        throw new IllegalArgumentException(CacheBuilderSpec.format("key %s invalid unit: was %s, must end with one of [dhms]", str, str2));
                    }
                    timeUnit = TimeUnit.SECONDS;
                }
                parseDuration(cacheBuilderSpec, Long.parseLong(str2.substring(0, str2.length() - 1)), timeUnit);
            } catch (NumberFormatException unused) {
                w01.a(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2));
            }
        }

        public abstract void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit);

        public /* synthetic */ DurationParser(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static abstract class IntegerParser implements ValueParser {
        private IntegerParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            if (Strings.isNullOrEmpty(str2)) {
                kg9.a("value of key ", str, " omitted");
                return;
            }
            try {
                parseInteger(cacheBuilderSpec, Integer.parseInt(str2));
            } catch (NumberFormatException e) {
                nrd.a(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2), e);
            }
        }

        public abstract void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i);

        public /* synthetic */ IntegerParser(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static abstract class LongParser implements ValueParser {
        private LongParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            if (Strings.isNullOrEmpty(str2)) {
                kg9.a("value of key ", str, " omitted");
                return;
            }
            try {
                parseLong(cacheBuilderSpec, Long.parseLong(str2));
            } catch (NumberFormatException e) {
                nrd.a(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2), e);
            }
        }

        public abstract void parseLong(CacheBuilderSpec cacheBuilderSpec, long j);

        public /* synthetic */ LongParser(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static final class RecordStatsParser implements ValueParser {
        private RecordStatsParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument(str2 == null, "recordStats does not take values");
            Preconditions.checkArgument(cacheBuilderSpec.recordStats == null, "recordStats already set");
            cacheBuilderSpec.recordStats = Boolean.TRUE;
        }

        public /* synthetic */ RecordStatsParser(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static final class AccessDurationParser extends DurationParser {
        private AccessDurationParser() {
            super(null);
        }

        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.accessExpirationTimeUnit == null, "expireAfterAccess already set");
            cacheBuilderSpec.accessExpirationDuration = j;
            cacheBuilderSpec.accessExpirationTimeUnit = timeUnit;
        }

        public /* synthetic */ AccessDurationParser(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static final class ConcurrencyLevelParser extends IntegerParser {
        private ConcurrencyLevelParser() {
            super(null);
        }

        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        public void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i) {
            Integer num = cacheBuilderSpec.concurrencyLevel;
            Preconditions.checkArgument(num == null, "concurrency level was already set to %s", num);
            cacheBuilderSpec.concurrencyLevel = Integer.valueOf(i);
        }

        public /* synthetic */ ConcurrencyLevelParser(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static final class InitialCapacityParser extends IntegerParser {
        private InitialCapacityParser() {
            super(null);
        }

        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        public void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i) {
            Integer num = cacheBuilderSpec.initialCapacity;
            Preconditions.checkArgument(num == null, "initial capacity was already set to %s", num);
            cacheBuilderSpec.initialCapacity = Integer.valueOf(i);
        }

        public /* synthetic */ InitialCapacityParser(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static final class MaximumSizeParser extends LongParser {
        private MaximumSizeParser() {
            super(null);
        }

        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        public void parseLong(CacheBuilderSpec cacheBuilderSpec, long j) {
            Long l = cacheBuilderSpec.maximumSize;
            Preconditions.checkArgument(l == null, "maximum size was already set to %s", l);
            Long l2 = cacheBuilderSpec.maximumWeight;
            Preconditions.checkArgument(l2 == null, "maximum weight was already set to %s", l2);
            cacheBuilderSpec.maximumSize = Long.valueOf(j);
        }

        public /* synthetic */ MaximumSizeParser(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static final class MaximumWeightParser extends LongParser {
        private MaximumWeightParser() {
            super(null);
        }

        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        public void parseLong(CacheBuilderSpec cacheBuilderSpec, long j) {
            Long l = cacheBuilderSpec.maximumWeight;
            Preconditions.checkArgument(l == null, "maximum weight was already set to %s", l);
            Long l2 = cacheBuilderSpec.maximumSize;
            Preconditions.checkArgument(l2 == null, "maximum size was already set to %s", l2);
            cacheBuilderSpec.maximumWeight = Long.valueOf(j);
        }

        public /* synthetic */ MaximumWeightParser(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static final class RefreshDurationParser extends DurationParser {
        private RefreshDurationParser() {
            super(null);
        }

        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.refreshTimeUnit == null, "refreshAfterWrite already set");
            cacheBuilderSpec.refreshDuration = j;
            cacheBuilderSpec.refreshTimeUnit = timeUnit;
        }

        public /* synthetic */ RefreshDurationParser(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static final class WriteDurationParser extends DurationParser {
        private WriteDurationParser() {
            super(null);
        }

        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.writeExpirationTimeUnit == null, "expireAfterWrite already set");
            cacheBuilderSpec.writeExpirationDuration = j;
            cacheBuilderSpec.writeExpirationTimeUnit = timeUnit;
        }

        public /* synthetic */ WriteDurationParser(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
