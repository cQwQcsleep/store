package org.fusesource.jansi.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.fusesource.jansi.AnsiColors;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ColorsAnsiProcessor extends AnsiProcessor {
    private final AnsiColors colors;

    public ColorsAnsiProcessor(OutputStream outputStream, AnsiColors ansiColors) {
        super(outputStream);
        this.colors = ansiColors;
    }

    @Override // org.fusesource.jansi.io.AnsiProcessor
    public boolean processCharsetSelect(ArrayList<Object> arrayList) {
        return false;
    }

    @Override // org.fusesource.jansi.io.AnsiProcessor
    public boolean processEscapeCommand(ArrayList<Object> arrayList, int i) throws IOException {
        AnsiColors ansiColors;
        boolean z;
        int i2;
        int i3;
        boolean z2 = false;
        if (i != 109 || ((ansiColors = this.colors) != AnsiColors.Colors256 && ansiColors != AnsiColors.Colors16)) {
            return false;
        }
        Iterator<Object> it = arrayList.iterator();
        boolean z3 = false;
        while (true) {
            int i4 = 48;
            if (!it.hasNext()) {
                if (!z3) {
                    return false;
                }
                StringBuilder sb = new StringBuilder(32);
                sb.append("\u001b[");
                Iterator<Object> it2 = arrayList.iterator();
                boolean z4 = true;
                while (it2.hasNext()) {
                    Object next = it2.next();
                    if (next != null) {
                        int iIntValue = ((Integer) next).intValue();
                        if (iIntValue == 38 || iIntValue == i4) {
                            int nextOptionInt = getNextOptionInt(it2);
                            if (nextOptionInt == 2) {
                                int nextOptionInt2 = getNextOptionInt(it2);
                                int nextOptionInt3 = getNextOptionInt(it2);
                                z = z2;
                                int nextOptionInt4 = getNextOptionInt(it2);
                                if (this.colors == AnsiColors.Colors256) {
                                    int iRoundRgbColor = Colors.roundRgbColor(nextOptionInt2, nextOptionInt3, nextOptionInt4, 256);
                                    if (!z4) {
                                        sb.append(';');
                                    }
                                    sb.append(iIntValue);
                                    sb.append(";5;");
                                    sb.append(iRoundRgbColor);
                                } else {
                                    int iRoundRgbColor2 = Colors.roundRgbColor(nextOptionInt2, nextOptionInt3, nextOptionInt4, 16);
                                    if (!z4) {
                                        sb.append(';');
                                    }
                                    if (iIntValue == 38) {
                                        i3 = iRoundRgbColor2 >= 8 ? iRoundRgbColor2 + 82 : iRoundRgbColor2 + 30;
                                    } else {
                                        i3 = iRoundRgbColor2 >= 8 ? iRoundRgbColor2 + 92 : iRoundRgbColor2 + 40;
                                    }
                                    sb.append(i3);
                                }
                            } else {
                                z = z2;
                                if (nextOptionInt != 5) {
                                    j2d.a();
                                    return z;
                                }
                                int nextOptionInt5 = getNextOptionInt(it2);
                                if (this.colors == AnsiColors.Colors256) {
                                    if (!z4) {
                                        sb.append(';');
                                    }
                                    sb.append(iIntValue);
                                    sb.append(";5;");
                                    sb.append(nextOptionInt5);
                                } else {
                                    int iRoundColor = Colors.roundColor(nextOptionInt5, 16);
                                    if (!z4) {
                                        sb.append(';');
                                    }
                                    if (iIntValue == 38) {
                                        i2 = iRoundColor >= 8 ? iRoundColor + 82 : iRoundColor + 30;
                                    } else {
                                        i2 = iRoundColor >= 8 ? iRoundColor + 92 : iRoundColor + 40;
                                    }
                                    sb.append(i2);
                                }
                            }
                            z4 = z;
                        } else {
                            if (!z4) {
                                sb.append(';');
                            }
                            sb.append(iIntValue);
                            z4 = z2;
                            z = z4;
                        }
                        z2 = z;
                        i4 = 48;
                    } else {
                        z = z2;
                    }
                    z2 = z;
                    i4 = 48;
                }
                sb.append('m');
                this.os.write(sb.toString().getBytes());
                return true;
            }
            Object next2 = it.next();
            if (next2 != null && next2.getClass() != Integer.class) {
                j2d.a();
                return false;
            }
            Integer num = (Integer) next2;
            z3 |= num.intValue() == 38 || num.intValue() == 48;
        }
    }

    @Override // org.fusesource.jansi.io.AnsiProcessor
    public boolean processOperatingSystemCommand(ArrayList<Object> arrayList) {
        return false;
    }
}
