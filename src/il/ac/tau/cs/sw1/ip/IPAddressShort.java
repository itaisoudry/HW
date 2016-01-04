package il.ac.tau.cs.sw1.ip;

public class IPAddressShort implements IPAddress {
	private short[] ip;

	IPAddressShort(short[] address) {
		this.ip = address;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < ip.length; i++) {
			builder.append(ip[i]);
			builder.append(".");
		}
		// Deletes the last "."
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

	@Override
	public boolean equals(IPAddress other) {
		if (ip.toString().equals(other.toString()))
			return true;
		return false;
	}

	@Override
	public int getOctet(int index) {
		switch (index) {
		case 0:
			return ip[0];
		case 1:
			return ip[1];
		case 2:
			return ip[2];
		case 3:
			return ip[3];
		}
		// This never happens since we assume that the input is correct.
		return 0;

	}

	@Override
	public boolean isPrivateNetwork() {
		short one, two, three, four;
		boolean flag = false;
		one = ip[0];
		two = ip[1];
		three = ip[2];
		four = ip[3];
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
