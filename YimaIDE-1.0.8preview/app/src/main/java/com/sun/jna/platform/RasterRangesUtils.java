package com.sun.jna.platform;

import java.awt.Rectangle;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.Raster;
import java.awt.image.SinglePixelPackedSampleModel;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RasterRangesUtils {
    private static final int[] subColMasks = {128, 64, 32, 16, 8, 4, 2, 1};
    private static final Comparator<Object> COMPARATOR = new Comparator<Object>() { // from class: com.sun.jna.platform.RasterRangesUtils.1
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Rectangle) obj).x - ((Rectangle) obj2).x;
        }
    };

    public interface RangesOutput {
        boolean outputRange(int i, int i2, int i3, int i4);
    }

    private static Set<Rectangle> mergeRects(Set<Rectangle> set, Set<Rectangle> set2) {
        HashSet hashSet = new HashSet(set);
        if (!set.isEmpty() && !set2.isEmpty()) {
            int i = 0;
            Rectangle[] rectangleArr = (Rectangle[]) set.toArray(new Rectangle[0]);
            Rectangle[] rectangleArr2 = (Rectangle[]) set2.toArray(new Rectangle[0]);
            int i2 = 0;
            loop0: while (i < rectangleArr.length && i2 < rectangleArr2.length) {
                while (rectangleArr2[i2].x < rectangleArr[i].x) {
                    i2++;
                    if (i2 == rectangleArr2.length) {
                        break loop0;
                    }
                }
                if (rectangleArr2[i2].x == rectangleArr[i].x && rectangleArr2[i2].width == rectangleArr[i].width) {
                    hashSet.remove(rectangleArr[i]);
                    rectangleArr2[i2].y = rectangleArr[i].y;
                    rectangleArr2[i2].height = rectangleArr[i].height + 1;
                    i2++;
                } else {
                    i++;
                }
            }
        }
        return hashSet;
    }

    public static boolean outputOccupiedRanges(Raster raster, RangesOutput rangesOutput) {
        Rectangle bounds = raster.getBounds();
        MultiPixelPackedSampleModel sampleModel = raster.getSampleModel();
        boolean z = sampleModel.getNumBands() == 4;
        if (raster.getParent() == null && bounds.x == 0 && bounds.y == 0) {
            DataBufferByte dataBuffer = raster.getDataBuffer();
            if (dataBuffer.getNumBanks() == 1) {
                if (sampleModel instanceof MultiPixelPackedSampleModel) {
                    if (sampleModel.getPixelBitStride() == 1) {
                        return outputOccupiedRangesOfBinaryPixels(dataBuffer.getData(), bounds.width, bounds.height, rangesOutput);
                    }
                } else if ((sampleModel instanceof SinglePixelPackedSampleModel) && sampleModel.getDataType() == 3) {
                    return outputOccupiedRanges(((DataBufferInt) dataBuffer).getData(), bounds.width, bounds.height, z ? -16777216 : 16777215, rangesOutput);
                }
            }
        }
        return outputOccupiedRanges(raster.getPixels(0, 0, bounds.width, bounds.height, (int[]) null), bounds.width, bounds.height, z ? -16777216 : 16777215, rangesOutput);
    }

    public static boolean outputOccupiedRangesOfBinaryPixels(byte[] bArr, int i, int i2, RangesOutput rangesOutput) {
        HashSet<Rectangle> hashSet = new HashSet();
        Set set = Collections.EMPTY_SET;
        int length = bArr.length / i2;
        int i3 = 0;
        while (i3 < i2) {
            TreeSet treeSet = new TreeSet(COMPARATOR);
            int i4 = i3 * length;
            int i5 = -1;
            for (int i6 = 0; i6 < length; i6++) {
                int i7 = i6 << 3;
                byte b = bArr[i4 + i6];
                if (b != 0) {
                    if (b != 255) {
                        for (int i8 = 0; i8 < 8; i8++) {
                            int i9 = i7 | i8;
                            if ((b & subColMasks[i8]) != 0) {
                                if (i5 < 0) {
                                    i5 = i9;
                                }
                            } else if (i5 >= 0) {
                                treeSet.add(new Rectangle(i5, i3, i9 - i5, 1));
                                i5 = -1;
                            }
                        }
                    } else if (i5 < 0) {
                        i5 = i7;
                    }
                } else if (i5 >= 0) {
                    treeSet.add(new Rectangle(i5, i3, i7 - i5, 1));
                    i5 = -1;
                }
            }
            if (i5 >= 0) {
                treeSet.add(new Rectangle(i5, i3, i - i5, 1));
            }
            hashSet.addAll(mergeRects(set, treeSet));
            i3++;
            set = treeSet;
        }
        hashSet.addAll(set);
        for (Rectangle rectangle : hashSet) {
            if (!rangesOutput.outputRange(rectangle.x, rectangle.y, rectangle.width, rectangle.height)) {
                return false;
            }
        }
        return true;
    }

    public static boolean outputOccupiedRanges(int[] iArr, int i, int i2, int i3, RangesOutput rangesOutput) {
        HashSet<Rectangle> hashSet = new HashSet();
        Set set = Collections.EMPTY_SET;
        int i4 = 0;
        while (i4 < i2) {
            TreeSet treeSet = new TreeSet(COMPARATOR);
            int i5 = i4 * i;
            int i6 = -1;
            for (int i7 = 0; i7 < i; i7++) {
                if ((iArr[i5 + i7] & i3) != 0) {
                    if (i6 < 0) {
                        i6 = i7;
                    }
                } else if (i6 >= 0) {
                    treeSet.add(new Rectangle(i6, i4, i7 - i6, 1));
                    i6 = -1;
                }
            }
            if (i6 >= 0) {
                treeSet.add(new Rectangle(i6, i4, i - i6, 1));
            }
            hashSet.addAll(mergeRects(set, treeSet));
            i4++;
            set = treeSet;
        }
        hashSet.addAll(set);
        for (Rectangle rectangle : hashSet) {
            if (!rangesOutput.outputRange(rectangle.x, rectangle.y, rectangle.width, rectangle.height)) {
                return false;
            }
        }
        return true;
    }
}
