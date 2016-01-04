package il.ac.tau.cs.sw1.ip;

public class TestIPAddress {

	public static void main(String[] args) {
		int address1 = -1062731775; // 192.168.0.1
		short[] address2 = { 192, 168, 0, 0 }; // 10.1.255.1

		IPAddress ip1 = IPAddressFactory.createAddress(address1);
		IPAddress ip2 = IPAddressFactory.createAddress(address2);
		IPAddress ip3 = IPAddressFactory.createAddress("127.0.0.1");
		System.out.println(ip1.toString());
		System.out.println(ip2.toString());

		for (int i = 0; i < 4; i++) {
			System.out.println(ip1.getOctet(i));
		}

		System.out.println("equals: " + ip1.equals(ip2));
		System.out.println("Is private Network: " + ip2.isPrivateNetwork());
	}
}
