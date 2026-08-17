package org.bouncycastle.pqc.crypto.falcon;

import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class FalconKeyGen {
    private short[] REV10 = {0, 512, 256, 768, 128, 640, 384, 896, 64, 576, 320, 832, 192, 704, 448, 960, 32, 544, 288, 800, 160, 672, 416, 928, 96, 608, 352, 864, 224, 736, 480, 992, 16, 528, 272, 784, 144, 656, 400, 912, 80, 592, 336, 848, 208, 720, 464, 976, 48, 560, 304, 816, 176, 688, 432, 944, 112, 624, 368, 880, 240, 752, 496, 1008, 8, 520, 264, 776, 136, 648, 392, 904, 72, 584, 328, 840, 200, 712, 456, 968, 40, 552, 296, 808, 168, 680, 424, 936, 104, 616, 360, 872, 232, 744, 488, 1000, 24, 536, 280, 792, 152, 664, 408, 920, 88, 600, 344, 856, 216, 728, 472, 984, 56, 568, 312, 824, 184, 696, 440, 952, 120, 632, 376, 888, 248, 760, 504, 1016, 4, 516, 260, 772, 132, 644, 388, 900, 68, 580, 324, 836, 196, 708, 452, 964, 36, 548, 292, 804, 164, 676, 420, 932, 100, 612, 356, 868, 228, 740, 484, 996, 20, 532, 276, 788, 148, 660, 404, 916, 84, 596, 340, 852, 212, 724, 468, 980, 52, 564, 308, 820, 180, 692, 436, 948, 116, 628, 372, 884, 244, 756, 500, 1012, 12, 524, 268, 780, 140, 652, 396, 908, 76, 588, 332, 844, 204, 716, 460, 972, 44, 556, 300, 812, 172, 684, 428, 940, 108, 620, 364, 876, 236, 748, 492, 1004, 28, 540, 284, 796, 156, 668, 412, 924, 92, 604, 348, 860, 220, 732, 476, 988, 60, 572, 316, 828, 188, 700, 444, 956, 124, 636, 380, 892, 252, 764, 508, 1020, 2, 514, 258, 770, 130, 642, 386, 898, 66, 578, 322, 834, 194, 706, 450, 962, 34, 546, 290, 802, 162, 674, 418, 930, 98, 610, 354, 866, 226, 738, 482, 994, 18, 530, 274, 786, 146, 658, 402, 914, 82, 594, 338, 850, 210, 722, 466, 978, 50, 562, 306, 818, 178, 690, 434, 946, 114, 626, 370, 882, 242, 754, 498, 1010, 10, 522, 266, 778, 138, 650, 394, 906, 74, 586, 330, 842, 202, 714, 458, 970, 42, 554, 298, 810, 170, 682, 426, 938, 106, 618, 362, 874, 234, 746, 490, 1002, 26, 538, 282, 794, 154, 666, 410, 922, 90, 602, 346, 858, 218, 730, 474, 986, 58, 570, 314, 826, 186, 698, 442, 954, 122, 634, 378, 890, 250, 762, 506, 1018, 6, 518, 262, 774, 134, 646, 390, 902, 70, 582, 326, 838, 198, 710, 454, 966, 38, 550, 294, 806, 166, 678, 422, 934, 102, 614, 358, 870, 230, 742, 486, 998, 22, 534, 278, 790, 150, 662, 406, 918, 86, 598, 342, 854, 214, 726, 470, 982, 54, 566, 310, 822, 182, 694, 438, 950, 118, 630, 374, 886, 246, 758, 502, 1014, 14, 526, 270, 782, 142, 654, 398, 910, 78, 590, 334, 846, 206, 718, 462, 974, 46, 558, 302, 814, 174, 686, 430, 942, 110, 622, 366, 878, 238, 750, 494, 1006, 30, 542, 286, 798, 158, 670, 414, 926, 94, 606, 350, 862, 222, 734, 478, 990, 62, 574, 318, 830, 190, 702, 446, 958, 126, 638, 382, 894, 254, 766, 510, 1022, 1, 513, 257, 769, 129, 641, 385, 897, 65, 577, 321, 833, 193, 705, 449, 961, 33, 545, 289, 801, 161, 673, 417, 929, 97, 609, 353, 865, 225, 737, 481, 993, 17, 529, 273, 785, 145, 657, 401, 913, 81, 593, 337, 849, 209, 721, 465, 977, 49, 561, 305, 817, 177, 689, 433, 945, 113, 625, 369, 881, 241, 753, 497, 1009, 9, 521, 265, 777, 137, 649, 393, 905, 73, 585, 329, 841, 201, 713, 457, 969, 41, 553, 297, 809, 169, 681, 425, 937, 105, 617, 361, 873, 233, 745, 489, 1001, 25, 537, 281, 793, 153, 665, 409, 921, 89, 601, 345, 857, 217, 729, 473, 985, 57, 569, 313, 825, 185, 697, 441, 953, 121, 633, 377, 889, 249, 761, 505, 1017, 5, 517, 261, 773, 133, 645, 389, 901, 69, 581, 325, 837, 197, 709, 453, 965, 37, 549, 293, 805, 165, 677, 421, 933, 101, 613, 357, 869, 229, 741, 485, 997, 21, 533, 277, 789, 149, 661, 405, 917, 85, 597, 341, 853, 213, 725, 469, 981, 53, 565, 309, 821, 181, 693, 437, 949, 117, 629, 373, 885, 245, 757, 501, 1013, 13, 525, 269, 781, 141, 653, 397, 909, 77, 589, 333, 845, 205, 717, 461, 973, 45, 557, 301, 813, 173, 685, 429, 941, 109, 621, 365, 877, 237, 749, 493, 1005, 29, 541, 285, 797, 157, 669, 413, 925, 93, 605, 349, 861, 221, 733, 477, 989, 61, 573, 317, 829, 189, 701, 445, 957, 125, 637, 381, 893, 253, 765, 509, 1021, 3, 515, 259, 771, 131, 643, 387, 899, 67, 579, 323, 835, 195, 707, 451, 963, 35, 547, 291, 803, 163, 675, 419, 931, 99, 611, 355, 867, 227, 739, 483, 995, 19, 531, 275, 787, 147, 659, 403, 915, 83, 595, 339, 851, 211, 723, 467, 979, 51, 563, 307, 819, 179, 691, 435, 947, 115, 627, 371, 883, 243, 755, 499, 1011, 11, 523, 267, 779, 139, 651, 395, 907, 75, 587, 331, 843, 203, 715, 459, 971, 43, 555, 299, 811, 171, 683, 427, 939, 107, 619, 363, 875, 235, 747, 491, 1003, 27, 539, 283, 795, 155, 667, 411, 923, 91, 603, 347, 859, 219, 731, 475, 987, 59, 571, 315, 827, 187, 699, 443, 955, 123, 635, 379, 891, 251, 763, 507, 1019, 7, 519, 263, 775, 135, 647, 391, 903, 71, 583, 327, 839, 199, 711, 455, 967, 39, 551, 295, 807, 167, 679, 423, 935, 103, 615, 359, 871, 231, 743, 487, 999, 23, 535, 279, 791, 151, 663, 407, 919, 87, 599, 343, 855, 215, 727, 471, 983, 55, 567, 311, 823, 183, 695, 439, 951, 119, 631, 375, 887, 247, 759, 503, 1015, 15, 527, 271, 783, 143, 655, 399, 911, 79, 591, 335, 847, 207, 719, 463, 975, 47, 559, 303, 815, 175, 687, 431, 943, 111, 623, 367, 879, 239, 751, 495, 1007, 31, 543, 287, 799, 159, 671, 415, 927, 
    95, 607, 351, 863, 223, 735, 479, 991, 63, 575, 319, 831, 191, 703, 447, 959, 127, 639, 383, 895, 255, 767, 511, 1023};
    final long[] gauss_1024_12289 = {1283868770400643928L, 6416574995475331444L, 4078260278032692663L, 2353523259288686585L, 1227179971273316331L, 575931623374121527L, 242543240509105209L, 91437049221049666L, 30799446349977173L, 9255276791179340L, 2478152334826140L, 590642893610164L, 125206034929641L, 23590435911403L, 3948334035941L, 586753615614L, 77391054539L, 9056793210L, 940121950, 86539696, 7062824, 510971, 32764, 1862, 94, 4, 0};
    final int[] MAX_BL_SMALL = {1, 1, 2, 2, 4, 7, 14, 27, 53, 106, 209};
    final int[] MAX_BL_LARGE = {2, 2, 5, 7, 12, 21, 40, 78, 157, 308};
    final int[] bitlength_avg = {4, 11, 24, 50, 102, 202, 401, 794, 1577, 3138, 6308};
    final int[] bitlength_std = {0, 1, 1, 1, 1, 2, 4, 5, 8, 13, 25};
    final int DEPTH_INT_FG = 4;
    FPREngine fpr = new FPREngine();
    FalconSmallPrimeList primes = new FalconSmallPrimeList();
    FalconFFT fft = new FalconFFT();
    FalconCodec codec = new FalconCodec();
    FalconVrfy vrfy = new FalconVrfy();

    private static int mkn(int i) {
        return 1 << i;
    }

    private long toUnsignedLong(int i) {
        return ((long) i) & 4294967295L;
    }

    public long get_rng_u64(SHAKE256 shake256) {
        byte[] bArr = new byte[8];
        shake256.inner_shake256_extract(bArr, 0, 8);
        return ((((long) bArr[1]) & 255) << 8) | (((long) bArr[0]) & 255) | ((((long) bArr[2]) & 255) << 16) | ((((long) bArr[3]) & 255) << 24) | ((((long) bArr[4]) & 255) << 32) | ((((long) bArr[5]) & 255) << 40) | ((((long) bArr[6]) & 255) << 48) | ((((long) bArr[7]) & 255) << 56);
    }

    public void keygen(SHAKE256 shake256, byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, byte[] bArr4, int i4, short[] sArr, int i5, int i6) {
        FalconKeyGen falconKeyGen;
        int i7;
        FPREngine fPREngine;
        int i8;
        short[] sArr2;
        int i9;
        int i10;
        byte b;
        FalconKeyGen falconKeyGen2 = this;
        bArr = bArr;
        i = i;
        bArr2 = bArr2;
        i2 = i2;
        int i11 = i6;
        int iMkn = mkn(i11);
        short[] sArr3 = sArr;
        while (true) {
            FalconFPR[] falconFPRArr = new FalconFPR[iMkn * 3];
            falconKeyGen2.poly_small_mkgauss(shake256, bArr, i, i11);
            falconKeyGen2.poly_small_mkgauss(shake256, bArr2, i2, i11);
            int i12 = 1 << (falconKeyGen2.codec.max_fg_bits[i11] - 1);
            for (int i13 = 0; i13 < iMkn; i13++) {
                byte b2 = bArr[i + i13];
                if (b2 >= i12 || b2 <= (i10 = -i12) || (b = bArr2[i2 + i13]) >= i12 || b <= i10) {
                    i12 = -1;
                    break;
                }
            }
            if (i12 >= 0) {
                int iPoly_small_sqnorm = falconKeyGen2.poly_small_sqnorm(bArr, i, i11);
                int iPoly_small_sqnorm2 = falconKeyGen2.poly_small_sqnorm(bArr2, i2, i11);
                if ((((long) ((-((iPoly_small_sqnorm | iPoly_small_sqnorm2) >>> 31)) | (iPoly_small_sqnorm + iPoly_small_sqnorm2))) & 4294967295L) < 16823) {
                    int i14 = iMkn + iMkn;
                    falconKeyGen2.poly_small_to_fp(falconFPRArr, 0, bArr, i, i11);
                    int i15 = iMkn;
                    falconKeyGen2.poly_small_to_fp(falconFPRArr, i15, bArr2, i2, i11);
                    falconKeyGen = falconKeyGen2;
                    falconKeyGen.fft.FFT(falconFPRArr, 0, i11);
                    falconKeyGen.fft.FFT(falconFPRArr, i15, i11);
                    falconKeyGen.fft.poly_invnorm2_fft(falconFPRArr, i14, falconFPRArr, 0, falconFPRArr, i15, i6);
                    falconKeyGen.fft.poly_adj_fft(falconFPRArr, 0, i6);
                    falconKeyGen.fft.poly_adj_fft(falconFPRArr, i15, i6);
                    falconKeyGen.fft.poly_mulconst(falconFPRArr, 0, falconKeyGen.fpr.fpr_q, i6);
                    falconKeyGen.fft.poly_mulconst(falconFPRArr, i15, falconKeyGen.fpr.fpr_q, i6);
                    falconKeyGen.fft.poly_mul_autoadj_fft(falconFPRArr, 0, falconFPRArr, i14, i6);
                    i11 = i6;
                    falconKeyGen.fft.poly_mul_autoadj_fft(falconFPRArr, i15, falconFPRArr, i14, i11);
                    i7 = i15;
                    falconKeyGen.fft.iFFT(falconFPRArr, 0, i11);
                    falconKeyGen.fft.iFFT(falconFPRArr, i7, i11);
                    FalconFPR falconFPRFpr_add = falconKeyGen.fpr.fpr_zero;
                    int i16 = 0;
                    while (true) {
                        fPREngine = falconKeyGen.fpr;
                        if (i16 >= i7) {
                            break;
                        }
                        FalconFPR falconFPRFpr_add2 = fPREngine.fpr_add(falconFPRFpr_add, fPREngine.fpr_sqr(falconFPRArr[i16]));
                        FPREngine fPREngine2 = falconKeyGen.fpr;
                        falconFPRFpr_add = fPREngine2.fpr_add(falconFPRFpr_add2, fPREngine2.fpr_sqr(falconFPRArr[i7 + i16]));
                        i16++;
                    }
                    if (fPREngine.fpr_lt(falconFPRFpr_add, fPREngine.fpr_bnorm_max)) {
                        short[] sArr4 = new short[i7 * 2];
                        if (sArr3 == null) {
                            sArr2 = sArr4;
                            i8 = 0;
                            i9 = i7;
                        } else {
                            i8 = i5;
                            sArr2 = sArr3;
                            i9 = 0;
                        }
                        short[] sArr5 = sArr2;
                        if (falconKeyGen.vrfy.compute_public(sArr2, i8, bArr, i, bArr2, i2, i11, sArr4, i9) != 0) {
                            if (falconKeyGen.solve_NTRU(i11, bArr3, i3, bArr4, i4, bArr, i, bArr2, i2, (1 << (falconKeyGen.codec.max_FG_bits[i11] - 1)) - 1, new int[i11 > 2 ? i7 * 28 : i7 * 84], 0) != 0) {
                                return;
                            }
                        }
                        falconKeyGen2 = this;
                        i11 = i6;
                        iMkn = i7;
                        sArr3 = sArr5;
                    } else {
                        falconKeyGen2 = falconKeyGen;
                        iMkn = i7;
                    }
                }
            }
            falconKeyGen = falconKeyGen2;
            i7 = iMkn;
            falconKeyGen2 = falconKeyGen;
            iMkn = i7;
        }
    }

    public void make_fg(int[] iArr, int i, byte[] bArr, int i2, byte[] bArr2, int i3, int i4, int i5, int i6) {
        int iMkn = mkn(i4);
        int i7 = i + iMkn;
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i8 = falconSmallPrimeArr[0].p;
        for (int i9 = 0; i9 < iMkn; i9++) {
            iArr[i + i9] = modp_set(bArr[i2 + i9], i8);
            iArr[i7 + i9] = modp_set(bArr2[i3 + i9], i8);
        }
        if (i5 != 0 || i6 == 0) {
            int i10 = 0;
            while (i10 < i5) {
                int i11 = i10 + 1;
                make_fg_step(iArr, i, i4 - i10, i10, i10 != 0 ? 1 : 0, (i11 < i5 || i6 != 0) ? 1 : 0);
                i10 = i11;
            }
            return;
        }
        int i12 = falconSmallPrimeArr[0].p;
        int iModp_ninv31 = modp_ninv31(i12);
        int i13 = i7 + iMkn;
        modp_mkgm2(iArr, i13, iArr, iMkn + i13, i4, falconSmallPrimeArr[0].g, i12, iModp_ninv31);
        modp_NTT2(iArr, i, iArr, i13, i4, i12, iModp_ninv31);
        modp_NTT2(iArr, i7, iArr, i13, i4, i12, iModp_ninv31);
    }

    public void make_fg_step(int[] iArr, int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        FalconKeyGen falconKeyGen;
        int i10;
        int i11;
        FalconKeyGen falconKeyGen2;
        int i12;
        int i13;
        FalconKeyGen falconKeyGen3 = this;
        int[] iArr2 = iArr;
        boolean z = true;
        int i14 = 1 << i2;
        int i15 = i14 >> 1;
        int[] iArr3 = falconKeyGen3.MAX_BL_SMALL;
        int i16 = iArr3[i3];
        int i17 = iArr3[i3 + 1];
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i18 = i15 * i17;
        int i19 = i + i18;
        int i20 = i19 + i18;
        int i21 = i14 * i16;
        int i22 = i20 + i21;
        int i23 = i22 + i21;
        int i24 = i23 + i14;
        int i25 = i24 + i14;
        System.arraycopy(iArr2, i, iArr2, i20, i14 * 2 * i16);
        int i26 = 0;
        while (i26 < i16) {
            int i27 = falconSmallPrimeArr[i26].p;
            int iModp_ninv31 = falconKeyGen3.modp_ninv31(i27);
            int iModp_R2 = falconKeyGen3.modp_R2(i27, iModp_ninv31);
            boolean z2 = z;
            int i28 = i26;
            int i29 = i20;
            int i30 = i23;
            int i31 = i24;
            falconKeyGen3.modp_mkgm2(iArr2, i30, iArr, i31, i2, falconSmallPrimeArr[i26].g, i27, iModp_ninv31);
            int i32 = i29 + i28;
            int i33 = 0;
            int i34 = i32;
            while (i33 < i14) {
                iArr[i25 + i33] = iArr[i34];
                i33++;
                i34 += i16;
            }
            if (i4 == 0) {
                int i35 = i25;
                falconKeyGen = this;
                falconKeyGen.modp_NTT2(iArr, i35, iArr, i30, i2, i27, iModp_ninv31);
                i8 = i35;
                i9 = iModp_ninv31;
                i27 = i27;
            } else {
                i8 = i25;
                i9 = iModp_ninv31;
                falconKeyGen = this;
            }
            int i36 = i + i28;
            int i37 = 0;
            int i38 = i36;
            while (i37 < i15) {
                int i39 = i8 + (i37 << 1);
                iArr[i38] = falconKeyGen.modp_montymul(falconKeyGen.modp_montymul(iArr[i39], iArr[i39 + 1], i27, i9), iModp_R2, i27, i9);
                i37++;
                i38 += i17;
            }
            if (i4 != 0) {
                int i40 = i16;
                falconKeyGen.modp_iNTT2_ext(iArr, i32, i40, iArr, i31, i2, i27, i9);
                i10 = i40;
            } else {
                i10 = i16;
            }
            int i41 = i22 + i28;
            int i42 = 0;
            int i43 = i41;
            while (i42 < i14) {
                iArr[i8 + i42] = iArr[i43];
                i42++;
                i43 += i10;
            }
            if (i4 == 0) {
                int i44 = i27;
                int i45 = i9;
                falconKeyGen2 = this;
                falconKeyGen2.modp_NTT2(iArr, i8, iArr, i30, i2, i44, i45);
                i11 = i45;
                i27 = i44;
            } else {
                i11 = i9;
                falconKeyGen2 = this;
            }
            int i46 = i19 + i28;
            int i47 = 0;
            int i48 = i46;
            while (i47 < i15) {
                int i49 = i8 + (i47 << 1);
                iArr[i48] = falconKeyGen2.modp_montymul(falconKeyGen2.modp_montymul(iArr[i49], iArr[i49 + 1], i27, i11), iModp_R2, i27, i11);
                i47++;
                i48 += i17;
            }
            if (i4 != 0) {
                i12 = r8;
                falconKeyGen2.modp_iNTT2_ext(iArr, i41, i10, iArr, i12, i2, i27, i11);
            } else {
                i12 = r8;
            }
            int i50 = i10;
            if (i5 == 0) {
                int i51 = i2 - 1;
                int i52 = i17;
                modp_iNTT2_ext(iArr, i36, i52, iArr, i12, i51, i27, i11);
                modp_iNTT2_ext(iArr, i46, i52, iArr, i12, i51, i27, i11);
                i13 = i52;
            } else {
                i13 = i17;
            }
            i26 = i28 + 1;
            falconKeyGen3 = this;
            iArr2 = iArr;
            i20 = i29;
            i24 = i12;
            i23 = i30;
            z = z2;
            i25 = i8;
            i17 = i13;
            i16 = i50;
        }
        int i53 = i16;
        int i54 = i17;
        int i55 = i25;
        int i56 = i20;
        int i57 = i23;
        int i58 = i24;
        FalconKeyGen falconKeyGen4 = this;
        falconKeyGen4.zint_rebuild_CRT(iArr, i20, i53, i53, i14, falconSmallPrimeArr, 1, iArr, i57);
        falconKeyGen4.zint_rebuild_CRT(iArr, i22, i53, i53, i14, falconSmallPrimeArr, 1, iArr, i57);
        int i59 = i57;
        int i60 = i53;
        while (i60 < i54) {
            int i61 = falconSmallPrimeArr[i60].p;
            int iModp_ninv32 = falconKeyGen4.modp_ninv31(i61);
            int iModp_R3 = falconKeyGen4.modp_R2(i61, iModp_ninv32);
            int iModp_Rx = falconKeyGen4.modp_Rx(i53, i61, iModp_ninv32, iModp_R3);
            int i62 = iModp_R3;
            int i63 = i59;
            int i64 = i58;
            int i65 = i60;
            falconKeyGen4.modp_mkgm2(iArr, i63, iArr, i64, i2, falconSmallPrimeArr[i60].g, i61, iModp_ninv32);
            int i66 = i56;
            int i67 = 0;
            while (i67 < i14) {
                int i68 = i61;
                int i69 = i53;
                int i70 = iModp_Rx;
                int i71 = i62;
                int iZint_mod_small_signed = zint_mod_small_signed(iArr, i66, i69, i68, iModp_ninv32, i71, i70);
                i61 = i68;
                iArr[i55 + i67] = iZint_mod_small_signed;
                i67++;
                i66 += i69;
                i53 = i69;
                iModp_Rx = i70;
                i62 = i71;
            }
            int i72 = iModp_Rx;
            int i73 = i62;
            int i74 = i53;
            FalconKeyGen falconKeyGen5 = this;
            int i75 = i73;
            int i76 = i61;
            falconKeyGen5.modp_NTT2(iArr, i55, iArr, i63, i2, i76, iModp_ninv32);
            int i77 = i76;
            int i78 = i + i65;
            int i79 = i78;
            int i80 = 0;
            while (i80 < i15) {
                int i81 = i55 + (i80 << 1);
                iArr[i79] = falconKeyGen5.modp_montymul(falconKeyGen5.modp_montymul(iArr[i81], iArr[i81 + 1], i77, iModp_ninv32), i75, i77, iModp_ninv32);
                i80++;
                i79 += i54;
            }
            int i82 = i22;
            int i83 = 0;
            while (i83 < i14) {
                int i84 = i77;
                int i85 = i75;
                int i86 = i74;
                int i87 = i72;
                int iZint_mod_small_signed2 = falconKeyGen5.zint_mod_small_signed(iArr, i82, i86, i84, iModp_ninv32, i85, i87);
                i77 = i84;
                iArr[i55 + i83] = iZint_mod_small_signed2;
                i83++;
                i82 += i86;
                i75 = i85;
                i74 = i86;
                i72 = i87;
                falconKeyGen5 = this;
            }
            int i88 = i74;
            int i89 = i77;
            modp_NTT2(iArr, i55, iArr, i63, i2, i89, iModp_ninv32);
            int i90 = i19 + i65;
            int i91 = 0;
            int i92 = i90;
            while (i91 < i15) {
                int i93 = i55 + (i91 << 1);
                iArr[i92] = modp_montymul(modp_montymul(iArr[i93], iArr[i93 + 1], i89, iModp_ninv32), i75, i89, iModp_ninv32);
                i91++;
                i92 += i54;
            }
            if (i5 == 0) {
                int i94 = i2 - 1;
                i6 = i54;
                i7 = i64;
                modp_iNTT2_ext(iArr, i78, i6, iArr, i7, i94, i89, iModp_ninv32);
                modp_iNTT2_ext(iArr, i90, i6, iArr, i7, i94, i89, iModp_ninv32);
            } else {
                i6 = i54;
                i7 = i64;
            }
            i60 = i65 + 1;
            falconKeyGen4 = this;
            i54 = i6;
            i58 = i7;
            i59 = i63;
            i53 = i88;
        }
    }

    public int mkgauss(SHAKE256 shake256, int i) {
        int i2 = 1 << (10 - i);
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            long j = get_rng_u64(shake256);
            int i5 = (int) (j >>> 63);
            int i6 = (int) (((j & LongCompanionObject.MAX_VALUE) - this.gauss_1024_12289[0]) >>> 63);
            long j2 = LongCompanionObject.MAX_VALUE & get_rng_u64(shake256);
            int i7 = 1;
            int i8 = 0;
            while (true) {
                long[] jArr = this.gauss_1024_12289;
                if (i7 < jArr.length) {
                    int i9 = ((int) ((j2 - jArr[i7]) >>> 63)) ^ 1;
                    i8 |= (-((i6 ^ 1) & i9)) & i7;
                    i6 |= i9;
                    i7++;
                }
            }
            i3 += ((-i5) ^ i8) + i5;
        }
        return i3;
    }

    public void modp_NTT2(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4, int i5) {
        modp_NTT2_ext(iArr, i, 1, iArr2, i2, i3, i4, i5);
    }

    public void modp_NTT2_ext(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6) {
        if (i4 == 0) {
            return;
        }
        int iMkn = mkn(i4);
        int i7 = 1;
        int i8 = iMkn;
        while (i7 < iMkn) {
            int i9 = i8 >> 1;
            int i10 = 0;
            int i11 = 0;
            while (i10 < i7) {
                int i12 = iArr2[i3 + i7 + i10];
                int i13 = i + (i11 * i2);
                int i14 = (i9 * i2) + i13;
                int i15 = 0;
                while (i15 < i9) {
                    int i16 = iArr[i13];
                    int iModp_montymul = modp_montymul(iArr[i14], i12, i5, i6);
                    iArr[i13] = modp_add(i16, iModp_montymul, i5);
                    iArr[i14] = modp_sub(i16, iModp_montymul, i5);
                    i15++;
                    i13 += i2;
                    i14 += i2;
                }
                i10++;
                i11 += i8;
            }
            i7 <<= 1;
            i8 = i9;
        }
    }

    public int modp_R(int i) {
        return IntCompanionObject.MIN_VALUE - i;
    }

    public int modp_R2(int i, int i2) {
        int iModp_R = modp_R(i);
        int iModp_add = modp_add(iModp_R, iModp_R, i);
        int iModp_montymul = modp_montymul(iModp_add, iModp_add, i, i2);
        int iModp_montymul2 = modp_montymul(iModp_montymul, iModp_montymul, i, i2);
        int iModp_montymul3 = modp_montymul(iModp_montymul2, iModp_montymul2, i, i2);
        int iModp_montymul4 = modp_montymul(iModp_montymul3, iModp_montymul3, i, i2);
        int iModp_montymul5 = modp_montymul(iModp_montymul4, iModp_montymul4, i, i2);
        return (iModp_montymul5 + (i & (-(iModp_montymul5 & 1)))) >>> 1;
    }

    public int modp_Rx(int i, int i2, int i3, int i4) {
        int i5 = i - 1;
        int iModp_R = modp_R(i2);
        int i6 = 0;
        while (true) {
            int i7 = 1 << i6;
            if (i7 > i5) {
                return iModp_R;
            }
            if ((i7 & i5) != 0) {
                iModp_R = modp_montymul(iModp_R, i4, i2, i3);
            }
            i4 = modp_montymul(i4, i4, i2, i3);
            i6++;
        }
    }

    public int modp_add(int i, int i2, int i3) {
        int i4 = (i + i2) - i3;
        return i4 + ((-(i4 >>> 31)) & i3);
    }

    public int modp_div(int i, int i2, int i3, int i4, int i5) {
        int i6 = i3 - 2;
        for (int i7 = 30; i7 >= 0; i7--) {
            int iModp_montymul = modp_montymul(i5, i5, i3, i4);
            i5 = iModp_montymul ^ ((-(1 & (i6 >>> i7))) & (modp_montymul(iModp_montymul, i2, i3, i4) ^ iModp_montymul));
        }
        return modp_montymul(i, modp_montymul(i5, 1, i3, i4), i3, i4);
    }

    public void modp_iNTT2(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4, int i5) {
        modp_iNTT2_ext(iArr, i, 1, iArr2, i2, i3, i4, i5);
    }

    public void modp_iNTT2_ext(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6) {
        int i7;
        if (i4 == 0) {
            return;
        }
        int iMkn = mkn(i4);
        int i8 = 1;
        int i9 = iMkn;
        int i10 = 1;
        while (true) {
            i7 = 0;
            if (i9 <= i8) {
                break;
            }
            i9 >>= 1;
            int i11 = i10 << 1;
            int i12 = 0;
            int i13 = 0;
            while (i12 < i9) {
                int i14 = iArr2[i3 + i9 + i12];
                int i15 = i + (i13 * i2);
                int i16 = (i10 * i2) + i15;
                int i17 = 0;
                while (i17 < i10) {
                    int i18 = iArr[i15];
                    int i19 = i8;
                    int i20 = iArr[i16];
                    iArr[i15] = modp_add(i18, i20, i5);
                    iArr[i16] = modp_montymul(modp_sub(i18, i20, i5), i14, i5, i6);
                    i17++;
                    i15 += i2;
                    i16 += i2;
                    i8 = i19;
                }
                i12++;
                i13 += i11;
            }
            i10 = i11;
        }
        int i21 = i8 << (31 - i4);
        int i22 = i;
        while (i7 < iMkn) {
            iArr[i22] = modp_montymul(iArr[i22], i21, i5, i6);
            i7++;
            i22 += i2;
        }
    }

    public void modp_mkgm2(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4, int i5, int i6) {
        int iMkn = mkn(i3);
        int iModp_R2 = modp_R2(i5, i6);
        int iModp_montymul = modp_montymul(i4, iModp_R2, i5, i6);
        for (int i7 = i3; i7 < 10; i7++) {
            iModp_montymul = modp_montymul(iModp_montymul, iModp_montymul, i5, i6);
        }
        int iModp_div = modp_div(iModp_R2, iModp_montymul, i5, i6, modp_R(i5));
        int i8 = 10 - i3;
        int iModp_R = modp_R(i5);
        int iModp_montymul2 = iModp_R;
        for (int i9 = 0; i9 < iMkn; i9++) {
            short s = this.REV10[i9 << i8];
            iArr[i + s] = iModp_R;
            iArr2[i2 + s] = iModp_montymul2;
            iModp_R = modp_montymul(iModp_R, iModp_montymul, i5, i6);
            iModp_montymul2 = modp_montymul(iModp_montymul2, iModp_div, i5, i6);
        }
    }

    public int modp_montymul(int i, int i2, int i3, int i4) {
        long unsignedLong = toUnsignedLong(i) * toUnsignedLong(i2);
        int unsignedLong2 = ((int) ((unsignedLong + (((((long) i4) * unsignedLong) & toUnsignedLong(Integer.MAX_VALUE)) * ((long) i3))) >>> 31)) - i3;
        return unsignedLong2 + ((-(unsignedLong2 >>> 31)) & i3);
    }

    public int modp_ninv31(int i) {
        int i2 = 2 - i;
        int i3 = i2 * (2 - (i * i2));
        int i4 = i3 * (2 - (i * i3));
        int i5 = i4 * (2 - (i * i4));
        return (-(i5 * (2 - (i * i5)))) & Integer.MAX_VALUE;
    }

    public int modp_norm(int i, int i2) {
        return i - ((((i - ((i2 + 1) >>> 1)) >>> 31) - 1) & i2);
    }

    public void modp_poly_rec_res(int[] iArr, int i, int i2, int i3, int i4, int i5) {
        int i6 = 1 << (i2 - 1);
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = (i7 << 1) + i;
            iArr[i + i7] = modp_montymul(modp_montymul(iArr[i8], iArr[i8 + 1], i3, i4), i5, i3, i4);
        }
    }

    public int modp_set(int i, int i2) {
        return i + ((-(i >>> 31)) & i2);
    }

    public int modp_sub(int i, int i2, int i3) {
        int i4 = i - i2;
        return i4 + ((-(i4 >>> 31)) & i3);
    }

    public void poly_big_to_fp(FalconFPR[] falconFPRArr, int i, int[] iArr, int i2, int i3, int i4, int i5) {
        int iMkn = mkn(i5);
        if (i3 == 0) {
            for (int i6 = 0; i6 < iMkn; i6++) {
                falconFPRArr[i + i6] = this.fpr.fpr_zero;
            }
            return;
        }
        int i7 = i2;
        int i8 = 0;
        while (i8 < iMkn) {
            int i9 = -(iArr[(i7 + i3) - 1] >>> 30);
            int i10 = i9 >>> 1;
            int i11 = i9 & 1;
            FPREngine fPREngine = this.fpr;
            FalconFPR falconFPRFpr_add = fPREngine.fpr_zero;
            FalconFPR falconFPRFpr_mul = fPREngine.fpr_one;
            int i12 = 0;
            while (i12 < i3) {
                int i13 = (iArr[i7 + i12] ^ i10) + i11;
                i11 = i13 >>> 31;
                int i14 = i13 & Integer.MAX_VALUE;
                FPREngine fPREngine2 = this.fpr;
                falconFPRFpr_add = fPREngine2.fpr_add(falconFPRFpr_add, fPREngine2.fpr_mul(fPREngine2.fpr_of(i14 - ((i14 << 1) & i9)), falconFPRFpr_mul));
                i12++;
                FPREngine fPREngine3 = this.fpr;
                falconFPRFpr_mul = fPREngine3.fpr_mul(falconFPRFpr_mul, fPREngine3.fpr_ptwo31);
            }
            falconFPRArr[i + i8] = falconFPRFpr_add;
            i8++;
            i7 += i4;
        }
    }

    public int poly_big_to_small(byte[] bArr, int i, int[] iArr, int i2, int i3, int i4) {
        int iMkn = mkn(i4);
        for (int i5 = 0; i5 < iMkn; i5++) {
            int iZint_one_to_plain = zint_one_to_plain(iArr, i2 + i5);
            if (iZint_one_to_plain < (-i3) || iZint_one_to_plain > i3) {
                return 0;
            }
            bArr[i + i5] = (byte) iZint_one_to_plain;
        }
        return 1;
    }

    public void poly_small_mkgauss(SHAKE256 shake256, byte[] bArr, int i, int i2) {
        int iMkgauss;
        int iMkn = mkn(i2);
        int i3 = 0;
        for (int i4 = 0; i4 < iMkn; i4++) {
            while (true) {
                iMkgauss = mkgauss(shake256, i2);
                if (iMkgauss >= -127 && iMkgauss <= 127) {
                    if (i4 == iMkn - 1) {
                        if (((iMkgauss & 1) ^ i3) != 0) {
                            break;
                        }
                    } else {
                        i3 ^= iMkgauss & 1;
                        break;
                    }
                }
            }
            bArr[i + i4] = (byte) iMkgauss;
        }
    }

    public int poly_small_sqnorm(byte[] bArr, int i, int i2) {
        int iMkn = mkn(i2);
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < iMkn; i5++) {
            byte b = bArr[i + i5];
            i3 += b * b;
            i4 |= i3;
        }
        return (-(i4 >>> 31)) | i3;
    }

    public void poly_small_to_fp(FalconFPR[] falconFPRArr, int i, byte[] bArr, int i2, int i3) {
        int iMkn = mkn(i3);
        for (int i4 = 0; i4 < iMkn; i4++) {
            falconFPRArr[i + i4] = this.fpr.fpr_of(bArr[i2 + i4]);
        }
    }

    public void poly_sub_scaled(int[] iArr, int i, int i2, int i3, int[] iArr2, int i4, int i5, int i6, int[] iArr3, int i7, int i8, int i9, int i10) {
        int iMkn = mkn(i10);
        for (int i11 = 0; i11 < iMkn; i11++) {
            int i12 = i4;
            int i13 = -iArr3[i7 + i11];
            int i14 = i + (i11 * i3);
            for (int i15 = 0; i15 < iMkn; i15++) {
                zint_add_scaled_mul_small(iArr, i14, i2, iArr2, i12, i5, i13, i8, i9);
                if (i11 + i15 == iMkn - 1) {
                    i14 = i;
                    i13 = -i13;
                } else {
                    i14 += i3;
                }
                i12 += i6;
            }
        }
    }

    public void poly_sub_scaled_ntt(int[] iArr, int i, int i2, int i3, int[] iArr2, int i4, int i5, int i6, int[] iArr3, int i7, int i8, int i9, int i10, int[] iArr4, int i11) {
        int i12 = i5;
        int iMkn = mkn(i10);
        int i13 = i12 + 1;
        int iMkn2 = i11 + mkn(i10);
        int iMkn3 = iMkn2 + mkn(i10);
        int i14 = iMkn3 + (iMkn * i13);
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i15 = 0;
        int i16 = 0;
        while (i16 < i13) {
            int i17 = falconSmallPrimeArr[i16].p;
            int iModp_ninv31 = modp_ninv31(i17);
            int iModp_R2 = modp_R2(i17, iModp_ninv31);
            int iModp_Rx = modp_Rx(i12, i17, iModp_ninv31, iModp_R2);
            int i18 = i16;
            modp_mkgm2(iArr4, i11, iArr4, iMkn2, i10, falconSmallPrimeArr[i16].g, i17, iModp_ninv31);
            int i19 = iMkn2;
            for (int i20 = 0; i20 < iMkn; i20++) {
                iArr4[i14 + i20] = modp_set(iArr3[i7 + i20], i17);
            }
            int i21 = i14;
            modp_NTT2(iArr4, i21, iArr4, i11, i10, i17, iModp_ninv31);
            int i22 = i17;
            int i23 = iMkn3 + i18;
            int i24 = i4;
            int i25 = i23;
            int i26 = 0;
            while (i26 < iMkn) {
                int i27 = i22;
                int i28 = iModp_ninv31;
                int i29 = iModp_Rx;
                int iZint_mod_small_signed = zint_mod_small_signed(iArr2, i24, i5, i27, i28, iModp_R2, i29);
                i22 = i27;
                iArr4[i25] = iZint_mod_small_signed;
                i24 += i6;
                i25 += i13;
                iModp_Rx = i29;
                i26++;
                iModp_ninv31 = i28;
            }
            int i30 = i13;
            modp_NTT2_ext(iArr4, i23, i30, iArr4, i11, i10, i22, iModp_ninv31);
            int i31 = 0;
            while (i31 < iMkn) {
                iArr4[i23] = modp_montymul(modp_montymul(iArr4[i21 + i31], iArr4[i23], i22, iModp_ninv31), iModp_R2, i22, iModp_ninv31);
                i31++;
                i23 += i30;
            }
            modp_iNTT2_ext(iArr4, i23, i30, iArr4, i19, i10, i22, iModp_ninv31);
            i16 = i18 + 1;
            i13 = i30;
            i14 = i21;
            iMkn2 = i19;
            i12 = i5;
        }
        int i32 = i13;
        zint_rebuild_CRT(iArr4, iMkn3, i32, i32, iMkn, falconSmallPrimeArr, 1, iArr4, i14);
        int i33 = iMkn3;
        int i34 = i;
        while (i15 < iMkn) {
            int i35 = i32;
            zint_sub_scaled(iArr, i34, i2, iArr4, i33, i35, i8, i9);
            i32 = i35;
            i15++;
            i34 += i3;
            i33 += i32;
        }
    }

    public int solve_NTRU(int i, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, byte[] bArr4, int i5, int i6, int[] iArr, int i7) {
        byte[] bArr5;
        int i8;
        int iMkn = mkn(i);
        if (solve_NTRU_deepest(i, bArr3, i4, bArr4, i5, iArr, i7) == 0) {
            return 0;
        }
        int i9 = i;
        if (i9 > 2) {
            int i10 = i;
            while (true) {
                int i11 = i10 - 1;
                if (i10 <= 2) {
                    if (solve_NTRU_binary_depth1(i, bArr3, i4, bArr4, i5, iArr, i7) != 0 && solve_NTRU_binary_depth0(i, bArr3, i4, bArr4, i5, iArr, i7) != 0) {
                        break;
                    }
                    return 0;
                }
                if (solve_NTRU_intermediate(i, bArr3, i4, bArr4, i5, i11, iArr, i7) == 0) {
                    return 0;
                }
                i10 = i11;
            }
        } else {
            int i12 = i9;
            while (true) {
                int i13 = i12 - 1;
                if (i12 <= 0) {
                    break;
                }
                if (solve_NTRU_intermediate(i9, bArr3, i4, bArr4, i5, i13, iArr, i7) == 0) {
                    return 0;
                }
                i9 = i;
                i12 = i13;
            }
        }
        if (bArr2 == null) {
            bArr5 = new byte[iMkn];
            i8 = 0;
        } else {
            bArr5 = bArr2;
            i8 = i3;
        }
        if (poly_big_to_small(bArr, i2, iArr, i7, i6, i) != 0) {
            int i14 = i7 + iMkn;
            byte[] bArr6 = bArr5;
            int i15 = i8;
            if (poly_big_to_small(bArr6, i15, iArr, i14, i6, i) != 0) {
                int i16 = i14 + iMkn;
                int i17 = i16 + iMkn;
                int i18 = i17 + iMkn;
                FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
                int i19 = falconSmallPrimeArr[0].p;
                int iModp_ninv31 = modp_ninv31(i19);
                modp_mkgm2(iArr, i18, iArr, i7, i, falconSmallPrimeArr[0].g, i19, iModp_ninv31);
                for (int i20 = 0; i20 < iMkn; i20++) {
                    iArr[i7 + i20] = modp_set(bArr6[i15 + i20], i19);
                }
                for (int i21 = 0; i21 < iMkn; i21++) {
                    iArr[i14 + i21] = modp_set(bArr3[i4 + i21], i19);
                    iArr[i16 + i21] = modp_set(bArr4[i5 + i21], i19);
                    iArr[i17 + i21] = modp_set(bArr[i2 + i21], i19);
                }
                modp_NTT2(iArr, i14, iArr, i18, i, i19, iModp_ninv31);
                modp_NTT2(iArr, i16, iArr, i18, i, i19, iModp_ninv31);
                modp_NTT2(iArr, i17, iArr, i18, i, i19, iModp_ninv31);
                modp_NTT2(iArr, i7, iArr, i18, i, i19, iModp_ninv31);
                int iModp_montymul = modp_montymul(12289, 1, i19, iModp_ninv31);
                for (int i22 = 0; i22 < iMkn; i22++) {
                    if (modp_sub(modp_montymul(iArr[i14 + i22], iArr[i7 + i22], i19, iModp_ninv31), modp_montymul(iArr[i16 + i22], iArr[i17 + i22], i19, iModp_ninv31), i19) != iModp_montymul) {
                        return 0;
                    }
                }
                return 1;
            }
        }
        return 0;
    }

    public int solve_NTRU_binary_depth0(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int[] iArr, int i4) {
        int i5 = 1;
        int i6 = 1 << i;
        int i7 = i6 >> 1;
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i8 = 0;
        int i9 = falconSmallPrimeArr[0].p;
        int iModp_ninv31 = modp_ninv31(i9);
        int iModp_R2 = modp_R2(i9, iModp_ninv31);
        int i10 = i4 + i7;
        int i11 = i10 + i7;
        int i12 = i11 + i6;
        int i13 = i12 + i6;
        int i14 = i13 + i6;
        modp_mkgm2(iArr, i13, iArr, i14, i, falconSmallPrimeArr[0].g, i9, iModp_ninv31);
        for (int i15 = 0; i15 < i7; i15++) {
            int i16 = i4 + i15;
            iArr[i16] = modp_set(zint_one_to_plain(iArr, i16), i9);
            int i17 = i10 + i15;
            iArr[i17] = modp_set(zint_one_to_plain(iArr, i17), i9);
        }
        int i18 = i - 1;
        modp_NTT2(iArr, i4, iArr, i13, i18, i9, iModp_ninv31);
        modp_NTT2(iArr, i10, iArr, i13, i18, i9, iModp_ninv31);
        for (int i19 = 0; i19 < i6; i19++) {
            iArr[i11 + i19] = modp_set(bArr[i2 + i19], i9);
            iArr[i12 + i19] = modp_set(bArr2[i3 + i19], i9);
        }
        modp_NTT2(iArr, i11, iArr, i13, i, i9, iModp_ninv31);
        modp_NTT2(iArr, i12, iArr, i13, i, i9, iModp_ninv31);
        int i20 = 0;
        while (i20 < i6) {
            int i21 = i11 + i20;
            int i22 = iArr[i21];
            int i23 = i21 + 1;
            int i24 = iArr[i23];
            int i25 = i12 + i20;
            int i26 = i5;
            int i27 = iArr[i25];
            int i28 = i25 + 1;
            int i29 = i8;
            int i30 = iArr[i28];
            int i31 = i20 >> 1;
            int i32 = i20;
            int iModp_montymul = modp_montymul(iArr[i4 + i31], iModp_R2, i9, iModp_ninv31);
            int iModp_montymul2 = modp_montymul(iArr[i10 + i31], iModp_R2, i9, iModp_ninv31);
            iArr[i21] = modp_montymul(i30, iModp_montymul, i9, iModp_ninv31);
            iArr[i23] = modp_montymul(i27, iModp_montymul, i9, iModp_ninv31);
            iArr[i25] = modp_montymul(i24, iModp_montymul2, i9, iModp_ninv31);
            iArr[i28] = modp_montymul(i22, iModp_montymul2, i9, iModp_ninv31);
            i20 = i32 + 2;
            i5 = i26;
            i8 = i29;
        }
        int i33 = i5;
        int i34 = i8;
        modp_iNTT2(iArr, i11, iArr, i14, i, i9, iModp_ninv31);
        modp_iNTT2(iArr, i12, iArr, i14, i, i9, iModp_ninv31);
        int i35 = i4 + i6;
        int i36 = i35 + i6;
        System.arraycopy(iArr, i11, iArr, i4, i6 * 2);
        int i37 = i36 + i6;
        int i38 = i37 + i6;
        int i39 = i38 + i6;
        int i40 = i39 + i6;
        modp_mkgm2(iArr, i36, iArr, i37, i, FalconSmallPrimeList.PRIMES[i34].g, i9, iModp_ninv31);
        modp_NTT2(iArr, i4, iArr, i36, i, i9, iModp_ninv31);
        modp_NTT2(iArr, i35, iArr, i36, i, i9, iModp_ninv31);
        int iModp_set = modp_set(bArr[i2], i9);
        iArr[i40] = iModp_set;
        iArr[i39] = iModp_set;
        for (int i41 = i33; i41 < i6; i41++) {
            int i42 = i2 + i41;
            iArr[i39 + i41] = modp_set(bArr[i42], i9);
            iArr[(i40 + i6) - i41] = modp_set(-bArr[i42], i9);
        }
        modp_NTT2(iArr, i39, iArr, i36, i, i9, iModp_ninv31);
        modp_NTT2(iArr, i40, iArr, i36, i, i9, iModp_ninv31);
        for (int i43 = i34; i43 < i6; i43++) {
            int iModp_montymul3 = modp_montymul(iArr[i40 + i43], iModp_R2, i9, iModp_ninv31);
            iArr[i37 + i43] = modp_montymul(iModp_montymul3, iArr[i4 + i43], i9, iModp_ninv31);
            iArr[i38 + i43] = modp_montymul(iModp_montymul3, iArr[i39 + i43], i9, iModp_ninv31);
        }
        int iModp_set2 = modp_set(bArr2[i3], i9);
        iArr[i40] = iModp_set2;
        iArr[i39] = iModp_set2;
        for (int i44 = i33; i44 < i6; i44++) {
            int i45 = i3 + i44;
            iArr[i39 + i44] = modp_set(bArr2[i45], i9);
            iArr[(i40 + i6) - i44] = modp_set(-bArr2[i45], i9);
        }
        modp_NTT2(iArr, i39, iArr, i36, i, i9, iModp_ninv31);
        modp_NTT2(iArr, i40, iArr, i36, i, i9, iModp_ninv31);
        for (int i46 = i34; i46 < i6; i46++) {
            int iModp_montymul4 = modp_montymul(iArr[i40 + i46], iModp_R2, i9, iModp_ninv31);
            int i47 = i37 + i46;
            iArr[i47] = modp_add(iArr[i47], modp_montymul(iModp_montymul4, iArr[i35 + i46], i9, iModp_ninv31), i9);
            int i48 = i38 + i46;
            iArr[i48] = modp_add(iArr[i48], modp_montymul(iModp_montymul4, iArr[i39 + i46], i9, iModp_ninv31), i9);
        }
        int i49 = iModp_ninv31;
        modp_mkgm2(iArr, i36, iArr, i39, i, FalconSmallPrimeList.PRIMES[i34].g, i9, i49);
        modp_iNTT2(iArr, i37, iArr, i39, i, i9, i49);
        modp_iNTT2(iArr, i38, iArr, i39, i, i9, i49);
        for (int i50 = i34; i50 < i6; i50++) {
            int i51 = i37 + i50;
            iArr[i36 + i50] = modp_norm(iArr[i51], i9);
            iArr[i51] = modp_norm(iArr[i38 + i50], i9);
        }
        FalconFPR[] falconFPRArr = new FalconFPR[i6 * 3];
        int i52 = i6 + i6;
        for (int i53 = i34; i53 < i6; i53++) {
            falconFPRArr[i52 + i53] = this.fpr.fpr_of(iArr[i37 + i53]);
        }
        this.fft.FFT(falconFPRArr, i52, i);
        System.arraycopy(falconFPRArr, i52, falconFPRArr, i6, i7);
        int i54 = i6 + i7;
        int i55 = i34;
        while (i55 < i6) {
            falconFPRArr[i54 + i55] = this.fpr.fpr_of(iArr[i36 + i55]);
            i55++;
            i35 = i35;
            i49 = i49;
        }
        int i56 = i49;
        int i57 = i35;
        this.fft.FFT(falconFPRArr, i54, i);
        this.fft.poly_div_autoadj_fft(falconFPRArr, i54, falconFPRArr, i6, i);
        this.fft.iFFT(falconFPRArr, i54, i);
        for (int i58 = i34; i58 < i6; i58++) {
            iArr[i36 + i58] = modp_set((int) this.fpr.fpr_rint(falconFPRArr[i54 + i58]), i9);
        }
        modp_mkgm2(iArr, i37, iArr, i38, i, FalconSmallPrimeList.PRIMES[i34].g, i9, i56);
        for (int i59 = i34; i59 < i6; i59++) {
            iArr[i39 + i59] = modp_set(bArr[i2 + i59], i9);
            iArr[i40 + i59] = modp_set(bArr2[i3 + i59], i9);
        }
        modp_NTT2(iArr, i36, iArr, i37, i, i9, i56);
        modp_NTT2(iArr, i39, iArr, i37, i, i9, i56);
        modp_NTT2(iArr, i40, iArr, i37, i, i9, i56);
        for (int i60 = i34; i60 < i6; i60++) {
            int iModp_montymul5 = modp_montymul(iArr[i36 + i60], iModp_R2, i9, i56);
            int i61 = i4 + i60;
            iArr[i61] = modp_sub(iArr[i61], modp_montymul(iModp_montymul5, iArr[i39 + i60], i9, i56), i9);
            int i62 = i57 + i60;
            iArr[i62] = modp_sub(iArr[i62], modp_montymul(iModp_montymul5, iArr[i40 + i60], i9, i56), i9);
        }
        modp_iNTT2(iArr, i4, iArr, i38, i, i9, i56);
        modp_iNTT2(iArr, i57, iArr, i38, i, i9, i56);
        for (int i63 = i34; i63 < i6; i63++) {
            int i64 = i4 + i63;
            iArr[i64] = modp_norm(iArr[i64], i9);
            int i65 = i57 + i63;
            iArr[i65] = modp_norm(iArr[i65], i9);
        }
        return i33;
    }

    public int solve_NTRU_binary_depth1(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int[] iArr, int i4) {
        int i5;
        FalconKeyGen falconKeyGen = this;
        int i6 = 1;
        int i7 = 1 << i;
        int i8 = i - 1;
        int i9 = 1 << i8;
        int i10 = i9 >> 1;
        int[] iArr2 = falconKeyGen.MAX_BL_SMALL;
        int i11 = iArr2[1];
        int i12 = iArr2[2];
        int i13 = falconKeyGen.MAX_BL_LARGE[1];
        int i14 = i12 * i10;
        int i15 = i4 + i14;
        int i16 = i15 + i14;
        int i17 = i13 * i9;
        int i18 = i16 + i17;
        int i19 = 0;
        int i20 = 0;
        while (i20 < i13) {
            int i21 = FalconSmallPrimeList.PRIMES[i20].p;
            int i22 = i19;
            int iModp_ninv31 = falconKeyGen.modp_ninv31(i21);
            int i23 = i20;
            int iModp_R2 = falconKeyGen.modp_R2(i21, iModp_ninv31);
            int i24 = i18;
            int iModp_Rx = falconKeyGen.modp_Rx(i12, i21, iModp_ninv31, iModp_R2);
            int i25 = i16 + i23;
            int i26 = i24 + i23;
            int i27 = i17;
            int i28 = i4;
            int i29 = i6;
            int i30 = i15;
            int i31 = i22;
            while (i31 < i10) {
                int i32 = i28;
                iArr[i25] = falconKeyGen.zint_mod_small_signed(iArr, i28, i12, i21, iModp_ninv31, iModp_R2, iModp_Rx);
                falconKeyGen = this;
                int i33 = i30;
                iArr[i26] = falconKeyGen.zint_mod_small_signed(iArr, i33, i12, i21, iModp_ninv31, iModp_R2, iModp_Rx);
                i25 += i13;
                i26 += i13;
                i30 = i33 + i12;
                i28 = i32 + i12;
                i24 = i24;
                i10 = i10;
                i23 = i23;
                i16 = i16;
                i31++;
                i27 = i27;
                i11 = i11;
            }
            i20 = i23 + 1;
            i16 = i16;
            i18 = i24;
            i17 = i27;
            i11 = i11;
            i10 = i10;
            i6 = i29;
            i19 = 0;
        }
        int i34 = i6;
        int i35 = i10;
        int i36 = i11;
        int i37 = i16;
        int i38 = i17;
        int[] iArr3 = iArr;
        System.arraycopy(iArr3, i37, iArr3, i4, i38);
        int i39 = i4 + i38;
        System.arraycopy(iArr3, i18, iArr3, i39, i38);
        int i40 = i39 + i38;
        int i41 = i36 * i9;
        int i42 = i40 + i41;
        int i43 = i42 + i41;
        int i44 = 0;
        while (i44 < i13) {
            FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
            int i45 = falconSmallPrimeArr[i44].p;
            int iModp_ninv32 = falconKeyGen.modp_ninv31(i45);
            int iModp_R3 = falconKeyGen.modp_R2(i45, iModp_ninv32);
            int i46 = i44;
            int i47 = i43;
            int i48 = i47 + i7;
            int i49 = i48 + i9;
            int i50 = i49 + i7;
            int i51 = i13;
            falconKeyGen.modp_mkgm2(iArr3, i47, iArr, i48, i, falconSmallPrimeArr[i46].g, i45, iModp_ninv32);
            for (int i52 = 0; i52 < i7; i52++) {
                i5 = i45;
                iArr[i49 + i52] = falconKeyGen.modp_set(bArr[i2 + i52], i5);
                iArr[i50 + i52] = falconKeyGen.modp_set(bArr2[i3 + i52], i5);
            }
            i5 = i45;
            falconKeyGen.modp_NTT2(iArr, i49, iArr, i47, i, i5, iModp_ninv32);
            modp_NTT2(iArr, i50, iArr, i47, i, i5, iModp_ninv32);
            int i53 = i47;
            int i54 = i;
            while (i54 > i8) {
                int i55 = i5;
                int i56 = iModp_R3;
                int i57 = i49;
                modp_poly_rec_res(iArr, i57, i54, i55, iModp_ninv32, i56);
                modp_poly_rec_res(iArr, i50, i54, i55, iModp_ninv32, i56);
                i5 = i55;
                i54--;
                i49 = i57;
                i53 = i53;
                iModp_R3 = i56;
            }
            int i58 = iModp_R3;
            int i59 = i53;
            int i60 = i59 + i9;
            System.arraycopy(iArr, i48, iArr, i60, i9);
            int i61 = i60 + i9;
            System.arraycopy(iArr, i49, iArr, i61, i9);
            int i62 = i61 + i9;
            System.arraycopy(iArr, i50, iArr, i62, i9);
            int i63 = i62 + i9;
            int i64 = i63 + i35;
            int i65 = i4 + i46;
            int i66 = i39 + i46;
            int i67 = i35;
            int i68 = i39;
            int i69 = i65;
            int i70 = i66;
            int i71 = 0;
            while (i71 < i67) {
                iArr[i63 + i71] = iArr[i69];
                iArr[i64 + i71] = iArr[i70];
                i71++;
                i69 += i51;
                i70 += i51;
            }
            int i72 = i - 2;
            modp_NTT2(iArr, i63, iArr, i59, i72, i5, iModp_ninv32);
            int i73 = i64;
            modp_NTT2(iArr, i73, iArr, i59, i72, i5, iModp_ninv32);
            int i74 = i65;
            int i75 = i66;
            int i76 = 0;
            while (i76 < i67) {
                int i77 = i76 << 1;
                int i78 = i61 + i77;
                int i79 = i74;
                int i80 = iArr[i78];
                int i81 = i73;
                int i82 = iArr[i78 + 1];
                int i83 = i62 + i77;
                int i84 = i75;
                int i85 = iArr[i83];
                int i86 = iArr[i83 + 1];
                int i87 = i76;
                int iModp_montymul = modp_montymul(iArr[i63 + i76], i58, i5, iModp_ninv32);
                int i88 = i7;
                int iModp_montymul2 = modp_montymul(iArr[i81 + i87], i58, i5, iModp_ninv32);
                iArr[i79] = modp_montymul(i86, iModp_montymul, i5, iModp_ninv32);
                iArr[i79 + i51] = modp_montymul(i85, iModp_montymul, i5, iModp_ninv32);
                iArr[i84] = modp_montymul(i82, iModp_montymul2, i5, iModp_ninv32);
                iArr[i84 + i51] = modp_montymul(i80, iModp_montymul2, i5, iModp_ninv32);
                i76 = i87 + 1;
                int i89 = i51 << 1;
                i75 = i84 + i89;
                i74 = i79 + i89;
                i73 = i81;
                i7 = i88;
            }
            int i90 = i7;
            int i91 = i5;
            int i92 = i8;
            modp_iNTT2_ext(iArr, i65, i51, iArr, i60, i92, i91, iModp_ninv32);
            modp_iNTT2_ext(iArr, i66, i51, iArr, i60, i92, i91, iModp_ninv32);
            int i93 = i36;
            if (i46 < i93) {
                modp_iNTT2(iArr, i61, iArr, i60, i92, i91, iModp_ninv32);
                modp_iNTT2(iArr, i62, iArr, i60, i92, i91, iModp_ninv32);
                int i94 = i40 + i46;
                int i95 = i42 + i46;
                int i96 = 0;
                while (i96 < i9) {
                    iArr[i94] = iArr[i61 + i96];
                    iArr[i95] = iArr[i62 + i96];
                    i96++;
                    i94 += i93;
                    i95 += i93;
                }
            }
            i44 = i46 + 1;
            i35 = i67;
            i39 = i68;
            falconKeyGen = this;
            iArr3 = iArr;
            i36 = i93;
            i43 = i59;
            i13 = i51;
            i8 = i92;
            i7 = i90;
        }
        int i97 = i43;
        int i98 = i13;
        int i99 = i8;
        int i100 = i36;
        int i101 = i9 << 1;
        FalconSmallPrime[] falconSmallPrimeArr2 = FalconSmallPrimeList.PRIMES;
        zint_rebuild_CRT(iArr, i4, i98, i98, i101, falconSmallPrimeArr2, 1, iArr, i97);
        zint_rebuild_CRT(iArr, i40, i100, i100, i101, falconSmallPrimeArr2, 1, iArr, i97);
        FalconFPR[] falconFPRArr = new FalconFPR[i9];
        FalconFPR[] falconFPRArr2 = new FalconFPR[i9];
        poly_big_to_fp(falconFPRArr, 0, iArr, i4, i98, i98, i99);
        poly_big_to_fp(falconFPRArr2, 0, iArr, i39, i98, i98, i99);
        System.arraycopy(iArr, i40, iArr, i4, i100 * 2 * i9);
        FalconFPR[] falconFPRArr3 = new FalconFPR[i9];
        FalconFPR[] falconFPRArr4 = new FalconFPR[i9];
        poly_big_to_fp(falconFPRArr3, 0, iArr, i4, i100, i100, i99);
        poly_big_to_fp(falconFPRArr4, 0, iArr, i4 + i41, i100, i100, i99);
        this.fft.FFT(falconFPRArr, 0, i99);
        this.fft.FFT(falconFPRArr2, 0, i99);
        this.fft.FFT(falconFPRArr3, 0, i99);
        this.fft.FFT(falconFPRArr4, 0, i99);
        FalconFPR[] falconFPRArr5 = new FalconFPR[i9];
        FalconFPR[] falconFPRArr6 = new FalconFPR[i35];
        this.fft.poly_add_muladj_fft(falconFPRArr5, 0, falconFPRArr, 0, falconFPRArr2, 0, falconFPRArr3, 0, falconFPRArr4, 0, i99);
        this.fft.poly_invnorm2_fft(falconFPRArr6, 0, falconFPRArr3, 0, falconFPRArr4, 0, i99);
        this.fft.poly_mul_autoadj_fft(falconFPRArr5, 0, falconFPRArr6, 0, i99);
        this.fft.iFFT(falconFPRArr5, 0, i99);
        for (int i102 = 0; i102 < i9; i102++) {
            FalconFPR falconFPR = falconFPRArr5[i102];
            FPREngine fPREngine = this.fpr;
            if (!fPREngine.fpr_lt(falconFPR, fPREngine.fpr_ptwo63m1)) {
                return 0;
            }
            FPREngine fPREngine2 = this.fpr;
            if (!fPREngine2.fpr_lt(fPREngine2.fpr_mtwo63m1, falconFPR)) {
                return 0;
            }
            FPREngine fPREngine3 = this.fpr;
            falconFPRArr5[i102] = fPREngine3.fpr_of(fPREngine3.fpr_rint(falconFPR));
        }
        this.fft.FFT(falconFPRArr5, 0, i99);
        this.fft.poly_mul_fft(falconFPRArr3, 0, falconFPRArr5, 0, i99);
        this.fft.poly_mul_fft(falconFPRArr4, 0, falconFPRArr5, 0, i99);
        this.fft.poly_sub(falconFPRArr, 0, falconFPRArr3, 0, i99);
        this.fft.poly_sub(falconFPRArr2, 0, falconFPRArr4, 0, i99);
        this.fft.iFFT(falconFPRArr, 0, i99);
        this.fft.iFFT(falconFPRArr2, 0, i99);
        int i103 = i4 + i9;
        for (int i104 = 0; i104 < i9; i104++) {
            iArr[i4 + i104] = (int) this.fpr.fpr_rint(falconFPRArr[i104]);
            iArr[i103 + i104] = (int) this.fpr.fpr_rint(falconFPRArr2[i104]);
        }
        return i34;
    }

    public int solve_NTRU_deepest(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int[] iArr, int i4) {
        int i5 = this.MAX_BL_SMALL[i];
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i6 = i4 + i5;
        int i7 = i6 + i5;
        int i8 = i7 + i5;
        int i9 = i8 + i5;
        make_fg(iArr, i7, bArr, i2, bArr2, i3, i, i, 0);
        zint_rebuild_CRT(iArr, i7, i5, i5, 2, falconSmallPrimeArr, 0, iArr, i9);
        return (zint_bezout(iArr, i6, iArr, i4, iArr, i7, iArr, i8, i5, iArr, i9) != 0 && zint_mul_small(iArr, i4, i5, 12289) == 0 && zint_mul_small(iArr, i6, i5, 12289) == 0) ? 1 : 0;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0493 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x0495  */
    /* JADX WARN: Code duplicated, block: B:76:0x049f  */
    /* JADX WARN: Code duplicated, block: B:78:0x04ad A[LOOP:11: B:77:0x04ab->B:78:0x04ad, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:81:0x04c2 A[LOOP:12: B:80:0x04c0->B:81:0x04c2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:83:0x04cf  */
    /* JADX WARN: Code duplicated, block: B:87:0x04da A[LOOP:13: B:85:0x04d6->B:87:0x04da, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:89:0x04e3  */
    /* JADX WARN: Code duplicated, block: B:91:0x04e9  */
    /* JADX WARN: Code duplicated, block: B:92:0x04ec  */
    public int solve_NTRU_intermediate(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int i4, int[] iArr, int i5) {
        FalconFPR falconFPRFpr_sqr;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int[] iArr2;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        FalconSmallPrime[] falconSmallPrimeArr;
        int i27;
        int i28;
        int i29;
        int i30;
        int i31;
        int i32;
        int i33;
        int i34;
        int i35;
        int i36;
        int i37;
        FalconKeyGen falconKeyGen = this;
        int i38 = i - i4;
        int i39 = 1 << i38;
        int i40 = i39 >> 1;
        int[] iArr3 = falconKeyGen.MAX_BL_SMALL;
        int i41 = iArr3[i4];
        int i42 = iArr3[i4 + 1];
        int i43 = falconKeyGen.MAX_BL_LARGE[i4];
        FalconSmallPrime[] falconSmallPrimeArr2 = FalconSmallPrimeList.PRIMES;
        int i44 = i42 * i40;
        int i45 = i5 + i44 + i44;
        int[] iArr4 = iArr;
        int i46 = i39;
        int i47 = i43;
        falconKeyGen.make_fg(iArr4, i45, bArr, i2, bArr2, i3, i, i4, 1);
        int i48 = i46 * i47;
        int i49 = i5 + i48;
        int i50 = i49 + i48;
        System.arraycopy(iArr4, i45, iArr4, i50, i46 * 2 * i41);
        int i51 = i41 * i46;
        int i52 = i50 + i51;
        int i53 = i52 + i51;
        System.arraycopy(iArr4, i5, iArr4, i53, i40 * 2 * i42);
        int i54 = i53 + i44;
        int i55 = 0;
        int i56 = 0;
        while (i56 < i47) {
            int i57 = falconSmallPrimeArr2[i56].p;
            int iModp_ninv31 = falconKeyGen.modp_ninv31(i57);
            int iModp_R2 = falconKeyGen.modp_R2(i57, iModp_ninv31);
            int iModp_Rx = falconKeyGen.modp_Rx(i42, i57, iModp_ninv31, iModp_R2);
            int i58 = i5 + i56;
            int i59 = i49 + i56;
            int i60 = i55;
            int i61 = i50;
            int i62 = i54;
            int i63 = i60;
            int i64 = i53;
            while (true) {
                i35 = i60;
                i36 = i56;
                i37 = i42;
                if (i63 < i40) {
                    int i65 = i64;
                    iArr[i58] = falconKeyGen.zint_mod_small_signed(iArr4, i64, i37, i57, iModp_ninv31, iModp_R2, iModp_Rx);
                    falconKeyGen = this;
                    int i66 = i62;
                    iArr[i59] = falconKeyGen.zint_mod_small_signed(iArr, i66, i37, i57, iModp_ninv31, iModp_R2, iModp_Rx);
                    i63++;
                    int i67 = i65 + i37;
                    i62 = i66 + i37;
                    i58 += i47;
                    i59 += i47;
                    i42 = i37;
                    i56 = i36;
                    i60 = i35;
                    i64 = i67;
                    iArr4 = iArr;
                }
            }
            i50 = i61;
            i55 = i35;
            i42 = i37;
            i56 = i36 + 1;
            iArr4 = iArr;
        }
        int i68 = i50;
        int i69 = i55;
        while (i69 < i47) {
            int i70 = falconSmallPrimeArr2[i69].p;
            int iModp_ninv32 = falconKeyGen.modp_ninv31(i70);
            int iModp_R3 = falconKeyGen.modp_R2(i70, iModp_ninv32);
            if (i69 == i41) {
                i26 = i41;
                FalconSmallPrime[] falconSmallPrimeArr3 = falconSmallPrimeArr2;
                int i71 = i46;
                int i72 = i68;
                falconKeyGen.zint_rebuild_CRT(iArr, i72, i26, i41, i71, falconSmallPrimeArr3, 1, iArr, i53);
                i28 = i72;
                zint_rebuild_CRT(iArr, i52, i26, i26, i71, falconSmallPrimeArr3, 1, iArr, i53);
                falconSmallPrimeArr = falconSmallPrimeArr3;
                i27 = i71;
            } else {
                i26 = i41;
                falconSmallPrimeArr = falconSmallPrimeArr2;
                i27 = i46;
                i28 = i68;
            }
            int i73 = i53 + i27;
            int i74 = i73 + i27;
            int i75 = i74 + i27;
            int i76 = i53;
            int i77 = i38;
            int i78 = iModp_ninv32;
            int i79 = i26;
            int i80 = i70;
            modp_mkgm2(iArr, i76, iArr, i73, i77, falconSmallPrimeArr[i69].g, i80, i78);
            if (i69 < i79) {
                int i81 = i28 + i69;
                int i82 = i52 + i69;
                int i83 = i81;
                int i84 = i82;
                int i85 = 0;
                while (i85 < i27) {
                    iArr[i74 + i85] = iArr[i83];
                    iArr[i75 + i85] = iArr[i84];
                    i85++;
                    i83 += i79;
                    i84 += i79;
                }
                modp_iNTT2_ext(iArr, i81, i79, iArr, i73, i77, i80, i78);
                modp_iNTT2_ext(iArr, i82, i79, iArr, i73, i77, i80, i78);
                i29 = i73;
                i34 = i78;
                i30 = i76;
                i31 = i74;
                i32 = i75;
                i33 = i77;
            } else {
                FalconKeyGen falconKeyGen2 = this;
                i29 = i73;
                int i86 = i80;
                int iModp_Rx2 = falconKeyGen2.modp_Rx(i79, i86, i78, iModp_R3);
                int i87 = i28;
                int i88 = i52;
                int i89 = 0;
                while (i89 < i27) {
                    int i90 = i86;
                    int i91 = i78;
                    int i92 = iModp_R3;
                    int i93 = i89;
                    int iZint_mod_small_signed = falconKeyGen2.zint_mod_small_signed(iArr, i87, i79, i90, i91, i92, iModp_Rx2);
                    int i94 = i87;
                    int i95 = iModp_Rx2;
                    iArr[i74 + i89] = iZint_mod_small_signed;
                    int i96 = i88;
                    iArr[i75 + i93] = zint_mod_small_signed(iArr, i96, i79, i90, i91, i92, i95);
                    i89 = i93 + 1;
                    i88 = i96 + i79;
                    i87 = i94 + i79;
                    i78 = i91;
                    iModp_R3 = i92;
                    i86 = i90;
                    iModp_Rx2 = i95;
                    falconKeyGen2 = this;
                }
                int i97 = i86;
                int i98 = i78;
                i30 = i76;
                i79 = i79;
                modp_NTT2(iArr, i74, iArr, i30, i77, i97, i98);
                i31 = i74;
                modp_NTT2(iArr, i75, iArr, i30, i77, i97, i98);
                i32 = i75;
                i33 = i77;
                i34 = i98;
                i80 = i97;
            }
            int i99 = i32 + i27;
            int i100 = i99 + i40;
            int i101 = i5 + i69;
            int i102 = i49 + i69;
            int i103 = i101;
            int i104 = i102;
            int i105 = 0;
            while (i105 < i40) {
                iArr[i99 + i105] = iArr[i103];
                iArr[i100 + i105] = iArr[i104];
                i105++;
                i103 += i47;
                i104 += i47;
            }
            int i106 = i80;
            int i107 = i34;
            int i108 = i33 - 1;
            modp_NTT2(iArr, i99, iArr, i30, i108, i106, i107);
            int i109 = i100;
            modp_NTT2(iArr, i109, iArr, i30, i108, i106, i107);
            int i110 = i27;
            i53 = i30;
            int i111 = i101;
            int i112 = i102;
            int i113 = 0;
            while (i113 < i40) {
                int i114 = i113 << 1;
                int i115 = i31 + i114;
                int i116 = i113;
                int i117 = iArr[i115];
                int i118 = i109;
                int i119 = iArr[i115 + 1];
                int i120 = i32 + i114;
                int i121 = i111;
                int i122 = iArr[i120];
                int i123 = iArr[i120 + 1];
                int i124 = i112;
                int iModp_montymul = modp_montymul(iArr[i99 + i116], iModp_R3, i106, i107);
                int i125 = i33;
                int iModp_montymul2 = modp_montymul(iArr[i118 + i116], iModp_R3, i106, i107);
                iArr[i121] = modp_montymul(i123, iModp_montymul, i106, i107);
                iArr[i121 + i47] = modp_montymul(i122, iModp_montymul, i106, i107);
                iArr[i124] = modp_montymul(i119, iModp_montymul2, i106, i107);
                iArr[i124 + i47] = modp_montymul(i117, iModp_montymul2, i106, i107);
                i113 = i116 + 1;
                int i126 = i47 << 1;
                i111 = i121 + i126;
                i112 = i124 + i126;
                i109 = i118;
                i33 = i125;
            }
            int i127 = i33;
            int i128 = i29;
            modp_iNTT2_ext(iArr, i101, i47, iArr, i128, i127, i106, i107);
            falconKeyGen = this;
            falconKeyGen.modp_iNTT2_ext(iArr, i102, i47, iArr, i128, i127, i106, i107);
            i69++;
            i41 = i79;
            i68 = i28;
            falconSmallPrimeArr2 = falconSmallPrimeArr;
            i46 = i110;
            i38 = i127;
        }
        int i129 = i38;
        int i130 = i41;
        FalconSmallPrime[] falconSmallPrimeArr4 = falconSmallPrimeArr2;
        int i131 = i46;
        int i132 = i68;
        int i133 = i4;
        zint_rebuild_CRT(iArr, i5, i47, i47, i131, falconSmallPrimeArr4, 1, iArr, i53);
        zint_rebuild_CRT(iArr, i49, i47, i47, i131, falconSmallPrimeArr4, 1, iArr, i53);
        int i134 = i49;
        int i135 = i131;
        FalconFPR[] falconFPRArr = new FalconFPR[i135];
        FalconFPR[] falconFPRArr2 = new FalconFPR[i135];
        FalconFPR[] falconFPRArr3 = new FalconFPR[i135];
        FalconFPR[] falconFPRArr4 = new FalconFPR[i40];
        FalconFPR[] falconFPRArr5 = new FalconFPR[i135];
        int i136 = i53;
        int[] iArr5 = new int[i135];
        int i137 = i130 > 10 ? 10 : i130;
        int i138 = (i132 + i130) - i137;
        FalconFPR[] falconFPRArr6 = falconFPRArr;
        poly_big_to_fp(falconFPRArr2, 0, iArr, i138, i137, i130, i129);
        int i139 = i137;
        poly_big_to_fp(falconFPRArr3, 0, iArr, (i52 + i130) - i139, i139, i130, i129);
        int i140 = i130;
        int i141 = (i140 - i139) * 31;
        int i142 = this.bitlength_avg[i133];
        int i143 = this.bitlength_std[i133];
        int i144 = i142 - (i143 * 6);
        int i145 = i142 + (i143 * 6);
        this.fft.FFT(falconFPRArr2, 0, i129);
        this.fft.FFT(falconFPRArr3, 0, i129);
        FalconKeyGen falconKeyGen3 = this;
        this.fft.poly_invnorm2_fft(falconFPRArr4, 0, falconFPRArr2, 0, falconFPRArr3, 0, i129);
        FalconFPR[] falconFPRArr7 = falconFPRArr2;
        FalconFPR[] falconFPRArr8 = falconFPRArr3;
        int i146 = i129;
        falconKeyGen3.fft.poly_adj_fft(falconFPRArr7, 0, i146);
        falconKeyGen3.fft.poly_adj_fft(falconFPRArr8, 0, i146);
        int i147 = i47 * 31;
        int i148 = i147 - i144;
        int i149 = i47;
        int i150 = 10;
        while (true) {
            int i151 = i146;
            int i152 = i149 > i150 ? i150 : i149;
            int i153 = i150;
            FalconKeyGen falconKeyGen4 = falconKeyGen3;
            FalconFPR[] falconFPRArr9 = falconFPRArr7;
            int i154 = i47;
            FalconFPR[] falconFPRArr10 = falconFPRArr8;
            FalconFPR[] falconFPRArr11 = falconFPRArr5;
            int i155 = i147;
            falconKeyGen4.poly_big_to_fp(falconFPRArr11, 0, iArr, (i5 + i149) - i152, i152, i154, i151);
            int i156 = i152;
            FalconFPR[] falconFPRArr12 = falconFPRArr6;
            poly_big_to_fp(falconFPRArr12, 0, iArr, (i134 + i149) - i156, i156, i154, i151);
            this.fft.FFT(falconFPRArr11, 0, i151);
            this.fft.FFT(falconFPRArr12, 0, i151);
            this.fft.poly_mul_fft(falconFPRArr11, 0, falconFPRArr9, 0, i151);
            this.fft.poly_mul_fft(falconFPRArr12, 0, falconFPRArr10, 0, i151);
            this.fft.poly_add(falconFPRArr12, 0, falconFPRArr11, 0, i151);
            this.fft.poly_mul_autoadj_fft(falconFPRArr12, 0, falconFPRArr4, 0, i151);
            this.fft.iFFT(falconFPRArr12, 0, i151);
            int i157 = (i148 - ((i149 - i152) * 31)) + i141;
            FPREngine fPREngine = this.fpr;
            if (i157 < 0) {
                i157 = -i157;
                falconFPRFpr_sqr = fPREngine.fpr_two;
            } else {
                falconFPRFpr_sqr = fPREngine.fpr_onehalf;
            }
            FalconFPR falconFPRFpr_mul = this.fpr.fpr_one;
            while (i157 != 0) {
                if ((i157 & 1) != 0) {
                    falconFPRFpr_mul = this.fpr.fpr_mul(falconFPRFpr_mul, falconFPRFpr_sqr);
                }
                i157 >>= 1;
                falconFPRFpr_sqr = this.fpr.fpr_sqr(falconFPRFpr_sqr);
            }
            for (int i158 = 0; i158 < i135; i158++) {
                FalconFPR falconFPRFpr_mul2 = this.fpr.fpr_mul(falconFPRArr12[i158], falconFPRFpr_mul);
                FPREngine fPREngine2 = this.fpr;
                if (!fPREngine2.fpr_lt(fPREngine2.fpr_mtwo31m1, falconFPRFpr_mul2)) {
                    return 0;
                }
                FPREngine fPREngine3 = this.fpr;
                if (!fPREngine3.fpr_lt(falconFPRFpr_mul2, fPREngine3.fpr_ptwo31m1)) {
                    return 0;
                }
                iArr5[i158] = (int) this.fpr.fpr_rint(falconFPRFpr_mul2);
            }
            int i159 = i148 / 31;
            int i160 = i149;
            int i161 = i148 % 31;
            if (i133 <= 4) {
                int i162 = i132;
                i9 = i153;
                i6 = i154;
                int i163 = i140;
                i7 = i135;
                poly_sub_scaled_ntt(iArr, i5, i160, i6, iArr, i162, i163, i140, iArr5, 0, i159, i161, i151, iArr, i136);
                i8 = i162;
                i10 = i151;
                i11 = i134;
                iArr5 = iArr5;
                i12 = i163;
                i13 = i160;
                iArr2 = iArr;
                poly_sub_scaled_ntt(iArr2, i11, i13, i6, iArr, i52, i12, i163, iArr5, 0, i159, i161, i10, iArr, i136);
            } else {
                i6 = i154;
                i7 = i135;
                int i164 = i140;
                i8 = i132;
                i9 = i153;
                poly_sub_scaled(iArr, i5, i160, i6, iArr, i8, i164, i164, iArr5, 0, i159, i161, i151);
                i10 = i151;
                i11 = i134;
                i12 = i164;
                i13 = i160;
                iArr2 = iArr;
                poly_sub_scaled(iArr2, i11, i13, i6, iArr, i52, i12, i164, iArr5, 0, i159, i161, i10);
            }
            int i165 = i13;
            int i166 = i12;
            i146 = i10;
            int i167 = i148 + i145;
            int i168 = i167 + 10;
            if (i168 < i155) {
                if (i165 * 31 >= i167 + 41) {
                    i149 = i165 - 1;
                }
                if (i148 <= 0) {
                    if (i149 < i166) {
                        i18 = i5;
                        i19 = i11;
                        i20 = 0;
                        i14 = i7;
                        while (i20 < i14) {
                            i21 = (-(iArr2[(i18 + i149) - 1] >>> 30)) >>> 1;
                            for (i22 = i149; i22 < i166; i22++) {
                                iArr2[i18 + i22] = i21;
                            }
                            i23 = (-(iArr2[(i19 + i149) - 1] >>> 30)) >>> 1;
                            for (i24 = i149; i24 < i166; i24++) {
                                iArr2[i19 + i24] = i23;
                            }
                            i20++;
                            i18 += i6;
                            i19 += i6;
                        }
                    } else {
                        i14 = i7;
                    }
                    i15 = i5;
                    i16 = i15;
                    i17 = 0;
                    while (i17 < (i14 << 1)) {
                        System.arraycopy(iArr2, i15, iArr2, i16, i166);
                        i17++;
                        i16 += i166;
                        i15 += i6;
                    }
                    return 1;
                }
                i135 = i7;
                i25 = i148 - 25;
                if (i25 < 0) {
                    i148 = 0;
                } else {
                    i148 = i25;
                }
                falconKeyGen3 = this;
                i133 = i4;
                i134 = i11;
                i47 = i6;
                falconFPRArr6 = falconFPRArr12;
                i150 = i9;
                i132 = i8;
                falconFPRArr7 = falconFPRArr9;
                falconFPRArr8 = falconFPRArr10;
                falconFPRArr5 = falconFPRArr11;
                i140 = i166;
                i147 = i168;
            } else {
                i168 = i155;
            }
            i149 = i165;
            if (i148 <= 0) {
                if (i149 < i166) {
                    i18 = i5;
                    i19 = i11;
                    i20 = 0;
                    i14 = i7;
                    while (i20 < i14) {
                        i21 = (-(iArr2[(i18 + i149) - 1] >>> 30)) >>> 1;
                        while (i22 < i166) {
                            iArr2[i18 + i22] = i21;
                        }
                        i23 = (-(iArr2[(i19 + i149) - 1] >>> 30)) >>> 1;
                        while (i24 < i166) {
                            iArr2[i19 + i24] = i23;
                        }
                        i20++;
                        i18 += i6;
                        i19 += i6;
                    }
                } else {
                    i14 = i7;
                }
                i15 = i5;
                i16 = i15;
                i17 = 0;
                while (i17 < (i14 << 1)) {
                    System.arraycopy(iArr2, i15, iArr2, i16, i166);
                    i17++;
                    i16 += i166;
                    i15 += i6;
                }
                return 1;
            }
            i135 = i7;
            i25 = i148 - 25;
            if (i25 < 0) {
                i148 = 0;
            } else {
                i148 = i25;
            }
            falconKeyGen3 = this;
            i133 = i4;
            i134 = i11;
            i47 = i6;
            falconFPRArr6 = falconFPRArr12;
            i150 = i9;
            i132 = i8;
            falconFPRArr7 = falconFPRArr9;
            falconFPRArr8 = falconFPRArr10;
            falconFPRArr5 = falconFPRArr11;
            i140 = i166;
            i147 = i168;
        }
    }

    public void zint_add_mul_small(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = i + i6;
            long unsignedLong = (toUnsignedLong(iArr2[i2 + i6]) * toUnsignedLong(i4)) + toUnsignedLong(iArr[i7]) + toUnsignedLong(i5);
            iArr[i7] = ((int) unsignedLong) & Integer.MAX_VALUE;
            i5 = (int) (unsignedLong >>> 31);
        }
        iArr[i + i3] = i5;
    }

    public void zint_add_scaled_mul_small(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6, int i7) {
        if (i4 == 0) {
            return;
        }
        int i8 = (-(iArr2[(i3 + i4) - 1] >>> 30)) >>> 1;
        int i9 = 0;
        int i10 = i6;
        int i11 = 0;
        while (i10 < i2) {
            int i12 = i10 - i6;
            int i13 = i12 < i4 ? iArr2[i3 + i12] : i8;
            int i14 = i + i10;
            long unsignedLong = (toUnsignedLong(i9 | ((i13 << i7) & Integer.MAX_VALUE)) * ((long) i5)) + toUnsignedLong(iArr[i14]) + ((long) i11);
            iArr[i14] = ((int) unsignedLong) & Integer.MAX_VALUE;
            i11 = (int) (unsignedLong >>> 31);
            i10++;
            i9 = i13 >>> (31 - i7);
        }
    }

    public int zint_bezout(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3, int[] iArr4, int i4, int i5, int[] iArr5, int i6) {
        FalconKeyGen falconKeyGen = this;
        int i7 = i5;
        int[] iArr6 = iArr5;
        if (i7 == 0) {
            return 0;
        }
        int i8 = i6 + i7;
        int i9 = i8 + i7;
        int i10 = i9 + i7;
        int iModp_ninv31 = falconKeyGen.modp_ninv31(iArr3[i3]);
        int iModp_ninv32 = falconKeyGen.modp_ninv31(iArr4[i4]);
        System.arraycopy(iArr3, i3, iArr6, i9, i7);
        System.arraycopy(iArr4, i4, iArr6, i10, i7);
        iArr[i] = 1;
        iArr2[i2] = 0;
        for (int i11 = 1; i11 < i7; i11++) {
            iArr[i + i11] = 0;
            iArr2[i2 + i11] = 0;
        }
        System.arraycopy(iArr4, i4, iArr6, i6, i7);
        System.arraycopy(iArr3, i3, iArr6, i8, i7);
        iArr6[i8] = iArr6[i8] - 1;
        int i12 = 30;
        int i13 = (i7 * 62) + 30;
        while (i13 >= i12) {
            int i14 = -1;
            int i15 = i7;
            int i16 = -1;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            while (true) {
                int i21 = i15 - 1;
                if (i15 <= 0) {
                    break;
                }
                int i22 = iArr6[i9 + i21];
                int i23 = iArr6[i10 + i21];
                i18 ^= (i18 ^ i22) & i16;
                i17 ^= (i17 ^ i22) & i14;
                i20 ^= (i20 ^ i23) & i16;
                i19 ^= (i19 ^ i23) & i14;
                int i24 = ((((i22 | i23) + Integer.MAX_VALUE) >>> 31) - 1) & i16;
                int i25 = i16;
                i16 = i24;
                i14 = i25;
                i15 = i21;
            }
            int i26 = ~i14;
            int i27 = i18 & i26;
            long unsignedLong = (falconKeyGen.toUnsignedLong(i27) << 31) + falconKeyGen.toUnsignedLong(i17 | (i18 & i14));
            long unsignedLong2 = (falconKeyGen.toUnsignedLong(i20 & i26) << 31) + falconKeyGen.toUnsignedLong(i19 | (i20 & i14));
            int i28 = iArr6[i9];
            int i29 = iArr6[i10];
            long j = 0;
            int i30 = i9;
            int i31 = 0;
            long j2 = 0;
            long j3 = 1;
            long j4 = 1;
            for (int i32 = 31; i31 < i32; i32 = 31) {
                long j5 = unsignedLong2 - unsignedLong;
                int i33 = i31;
                int i34 = (int) ((j5 ^ ((unsignedLong ^ unsignedLong2) & (unsignedLong ^ j5))) >>> 63);
                int i35 = (i28 >> i33) & 1;
                int i36 = i35 & (i29 >> i33) & 1;
                int i37 = i36 & i34;
                int i38 = i36 & (~i34);
                int i39 = i37 | (i35 ^ 1);
                int i40 = i28 - ((-i37) & i29);
                long j6 = unsignedLong - ((-falconKeyGen.toUnsignedLong(i37)) & unsignedLong2);
                long j7 = -i37;
                long j8 = j3 - (j2 & j7);
                long j9 = j - (j4 & j7);
                int i41 = i29 - ((-i38) & i40);
                long j10 = unsignedLong2 - (j6 & (-falconKeyGen.toUnsignedLong(i38)));
                long j11 = -i38;
                long j12 = j2 - (j8 & j11);
                long j13 = j4 - (j9 & j11);
                i28 = i40 + ((i39 - 1) & i40);
                long j14 = i39;
                long j15 = j14 - 1;
                j3 = j8 + (j8 & j15);
                j = j9 + (j9 & j15);
                unsignedLong = j6 ^ ((j6 ^ (j6 >> 1)) & (-falconKeyGen.toUnsignedLong(i39)));
                i29 = i41 + ((-i39) & i41);
                long j16 = -j14;
                j2 = j12 + (j12 & j16);
                j4 = j13 + (j13 & j16);
                unsignedLong2 = j10 ^ (((j10 >> 1) ^ j10) & (falconKeyGen.toUnsignedLong(i39) - 1));
                i31 = i33 + 1;
                i8 = i8;
            }
            int i42 = i8;
            int i43 = i10;
            long j17 = j;
            long j18 = j2;
            long j19 = j4;
            int iZint_co_reduce = falconKeyGen.zint_co_reduce(iArr5, i30, iArr5, i43, i5, j3, j17, j18, j19);
            long j20 = -(iZint_co_reduce & 1);
            long j21 = j3 - ((j3 + j3) & j20);
            long j22 = j17 - ((j17 + j17) & j20);
            long j23 = -(iZint_co_reduce >>> 1);
            long j24 = j18 - ((j18 + j18) & j23);
            long j25 = j19 - ((j19 + j19) & j23);
            falconKeyGen = this;
            iArr6 = iArr5;
            int i44 = iModp_ninv32;
            i7 = i5;
            falconKeyGen.zint_co_reduce_mod(iArr, i, iArr6, i6, iArr4, i4, i7, i44, j21, j22, j24, j25);
            int i45 = iModp_ninv31;
            falconKeyGen.zint_co_reduce_mod(iArr2, i2, iArr6, i42, iArr3, i3, i7, i45, j21, j22, j24, j25);
            i13 -= 30;
            iModp_ninv32 = i44;
            i12 = 30;
            i9 = i30;
            i8 = i42;
            iModp_ninv31 = i45;
            i10 = i43;
        }
        int i46 = i9;
        int i47 = iArr5[i46] ^ 1;
        for (int i48 = 1; i48 < i7; i48++) {
            i47 |= iArr5[i46 + i48];
        }
        return (1 - ((i47 | (-i47)) >>> 31)) & iArr3[i3] & iArr4[i4];
    }

    public int zint_co_reduce(int[] iArr, int i, int[] iArr2, int i2, int i3, long j, long j2, long j3, long j4) {
        long j5 = 0;
        int i4 = 0;
        long j6 = 0;
        while (i4 < i3) {
            int i5 = i + i4;
            int i6 = i2 + i4;
            int i7 = i4;
            long j7 = iArr[i5];
            long j8 = j7 * j;
            long j9 = iArr2[i6];
            long j10 = j8 + (j9 * j2) + j5;
            long j11 = (j7 * j3) + (j9 * j4) + j6;
            if (i7 > 0) {
                iArr[i5 - 1] = ((int) j10) & Integer.MAX_VALUE;
                iArr2[i6 - 1] = ((int) j11) & Integer.MAX_VALUE;
            }
            j5 = j10 >> 31;
            j6 = j11 >> 31;
            i4 = i7 + 1;
        }
        iArr[(i + i3) - 1] = (int) j5;
        iArr2[(i2 + i3) - 1] = (int) j6;
        int i8 = (int) (j5 >>> 63);
        int i9 = (int) (j6 >>> 63);
        zint_negate(iArr, i, i3, i8);
        zint_negate(iArr2, i2, i3, i9);
        return (i9 << 1) | i8;
    }

    public void zint_co_reduce_mod(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3, int i4, int i5, long j, long j2, long j3, long j4) {
        int i6 = iArr[i];
        int i7 = iArr2[i2];
        int i8 = (((((int) j) * i6) + (((int) j2) * i7)) * i5) & Integer.MAX_VALUE;
        int i9 = (((i6 * ((int) j3)) + (i7 * ((int) j4))) * i5) & Integer.MAX_VALUE;
        long j5 = 0;
        long j6 = 0;
        for (int i10 = 0; i10 < i4; i10++) {
            int i11 = i + i10;
            int i12 = i2 + i10;
            long j7 = iArr[i11];
            long j8 = iArr2[i12];
            int i13 = i3 + i10;
            long unsignedLong = (j7 * j) + (j8 * j2) + (((long) iArr3[i13]) * toUnsignedLong(i8)) + j5;
            long unsignedLong2 = (j7 * j3) + (j8 * j4) + (((long) iArr3[i13]) * toUnsignedLong(i9)) + j6;
            if (i10 > 0) {
                iArr[i11 - 1] = ((int) unsignedLong) & Integer.MAX_VALUE;
                iArr2[i12 - 1] = ((int) unsignedLong2) & Integer.MAX_VALUE;
            }
            j5 = unsignedLong >> 31;
            j6 = unsignedLong2 >> 31;
        }
        long j9 = j5;
        iArr[(i + i4) - 1] = (int) j9;
        iArr2[(i2 + i4) - 1] = (int) j6;
        zint_finish_mod(iArr, i, i4, iArr3, i3, (int) (j9 >>> 63));
        zint_finish_mod(iArr2, i2, i4, iArr3, i3, (int) (j6 >>> 63));
    }

    public void zint_finish_mod(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            i5 = ((iArr[i + i6] - iArr2[i3 + i6]) - i5) >>> 31;
        }
        int i7 = (-i4) >>> 1;
        int i8 = -((1 - i5) | i4);
        for (int i9 = 0; i9 < i2; i9++) {
            int i10 = i + i9;
            int i11 = (iArr[i10] - ((iArr2[i3 + i9] ^ i7) & i8)) - i4;
            iArr[i10] = Integer.MAX_VALUE & i11;
            i4 = i11 >>> 31;
        }
    }

    public int zint_mod_small_signed(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        if (i2 == 0) {
            return 0;
        }
        return modp_sub(zint_mod_small_unsigned(iArr, i, i2, i3, i4, i5), (-(iArr[(i + i2) - 1] >>> 30)) & i6, i3);
    }

    public int zint_mod_small_unsigned(int[] iArr, int i, int i2, int i3, int i4, int i5) {
        int iModp_add = 0;
        while (true) {
            int i6 = i2 - 1;
            if (i2 <= 0) {
                return iModp_add;
            }
            int iModp_montymul = modp_montymul(iModp_add, i5, i3, i4);
            int i7 = iArr[i + i6] - i3;
            iModp_add = modp_add(iModp_montymul, i7 + ((-(i7 >>> 31)) & i3), i3);
            i2 = i6;
        }
    }

    public int zint_mul_small(int[] iArr, int i, int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i + i5;
            long unsignedLong = (toUnsignedLong(iArr[i6]) * toUnsignedLong(i3)) + ((long) i4);
            iArr[i6] = ((int) unsignedLong) & Integer.MAX_VALUE;
            i4 = (int) (unsignedLong >> 31);
        }
        return i4;
    }

    public void zint_negate(int[] iArr, int i, int i2, int i3) {
        int i4 = (-i3) >>> 1;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i + i5;
            int i7 = (iArr[i6] ^ i4) + i3;
            iArr[i6] = Integer.MAX_VALUE & i7;
            i3 = i7 >>> 31;
        }
    }

    public void zint_norm_zero(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        int i4 = 0;
        int i5 = i3;
        int i6 = 0;
        while (true) {
            int i7 = i5 - 1;
            if (i5 <= 0) {
                zint_sub(iArr, i, iArr2, i2, i3, i4 >>> 31);
                return;
            }
            int i8 = iArr[i + i7];
            int i9 = iArr2[i2 + i7];
            int i10 = ((i6 << 30) | (i9 >>> 1)) - i8;
            i4 |= ((-(i10 >>> 31)) | ((-i10) >>> 31)) & ((i4 & 1) - 1);
            i5 = i7;
            i6 = i9 & 1;
        }
    }

    public int zint_one_to_plain(int[] iArr, int i) {
        int i2 = iArr[i];
        return i2 | ((1073741824 & i2) << 1);
    }

    public void zint_rebuild_CRT(int[] iArr, int i, int i2, int i3, int i4, FalconSmallPrime[] falconSmallPrimeArr, int i5, int[] iArr2, int i6) {
        FalconKeyGen falconKeyGen = this;
        int i7 = 0;
        iArr2[i6] = falconSmallPrimeArr[0].p;
        int i8 = 1;
        int i9 = i2;
        while (true) {
            int i10 = i8;
            if (i10 >= i9) {
                break;
            }
            FalconSmallPrime falconSmallPrime = falconSmallPrimeArr[i10];
            int i11 = falconSmallPrime.p;
            int i12 = falconSmallPrime.s;
            int iModp_ninv31 = falconKeyGen.modp_ninv31(i11);
            int iModp_R2 = falconKeyGen.modp_R2(i11, iModp_ninv31);
            int i13 = i;
            int i14 = 0;
            while (i14 < i4) {
                int i15 = i11;
                int i16 = iModp_ninv31;
                int i17 = iModp_R2;
                int iModp_montymul = falconKeyGen.modp_montymul(i12, falconKeyGen.modp_sub(iArr[i13 + i10], falconKeyGen.zint_mod_small_unsigned(iArr, i13, i10, i11, iModp_ninv31, iModp_R2), i15), i15, i16);
                int i18 = i10;
                falconKeyGen.zint_add_mul_small(iArr, i13, iArr2, i6, i18, iModp_montymul);
                i14++;
                i13 += i3;
                i10 = i18;
                i11 = i15;
                iModp_ninv31 = i16;
                iModp_R2 = i17;
            }
            int i19 = i10;
            iArr2[i6 + i19] = falconKeyGen.zint_mul_small(iArr2, i6, i19, i11);
            i8 = i19 + 1;
        }
        if (i5 != 0) {
            int i20 = i;
            while (i7 < i4) {
                falconKeyGen.zint_norm_zero(iArr, i20, iArr2, i6, i9);
                i7++;
                i20 += i3;
                falconKeyGen = this;
                i9 = i2;
            }
        }
    }

    public int zint_sub(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4) {
        int i5 = -i4;
        int i6 = 0;
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = i + i7;
            int i9 = iArr[i8];
            int i10 = (i9 - iArr2[i2 + i7]) - i6;
            i6 = i10 >>> 31;
            iArr[i8] = i9 ^ (((i10 & Integer.MAX_VALUE) ^ i9) & i5);
        }
        return i6;
    }

    public void zint_sub_scaled(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6) {
        if (i4 == 0) {
            return;
        }
        int i7 = (-(iArr2[(i3 + i4) - 1] >>> 30)) >>> 1;
        int i8 = 0;
        int i9 = i5;
        int i10 = 0;
        while (i9 < i2) {
            int i11 = i9 - i5;
            int i12 = i11 < i4 ? iArr2[i11 + i3] : i7;
            int i13 = i + i9;
            int i14 = (iArr[i13] - (i8 | ((i12 << i6) & Integer.MAX_VALUE))) - i10;
            iArr[i13] = i14 & Integer.MAX_VALUE;
            i10 = i14 >>> 31;
            i9++;
            i8 = i12 >>> (31 - i6);
        }
    }
}
