package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class A2 {
    public static void a(com.android.tools.r8.graph.B1 b1, BiConsumer biConsumer) {
        com.android.tools.r8.graph.I2[] i2Arr = {b1.x2, b1.y2};
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            com.android.tools.r8.graph.I2 i3 = i2Arr[i2];
            C0322w2 c0322w2A = b1.a(i3, b1.a(b1.B1, new com.android.tools.r8.graph.I2[0]), "capacity");
            EnumC3077y2 enumC3077y2 = EnumC3077y2.c;
            biConsumer.accept(c0322w2A, enumC3077y2);
            com.android.tools.r8.graph.I2 i4 = b1.B1;
            biConsumer.accept(b1.a(i3, b1.a(i4, i4), "codePointAt"), enumC3077y2);
            com.android.tools.r8.graph.I2 i5 = b1.B1;
            biConsumer.accept(b1.a(i3, b1.a(i5, i5), "codePointBefore"), enumC3077y2);
            com.android.tools.r8.graph.I2 i6 = b1.B1;
            biConsumer.accept(b1.a(i3, b1.a(i6, i6, i6), "codePointCount"), enumC3077y2);
            biConsumer.accept(b1.a(i3, b1.a(b1.E1, b1.B1), "ensureCapacity"), enumC3077y2);
            com.android.tools.r8.graph.I2 i7 = b1.E1;
            com.android.tools.r8.graph.I2 i8 = b1.B1;
            biConsumer.accept(b1.a(i3, b1.a(i7, i8, i8, b1.H1, i8), "getChars"), enumC3077y2);
            biConsumer.accept(b1.a(i3, b1.a(b1.B1, b1.Y1), "indexOf"), enumC3077y2);
            com.android.tools.r8.graph.I2 i9 = b1.B1;
            biConsumer.accept(b1.a(i3, b1.a(i9, b1.Y1, i9), "indexOf"), enumC3077y2);
            biConsumer.accept(b1.a(i3, b1.a(b1.B1, b1.Y1), "lastIndexOf"), enumC3077y2);
            com.android.tools.r8.graph.I2 i10 = b1.B1;
            biConsumer.accept(b1.a(i3, b1.a(i10, b1.Y1, i10), "lastIndexOf"), enumC3077y2);
            com.android.tools.r8.graph.I2 i11 = b1.B1;
            biConsumer.accept(b1.a(i3, b1.a(i11, i11, i11), "offsetByCodePoints"), enumC3077y2);
            biConsumer.accept(b1.a(i3, b1.a(b1.E1, b1.B1, b1.y1), "setCharAt"), enumC3077y2);
            biConsumer.accept(b1.a(i3, b1.a(b1.E1, b1.B1), "setLength"), enumC3077y2);
            biConsumer.accept(b1.a(i3, b1.a(b1.Y1, b1.B1), "substring"), enumC3077y2);
            com.android.tools.r8.graph.I2 i12 = b1.Y1;
            com.android.tools.r8.graph.I2 i13 = b1.B1;
            biConsumer.accept(b1.a(i3, b1.a(i12, i13, i13), "substring"), enumC3077y2);
            biConsumer.accept(b1.a(i3, b1.a(b1.E1, new com.android.tools.r8.graph.I2[0]), "trimToSize"), enumC3077y2);
        }
        biConsumer.accept(b1.a(b1.e3, b1.a(b1.d3, new com.android.tools.r8.graph.I2[0]), "getMap"), EnumC3077y2.z);
        String[] strArr = {"Landroid/nfc/tech/Ndef;", "Landroid/nfc/tech/NfcA;", "Landroid/nfc/tech/NfcB;", "Landroid/nfc/tech/NfcBarcode;", "Landroid/nfc/tech/NfcF;", "Landroid/nfc/tech/NdefFormatable;", "Landroid/nfc/tech/IsoDep;", "Landroid/nfc/tech/MifareClassic;", "Landroid/nfc/tech/MifareUltralight;", "Landroid/nfc/tech/NfcV;"};
        com.android.tools.r8.graph.I2 i2E = b1.e("Landroid/nfc/Tag;");
        for (int i14 = 0; i14 < 10; i14++) {
            com.android.tools.r8.graph.I2 i2E2 = b1.e(strArr[i14]);
            C0322w2 c0322w2A2 = b1.a(i2E2, b1.a(b1.w1, new com.android.tools.r8.graph.I2[0]), "isConnected");
            EnumC3077y2 enumC3077y3 = EnumC3077y2.u;
            biConsumer.accept(c0322w2A2, enumC3077y3);
            biConsumer.accept(b1.a(i2E2, b1.a(i2E, new com.android.tools.r8.graph.I2[0]), "getTag"), enumC3077y3);
            biConsumer.accept(b1.a(i2E2, b1.a(b1.E1, new com.android.tools.r8.graph.I2[0]), "close"), enumC3077y3);
            biConsumer.accept(b1.a(i2E2, b1.a(b1.E1, new com.android.tools.r8.graph.I2[0]), "connect"), enumC3077y3);
        }
        com.android.tools.r8.graph.I2 i2E3 = b1.e("Landroid/webkit/CookieSyncManager;");
        com.android.tools.r8.graph.E2 e2A = b1.a(b1.E1, new com.android.tools.r8.graph.I2[0]);
        String[] strArr2 = {"sync", "resetSync", "startSync", "stopSync", "run"};
        for (int i15 = 0; i15 < 5; i15++) {
            biConsumer.accept(b1.a(i2E3, e2A, strArr2[i15]), EnumC3077y2.p);
        }
        com.android.tools.r8.graph.I2 i2E4 = b1.e("Ljava/time/temporal/ValueRange;");
        com.android.tools.r8.graph.I2 i2E5 = b1.e("Ljava/time/chrono/ChronoLocalDate;");
        com.android.tools.r8.graph.I2 i2E6 = b1.e("Ljava/time/temporal/Temporal;");
        com.android.tools.r8.graph.I2 i2E7 = b1.e("Ljava/time/temporal/TemporalField;");
        com.android.tools.r8.graph.I2 i2E8 = b1.e("Ljava/time/temporal/TemporalUnit;");
        com.android.tools.r8.graph.I2 i2E9 = b1.e("Ljava/time/temporal/TemporalAmount;");
        com.android.tools.r8.graph.I2 i2E10 = b1.e("Ljava/time/temporal/TemporalAdjuster;");
        String[] strArr3 = {"Ljava/time/chrono/JapaneseDate;", "Ljava/time/chrono/MinguoDate;", "Ljava/time/chrono/HijrahDate;", "Ljava/time/chrono/ThaiBuddhistDate;"};
        int i16 = 0;
        while (i16 < 4) {
            com.android.tools.r8.graph.I2 i2E11 = b1.e(strArr3[i16]);
            C0322w2 c0322w2A3 = b1.a(i2E11, b1.a(b1.B1, new com.android.tools.r8.graph.I2[i]), "lengthOfMonth");
            EnumC3077y2 enumC3077y4 = EnumC3077y2.B;
            biConsumer.accept(c0322w2A3, enumC3077y4);
            com.android.tools.r8.graph.I2 i17 = i2E9;
            biConsumer.accept(b1.a(i2E11, b1.a(b1.B1, new com.android.tools.r8.graph.I2[i]), "lengthOfYear"), enumC3077y4);
            biConsumer.accept(b1.a(i2E11, b1.a(b1.w1, i2E7), "isSupported"), enumC3077y4);
            biConsumer.accept(b1.a(i2E11, b1.a(i2E4, i2E7), "range"), enumC3077y4);
            biConsumer.accept(b1.a(i2E11, b1.a(b1.C1, i2E7), "getLong"), enumC3077y4);
            biConsumer.accept(b1.a(i2E11, b1.a(b1.e("Ljava/time/chrono/ChronoLocalDateTime;"), b1.e("Ljava/time/LocalTime;")), "atTime"), enumC3077y4);
            biConsumer.accept(b1.a(i2E11, b1.a(b1.e("Ljava/time/chrono/ChronoPeriod;"), i2E5), "until"), enumC3077y4);
            com.android.tools.r8.graph.I2 i18 = i2E4;
            biConsumer.accept(b1.a(i2E11, b1.a(b1.C1, new com.android.tools.r8.graph.I2[i]), "toEpochDay"), enumC3077y4);
            biConsumer.accept(b1.a(i2E11, b1.a(b1.C1, i2E6, i2E8), "until"), enumC3077y4);
            biConsumer.accept(b1.a(i2E11, b1.a(b1.e("Ljava/time/chrono/Era;"), new com.android.tools.r8.graph.I2[i]), "getEra"), enumC3077y4);
            biConsumer.accept(b1.a(i2E11, b1.a(b1.e("Ljava/time/chrono/Chronology;"), new com.android.tools.r8.graph.I2[i]), "getChronology"), enumC3077y4);
            com.android.tools.r8.graph.I2[] i2Arr2 = {i2E5, i2E6};
            int i19 = i;
            while (i19 < 2) {
                com.android.tools.r8.graph.I2 i20 = i2Arr2[i19];
                C0322w2 c0322w2A4 = b1.a(i2E11, b1.a(i20, b1.C1, i2E8), "minus");
                com.android.tools.r8.graph.I2[] i2Arr3 = i2Arr2;
                EnumC3077y2 enumC3077y5 = EnumC3077y2.B;
                biConsumer.accept(c0322w2A4, enumC3077y5);
                biConsumer.accept(b1.a(i2E11, b1.a(i20, i17), "minus"), enumC3077y5);
                biConsumer.accept(b1.a(i2E11, b1.a(i20, b1.C1, i2E8), "plus"), enumC3077y5);
                biConsumer.accept(b1.a(i2E11, b1.a(i20, i17), "plus"), enumC3077y5);
                biConsumer.accept(b1.a(i2E11, b1.a(i20, i2E7, b1.C1), "with"), enumC3077y5);
                biConsumer.accept(b1.a(i2E11, b1.a(i20, i2E10), "with"), enumC3077y5);
                i19++;
                i2Arr2 = i2Arr3;
            }
            i16++;
            i2E9 = i17;
            i2E4 = i18;
            i = 0;
        }
        biConsumer.accept(b1.a(b1.e("Ljava/time/chrono/HijrahDate;"), b1.a(b1.w1, new com.android.tools.r8.graph.I2[0]), "isLeapYear"), EnumC3077y2.B);
    }

    public static Set<String> a() {
        HashSet hashSet = new HashSet();
        hashSet.add("androidx.annotation.RecentlyNullable");
        hashSet.add("androidx.annotation.RecentlyNonNull");
        hashSet.add("android.annotation.Nullable");
        hashSet.add("android.annotation.NonNull");
        return hashSet;
    }
}
