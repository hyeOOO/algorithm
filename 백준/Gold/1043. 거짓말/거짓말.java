import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static boolean[] knowsTruth;
	static List<List<Integer>> party = new ArrayList<>();
	static List<List<Integer>> peoples = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
        knowsTruth = new boolean[N + 1];

		for(int i=0; i<=N; i++) {
			peoples.add(new ArrayList<>());
		}
		
		for(int i=0; i<=M; i++) {
			party.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		int knownCount = Integer.parseInt(st.nextToken());
		if(knownCount>0) {
			for (int i = 0; i < knownCount; i++) {
                int person = Integer.parseInt(st.nextToken());
                knowsTruth[person] = true;
            }
		}
		
		for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());

            for (int j = 0; j < count; j++) {
                int people = Integer.parseInt(st.nextToken());
                party.get(i).add(people);
                peoples.get(people).add(i);
            }
        }
		
//		System.out.println(party);
//		System.out.println(peoples);
		
		spreadTruth();
		
		int count = 0;
		for(int i=1; i<=M; i++) {
			boolean canLie = true;
			
			for(int person : party.get(i)) {
				if(knowsTruth[person]) {
					canLie = false;
					break;
				}
			}
			
			if(canLie) count++;
		}
		
		System.out.println(count);
	}
	
	public static void spreadTruth() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			if(knowsTruth[i]) {
				q.offer(i);
				visited[i] = true;
			}
		}
		
		while(!q.isEmpty()) {
			int person = q.poll();
			
			for(int partyId : peoples.get(person)) {
				for(int joinPeople : party.get(partyId)) {
					if(!visited[joinPeople]) {
						visited[joinPeople] = true;
						knowsTruth[joinPeople] = true;
						q.offer(joinPeople);
					}
				}
			}
		}
	}
}
