package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004b\u0002\b\u0003\u001a\u001a\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004b\u0002\b\u0003\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004b\u0002\b\u0003\u001a\u001a\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004b\u0002\b\u0003\u001a(\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nb\u0002\b\u0003\u001a\u0012\u0010\u000b\u001a\u00020\u0001*\u00020\u0001H\u0087\u008a\u0004b\u0002\b\u0003\u001a \u0010\f\u001a\u00020\u0001*\u00020\u0001H\u0087\u008a\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003\u001a \u0010\u000e\u001a\u00020\u0001*\u00020\u0001H\u0087\u008a\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003\u001a \u0010\u000f\u001a\u00020\u0001*\u00020\u0001H\u0087\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003\u001a(\u0010\u0010\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003\u001a(\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003\u001a(\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003\u001a(\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0087\u008c\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003\u001a(\u0010\u0016\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0087\u008c\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003\u001a \u0010\u0017\u001a\u00020\u0001*\u00020\u0015H\u0087\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003\u001a \u0010\u0017\u001a\u00020\u0001*\u00020\u0018H\u0087\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003\u001a'\u0010\u0017\u001a\u00020\u0001*\u00020\u0019H\u0087\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u001cb\u0002\b\u0003¢\u0006\u0004\b\u001a\u0010\u001b\u001a#\u0010\u0017\u001a\u00020\u0001*\u00020\u001dH\u0087\u0080\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u001c¢\u0006\u0004\b\u001e\u0010\u001f\u001a \u0010 \u001a\u00020!*\u00020\u0001H\u0087\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003\u001a4\u0010 \u001a\u00020!*\u00020\u00012\b\b\u0002\u0010\"\u001a\u00020\u00152\b\b\u0002\u0010#\u001a\u00020$H\u0087\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\rb\u0002\b\u0003¨\u0006%"}, d2 = {"plus", "Ljava/math/BigInteger;", "other", "Lkotlin/internal/InlineOnly;", "minus", "times", "div", "rem", "Lkotlin/SinceKotlin;", "version", "1.1", "unaryMinus", "inc", "1.2", "dec", "inv", "and", "or", "xor", "shl", "n", "", "shr", "toBigInteger", "", "Lkotlin/UInt;", "toBigInteger-WZ4Q5Ns", "(I)Ljava/math/BigInteger;", "2.4", "Lkotlin/ULong;", "toBigInteger-VKZWuLQ", "(J)Ljava/math/BigInteger;", "toBigDecimal", "Ljava/math/BigDecimal;", "scale", "mathContext", "Ljava/math/MathContext;", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/NumbersKt")
class NumbersKt__BigIntegersKt extends NumbersKt__BigDecimalsKt {
    private static final BigInteger and(BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger.getClass();
        bigInteger2.getClass();
        BigInteger bigIntegerAnd = bigInteger.and(bigInteger2);
        bigIntegerAnd.getClass();
        return bigIntegerAnd;
    }

    private static final BigInteger dec(BigInteger bigInteger) {
        bigInteger.getClass();
        BigInteger bigIntegerSubtract = bigInteger.subtract(BigInteger.ONE);
        bigIntegerSubtract.getClass();
        return bigIntegerSubtract;
    }

    private static final BigInteger div(BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger.getClass();
        bigInteger2.getClass();
        BigInteger bigIntegerDivide = bigInteger.divide(bigInteger2);
        bigIntegerDivide.getClass();
        return bigIntegerDivide;
    }

    private static final BigInteger inc(BigInteger bigInteger) {
        bigInteger.getClass();
        BigInteger bigIntegerAdd = bigInteger.add(BigInteger.ONE);
        bigIntegerAdd.getClass();
        return bigIntegerAdd;
    }

    private static final BigInteger inv(BigInteger bigInteger) {
        bigInteger.getClass();
        BigInteger bigIntegerNot = bigInteger.not();
        bigIntegerNot.getClass();
        return bigIntegerNot;
    }

    private static final BigInteger minus(BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger.getClass();
        bigInteger2.getClass();
        BigInteger bigIntegerSubtract = bigInteger.subtract(bigInteger2);
        bigIntegerSubtract.getClass();
        return bigIntegerSubtract;
    }

    private static final BigInteger or(BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger.getClass();
        bigInteger2.getClass();
        BigInteger bigIntegerOr = bigInteger.or(bigInteger2);
        bigIntegerOr.getClass();
        return bigIntegerOr;
    }

    private static final BigInteger plus(BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger.getClass();
        bigInteger2.getClass();
        BigInteger bigIntegerAdd = bigInteger.add(bigInteger2);
        bigIntegerAdd.getClass();
        return bigIntegerAdd;
    }

    private static final BigInteger rem(BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger.getClass();
        bigInteger2.getClass();
        BigInteger bigIntegerRemainder = bigInteger.remainder(bigInteger2);
        bigIntegerRemainder.getClass();
        return bigIntegerRemainder;
    }

    private static final BigInteger shl(BigInteger bigInteger, int i) {
        bigInteger.getClass();
        BigInteger bigIntegerShiftLeft = bigInteger.shiftLeft(i);
        bigIntegerShiftLeft.getClass();
        return bigIntegerShiftLeft;
    }

    private static final BigInteger shr(BigInteger bigInteger, int i) {
        bigInteger.getClass();
        BigInteger bigIntegerShiftRight = bigInteger.shiftRight(i);
        bigIntegerShiftRight.getClass();
        return bigIntegerShiftRight;
    }

    private static final BigInteger times(BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger.getClass();
        bigInteger2.getClass();
        BigInteger bigIntegerMultiply = bigInteger.multiply(bigInteger2);
        bigIntegerMultiply.getClass();
        return bigIntegerMultiply;
    }

    private static final BigDecimal toBigDecimal(BigInteger bigInteger, int i, MathContext mathContext) {
        bigInteger.getClass();
        mathContext.getClass();
        return new BigDecimal(bigInteger, i, mathContext);
    }

    public static /* synthetic */ BigDecimal toBigDecimal$default(BigInteger bigInteger, int i, MathContext mathContext, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            mathContext = MathContext.UNLIMITED;
            mathContext.getClass();
        }
        bigInteger.getClass();
        mathContext.getClass();
        return new BigDecimal(bigInteger, i, mathContext);
    }

    private static final BigInteger toBigInteger(int i) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(i);
        bigIntegerValueOf.getClass();
        return bigIntegerValueOf;
    }

    /* JADX INFO: renamed from: toBigInteger-VKZWuLQ, reason: not valid java name */
    public static final BigInteger m33toBigIntegerVKZWuLQ(long j) {
        if (j >= 0) {
            BigInteger bigIntegerValueOf = BigInteger.valueOf(j);
            bigIntegerValueOf.getClass();
            return bigIntegerValueOf;
        }
        BigInteger bigIntegerValueOf2 = BigInteger.valueOf(j >>> 1);
        bigIntegerValueOf2.getClass();
        BigInteger bigIntegerShiftLeft = bigIntegerValueOf2.shiftLeft(1);
        long jRemainderUnsigned = Long.remainderUnsigned(j, 2L);
        bigIntegerShiftLeft.getClass();
        if (jRemainderUnsigned != 1) {
            return bigIntegerShiftLeft;
        }
        BigInteger bigInteger = BigInteger.ONE;
        bigInteger.getClass();
        BigInteger bigIntegerAdd = bigIntegerShiftLeft.add(bigInteger);
        bigIntegerAdd.getClass();
        return bigIntegerAdd;
    }

    /* JADX INFO: renamed from: toBigInteger-WZ4Q5Ns, reason: not valid java name */
    private static final BigInteger m34toBigIntegerWZ4Q5Ns(int i) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(((long) i) & 4294967295L);
        bigIntegerValueOf.getClass();
        return bigIntegerValueOf;
    }

    private static final BigInteger unaryMinus(BigInteger bigInteger) {
        bigInteger.getClass();
        BigInteger bigIntegerNegate = bigInteger.negate();
        bigIntegerNegate.getClass();
        return bigIntegerNegate;
    }

    private static final BigInteger xor(BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger.getClass();
        bigInteger2.getClass();
        BigInteger bigIntegerXor = bigInteger.xor(bigInteger2);
        bigIntegerXor.getClass();
        return bigIntegerXor;
    }

    private static final BigInteger toBigInteger(long j) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(j);
        bigIntegerValueOf.getClass();
        return bigIntegerValueOf;
    }

    private static final BigDecimal toBigDecimal(BigInteger bigInteger) {
        bigInteger.getClass();
        return new BigDecimal(bigInteger);
    }
}
