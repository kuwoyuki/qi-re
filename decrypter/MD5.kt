package decrypt

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

/* renamed from: com.qidian.QDReader.core.i.r */
object MD5 {
    /* renamed from: a */
//    @Throws(Exception::class)
//    fun m20044a(str: String): String {
//        val digest = MessageDigest.getInstance("MD5").digest(str.toByteArray(charset("UTF-8")))
//        val sb = StringBuilder(digest.size * 2)
//        for (b in digest) {
//            val b2 = (b.toInt() and 255).toByte()
//            if (b2 < 16) {
//                sb.append("0")
//            }
//            sb.append(Integer.toHexString(b2.toInt()))
//        }
//        return sb.toString()
//    }

    /* renamed from: b */
    @Throws(NoSuchAlgorithmException::class)
    private fun m20048b(bArr: ByteArray): ByteArray {
        val instance = MessageDigest.getInstance("MD5")
        instance.update(bArr)
        return instance.digest()
    }

    /* renamed from: a */
    @Throws(NoSuchAlgorithmException::class)
    fun m20045a(str: String, str2: String): String {
        val res = m20047a(str.toByteArray(), str2.toByteArray())
        return Base64.getEncoder().encodeToString(res)
    }

    /* renamed from: a */
    @Throws(NoSuchAlgorithmException::class)
    private fun m20047a(bArr: ByteArray, bArr2: ByteArray): ByteArray {
        var bArr = bArr
        val bArr3 = ByteArray(64)
        val bArr4 = ByteArray(64)
        for (i in 0..63) {
            bArr3[i] = 54
            bArr4[i] = 92
        }
        val bArr5 = ByteArray(64)
        if (bArr.size > 64) {
            bArr = m20048b(bArr)
        }
        System.arraycopy(bArr, 0, bArr5, 0, bArr.size)
        if (bArr.size < 64) {
            for (length in bArr.size until bArr5.size) {
                bArr5[length] = 0
            }
        }
        val bArr6 = ByteArray(64)
        for (i2 in 0..63) {
            bArr6[i2] = (bArr5[i2].toInt() xor bArr3[i2].toInt()).toByte()
        }
        val bArr7 = ByteArray(bArr6.size + bArr2.size)
        System.arraycopy(bArr6, 0, bArr7, 0, bArr6.size)
        System.arraycopy(bArr2, 0, bArr7, bArr5.size + 0, bArr2.size)
        val b = m20048b(bArr7)
        val bArr8 = ByteArray(64)
        for (i3 in 0..63) {
            bArr8[i3] = (bArr5[i3].toInt() xor bArr4[i3].toInt()).toByte()
        }
        val bArr9 = ByteArray(bArr8.size + b.size)
        System.arraycopy(bArr8, 0, bArr9, 0, bArr8.size)
        System.arraycopy(b, 0, bArr9, bArr5.size + 0, b.size)
        return m20048b(bArr9)
    }
}
