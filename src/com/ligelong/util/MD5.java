/**
 * Create on 2011-11-17
 */
package com.ligelong.util;

/**
 * 
 * <code>MD5</code>
 *
 * @author David Gong
 */
public class MD5 {
	private int buf[]; // These were originally unsigned ints.
	// This Java code makes an effort to avoid sign traps.
	// buf[] is where the hash accumulates.
	private long bits; // This is the count of bits hashed so far.
	private byte in[]; // This is a buffer where we stash bytes until we have
	// enough (64) to perform a transform operation.
	private int inint[];

	// inint[] used and discarded inside transform(),
	// but why allocate it over and over?
	// (In the C version this is allocated on the stack.)

	public MD5() {
		buf = new int[4];
		// fill the hash accumulator with a seed value
		buf[0] = 0x67452301;
		buf[1] = 0xefcdab89;
		buf[2] = 0x98badcfe;
		buf[3] = 0x10325476;

		// initially, we've hashed zero bits
		bits = 0L;

		in = new byte[64];
		inint = new int[16];
	}

	public void update(byte[] newbuf) {
		update(newbuf, 0, newbuf.length);
	}

	public void update(byte[] newbuf, int length) {
		update(newbuf, 0, length);
	}

	public void update(byte[] newbuf, int bs, int buflen) {
		int t;
		int len = buflen;
		int bufstart = bs;

		// shash old bits value for the "Bytes already in" computation
		// just below.
		t = (int) bits; // (int) cast should just drop high bits, I hope

		/* update bitcount */
		/*
		 * the C code used two 32-bit ints separately, and carefully ensured
		 * that the carry carried. Java has a 64-bit long, which is just what
		 * the code really wants.
		 */
		bits += len << 3;

		t = (t >>> 3) & 0x3f; /* Bytes already in this->in */

		/* Handle any leading odd-sized chunks */
		/* (that is, any left-over chunk left by last update() */

		if (t != 0) {
			int p = t;
			t = 64 - t;
			if (len < t) {
				System.arraycopy(newbuf, bufstart, in, p, len);
				return;
			}
			System.arraycopy(newbuf, bufstart, in, p, t);
			transform();
			bufstart += t;
			len -= t;
		}

		/* Process data in 64-byte chunks */
		while (len >= 64) {
			System.arraycopy(newbuf, bufstart, in, 0, 64);
			transform();
			bufstart += 64;
			len -= 64;
		}

		/* Handle any remaining bytes of data. */
		/* that is, stash them for the next update(). */
		System.arraycopy(newbuf, bufstart, in, 0, len);
	}

	/*
	 * Final wrapup - pad to 64-byte boundary with the bit pattern 1 0* (64-bit
	 * count of bits processed, MSB-first)
	 */
	public void md5final(byte[] digest) {
		/* "final" is a poor method name in Java. :v) */
		int count;
		int p; // in original code, this is a pointer; in this java code
		// it's an index into the array this->in.

		/* Compute number of bytes mod 64 */
		count = (int) ((bits >>> 3) & 0x3F);

		/*
		 * Set the first char of padding to 0x80. This is safe since there is
		 * always at least one byte free
		 */
		p = count;
		in[p++] = (byte) 0x80;

		/* Bytes of padding needed to make 64 bytes */
		count = 64 - 1 - count;

		/* Pad out to 56 mod 64 */
		if (count < 8) {
			/* Two lots of padding: Pad the first block to 64 bytes */
			zeroByteArray(in, p, count);
			transform();

			/* Now fill the next block with 56 bytes */
			zeroByteArray(in, 0, 56);
		} else {
			/* Pad block to 56 bytes */
			zeroByteArray(in, p, count - 8);
		}

		/* Append length in bits and transform */
		// Could use a PUT_64BIT... func here. This is a fairly
		// direct translation from the C code, where bits was an array
		// of two 32-bit ints.
		int lowbits = (int) bits;
		int highbits = (int) (bits >>> 32);
		PUT_32BIT_LSB_FIRST(in, 56, lowbits);
		PUT_32BIT_LSB_FIRST(in, 60, highbits);

		transform();
		PUT_32BIT_LSB_FIRST(digest, 0, buf[0]);
		PUT_32BIT_LSB_FIRST(digest, 4, buf[1]);
		PUT_32BIT_LSB_FIRST(digest, 8, buf[2]);
		PUT_32BIT_LSB_FIRST(digest, 12, buf[3]);

		/* zero sensitive data */
		/*
		 * notice this misses any sneaking out on the stack. The C version uses
		 * registers in some spots, perhaps because they care about this.
		 */
		zeroByteArray(in);
		zeroIntArray(buf);
		bits = 0;
		zeroIntArray(inint);
	}

