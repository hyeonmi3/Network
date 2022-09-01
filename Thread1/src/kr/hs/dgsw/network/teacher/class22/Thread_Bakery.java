package kr.hs.dgsw.network.teacher.class22;

public class Thread_Bakery extends Thread {

	public int thread_id;
	public static final int numberOfThreads = 100;
	public static boolean[] choosing = new boolean[numberOfThreads];																	 
	public static int[] ticket = new int[numberOfThreads];
	public static int count=0;
	
	public Thread_Bakery(int id) {
		thread_id = id;
	}

	public void run() {
		lock(thread_id);
			System.out.printf(thread_id + " �Ӱ迵�� �۾��� ");
			System.out.println("������� �۾� �Ϸ� ��: " + count);
			for(int i=0 ; i<500 ; i++) { /* nothing */ }
			count++;
		unlock(thread_id);

	}

	public void lock(int id) {
		choosing[id] = true;										// Ƽ�� �߱� ��
		ticket[id] = findMax() + 1;									// ������� �߱޵� ��ȣ�� ���� ��ȣ �߱�
		choosing[id] = false;										// Ƽ�� �߱� �Ϸ�

		for (int j = 0; j < numberOfThreads; j++) {
			if (j == id) continue;
			while (choosing[j]) { /* nothing */ }					// Ƽ�� ��ȣ�� �ο��޴� ����� ������ ���

			
			while (ticket[j] != 0 && 								// Ƽ�� ��ȣ�� 0���� �ƴϸ鼭
					(ticket[id] > ticket[j] || 						// ���� Ƽ�Ϲ�ȣ���� ���� Ƽ�Ϲ�ȣ�� �ְų�
							(ticket[id] == ticket[j] && id > j)		// ���� Ƽ�Ϲ�ȣ�� �����鼭 �۾���ȣ�� ������ ������ ������
							)) { /* nothing */ }					// ��� ���
						 
		}
	}

	private void unlock(int id) {
		ticket[id] = 0;
	}

	private int findMax() {
		
		int m = ticket[0];

		for (int i = 1; i < ticket.length; i++) {
			if (ticket[i] > m)
				m = ticket[i];
		}
		return m;
	}
}