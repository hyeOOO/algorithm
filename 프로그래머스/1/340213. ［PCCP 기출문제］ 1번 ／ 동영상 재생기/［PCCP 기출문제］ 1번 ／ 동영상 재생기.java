class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
    	// 문제에서 주어진 시간들을 int로 변환
        int len_s = second(video_len);
        int current_s = second(pos);
        int op_start_s = second(op_start);
        int op_end_s = second(op_end);

		// pos가 오프닝 시작,종료시간 사이에 있을 경우 -> 오프닝 종료시간으로 이동
        if(current_s >= op_start_s && current_s <= op_end_s) {
            current_s = op_end_s;
        }

        for(String command : commands) {
        	// prev일 경우 10초 전으로 이동 (단, 0보단 작아서는 안된다)
            // next일 경우 10초 후로 이동 (단, 동영상 길이보다 커서는 안된다)
            if(command.equals("prev")) {
                current_s = Math.max(current_s - 10, 0);
            } else {
                current_s = Math.min(current_s + 10, len_s);
            }

			// 이동한 시간이 오프닝 시간으로 들어왔다면 -> 오프닝 종료시간으로 이동
            if(current_s >= op_start_s && current_s <= op_end_s) {
                current_s = op_end_s;
            }
        }
        // 다시 분, 초로 변환 뒤 리턴
        String minute = String.format("%02d", (int) Math.floor((double) current_s / 60));
        String second = String.format("%02d", current_s % 60);
        return minute+":"+second;
    }
    
    private static int second(String time) {
        String[] time_ms = time.split(":");
        return (Integer.parseInt(time_ms[0]) * 60) +  Integer.parseInt(time_ms[1]);
    }
}