	public static String change(String str) {
		// This main() method was created to easily test
		// this class. It hashes whatever's on System.in.

		byte buf[] = new byte[397];
		// arbitrary buffer length designed to irritate update()
		//int rc;
		MD5 md = new MD5();
		byte out[] = new byte[16];
		//int i;
		//int len = 0;
		buf = str.getBytes();
		md.update(buf, str.length());
		md.md5final(out);
		return dumpBytes(out);
	}

	// ///////////////////////////////////////////////////////////////////
	// Below here ye will only finde private functions //
	// ///////////////////////////////////////////////////////////////////

	// There must be a way to do these functions that's
	// built into Java, and I just haven't noticed it yet.

	private void zeroByteArray(byte[] a) {
		zeroByteArray(a, 0, a.length);
	}

	private void zeroByteArray(byte[] a, int start, int length) {
		setByteArray(a, (byte) 0, start, length);
	}

	private void setByteArray(byte[] a, byte val, int start, int length) {
		int i;
		int end = start + length;
		for (i = start; i < end; i++) {
			a[i] = val;
		}
	}

	private void zeroIntArray(int[] a) {
		zeroIntArray(a, 0, a.length);
	}

	private void zeroIntArray(int[] a, int start, int length) {
		setIntArray(a, 0, start, length);
	}

	private void setIntArray(int[] a, int val, int start, int length) {
		int i;
		int end = start + length;
		for (i = start; i < end; i++) {
			a[i] = val;
		}
	}

	// In the C version, a call to MD5STEP is a macro-in-a-macro.
	// In this Java version, we pass an Fcore object to represent the
	// inner macro, and the MD5STEP() method performs the work of
	// the outer macro. It would be good if this could all get
	// inlined, but it would take a pretty aggressive compiler to
	// inline away the dynamic method lookup made by MD5STEP to
	// get to the Fcore.f function.

	private abstract class Fcore {
		/**
         * 
         */
        public Fcore() {
        }

        abstract int f(int x, int y, int z);
	}

	private Fcore F1 = new Fcore() {
		int f(int x, int y, int z) {
			return (z ^ (x & (y ^ z)));
		}
	};
	private Fcore F2 = new Fcore() {
		int f(int x, int y, int z) {
			return (y ^ (z & (x ^ y)));
		}
	};
	private Fcore F3 = new Fcore() {
		int f(int x, int y, int z) {
			return (x ^ y ^ z);
		}
	};
	private Fcore F4 = new Fcore() {
		int f(int x, int y, int z) {
			return (y ^ (x | ~z));
		}
	};

	private int MD5STEP(Fcore f, int w1, int x, int y, int z, int data, int s) {
	    int w = w1;
		w += f.f(x, y, z) + data;
		w = w << s | w >>> (32 - s);
		w += x;
		return w;
	}

