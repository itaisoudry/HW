package il.ac.tau.cs.sw1.ip;

import java.nio.ByteBuffer;

public class IPAddressInt implements IPAddress {
	private ByteBuffer buffer = ByteBuffer.allocate(4);

	IPAddressInt(int address) {
		buffer.put(0, (byte) (address >> 24));
		buffer.put(1, (byte) (address >> 16));
		buffer.put(2, (byte) (address >> 8));
		buffer.put(3, (byte) (address >> 0));
	}

	@Override
	public String toString() {
		//Construct a string from the bytes
		int one, two, three, four;
		one = (int) (buffer.get(0) & 0xFF);
		two = (int) (buffer.get(1) & 0xFF);
		three = (int) (buffer.get(2) & 0xFF);
		four = (int) (buffer.get(3) & 0xFF);
		StringBuilder builder = new StringBuilder();
		builder.append(one);
		builder.append(".");
		builder.append(two);
		builder.append(".");
		builder.append(three);
		builder.append(".");
		builder.append(four);

		return builder.toString();

	}

	@Override
	public boolean equals(IPAddress other) {
		if (this.toString().equals(other.toString()))
			return true;
		return false;
	}

	@Override
	public int getOctet(int index) {
		switch (index) {
		case 0:
			return buffer.get(0) & 0xFF;
		case 1:
			return buffer.get(1) & 0xFF;
		case 2:
			return buffer.get(2) & 0xFF;
		case 3:
			return buffer.get(3) & 0xFF;
		}
		// This part will never be executed.
		return 0;
	}

	@Override
	public boolean isPrivateNetwork() {
		int one, two, three, four;
		boolean flag = false;
		one = (int) (buffer.get(0) & 0xFF);
		two = (int) (buffer.get(1) & 0xFF);
		three = (int) (buffer.get(2) & 0xFF);
		four = (int) (buffer.get(3) & 0xFF);
		// Each if checks for a private network.
		if ((one == 10) && (two < 255) && (three < 255) && (four < 255)) {
			flag = true;
		} else if ((one == 172) && (two > 16 && two < 31) && (three < 255) && (four < 255)) {
			flag = true;
		} else if ((one == 192) && (two == 168) && (three < 255) && (four < 255)) {
			flag = true;
		} else if ((one == 169) && (one == 254) && (three < 255) && (four < 255)) {
			flag = true;
		}
		return flag;
	}

}
