package crypto;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Scanner;

public class RabinTest {
	public static void main(String[] args) throws UnsupportedEncodingException {

		BigInteger[] key = Rabin.genKey(512);
		BigInteger N = key[0];
		BigInteger p = key[1];
		BigInteger q = key[2];
		System.out.println("N: " + N);
		System.out.println("(p,q) = " + p + " , " + q);

		Scanner scanner = new Scanner(System.in);
		String s = "Khải Trần Văn";
		System.out.println("Nhập thông điểm muốn gửi:");
		s = scanner.nextLine();
		System.out.println("Plaintext: " + s);
		BigInteger m = new BigInteger(s.getBytes(Charset.forName("utf-8")));
		BigInteger c = Rabin.encrypt(m, N);
		System.out.println("c = " + c);

		boolean worked = false;
		String dec = "";
		BigInteger[] m2 = Rabin.decrypt(c, p, q);
		for (BigInteger b : m2) {
			dec = "";
			dec = new String(b.toByteArray(), Charset.forName("utf-8"));
			if (dec.equals(s)) {
				worked = true;
				break;
			}
		}
		if (worked) {
			System.out.println("Giả mã: " + dec);
		} else {
			System.out.println("error");
		}

	}
}
