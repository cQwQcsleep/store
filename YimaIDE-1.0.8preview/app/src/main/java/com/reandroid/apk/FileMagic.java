package com.reandroid.apk;

import com.reandroid.archive.InputSource;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class FileMagic {
    private static final int MAGIC_MAX_LENGTH = 16;
    private static final byte[] MAGIC_PNG = {-119, 80, 78, 71, 13, 10, 26, 10};
    private static final byte[] MAGIC_JPG = {-1, -40, -1, -32, 0, 16, 74, 70};
    private static final byte[] MAGIC_WEBP = {82, 73, 70, 70, -1, -1, -1, 0, 87, 69, 66, 80, 86, 80, 56};
    private static final byte[] MAGIC_TTF = {0, 1, 0, 0, 0, -1, -1, -1};

    private static boolean compareMagic(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return false;
        }
        int length = bArr.length;
        if (length > bArr2.length) {
            length = bArr2.length;
        }
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            byte b = bArr[i];
            if (b != -1 && b != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static String getExtensionFromMagic(InputSource inputSource) throws IOException {
        byte[] fileMagic = readFileMagic(inputSource);
        if (fileMagic == null) {
            return null;
        }
        if (isPng(fileMagic)) {
            return ".png";
        }
        if (isJpeg(fileMagic)) {
            return ".jpg";
        }
        if (isWebp(fileMagic)) {
            return ".webp";
        }
        if (isTtf(fileMagic)) {
            return ".ttf";
        }
        return null;
    }

    private static boolean isJpeg(byte[] bArr) {
        return compareMagic(MAGIC_JPG, bArr);
    }

    private static boolean isPng(byte[] bArr) {
        return compareMagic(MAGIC_PNG, bArr);
    }

    private static boolean isTtf(byte[] bArr) {
        return compareMagic(MAGIC_TTF, bArr);
    }

    private static boolean isWebp(byte[] bArr) {
        return compareMagic(MAGIC_WEBP, bArr);
    }

    private static byte[] readFileMagic(InputSource inputSource) throws IOException {
        InputStream inputStreamOpenStream = inputSource.openStream();
        byte[] bArr = new byte[16];
        int i = inputStreamOpenStream.read(bArr, 0, 16);
        inputStreamOpenStream.close();
        if (i < 16) {
            return null;
        }
        return bArr;
    }
}
