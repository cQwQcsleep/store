package org.jetbrains.kotlin.backend.konan;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import org.eclipse.jdt.internal.compiler.parser.TerminalTokens;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u0003\"!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\n"}, d2 = {"defaultRange", "Lkotlin/Pair;", "", "Lorg/jetbrains/kotlin/backend/konan/BoxCache;", "getDefaultRange", "(Lorg/jetbrains/kotlin/backend/konan/BoxCache;)Lkotlin/Pair;", "getKotlinClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "cache", "org.jetbrains.kotlin:ir.backend.native"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class BoxCacheKt {

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BoxCache.values().length];
            try {
                iArr[BoxCache.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BoxCache.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BoxCache.SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[BoxCache.CHAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[BoxCache.INT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[BoxCache.LONG.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Pair<Integer, Integer> getDefaultRange(BoxCache boxCache) {
        Integer numValueOf = Integer.valueOf(TerminalTokens.TokenNameopens);
        boxCache.getClass();
        switch (WhenMappings.$EnumSwitchMapping$0[boxCache.ordinal()]) {
            case 1:
                return TuplesKt.to(0, 1);
            case 2:
                return TuplesKt.to(-128, numValueOf);
            case 3:
                return TuplesKt.to(-128, numValueOf);
            case 4:
                return TuplesKt.to(0, 255);
            case 5:
                return TuplesKt.to(-128, numValueOf);
            case 6:
                return TuplesKt.to(-128, numValueOf);
            default:
                bu8.a();
                return null;
        }
    }

    public static final IrClass getKotlinClass(IrBuiltIns irBuiltIns, BoxCache boxCache) {
        IrClassSymbol booleanClass;
        irBuiltIns.getClass();
        boxCache.getClass();
        switch (WhenMappings.$EnumSwitchMapping$0[boxCache.ordinal()]) {
            case 1:
                booleanClass = irBuiltIns.getBooleanClass();
                break;
            case 2:
                booleanClass = irBuiltIns.getByteClass();
                break;
            case 3:
                booleanClass = irBuiltIns.getShortClass();
                break;
            case 4:
                booleanClass = irBuiltIns.getCharClass();
                break;
            case 5:
                booleanClass = irBuiltIns.getIntClass();
                break;
            case 6:
                booleanClass = irBuiltIns.getLongClass();
                break;
            default:
                bu8.a();
                return null;
        }
        return booleanClass.getOwner();
    }
}
