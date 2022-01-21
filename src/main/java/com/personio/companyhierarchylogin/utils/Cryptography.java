package com.personio.companyhierarchylogin.utils;

import java.math.BigInteger;

public class Cryptography {
    private static Cryptography cripto;
    private static BigInteger p;	// Primo 1
    private static BigInteger q;	// Primo 2
    private static BigInteger n;	// Primo 1 * Primo 2
    private static BigInteger e;

    public static Cryptography getInstance() {
        if(Cryptography.cripto == null)
            cripto = new Cryptography();
        return cripto;
    }

    private Cryptography() {
        p = new BigInteger("10875188780489316290471302410205522977749890346996251490114014814906672151809579532394516949294116616609234962867357759475118054548604334831364750926801253");
        q = new BigInteger("9694369307411322561552988547799012232808366434941867290010508438799422930037551589516244460178008152555207890861671117884210783269082855616091500495743227");
        e = new BigInteger("3");
        n = new BigInteger("105428096325879598794323277910310119355955408860312998768234869030926745593557817842028752743553457887511326980550238127254315605808886671494416082925713041986222486139713681579623054670975648273418593028787925151414301358873695894001133160823557498848295296182984516512401446903573915925951601153193949863431");
    }

    public String encode(String senha) {
        return new BigInteger(senha.getBytes()).modPow(e, n).toString();
    }
}
