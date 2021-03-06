
package rsa.domain;


import rsa.algorithms.ExtendedEuclideanAlgorithm;
import rsa.algorithms.ProbablePrime;
import rsa.algorithms.RandomNumber;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 
 * @author LauriKajakko
 */
public class KeyGenerator {
    private ProbablePrime probablePrime;
    private ExtendedEuclideanAlgorithm eea;

    private BigInteger p;
    private BigInteger q;
    private BigInteger phi;
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    
    private RandomNumber random;
    
    private PublicKey publicKey;
    private PrivateKey privateKey;
    
    
    /**
     *  Generates keys when class gets called
     *  Keys can be called through getters
     *
     *  The variables have been named the same as wikipedia's pseudocode
     * 
     * @param size Size of the key in bit length
     */
    public KeyGenerator(int size) {
        probablePrime = new ProbablePrime(size, 10, new Random());
        eea = new ExtendedEuclideanAlgorithm();

        random = new RandomNumber();

        p = probablePrime.generateLargePrime();
        q = probablePrime.generateLargePrime();
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = new BigInteger("65537");
        n = p.multiply(q);
        d = eea.modularInverse(e, phi);

        publicKey = new PublicKey(e, n);
        privateKey = new PrivateKey(d, n);
    }
    
    /**
     * 
     * @return private key 
     */
    public PrivateKey getPrivateKey() {
        return privateKey;
    }
    
    /**
     * 
     * @return public key 
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }
    
    
    
}