	private void transform() {
		/* load in[] byte array into an internal int array */
		int i;
		int[] inint1 = new int[16];

		for (i = 0; i < 16; i++) {
			inint1[i] = GET_32BIT_LSB_FIRST(in, 4 * i);
		}

		int a, b, c, d;
		a = buf[0];
		b = buf[1];
		c = buf[2];
		d = buf[3];

		a = MD5STEP(F1, a, b, c, d, inint1[0] + 0xd76aa478, 7);
		d = MD5STEP(F1, d, a, b, c, inint1[1] + 0xe8c7b756, 12);
		c = MD5STEP(F1, c, d, a, b, inint1[2] + 0x242070db, 17);
		b = MD5STEP(F1, b, c, d, a, inint1[3] + 0xc1bdceee, 22);
		a = MD5STEP(F1, a, b, c, d, inint1[4] + 0xf57c0faf, 7);
		d = MD5STEP(F1, d, a, b, c, inint1[5] + 0x4787c62a, 12);
		c = MD5STEP(F1, c, d, a, b, inint1[6] + 0xa8304613, 17);
		b = MD5STEP(F1, b, c, d, a, inint1[7] + 0xfd469501, 22);
		a = MD5STEP(F1, a, b, c, d, inint1[8] + 0x698098d8, 7);
		d = MD5STEP(F1, d, a, b, c, inint1[9] + 0x8b44f7af, 12);
		c = MD5STEP(F1, c, d, a, b, inint1[10] + 0xffff5bb1, 17);
		b = MD5STEP(F1, b, c, d, a, inint1[11] + 0x895cd7be, 22);
		a = MD5STEP(F1, a, b, c, d, inint1[12] + 0x6b901122, 7);
		d = MD5STEP(F1, d, a, b, c, inint1[13] + 0xfd987193, 12);
		c = MD5STEP(F1, c, d, a, b, inint1[14] + 0xa679438e, 17);
		b = MD5STEP(F1, b, c, d, a, inint1[15] + 0x49b40821, 22);

		a = MD5STEP(F2, a, b, c, d, inint1[1] + 0xf61e2562, 5);
		d = MD5STEP(F2, d, a, b, c, inint1[6] + 0xc040b340, 9);
		c = MD5STEP(F2, c, d, a, b, inint1[11] + 0x265e5a51, 14);
		b = MD5STEP(F2, b, c, d, a, inint1[0] + 0xe9b6c7aa, 20);
		a = MD5STEP(F2, a, b, c, d, inint1[5] + 0xd62f105d, 5);
		d = MD5STEP(F2, d, a, b, c, inint1[10] + 0x02441453, 9);
		c = MD5STEP(F2, c, d, a, b, inint1[15] + 0xd8a1e681, 14);
		b = MD5STEP(F2, b, c, d, a, inint1[4] + 0xe7d3fbc8, 20);
		a = MD5STEP(F2, a, b, c, d, inint1[9] + 0x21e1cde6, 5);
		d = MD5STEP(F2, d, a, b, c, inint1[14] + 0xc33707d6, 9);
		c = MD5STEP(F2, c, d, a, b, inint1[3] + 0xf4d50d87, 14);
		b = MD5STEP(F2, b, c, d, a, inint1[8] + 0x455a14ed, 20);
		a = MD5STEP(F2, a, b, c, d, inint1[13] + 0xa9e3e905, 5);
		d = MD5STEP(F2, d, a, b, c, inint1[2] + 0xfcefa3f8, 9);
		c = MD5STEP(F2, c, d, a, b, inint1[7] + 0x676f02d9, 14);
		b = MD5STEP(F2, b, c, d, a, inint1[12] + 0x8d2a4c8a, 20);

		a = MD5STEP(F3, a, b, c, d, inint1[5] + 0xfffa3942, 4);
		d = MD5STEP(F3, d, a, b, c, inint1[8] + 0x8771f681, 11);
		c = MD5STEP(F3, c, d, a, b, inint1[11] + 0x6d9d6122, 16);
		b = MD5STEP(F3, b, c, d, a, inint1[14] + 0xfde5380c, 23);
		a = MD5STEP(F3, a, b, c, d, inint1[1] + 0xa4beea44, 4);
		d = MD5STEP(F3, d, a, b, c, inint1[4] + 0x4bdecfa9, 11);
		c = MD5STEP(F3, c, d, a, b, inint1[7] + 0xf6bb4b60, 16);
		b = MD5STEP(F3, b, c, d, a, inint1[10] + 0xbebfbc70, 23);
		a = MD5STEP(F3, a, b, c, d, inint1[13] + 0x289b7ec6, 4);
		d = MD5STEP(F3, d, a, b, c, inint1[0] + 0xeaa127fa, 11);
		c = MD5STEP(F3, c, d, a, b, inint1[3] + 0xd4ef3085, 16);
		b = MD5STEP(F3, b, c, d, a, inint1[6] + 0x04881d05, 23);
		a = MD5STEP(F3, a, b, c, d, inint1[9] + 0xd9d4d039, 4);
		d = MD5STEP(F3, d, a, b, c, inint1[12] + 0xe6db99e5, 11);
		c = MD5STEP(F3, c, d, a, b, inint1[15] + 0x1fa27cf8, 16);
		b = MD5STEP(F3, b, c, d, a, inint1[2] + 0xc4ac5665, 23);

		a = MD5STEP(F4, a, b, c, d, inint1[0] + 0xf4292244, 6);
		d = MD5STEP(F4, d, a, b, c, inint1[7] + 0x432aff97, 10);
		c = MD5STEP(F4, c, d, a, b, inint1[14] + 0xab9423a7, 15);
		b = MD5STEP(F4, b, c, d, a, inint1[5] + 0xfc93a039, 21);
		a = MD5STEP(F4, a, b, c, d, inint1[12] + 0x655b59c3, 6);
		d = MD5STEP(F4, d, a, b, c, inint1[3] + 0x8f0ccc92, 10);
		c = MD5STEP(F4, c, d, a, b, inint1[10] + 0xffeff47d, 15);
		b = MD5STEP(F4, b, c, d, a, inint1[1] + 0x85845dd1, 21);
		a = MD5STEP(F4, a, b, c, d, inint1[8] + 0x6fa87e4f, 6);
		d = MD5STEP(F4, d, a, b, c, inint1[15] + 0xfe2ce6e0, 10);
		c = MD5STEP(F4, c, d, a, b, inint1[6] + 0xa3014314, 15);
		b = MD5STEP(F4, b, c, d, a, inint1[13] + 0x4e0811a1, 21);
		a = MD5STEP(F4, a, b, c, d, inint1[4] + 0xf7537e82, 6);
		d = MD5STEP(F4, d, a, b, c, inint1[11] + 0xbd3af235, 10);
		c = MD5STEP(F4, c, d, a, b, inint1[2] + 0x2ad7d2bb, 15);
		b = MD5STEP(F4, b, c, d, a, inint1[9] + 0xeb86d391, 21);

		buf[0] += a;
		buf[1] += b;
		buf[2] += c;
		buf[3] += d;
	}

