package vivo;

import java.io.*;
import java.util.*;

/**
 * Welcome to vivo !
 */

public class Main2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        String[] input = inputStr.split(" ");
        int totalDisk = Integer.parseInt(input[0]);
        int totalMemory = Integer.parseInt(input[1]);
        List<Service> services = parseServices(input[2].split("#"));
        int output = solution(totalDisk, totalMemory, services);
        System.out.println(output);
    }

    private static int solution(int totalDisk, int totalMemory, List<Service> services) {
        int num = services.size();
        int i, j, k;
        int ret[][] = new int[totalMemory + 1][totalDisk + 1];
        int INF = -66536;
        for (i = 1; i < totalMemory; i++) {
            for (j = 1; j < totalDisk; j++) {
                ret[i][j] = INF;
            }
        }
        for (k = 0; k < num; k++) {
            for (i = totalMemory; i >= services.get(k).memory; i--) {
                for (j = totalDisk; j >= services.get(k).disk; j--) {
                    ret[i][j] = Math.max(ret[i][j], ret[i - services.get(k).memory][j - services.get(k).disk] + services.get(k).users);
                }
            }
        }

        return ret[totalMemory][totalDisk];
    }

    private static List<Service> parseServices(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new ArrayList<Service>(0);
        }
        List<Service> services = new ArrayList<>(strArr.length);
        for (int i = 0; i < strArr.length; i++) {
            String[] serviceArr = strArr[i].split(",");
            int disk = Integer.parseInt(serviceArr[0]);
            int memory = Integer.parseInt(serviceArr[1]);
            int users = Integer.parseInt(serviceArr[2]);
            services.add(new Service(disk, memory, users));
        }
        return services;
    }

    static class Service {
        private int disk;

        private int memory;

        private int users;

        public Service(int disk, int memory, int users) {
            this.disk = disk;
            this.memory = memory;
            this.users = users;
        }

        public int getDisk() {
            return disk;
        }

        public void setDisk(int disk) {
            this.disk = disk;
        }

        public int getMemory() {
            return memory;
        }

        public void setMemory(int memory) {
            this.memory = memory;
        }

        public int getusers() {
            return users;
        }

        public void setusers(int users) {
            this.users = users;
        }
    }
}
