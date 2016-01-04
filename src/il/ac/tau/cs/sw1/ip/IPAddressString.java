package il.ac.tau.cs.sw1.ip;

public class IPAddressString implements IPAddress {
	private String ip;

	IPAddressString(String address) {
		this.ip = address;
	}

	@Override
	public String toString() {
		return ip;
	}

	@Override
	public boolean equals(IPAddress other) {
		if (ip.equals(other.toString())) {
			return true;
		}
		return false;
	}

	@Override
	public int getOctet(int index) {
		String one, two, three, four, newIp;
		// Substrigs and returns the specified octet.
		switch (index) {
		case 0:
			one = ip.substring(0, ip.indexOf("."));
			return Integer.valueOf(one);
		case 1:
			newIp = ip.substring(ip.indexOf(".") + 1, ip.lastIndexOf("."));
			two = newIp.substring(0, newIp.indexOf("."));
			return Integer.valueOf(two);
		case 2:
			newIp = ip.substring(ip.indexOf("."), ip.lastIndexOf("."));
			three = newIp.substring(newIp.lastIndexOf(".") + 1, newIp.length());
			return Integer.valueOf(three);
		case 3:
			four = ip.substring(ip.lastIndexOf(".") + 1, ip.length());
			return Integer.valueOf(four);
		}
		// We assume that the input is correct, so this won't happen.
		return 0;
	}

	@Override
	public boolean isPrivateNetwork() {
		int one, two, three, four;
		boolean flag = false;
		one = getOctet(0);
		two = getOctet(1);
		three = getOctet(2);
		four = getOctet(3);
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
