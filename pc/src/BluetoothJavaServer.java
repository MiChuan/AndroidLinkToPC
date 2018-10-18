import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

/**
 * �����ķ������ˡ�������Windows����ϵͳ��PC�����ϣ��� �ȴ��ֻ��ͻ��˻������������豸�����ӡ� �ô����ļ���һ��Java
 * SE�����ļ���������Windows PC�ϡ�
 * 
 * Java��Windows PC��ʵ���������������������⣺ 
 * 
 * 1��bluecove-2.1.1-SNAPSHOT.jar
 * ���أ�http://snapshot.bluecove.org/distribution/download/2.1.1-SNAPSHOT/2.1.1-SNAPSHOT.63/
 * 
 * 2��commons-io-2.6.jar
 * ���أ�http://commons.apache.org/proper/commons-io/download_io.cgi
 * 
 * @author fly
 *
 */
public class BluetoothJavaServer {
	private StreamConnectionNotifier mStreamConnectionNotifier = null;
	private StreamConnection mStreamConnection = null;

	public static void main(String[] args) {
		new BluetoothJavaServer();
	}

	public BluetoothJavaServer() {
		try {
			// �������˵�UUID������ֻ��˵�UUID��һ�¡��ֻ��˵�UUID��Ҫȥ���м��-�ָ����
			mStreamConnectionNotifier = (StreamConnectionNotifier) Connector
					.open("btspp://localhost:0000110100001000800000805F9B34FB");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// �����̶߳�д�����Ͻ��պͷ��͵����ݡ�
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("�������˿�ʼ����...");
					while (true) {
						mStreamConnection = mStreamConnectionNotifier.acceptAndOpen();
						System.out.println("��������");

						InputStream is = mStreamConnection.openInputStream();

						byte[] buffer = new byte[1024];

						System.out.println("��ʼ������...");
						// �����ݡ�
						while (is.read(buffer) != -1) {
							String s = new String(buffer);
							System.out.println(s);
						}

						is.close();
						mStreamConnection.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}).start();
	}
}
