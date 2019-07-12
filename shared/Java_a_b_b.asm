; jbyteArray __fastcall Java_a_b_b(JNIEnv *env, jobject thiz, jlong arg1, jlong arg2, jbyteArray arg3, jlong arg4, jstring arg5)
EXPORT Java_a_b_b
Java_a_b_b

var_58= -0x58
var_54= -0x54
var_50= -0x50
src= -0x44
var_40= -0x40
var_3C= -0x3C
var_38= -0x38
var_34= -0x34
var_30= -0x30
ptr= -0x2C
arg2=  0
arg3=  8
arg4=  0x10
arg5=  0x18

; __unwind {
PUSH.W          {R4-R11,LR}
MOV             R1, R3
SUB             SP, SP, #0x34
MOV             R4, R0
MOV             R0, R2
LDR.W           R5, =(aAB - 0x1608)
BL              longToString
ADD             R5, PC  ; "a/b"
STR             R0, [SP,#0x58+ptr]
LDRD.W          R0, R1, [SP,#0x58]
BL              longToString
STR             R0, [SP,#0x58+var_34]
LDRD.W          R0, R1, [SP,#0x68]
BL              longToString
STR             R0, [SP,#0x58+src]
MOVS            R0, #0x32 ; '2'
BL              initstr
MOVS            R0, #0xFF
BL              initstr
MOV             R8, R0
MOVS            R0, #0xFF
BL              initstr
MOVS            R0, #0xFF
BL              initstr
MOV             R6, R0
MOVS            R0, #0xFF
BL              initstr
MOV             R7, R0
MOVS            R0, #0xFF
BL              initstr
MOVS            R0, #0xFF
BL              initstr
LDR             R3, [R4]
MOV             R1, R5
LDR             R3, [R3,#0x18]
MOV             R11, R0
MOV             R0, R4
BLX             R3
MOV             R10, R0
CBNZ            R0, loc_1660

loc_165C
MOVS            R0, #0
B               loc_1A10

loc_1660
LDR             R3, [R4]
MOV             R0, R4
MOV             R1, R5
LDR             R3, [R3,#0x18]
BLX             R3
STR             R0, [SP,#0x58+var_30]
CMP             R0, #0
BEQ             loc_165C
LDR             R3, [R4]
MOV             R0, R4
MOV             R1, R5
LDR             R3, [R3,#0x18]
BLX             R3
STR             R0, [SP,#0x58+var_40]
CMP             R0, #0
BEQ             loc_165C
LDR             R5, =(aQdreaderJni - 0x168A)
MOVS            R0, #3
LDR             R2, =(aJni0 - 0x168C)
ADD             R5, PC  ; "QDReader_Jni"
ADD             R2, PC  ; "JNI:0"
MOV             R1, R5
BLX             __android_log_print
LDR             R3, [R4]
LDR             R1, [SP,#0x58+arg5]
MOVS            R2, #0
MOV             R0, R4
LDR.W           R3, [R3,#0x2A4]
BLX             R3
LDR             R2, [SP,#0x58+src]
LDR             R1, [SP,#0x58+var_34]
LDR             R3, [SP,#0x58+ptr]
STR             R2, [SP,#0x58+var_54]
LDR             R2, =(aBookidSChapter - 0x16B0)
STR             R1, [SP,#0x58+var_58]
MOV             R1, R5
ADD             R2, PC  ; "bookid: %s,chapterid: %s,userid: %s,ime"...
STR             R0, [SP,#0x58+var_3C]
STR             R0, [SP,#0x58+var_50]
MOVS            R0, #3
BLX             __android_log_print
LDR             R2, =(aJni1 - 0x16C2)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:1"
BLX             __android_log_print
LDR             R1, [SP,#0x58+src] ; src
MOV             R0, R8  ; dest
BLX             strcpy
LDR             R1, [SP,#0x58+var_3C] ; src
MOV             R0, R8  ; dest
BLX             strcat
LDR             R1, [SP,#0x58+var_34] ; src
BLX             strcat
LDR             R1, =(a2eee1433a152e8 - 0x16E0)
ADD             R1, PC  ; "2EEE1433A152E84B3756301D8FA3E69A"
BLX             strcat
LDR             R2, =(aJni2 - 0x16EC)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:2"
BLX             __android_log_print
LDR             R3, [R4]
MOV             R1, R8
MOV             R0, R4
LDR.W           R3, [R3,#0x29C]
BLX             R3
LDR             R2, =(aJni3 - 0x1702)
MOV             R1, R5
ADD             R2, PC  ; "JNI:3"
STR             R0, [SP,#0x58+var_38]
MOVS            R0, #3
BLX             __android_log_print
LDR             R3, [R4]
LDR             R2, =(aS - 0x1718)
MOV             R1, R10
MOV             R0, R4
LDR.W           R12, [R3,#0x1C4]
ADD             R2, PC  ; "s"
LDR             R3, =(aLjavaLangStrin_1 - 0x171C)
ADD             R3, PC  ; "(Ljava/lang/String;Ljava/lang/String;)L"...
BLX             R12
LDR             R2, =(aJni4 - 0x1724)
MOV             R1, R5
ADD             R2, PC  ; "JNI:4"
MOV             R9, R0
MOVS            R0, #3
BLX             __android_log_print
CMP.W           R9, #0
BEQ             loc_165C
LDR             R2, =(aSha1idD - 0x173C)
MOV             R3, R9
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "sha1id:%d"
BLX             __android_log_print
LDR             R2, =(aJni5 - 0x1748)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:5"
BLX             __android_log_print
LDR             R3, [R4]
LDR             R1, [SP,#0x58+var_38]
MOV             R2, R9
MOV             R0, R4
STR             R1, [SP,#0x58+var_58]
MOV             R1, R10
LDR.W           R12, [R3,#0x1C8]
LDR             R3, [SP,#0x58+arg5]
BLX             R12
LDR             R3, [R4]
MOV             R2, R8
LDR             R1, [SP,#0x58+var_38]
LDR.W           R3, [R3,#0x2A8]
MOV             R9, R0
MOV             R0, R4
BLX             R3
LDR             R2, =(aJni6 - 0x1778)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:6"
BLX             __android_log_print
LDR             R3, [R4]
MOV             R1, R9
MOVS            R2, #0
MOV             R0, R4
LDR.W           R3, [R3,#0x2A4]
BLX             R3
LDR             R2, =(aSha1key1S - 0x1790)
MOV             R1, R5
ADD             R2, PC  ; "sha1key1 = %s"
MOV             R8, R0
MOVS            R0, #3
MOV             R3, R8
BLX             __android_log_print
LDR             R2, =(aJni7 - 0x17A2)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:7"
BLX             __android_log_print
MOV             R0, R8  ; s
BLX             strlen
CMP             R0, #0x17
BLS             loc_17BA
MOV             R0, R8
MOVS            R1, #0
MOVS            R2, #0x17
BL              substr
MOV             R6, R0

loc_17BA
LDR             R5, =(aQdreaderJni - 0x17C6)
MOV             R3, R6
LDR             R2, =(aJni8Sha1key2SS - 0x17CC)
MOVS            R0, #3
ADD             R5, PC  ; "QDReader_Jni"
STR.W           R8, [SP,#0x58+var_58]
ADD             R2, PC  ; "JNI:8 sha1key2:%s sha1key1:%s"
MOV             R1, R5
BLX             __android_log_print
LDR             R3, [R4]
MOV             R1, R9
MOV             R2, R8
MOV             R0, R4
LDR.W           R3, [R3,#0x2A8]
BLX             R3
LDR             R3, [R4]
MOV             R1, R6
MOV             R0, R4
LDR.W           R3, [R3,#0x29C]
BLX             R3
LDR             R2, =(aJni9 - 0x17F2)
MOV             R1, R5
ADD             R2, PC  ; "JNI:9"
STR             R0, [SP,#0x58+var_38]
MOVS            R0, #3
BLX             __android_log_print
MOV             R1, R6  ; src
MOV             R0, R7  ; dest
BLX             strcpy
LDR             R1, [SP,#0x58+var_3C] ; src
MOV             R0, R7  ; dest
BLX             strcat
LDR             R2, =(aJni10 - 0x1812)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:10"
BLX             __android_log_print
LDR             R3, [R4]
MOV             R1, R7
MOV             R0, R4
LDR.W           R3, [R3,#0x29C]
BLX             R3
LDR             R2, =(aJni11 - 0x1828)
MOV             R1, R5
ADD             R2, PC  ; "JNI:11"
MOV             R9, R0
MOVS            R0, #3
BLX             __android_log_print
LDR             R3, [R4]
LDR             R2, =(aM - 0x183E)
MOV             R0, R4
LDR             R1, [SP,#0x58+var_30]
LDR.W           R12, [R3,#0x1C4]
ADD             R2, PC  ; "m"
LDR             R3, =(aLjavaLangStrin_1 - 0x1842)
ADD             R3, PC  ; "(Ljava/lang/String;Ljava/lang/String;)L"...
BLX             R12
LDR             R2, =(aJni12 - 0x184A)
MOV             R1, R5
ADD             R2, PC  ; "JNI:12"
MOV             R10, R0
MOVS            R0, #3
BLX             __android_log_print
CMP.W           R10, #0
BEQ.W           loc_165C
LDR             R2, =(aJni13 - 0x1862)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:13"
BLX             __android_log_print
LDR             R3, [R4]
LDR             R1, [SP,#0x58+src]
MOV             R0, R4
LDR.W           R3, [R3,#0x29C]
BLX             R3
LDR             R3, [R4]
MOV             R2, R10
STR.W           R9, [SP,#0x58+var_58]
LDR             R1, [SP,#0x58+var_30]
LDR.W           R12, [R3,#0x1C8]
MOV             R8, R0
MOV             R0, R4
MOV             R3, R8
BLX             R12
LDR             R3, [R4]
MOV             R2, R7
MOV             R1, R9
LDR.W           R3, [R3,#0x2A8]
MOV             R10, R0
MOV             R0, R4
BLX             R3
LDR             R2, =(aJni14 - 0x18A0)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:14"
BLX             __android_log_print
LDR             R3, [R4]
MOV             R1, R10
MOVS            R2, #0
MOV             R0, R4
LDR.W           R3, [R3,#0x2A4]
BLX             R3
LDR             R2, =(aJni15 - 0x18B8)
MOV             R1, R5
ADD             R2, PC  ; "JNI:15"
MOV             R7, R0
MOVS            R0, #3
BLX             __android_log_print
MOV             R0, R7  ; s
BLX             strlen
CMP             R0, #0x18
BLS             loc_18D6
MOV             R0, R7
MOVS            R1, #0
MOVS            R2, #0x17
BL              substr
MOV             R11, R0
B               loc_18DE

loc_18D6                ; dest
MOV             R0, R11
MOV             R1, R7  ; src
BLX             strcpy

loc_18DE
LDR             R3, [R4]
MOV             R2, R7
LDR             R5, =(aQdreaderJni - 0x18F0)
MOV             R0, R4
MOV             R1, R10
LDR.W           R3, [R3,#0x2A8]
ADD             R5, PC  ; "QDReader_Jni"
BLX             R3
LDR             R2, =(aJni16 - 0x18FA)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:16"
BLX             __android_log_print
LDR             R3, [R4]
MOV             R1, R11
MOV             R0, R4
LDR.W           R3, [R3,#0x29C]
BLX             R3
LDR             R2, =(aJni17 - 0x1910)
MOV             R1, R5
ADD             R2, PC  ; "JNI:17"
MOV             R9, R0
MOVS            R0, #3
BLX             __android_log_print
LDR             R3, [R4]
LDR             R2, =(aD_1 - 0x1926)
MOV             R0, R4
LDR             R1, [SP,#0x58+var_40]
LDR.W           R7, [R3,#0x1C4]
ADD             R2, PC  ; "d"
LDR             R3, =(aBljavaLangStri_0 - 0x192A)
ADD             R3, PC  ; "([BLjava/lang/String;)[B"
BLX             R7
LDR             R2, =(aJni18 - 0x1932)
MOV             R1, R5
ADD             R2, PC  ; "JNI:18"
MOV             R7, R0
MOVS            R0, #3
BLX             __android_log_print
CMP             R7, #0
BEQ.W           loc_165C
LDR             R2, =(aJni19 - 0x1948)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:19"
BLX             __android_log_print
LDR             R3, [R4]
MOV             R2, R7
STR.W           R9, [SP,#0x58+var_58]
LDR             R1, [SP,#0x58+var_40]
MOV             R0, R4
LDR.W           R12, [R3,#0x1C8]
LDR             R3, [SP,#0x58+arg3]
BLX             R12
LDR             R2, =(aJni20 - 0x1966)
MOV             R1, R5
ADD             R2, PC  ; "JNI:20"
MOV             R10, R0
MOVS            R0, #3
BLX             __android_log_print
LDR             R3, [R4]
LDR             R2, [SP,#0x58+var_38]
MOV             R0, R4
LDR             R1, [SP,#0x58+var_40]
STR             R2, [SP,#0x58+var_58]
MOV             R2, R7
LDR.W           R12, [R3,#0x1C8]
MOV             R3, R10
BLX             R12
LDR             R2, =(aJni21 - 0x1988)
MOV             R1, R5
ADD             R2, PC  ; "JNI:21"
MOV             R7, R0
MOVS            R0, #3
BLX             __android_log_print
LDR             R3, [R4]
MOV             R0, R4
LDR             R1, [SP,#0x58+arg5]
LDR             R2, [SP,#0x58+var_3C]
LDR.W           R3, [R3,#0x2A8]
BLX             R3
LDR             R2, =(aJni22S - 0x19A6)
MOV             R3, R6
MOV             R1, R5
ADD             R2, PC  ; "JNI:22 %s"
MOVS            R0, #3
BLX             __android_log_print
LDR             R2, =(aJni23 - 0x19B4)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:23"
BLX             __android_log_print
LDR             R3, [R4]
MOV             R0, R4
MOV             R1, R9
MOV             R2, R11
LDR.W           R3, [R3,#0x2A8]
BLX             R3
LDR             R2, =(aJni24 - 0x19CE)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:24"
BLX             __android_log_print
LDR             R3, [R4]
MOV             R0, R4
MOV             R1, R8
LDR             R2, [SP,#0x58+src]
LDR.W           R3, [R3,#0x2A8]
BLX             R3
LDR             R2, =(aJni25 - 0x19E8)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:25"
BLX             __android_log_print
LDR             R0, [SP,#0x58+ptr] ; ptr
BLX             free
LDR             R2, =(aJni26 - 0x19FA)
MOV             R1, R5
MOVS            R0, #3
ADD             R2, PC  ; "JNI:26"
BLX             __android_log_print
LDR             R0, [SP,#0x58+var_34] ; ptr
BLX             free
LDR             R2, =(aJni27 - 0x1A0C)
MOVS            R0, #3
MOV             R1, R5
ADD             R2, PC  ; "JNI:27"
BLX             __android_log_print
MOV             R0, R7

loc_1A10
ADD             SP, SP, #0x34
POP.W           {R4-R11,PC}
; End of function Java_a_b_b
