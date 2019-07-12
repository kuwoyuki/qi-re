package decrypt

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/* renamed from: com.qidian.QDReader.core.i.i */
object DES {
    /* renamed from: a */
//    @Throws(Exception::class)
//    fun m20014a(str: String, str2: String): String {
//        val ivParameterSpec = IvParameterSpec(ByteArray(8))
//        var bytes = str2.toByteArray(charset("UTF-8"))
//        if (bytes.size == 16) {
//            val bArr = ByteArray(24)
//            System.arraycopy(bytes, 0, bArr, 0, 16)
//            System.arraycopy(bytes, 0, bArr, 16, 8)
//            bytes = bArr
//        }
//        val secretKeySpec = SecretKeySpec(bytes, "DESede")
//        val instance = Cipher.getInstance("DESede/CBC/PKCS5Padding") ?: return ""
//        instance.init(1, secretKeySpec, ivParameterSpec)
//
//        return Base64.getEncoder().encodeToString(instance.doFinal(str.toByteArray()))
//    }

    /* renamed from: a */
    @Throws(Exception::class)
    fun m20015a(bArr: ByteArray?, str: String?): ByteArray? {
        if (bArr == null || str == null) {
            return null
        }

        val ivParameterSpec = IvParameterSpec(ByteArray(8))
        var bytes: ByteArray? = str.toByteArray(charset("UTF-8"))

        if (bytes == null || bytes.isEmpty()) {
            return null
        }

        if (bytes.size == 16) {
            val bArr2 = ByteArray(24)
            System.arraycopy(bytes, 0, bArr2, 0, 16)
            System.arraycopy(bytes, 0, bArr2, 16, 8)
            bytes = bArr2
        }
        val instance = Cipher.getInstance("DESede/CBC/PKCS5Padding")

        instance ?: return null

        instance.init(2, SecretKeySpec(bytes, "DESede"), ivParameterSpec)
        return instance.doFinal(bArr)
    }
}
