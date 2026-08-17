package com.sun.org.apache.xerces.internal.util;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.tools.javac.jvm.ByteCodes;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class PrimeNumberSequenceGenerator {
    private static final int[] PRIMES = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, WinError.ERROR_FILE_TOO_LARGE, 227, WinError.ERROR_PIPE_LOCAL, WinError.ERROR_PIPE_NOT_CONNECTED, 239, 241, Const.SAME_FRAME_EXTENDED, 257, 263, 269, ByteCodes.lshll, 277, 281, 283, 293, 307, 311, 313, WinError.ERROR_MR_MID_NOT_FOUND, 331, 337, 347, 349, WinError.ERROR_MAX_SESSIONS_REACHED, 359, 367, 373, 379, 383, 389, 397, WinError.ERROR_THREAD_MODE_NOT_BACKGROUND, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, WinError.ERROR_INVALID_ADDRESS, 491, 499, 503, 509, 521, 523, WinError.ERROR_TIMER_NOT_CANCELED, WinError.ERROR_INVALID_QUOTA_LOWER, WinError.ERROR_UNEXPECTED_MM_MAP_ERROR, WinError.ERROR_INVALID_LDT_OFFSET, WinError.ERROR_SYNCHRONIZATION_REQUIRED, WinError.ERROR_IO_PRIVILEGE_FAILED, WinError.ERROR_INVALID_IMAGE_HASH, WinError.ERROR_MUTANT_LIMIT_EXCEEDED, WinError.ERROR_VDM_HARD_ERROR, WinError.ERROR_STACK_OVERFLOW_READ, WinError.ERROR_FOUND_OUT_OF_SCOPE, WinError.ERROR_TIMER_RESOLUTION_NOT_SET, WinError.ERROR_REGISTRY_QUOTA_LIMIT, WinError.ERROR_PWD_HISTORY_CONFLICT, WinError.ERROR_INVALID_HW_PROFILE, WinError.ERROR_FLOAT_MULTIPLE_TRAPS, WinError.ERROR_SYSTEM_SHUTDOWN, WinError.ERROR_DS_VERSION_CHECK_FAILURE, WinError.ERROR_FAILED_DRIVER_ENTRY, WinError.ERROR_SYSTEM_HIVE_TOO_LARGE, 659, 661, WinError.ERROR_PNP_IRQ_TRANSLATION_FAILED, WinError.ERROR_EXTRANEOUS_INFORMATION, WinError.ERROR_PLUGPLAY_QUERY_VETOED, WinError.ERROR_DBG_TERMINATE_THREAD, WinError.ERROR_RXACT_STATE_CREATED, WinError.ERROR_RECEIVE_PARTIAL_EXPEDITED, WinError.ERROR_ALREADY_WIN32, WinError.ERROR_RESUME_HIBERNATION};

    public static /* synthetic */ void a(int[] iArr, Random random, int i) {
        int[] iArr2 = PRIMES;
        iArr[i] = iArr2[random.nextInt(iArr2.length)];
    }

    public static void generateSequence(final int[] iArr) {
        final Random random = new Random();
        IntStream.range(0, iArr.length).forEach(new IntConsumer() { // from class: com.sun.org.apache.xerces.internal.util.a
            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                PrimeNumberSequenceGenerator.a(iArr, random, i);
            }
        });
    }
}