	private int GET_32BIT_LSB_FIRST(byte[] b, int off) {
		return (b[off + 0] & 0xff) | ((b[off + 1] & 0xff) << 8)
				| ((b[off + 2] & 0xff) << 16)
				| ((b[off + 3] & 0xff) << 24);
	}

	private void PUT_32BIT_LSB_FIRST(byte[] b, int off, int value) {
		b[off + 0] = (byte) (value & 0xff);
		b[off + 1] = (byte) ((value >> 8) & 0xff);
		b[off + 2] = (byte) ((value >> 16) & 0xff);
		b[off + 3] = (byte) ((value >> 24) & 0xff);
	}

	// These are debug routines I was using while trying to
	// get this code to generate the same hashes as the C version.
	// (IIRC, all the errors were due to the absence of unsigned
	// ints in Java.)

	private static String dumpBytes(byte[] bytes) {
		int i;
		StringBuffer sb = new StringBuffer();
		for (i = 0; i < bytes.length; i++) {
			if (i % 32 == 0 && i != 0) {
				sb.append("\n");
			}
			String s = Integer.toHexString(bytes[i]);
			if (s.length() < 2) {
				s = "0" + s;
			}
			if (s.length() > 2) {
				s = s.substring(s.length() - 2);
			}
			sb.append(s);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(MD5.change("chnxhl"));
	}
}