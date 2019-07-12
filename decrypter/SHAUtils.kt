package decrypt

/* renamed from: com.qidian.QDReader.core.i.ab */
object SHA1 {
    private const val MASK = 65535

    /* renamed from: a */
    private fun m19930a(i: Int): Int {
        if (i < 20) {
            return 1518500249
        }
        if (i < 40) {
            return 1859775393
        }
        return if (i < 60) -1894007588 else -899497514
    }

    /* renamed from: a */
    private fun m19931a(i: Int, i2: Int): Int {
        return i.ushr(32 - i2) or (i shl i2)
    }

    /* renamed from: a */
    private fun m19932a(i: Int, i2: Int, i3: Int, i4: Int): Int {
        return if (i < 20) i2 and i3 or (i2 xor -1 and i4) else if (i < 40) i2 xor i3 xor i4 else if (i < 60) i2 and i3 or (i2 and i4) or (i3 and i4) else i2 xor i3 xor i4
    }

    // public static final int USER_MASK = 65535;
    /* renamed from: b */
    private fun m19938b(i: Int, i2: Int): Int {
        val i3 = (i and MASK) + (i2 and MASK)
        return (i shr 16) + (i2 shr 16) + (i3 shr 16) shl 16 or (i3 and MASK)
    }

    /* renamed from: a */
    fun m19934a(str: String, str2: String): String {
        return m19935a(m19940b(str, str2))
    }

    /* renamed from: a */
    private fun m19935a(iArr: IntArray): String {
        val b = m19942b(iArr, iArr.size * 4)
        var str = ""
        var i = 0
        while (i < b.size * 4) {
            val i2 = i + 1
            val i3 = b[i shr 2] shr (3 - i % 4) * 8 and 255 shl 16 or (b[i2 shr 2] shr (3 - i2 % 4) * 8 and 255 shl 8)
            val i4 = i + 2
            val i5 = i3 or (b[i4 shr 2] shr (3 - i4 % 4) * 8 and 255)
            var str2 = str
            for (i6 in 0..3) {
                if (i * 8 + i6 * 6 > b.size * 32) {
                    val sb = StringBuilder()
                    sb.append(str2)
                    sb.append("=")
                    str2 = sb.toString()
                } else {
                    val sb2 = StringBuilder()
                    sb2.append(str2)
                    sb2.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"[i5 shr (3 - i6) * 6 and 63])
                    str2 = sb2.toString()
                }
            }
            i += 3
            str = str2
        }
        return m19933a(str)
    }

    /* renamed from: a */
    private fun m19933a(str: String?): String {
        var str = str
        if (str == null) {
            str = ""
        }
        val length = str.length
        if (length <= 1) {
            return str
        }
        var i = length - 1
        val charAt = str[i]
        var str2 = ""
        while (i >= 0 && str[i] == charAt) {
            val sb = StringBuilder()
            sb.append(str2)
            sb.append(str[i])
            str2 = sb.toString()
            i--
        }
        return str.substring(0, str.indexOf(str2))
    }

    /* renamed from: b */
    private fun m19941b(iArr: IntArray): IntArray {
        if (iArr.size >= 16) {
            return iArr
        }
        val iArr2 = IntArray(16 - iArr.size)
        for (i in iArr2.indices) {
            iArr2[i] = 0
        }
        return m19937a(iArr, iArr2)
    }

    /* renamed from: a */
    private fun m19937a(iArr: IntArray, iArr2: IntArray): IntArray {
        val iArr3 = IntArray(iArr.size + iArr2.size)
        for (i in 0 until iArr.size + iArr2.size) {
            if (i < iArr.size) {
                iArr3[i] = iArr[i]
            } else {
                iArr3[i] = iArr2[i - iArr.size]
            }
        }
        return iArr3
    }

    /* renamed from: b */
    private fun m19940b(str: String?, str2: String?): IntArray {
        var str = str
        var str2 = str2
        if (str == null) {
            str = ""
        }
        if (str2 == null) {
            str2 = ""
        }
        var b = m19941b(m19939b(str))
        if (b.size > 16) {
            b = m19936a(b, str.length * 8)
        }
        val iArr = IntArray(16)
        val iArr2 = IntArray(16)
        for (i in 0..15) {
            iArr[i] = 0
            iArr2[i] = 0
        }
        for (i2 in 0..15) {
            iArr[i2] = b[i2] xor 909522486
            iArr2[i2] = b[i2] xor 1549556828
        }
        return m19936a(m19937a(iArr2, m19936a(m19937a(iArr, m19939b(str2)), str2.length * 8 + 512)), 672)
    }

    /* renamed from: a */
    private fun m19936a(iArr: IntArray, i: Int): IntArray {
        val i2 = i shr 5
        val b = m19942b(iArr, i2)
        b[i2] = b[i2] or (128 shl 24 - i % 32)
        val i3 = (i + 64 shr 9 shl 4) + 15
        val b2 = m19942b(b, i3)
        b2[i3] = i
        var i4 = 80
        val iArr2 = IntArray(80)
        var i5 = 0
        var i6 = 1732584193
        var i7 = -271733879
        var i8 = -1732584194
        var i9 = 271733878
        var i10 = -1009589776
        while (true) {
            var i11 = 1
            var i12 = 5
            if (i5 < b2.size) {
                var i13 = i6
                var i14 = i7
                var i15 = i8
                var i16 = i9
                var i17 = i10
                var i18 = 0
                while (i18 < i4) {
                    if (i18 < 16) {
                        iArr2[i18] = b2[i5 + i18]
                    } else {
                        iArr2[i18] =
                            m19931a(iArr2[i18 - 3] xor iArr2[i18 - 8] xor iArr2[i18 - 14] xor iArr2[i18 - 16], i11)
                    }
                    val b3 = m19938b(
                        m19938b(m19931a(i13, i12), m19932a(i18, i14, i15, i16)),
                        m19938b(m19938b(i17, iArr2[i18]), m19930a(i18))
                    )
                    val a = m19931a(i14, 30)
                    i18++
                    i17 = i16
                    i14 = i13
                    i12 = 5
                    i13 = b3
                    i16 = i15
                    i15 = a
                    i4 = 80
                    i11 = 1
                }
                i6 = m19938b(i13, i6)
                i7 = m19938b(i14, i7)
                i8 = m19938b(i15, i8)
                i9 = m19938b(i16, i9)
                i10 = m19938b(i17, i10)
                i5 += 16
                i4 = 80
            } else {
                return intArrayOf(i6, i7, i8, i9, i10)
            }
        }
    }

    /* renamed from: b */
    private fun m19939b(str: String?): IntArray {
        var str = str
        if (str == null) {
            str = ""
        }
        val iArr = IntArray(str.length * 8)
        var i = 0
        while (i < str.length * 8) {
            val i2 = i shr 5
            iArr[i2] = iArr[i2] or (str[i / 8].toInt() and 255 shl 24 - i % 32)
            i += 8
        }
        var i3 = 0
        var i4 = 0
        while (i3 < iArr.size && iArr[i3] != 0) {
            i3++
            i4++
        }
        val iArr2 = IntArray(i4)
        System.arraycopy(iArr, 0, iArr2, 0, i4)
        return iArr2
    }

    /* renamed from: b */
    private fun m19942b(iArr: IntArray, i: Int): IntArray {
        val length = iArr.size
        val i2 = i + 1
        if (length >= i2) {
            return iArr
        }
        val iArr2 = IntArray(i2)
        for (i3 in 0 until i) {
            iArr2[i3] = 0
        }
        System.arraycopy(iArr, 0, iArr2, 0, length)
        return iArr2
    }
}
