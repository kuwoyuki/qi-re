package decrypt

import java.security.NoSuchAlgorithmException

class Magik {
    fun decrypt(chapterId: Long, bs: ByteArray, userId: Long, imei: String): ByteArray? {
        val salt1 = "" + userId + imei + chapterId + "2EEE1433A152E84B3756301D8FA3E69A"

        // First secret, calls s :: String -> String -> String
        // SHA1 HMAC?
        var secret1 = sha(imei, salt1)

        if (secret1!!.length > 24) {
            secret1 = secret1.substring(0, 24)
        }

        val salt2 = secret1 + imei

        // Second secret, calls m :: String -> String -> String
        // something MD5
        var secret2 = md5(userId.toString() + "", salt2)

        if (secret2!!.length > 24) {
            secret2 = secret2.substring(0, 24)
        }

        // Double decrypt with DES?

        // d :: byte[] -> String -> byte[]
        val res = des(bs, secret2) ?: return null

        return des(res, secret1)
    }

    /* renamed from: a */
    private fun pad(str: String, i: Int, c: Char): String {
        val sb = StringBuilder()
        sb.append(str)
        val length = i - str.length
        for (i2 in 0 until length) {
            sb.append(c)
        }
        return sb.toString()
    }

    // SHA1
    /* renamed from: s */
    private fun sha(str: String, str2: String): String? {
        return try {
            val str3 = SHA1.m19934a(str, str2)
            try {
                if (str3.length < 24) {
                    pad(str3, 24, '0')
                } else str3
            } catch (e: Exception) {
                str3
            }

        } catch (e2: Exception) {
            null

        }

    }

    // MD5
    /* renamed from: m */
    private fun md5(str: String, str2: String): String? {
        return try {
            MD5.m20045a(str, str2)
        } catch (e: NoSuchAlgorithmException) {
            null
        }

    }

    // DES
    /* renamed from: d */
    private fun des(bArr: ByteArray, str: String): ByteArray? {
        return try {
            DES.m20015a(bArr, str)
        } catch (e: Exception) {
            null
        }

    }
}
