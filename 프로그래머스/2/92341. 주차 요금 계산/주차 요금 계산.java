import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 차번호, 합산 주차 시간 
        Map<String, Integer> parkingInfo = new HashMap<>();
        // 차번호, 입차 시간
        Map<String, String> parking = new HashMap<>();
        for(String record: records){
            StringTokenizer st = new StringTokenizer(record);
            String time = st.nextToken();
            String carNum = st.nextToken();
            String type = st.nextToken();
            
            if(type.equals("IN")){
                parking.put(carNum, time);
            }else{
                String inTime = parking.get(carNum);
                int parkingTime = getParkingTime(time, inTime);
                parkingInfo.put(carNum, parkingInfo.getOrDefault(carNum, 0)+parkingTime);
                parking.remove(carNum);
            }
        }
        
        // 입차했지만 출차 안한 차들 주차시간 가산
        for(Map.Entry<String, String> entry: parking.entrySet()){
            String carNum = entry.getKey();
            String inTime = entry.getValue();
            int parkingTime = getParkingTime("23:59", inTime);
            parkingInfo.put(carNum, parkingInfo.getOrDefault(carNum, 0)+parkingTime);
        }
        
        // 요금 계산 전 차 번호 오름차순 정렬
        List<int[]> carList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: parkingInfo.entrySet()){
            carList.add(new int[]{Integer.parseInt(entry.getKey()), entry.getValue()});
        }
        
        Collections.sort(carList, (a, b)-> a[0]-b[0]);
        
        int[] answer = new int[carList.size()];
        
        for(int i=0; i<carList.size(); i++){
            int parkingTime = carList.get(i)[1];
            if(parkingTime<=fees[0]){
                answer[i] = fees[1];
            }else{
                answer[i] = fees[1]+(int)Math.ceil((parkingTime - fees[0])/(double)fees[2])*fees[3];
            }
        }
        
        return answer;
    }
    
    public static int getParkingTime(String out, String in){
        StringTokenizer st1 = new StringTokenizer(out, ":");
        StringTokenizer st2 = new StringTokenizer(in, ":");
        int h = Integer.parseInt(st1.nextToken())-Integer.parseInt(st2.nextToken());
        int m = Integer.parseInt(st1.nextToken())-Integer.parseInt(st2.nextToken());
        if(m<0){
            h--;
            m += 60;
        }
        
        return h*60+m;
    }
}