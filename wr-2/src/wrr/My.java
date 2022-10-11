package wrr;

import java.util.StringTokenizer;

class My {
    int thread;
    String type;
    double arrive_time;
    double operate_time;

    My(String s) {
        StringTokenizer st = new StringTokenizer(s);
        thread = Integer.parseInt(st.nextToken());
        type = st.nextToken();
        arrive_time = Double.parseDouble(st.nextToken());
        operate_time = Double.parseDouble(st.nextToken());
    }
}

