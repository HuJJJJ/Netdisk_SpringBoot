package com.example.netdisk;

import lombok.var;

import java.util.LinkedList;
import java.util.Queue;

public class Demo {

    static int CurrentTime=0;
    public static void main(String[] args) {
        Queue<Job> jobs =new LinkedList();

        var job1 = new Job();
        job1.Name="A";
        job1.ArrivalTime = 0;
        job1.ServiceTime =4;
        jobs.add(job1);
        var job2 = new Job();
        job2.Name="B";
        job2.ArrivalTime = 1;
        job2.ServiceTime =3;
        jobs.add(job2);

        var job3 = new Job();
        job3.Name="C";
        job3.ArrivalTime = 2;
        job3.ServiceTime =5;
        jobs.add(job3);

        var job4 = new Job();
        job4.Name="D";
        job4.ArrivalTime = 3;
        job4.ServiceTime =2;
        jobs.add(job4);

        var job5 = new Job();
        job5.Name="E";
        job5.ArrivalTime = 4;
        job5.ServiceTime =4;
        jobs.add(job5);

        for (int i = 0; i < 5; i++) {
           var job =  jobs.poll();

            //确定作业的开始时间,如果到达时间早于上一个作业结束的时间，
            //则开始时间取上一个作业的结束时间，否则反之

            //第一个作业结束时间为4， 第二个作业到达时间为3
            if (CurrentTime == 0)
            {
                job.StartTime = CurrentTime;
            }
            else if (CurrentTime == job.ArrivalTime)
            {
                job.StartTime = job.ArrivalTime;
            }
            else if (CurrentTime > job.ArrivalTime)
            {
                job.StartTime = CurrentTime;
            }
            else if (CurrentTime < job.ArrivalTime)
            {
                job.StartTime = job.ArrivalTime;
            }

            //确定作业的结束时间
            job.EndTime = job.StartTime + job.ServiceTime;

            //确定作业的周转时间
            job.CycleTime = job.EndTime - job.ArrivalTime;

            CurrentTime=job.EndTime;

            System.out.println("当前进程名称:"+job.Name);
            System.out.println("当前进程开始时间:"+job.StartTime);
            System.out.println("当前进程完成时间:"+job.EndTime);
            System.out.println("当前进程周转时间:"+job.CycleTime);

        }


    }
}
