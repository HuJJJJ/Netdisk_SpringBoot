package com.example.netdisk;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import lombok.var;

import java.util.LinkedList;
import java.util.Queue;

public class Demo2 {
    static int CurrentTime = 0;

    public static void main(String[] args) {
        //周转时间=结束时间-到达时间;
        //结束时间 = 开始时间+服务时间
        //队列
        Queue<Job> jobs = new LinkedList<>();
        var job1 = new Job();
        job1.Name = "A";
        job1.ArrivalTime = 0;
        job1.ServiceTime = 4;
        jobs.add(job1);
        var job2 = new Job();
        job2.Name = "B";
        job2.ArrivalTime = 1;
        job2.ServiceTime = 3;
        jobs.add(job2);

        var job3 = new Job();
        job3.Name = "C";
        job3.ArrivalTime = 2;
        job3.ServiceTime = 5;
        jobs.add(job3);

        var job4 = new Job();
        job4.Name = "D";
        job4.ArrivalTime = 3;
        job4.ServiceTime = 2;
        jobs.add(job4);

        var job5 = new Job();
        job5.Name = "E";
        job5.ArrivalTime = 4;
        job5.ServiceTime = 4;
        jobs.add(job5);


        System.out.println("FCSF");
        for (int i = 0; i < 5; i++) {
            //取出作业
            var job = jobs.poll();

            if (CurrentTime == 0) {
                job.StartTime = job.ArrivalTime;
            } else if (CurrentTime > job.ArrivalTime) {
                job.StartTime = CurrentTime;
            } else if (CurrentTime < job.ArrivalTime) {
                job.StartTime = job.ArrivalTime;
            }

            //完成时间
            job.EndTime = job.StartTime + job.ServiceTime;
            //周转时间
            job.CycleTime = job.EndTime - job.ArrivalTime;
            //当前时间
            CurrentTime = job.EndTime;

            System.out.println("当前进程名称:"+job.Name);
            System.out.println("当前进程开始时间:"+job.StartTime);
            System.out.println("当前进程完成时间:"+job.EndTime);
            System.out.println("当前进程周转时间:"+job.CycleTime);
        }
    }
}
